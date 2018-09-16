using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Reader;

namespace UHFSDKTest
{
    public partial class Form1 : Form
    {
        public ReaderMethod reader = new ReaderMethod();
        volatile bool isLoop = false;
        public Form1()
        {
            InitializeComponent();
            reader.m_OnInventoryTag = onInventoryTag;
            reader.m_OnInventoryTagEnd = onInventoryTagEnd;
            reader.m_OnExeCMDStatus = onExeCMDStatus;
            reader.m_RefreshSetting = refreshSetting;
            reader.m_OnOperationTag = onOperationTag;
            reader.m_OnOperationTagEnd = onOperationTagEnd;
            reader.m_OnFastSwitchAntInventoryTagEnd = onFastSwitchAntInventoryTagEnd;
            reader.m_OnGetInventoryBufferTagCount = onGetInventoryBufferTagCount;
            reader.m_OnInventory6BTag = onInventory6BTag;
            reader.m_OnInventory6BTagEnd = onInventory6BTagEnd;
            reader.m_OnRead6BTag = onRead6BTag;
            reader.m_OnWrite6BTag = onWrite6BTag;
            reader.m_OnLock6BTag = onLock6BTag;
            reader.m_OnLockQuery6BTag = onLockQuery6BTag;
     
        }

        private void btnConnectRs232_Click(object sender, EventArgs e)
        {
            //Processing serial port to connect reader.
            string strException = string.Empty;
            string strComPort = cmbComPort.Text;
            int nBaudrate = Convert.ToInt32(cmbBaudrate.Text);

            int nRet = reader.OpenCom(strComPort, nBaudrate, out strException);
            if (nRet != 0)
            {
                string strLog = "Connection failed, failure cause: " + strException;
                Console.WriteLine(strLog);

                return;
            }
            else
            {
                string strLog = "Connect" + strComPort + "@" + nBaudrate.ToString();
                Console.WriteLine(strLog);
            } 
        }

        private void button1_Click(object sender, EventArgs e)
        {
            reader.CloseCom();
            Console.WriteLine("close Serial port!");
        }

       
        void refreshSetting(ReaderSetting readerSetting)
        {
            Console.WriteLine("Version:" + readerSetting.btMajor + "." + readerSetting.btMinor);
        }

         void onExeCMDStatus(byte cmd, byte status)
        {
            if (isLoop && (cmd == CMD.REAL_TIME_INVENTORY))
            {
                reader.InventoryReal((byte)0xFF, (byte)0xFF);
            }
            Console.WriteLine("CMD execute CMD:"+ CMD.format(cmd) + "++Status code:" + ERROR.format(status));
        }

         void onInventoryTag(RXInventoryTag tag)
        {
            Console.WriteLine("Inventory EPC:" + tag.strEPC);
        }

         void onInventoryTagEnd(RXInventoryTagEnd tagEnd)
        {
            if (isLoop)
            {
                reader.InventoryReal((byte)0xFF, (byte)0xFF);
            }
        }

         void onFastSwitchAntInventoryTagEnd(RXFastSwitchAntInventoryTagEnd tagEnd)
        {
            Console.WriteLine("Fast Inventory end:" + tagEnd.mTotalRead);
        }

         void onInventory6BTag(byte nAntID, String strUID)
        {
            Console.WriteLine("Inventory 6B Tag:" + strUID);
        }

         void onInventory6BTagEnd(int nTagCount)
        {
            Console.WriteLine("Inventory 6B Tag:" + nTagCount);
        }

         void onRead6BTag(byte antID, String strData)
        {
            Console.WriteLine("Read 6B Tag:" + strData);
        }

        void onWrite6BTag(byte nAntID, byte nWriteLen)
        {
            Console.WriteLine("Write 6B Tag:" + nWriteLen);
        }

        void onLock6BTag(byte nAntID, byte nStatus)
        {
            Console.WriteLine("Lock 6B Tag:" + nStatus);
        }

        void onLockQuery6BTag(byte nAntID, byte nStatus)
        {
            Console.WriteLine("Lock query 6B Tag:" + nStatus);
        }

        void onGetInventoryBufferTagCount(int nTagCount)
        {
            Console.WriteLine("Get Inventory Buffer Tag Count" + nTagCount);
        }

        void onOperationTag(RXOperationTag tag)
        {
            Console.WriteLine("Operation Tag" + tag.strData);
        }

        void onOperationTagEnd(int operationTagCount)
        {
            Console.WriteLine("Operation Tag End" + operationTagCount);
        }





        private void button2_Click(object sender, EventArgs e)
        {
            reader.InventoryReal((byte)0xFF, (byte)0xFF);
            isLoop = true;
            btInventoryRealStop.Enabled = true;
            btInventoryRealStart.Enabled = false;
        }

        private void button4_Click(object sender, EventArgs e)
        {
            reader.ReadTag((byte)0xFF,(byte)0x02,(byte)0x00,(byte)0x06,new byte[]{(byte)0x00,(byte)0x00,(byte)0x00});
        }

        private void button6_Click(object sender, EventArgs e)
        {
            reader.Inventory((byte)0xFF, (byte)0x01);
        }

        private void button5_Click(object sender, EventArgs e)
        {
            reader.GetInventoryBufferTagCount((byte)0xFF);
        }

        private void button8_Click(object sender, EventArgs e)
        {
            reader.GetFirmwareVersion((byte)0xFF);
        }

        private void button9_Click(object sender, EventArgs e)
        {
            reader.InventoryISO18000((byte)0xFF);
        }

        private void button11_Click(object sender, EventArgs e)
        {
            reader.ReadTagISO18000((byte)0xff, new byte[]{(byte)0xE0,(byte)0x04,(byte)0x00,(byte)0x00,(byte)0xF6,(byte)0x78,(byte)0x41,(byte)0x06},(byte)0x00,(byte)0x06);
        }

        private void button13_Click(object sender, EventArgs e)
        {
            reader.GetInventoryBuffer((byte)0xff);
        }

        private void button7_Click(object sender, EventArgs e)
        {
            reader.Reset((byte)0xFF);
        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            btInventoryRealStart.Enabled = true;
            btInventoryRealStop.Enabled = false;
            isLoop = false;
        }
    }
}
