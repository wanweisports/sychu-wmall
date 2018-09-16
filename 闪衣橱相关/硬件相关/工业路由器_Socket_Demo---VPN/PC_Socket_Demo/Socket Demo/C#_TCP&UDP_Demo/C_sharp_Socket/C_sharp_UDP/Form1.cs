// *************************************
// * C# UDP通讯例程
// * 济南有人物联网技术有限公司提供
//**************************************
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.Net.Sockets;
using System.Threading; 


namespace UDP_Server
{
    public partial class Form1 : Form
    {
        #region -字段-
        private static Socket my_socket;
        private static int LocalPort=8081;
        private static int RemotePort=8081;


        private static IPAddress m_GroupAddress_S; 
        private static IPEndPoint m_RemoteEP;  //对方地址   

       //rivate Thread th;

        private static byte[] receivebuffer;
        private static SocketAsyncEventArgs ReceiveSocketArgs;
        private static SocketAsyncEventArgs SendSocketArgs;
        #endregion

        #region -委托-
        delegate void SetTextCallback(string text);
      
        #endregion


        public Form1()
        {
            InitializeComponent();
            SendButton.Enabled = false;
        }

        /// <summary>
        /// 初始化UPDClient
        /// </summary>
        public void Initialize()
        {

            // 
            // 实例化 socket
            // 
            IPEndPoint m_localEP = new IPEndPoint(IPAddress.Any, LocalPort);
            my_socket = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
            my_socket.Bind(m_localEP);
            // 
            // 创建对方主机的终结点 
            // 
            m_GroupAddress_S = IPAddress.Parse(textBox_ip.Text); //要发送到的计算机IP 
            m_RemoteEP = new IPEndPoint(m_GroupAddress_S, RemotePort);


            receivebuffer = new byte[1024];
            ReceiveSocketArgs = new SocketAsyncEventArgs();
            ReceiveSocketArgs.RemoteEndPoint = m_localEP;
            ReceiveSocketArgs.SetBuffer(receivebuffer, 0, receivebuffer.Length);
            ReceiveSocketArgs.Completed +=new EventHandler<SocketAsyncEventArgs>(ReceiveSocketArgs_Completed);


            SendSocketArgs = new SocketAsyncEventArgs();
            SendSocketArgs.Completed += new EventHandler<SocketAsyncEventArgs>(SendSocketArgs_Completed);
        }



        #region -异步发送函数

        void SendSocketArgs_Completed(object sender, SocketAsyncEventArgs e)
        {
            switch (e.LastOperation)
            {
                case SocketAsyncOperation.SendTo:
                    this.ProcessSent(e);
                    break;
                default:
                    throw new ArgumentException("The last operation completed on the socket was not a send");
            }
        }

       /// <summary>
       /// 发送后的处理
       /// </summary>
       /// <param name="e"></param>
        private void ProcessSent(SocketAsyncEventArgs e)
        {

            if (e.BytesTransferred > 0 && e.SocketError == SocketError.Success)
            {

                String strData = "send message";
                if (this.listBox1.InvokeRequired)
                {
                    SetTextCallback d = new SetTextCallback(SetText);

                    this.Invoke(d, new object[] { strData });
                }
                else
                {
                    listBox1.Items.Add(strData);
                }
            }
           

        }
        #endregion

        #region -异步接收函数-

        /// <summary>
        /// 开始接收数据
        /// </summary>
        public void StartReceive()
        {
            String strData = "receive ";
            if (this.listBox1.InvokeRequired)
            {
                SetTextCallback d = new SetTextCallback(SetText);

                this.Invoke(d, new object[] { strData });
            }
            else
            {
                listBox1.Items.Add(strData);
            }
            try
            {
              
                if (!my_socket.ReceiveFromAsync(ReceiveSocketArgs))
                {
                    processReceived(ReceiveSocketArgs);
                }
            }
            catch
            {

            }
       
        }


        void ReceiveSocketArgs_Completed(object sender, SocketAsyncEventArgs e)
        {
            String strData = "receive Completed";
            if (this.listBox1.InvokeRequired)
            {
                SetTextCallback d = new SetTextCallback(SetText);

                this.Invoke(d, new object[] { strData });
            }
            else
            {
                listBox1.Items.Add(strData);
            }
            switch (e.LastOperation)
            {
                case SocketAsyncOperation.ReceiveFrom:
                    this.processReceived(e);
                    break;
                default:
                    throw new ArgumentException("The last operation completed on the socket was not a receive");
            }
        }

        void processReceived(SocketAsyncEventArgs e)
        {
            if (e.BytesTransferred > 0 && e.SocketError == SocketError.Success)
            {
                Int32 byteTransferred = e.BytesTransferred;
                String strData = Encoding.ASCII.GetString(e.Buffer,0,byteTransferred);
                if (this.listBox1.InvokeRequired)
                {
                    SetTextCallback d = new SetTextCallback(SetText);
                    
                    this.Invoke(d, new object[] { strData});
                }
                else
                {
                    listBox1.Items.Add(strData);
                }
            }

           StartReceive();
        }
        #endregion

        /// <summary>
        /// 监听函数，用来接收消息
        /// </summary>
        public void Listener()
        {
          /*
           //同步处理
            while (true)
            {
                int recv;
                IPEndPoint endpoint = new IPEndPoint(IPAddress.Any,0);
                EndPoint Remote = (EndPoint)(endpoint);
                Byte[] data = new Byte[1024];
                recv = my_socket.ReceiveFrom(data, ref Remote);//接收数据
                String strData = Encoding.ASCII.GetString(data);

                if (this.listBox1.InvokeRequired)
                {
                    SetTextCallback d = new SetTextCallback(SetText);
                    this.Invoke(d, new object[] { strData });
                }
                else
                {
                    listBox1.Items.Add(strData);
                }

            }
           */

            //异步处理
            StartReceive();
        }

        private void SetText(string text)
        {
            listBox1.Items.Add(text);
        }

        #region -事件响应函数-

        private void ListenButton_Click(object sender, EventArgs e)
        {
            Initialize();
            //同步线程法
            //th = new Thread(new ThreadStart(Listener));
            //th.Start();

            Listener();
            this.ListenButton.Enabled = false;
            SendButton.Enabled = true;
        }

        private void SendButton_Click(object sender, EventArgs e)
        {
            Byte[] buffer = null;

            Encoding ASCII = Encoding.ASCII;

            string s = textEdit.Text;

            buffer = new Byte[s.Length + 1];
            // 
            // 将数据发送给远程对方主机 
            // 

            int len = ASCII.GetBytes(s.ToCharArray(), 0, s.Length, buffer, 0);
            //EndPoint Remote = (EndPoint)(m_RemoteEP);

            //同步发送
           // my_socket.SendTo(buffer, len, SocketFlags.None, Remote);
            

            //异步发送
            SendSocketArgs.SetBuffer(buffer, 0, len);
            SendSocketArgs.RemoteEndPoint = m_RemoteEP;
            my_socket.SendToAsync(SendSocketArgs);
        }

        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            try
            {
                /*
                try
                {
                    if (th != null)
                    {
                        if (th.IsAlive)
                        {
                            th.Abort();
                        }

                        th = null;

                    }

                }
                catch
                {
                    try
                    {
                        System.Threading.Thread.ResetAbort();
                    }
                    catch
                    { }
                }
                */
                
                my_socket.Close();
            }
            catch
            {
            } 
   
        }

        private void StopButton_Click(object sender, EventArgs e)
        {
            try
            {
                /*
                try
                {
                    if (th != null)
                    {
                        if (th.IsAlive)
                        {
                            th.Abort();
                        }

                        th = null;

                    }

                }
                catch
                {
                    try
                    {
                        System.Threading.Thread.ResetAbort();
                    }
                    catch
                    { }
                }
                */
                my_socket.Close();
                ListenButton.Enabled = true;
                SendButton.Enabled = false;
            }
            catch
            {
            }
        }

        #endregion

        private void label2_Click(object sender, EventArgs e)
        {

        }

    }


}
