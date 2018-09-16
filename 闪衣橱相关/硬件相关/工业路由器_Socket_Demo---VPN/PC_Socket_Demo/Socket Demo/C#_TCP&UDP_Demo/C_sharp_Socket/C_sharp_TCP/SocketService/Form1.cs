/*************************
 *    Socket ip  服务器端
 *    济南有人物联网技术有限公司
 *    感谢：山东省农业机械研究院提供
 * ***********************/
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.Net.Sockets;
using System.Threading;

namespace SocketService
{
    public partial class Form1 : Form
    {
         
        
        private IPAddress serverIP = IPAddress.Parse("127.0.0.1");//以本机作测试



        private IPEndPoint serverFullAddr;//完整终端地址
        private Socket sock;
        Thread myThead = null;
        public Form1()
        {
            InitializeComponent();
        }
        //启动
        private void btnConn_Click(object sender, EventArgs e)
        {
            myThead = new Thread(new ThreadStart(BeginListen));
            myThead.Start();
            btnStart.Enabled = false;
            btnstop.Enabled = true;

        }
        private void BeginListen()
        {
            serverIP = IPAddress.Parse(tbxIP.Text);   //IP
            serverFullAddr = new IPEndPoint(serverIP, int.Parse(tbxPort.Text));//设置IP，端口
            sock = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);

            //指定本地主机地址和端口号
            sock.Bind(serverFullAddr);
            lbxMessage.Invoke(new SetTextCallback(SetText), "启动成功 时间:" + DateTime.Now, 1);
            byte[] message = new byte[1024];
            string mess = "";
            while (true)
            {
                try
                {
                    sock.Listen(5);//设置监听频率
                    Socket newSocket = sock.Accept();//阻塞方式
                    int bytes = newSocket.Receive(message);//接收数据
                    mess = Encoding.Default.GetString(message, 0, bytes);//对接收字节编码（S与C 两端编码格式必须一致不然中文乱码）（当接收的字节大于1024的时候 这应该是循环接收，测试就没有那样写了）
                    //do
                    //{
                    //    bytes = newSocket.Receive(message, message.Length, 0);
                    //    mess = mess + Encoding.ASCII.GetString(message, 0, bytes);
                    //}
                    //while (bytes > 0);

                    lbxMessage.Invoke(new SetTextCallback(SetText), mess, 1);//子线程操作主线程UI控件


                    //获取客户端的IP和端口
                    string ip11 = newSocket.RemoteEndPoint.AddressFamily.ToString();
                    
                    mess = "已接收数据： "+ mess +" 来自：" +ip11+ " 当前时间为：" + DateTime.Now; //处理数据
                    newSocket.Send(Encoding.Default.GetBytes(mess));//向客户端发送数据

                }
                catch (SocketException se)
                {
                    lbxMessage.Invoke(new SetTextCallback(SetText), mess + se, 1);

                }
            }
        }


        #region//声名委托
        delegate void SetTextCallback(string text, int num);
        private void SetText(string text, int num)
        {
            lbxMessage.Items.Add(text);
        }
        #endregion

        private void Form1_Load(object sender, EventArgs e)
        {
            btnStart.Enabled = true;
            btnstop.Enabled = false;
        }
        //停止
        private void btnstop_Click(object sender, EventArgs e)
        {
            try
            {
                sock.Close();
                myThead.Abort();
                btnStart.Enabled = true;
                btnstop.Enabled = false;
                lbxMessage.Items.Add("停止成功 时间:" + DateTime.Now);
            }
            catch (Exception ee)
            {
                lbxMessage.Text = "停止失败。。" + ee;
            }
        }


        
        private void btnSend_Click(object sender, EventArgs e)
        {
             


        }



        private void Sending(IAsyncResult rec_socket)
        {
            Socket socket = (Socket)rec_socket.AsyncState;
            try
            {
                if (socket.Connected)
                {
                    byte[] msgBuff = Encoding.UTF8.GetBytes(textBox1.Text);
                    socket.Send(msgBuff);
                }
                else
                {
                    Console.WriteLine("Error!", "Error!");
                }
            }
            catch
            {
                Console.WriteLine("Error!", "Error!");
            }
        }

        private void toolStripStatusLabel1_Click(object sender, EventArgs e)
        {

        }
         

 

    }
}
