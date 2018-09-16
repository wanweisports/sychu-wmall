/***************************
 *    Socket ip  客户端  *
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
using System.Net.Sockets;
using System.Threading;
using System.Net;

namespace SocketClient
{
    public partial class Form1 : Form
    {
        private IPAddress serverIP = IPAddress.Parse("127.0.0.1");//以本机作测试
     

        private IPEndPoint serverFullAddr;//完整终端地址

        private  Socket sock;
        public Form1()
        {
            InitializeComponent();
            btnClose.Enabled = false;
        }
        //连接服务器端
        private void btnConn_Click(object sender, EventArgs e)
        {
            serverIP = IPAddress.Parse(tbxIP.Text);
            try
            {
                serverFullAddr = new IPEndPoint(serverIP, int.Parse(tbxPort.Text));//设置IP，端口
                sock = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                //指定本地主机地址和端口号
                sock.Connect(serverFullAddr);
                btnConn.Enabled = false;
                lblError.Text = "连接服务器成功。。。。";
                btnClose.Enabled = true;
                sock.Close();
               
            }
            catch (Exception ee)
            {
                lblError.Text = "连接服务器失败。。。请仔细检查服务器是否开启"+ ee;
            }
            


        }
        //断开连接
        private void btnClose_Click(object sender, EventArgs e)
        {
            sock.Close();
            btnConn.Enabled = true ;
        }
        //发送消息
        private void btnSend_Click(object sender, EventArgs e)
        {
            serverFullAddr = new IPEndPoint(serverIP, int.Parse(tbxPort.Text));//设置IP，端口
            sock = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            //指定本地主机地址和端口号
            sock.Connect(serverFullAddr);
            byte[] byteSend = System.Text.Encoding.Default.GetBytes(this.tbxMessage.Text);
            byte[] message = new byte[1024];
            string mess = "";
            int bytes = 0;
            try
            {
                //发送数据
                sock.Send(byteSend);
               
                bytes = sock.Receive(message);//接收数据
                mess = mess + Encoding.Default.GetString(message,0,bytes);//编码（当接收的字节大于1024的时候 这应该是循环接收，测试就没有那样写了）
                //do
                //{
                //    bytes = newSocket.Receive(message, message.Length, 0);
                //    mess = mess + Encoding.ASCII.GetString(message, 0, bytes);
                //}
                // while (bytes > 0);
                tbxMessage.Text = mess; 
            }
            catch (Exception ex)
            {
                lblError.Text = "出现错误，请联系管理员"+ex;
            }
            sock.Close();
        }
   
        //清空消息
        private void btnClean_Click(object sender, EventArgs e)
        {
            tbxMessage.Text = "";
        }
       
        private void Form1_Load(object sender, EventArgs e)
        {

        }


    }
}
