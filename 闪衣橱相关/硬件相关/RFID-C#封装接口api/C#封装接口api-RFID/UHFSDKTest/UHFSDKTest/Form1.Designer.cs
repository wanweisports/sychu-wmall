namespace UHFSDKTest
{
    partial class Form1
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.btnConnectRs232 = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.btInventoryRealStart = new System.Windows.Forms.Button();
            this.button4 = new System.Windows.Forms.Button();
            this.button5 = new System.Windows.Forms.Button();
            this.button6 = new System.Windows.Forms.Button();
            this.button8 = new System.Windows.Forms.Button();
            this.button9 = new System.Windows.Forms.Button();
            this.button11 = new System.Windows.Forms.Button();
            this.cmbComPort = new System.Windows.Forms.ComboBox();
            this.cmbBaudrate = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.button13 = new System.Windows.Forms.Button();
            this.button7 = new System.Windows.Forms.Button();
            this.btInventoryRealStop = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // btnConnectRs232
            // 
            this.btnConnectRs232.Font = new System.Drawing.Font("Consolas", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnConnectRs232.Location = new System.Drawing.Point(315, 409);
            this.btnConnectRs232.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.btnConnectRs232.Name = "btnConnectRs232";
            this.btnConnectRs232.Size = new System.Drawing.Size(120, 29);
            this.btnConnectRs232.TabIndex = 3;
            this.btnConnectRs232.Text = "Connect";
            this.btnConnectRs232.UseVisualStyleBackColor = true;
            this.btnConnectRs232.Click += new System.EventHandler(this.btnConnectRs232_Click);
            // 
            // button1
            // 
            this.button1.Font = new System.Drawing.Font("Consolas", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button1.Location = new System.Drawing.Point(505, 409);
            this.button1.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(120, 29);
            this.button1.TabIndex = 4;
            this.button1.Text = "Disconnect";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // btInventoryRealStart
            // 
            this.btInventoryRealStart.Font = new System.Drawing.Font("Consolas", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btInventoryRealStart.Location = new System.Drawing.Point(16, 15);
            this.btInventoryRealStart.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.btInventoryRealStart.Name = "btInventoryRealStart";
            this.btInventoryRealStart.Size = new System.Drawing.Size(215, 29);
            this.btInventoryRealStart.TabIndex = 5;
            this.btInventoryRealStart.Text = "Test Inventory start";
            this.btInventoryRealStart.UseVisualStyleBackColor = true;
            this.btInventoryRealStart.Click += new System.EventHandler(this.button2_Click);
            // 
            // button4
            // 
            this.button4.Font = new System.Drawing.Font("Consolas", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button4.Location = new System.Drawing.Point(483, 15);
            this.button4.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.button4.Name = "button4";
            this.button4.Size = new System.Drawing.Size(176, 29);
            this.button4.TabIndex = 7;
            this.button4.Text = "Test Read Tag";
            this.button4.UseVisualStyleBackColor = true;
            this.button4.Click += new System.EventHandler(this.button4_Click);
            // 
            // button5
            // 
            this.button5.Font = new System.Drawing.Font("Consolas", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button5.Location = new System.Drawing.Point(335, 142);
            this.button5.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.button5.Name = "button5";
            this.button5.Size = new System.Drawing.Size(324, 29);
            this.button5.TabIndex = 8;
            this.button5.Text = "Test get buffer tag count";
            this.button5.UseVisualStyleBackColor = true;
            this.button5.Click += new System.EventHandler(this.button5_Click);
            // 
            // button6
            // 
            this.button6.Font = new System.Drawing.Font("Consolas", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button6.Location = new System.Drawing.Point(23, 89);
            this.button6.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.button6.Name = "button6";
            this.button6.Size = new System.Drawing.Size(235, 29);
            this.button6.TabIndex = 9;
            this.button6.Text = "Test Buffer inventory";
            this.button6.UseVisualStyleBackColor = true;
            this.button6.Click += new System.EventHandler(this.button6_Click);
            // 
            // button8
            // 
            this.button8.Font = new System.Drawing.Font("Consolas", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button8.Location = new System.Drawing.Point(23, 142);
            this.button8.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.button8.Name = "button8";
            this.button8.Size = new System.Drawing.Size(231, 29);
            this.button8.TabIndex = 11;
            this.button8.Text = "Test Reader Version";
            this.button8.UseVisualStyleBackColor = true;
            this.button8.Click += new System.EventHandler(this.button8_Click);
            // 
            // button9
            // 
            this.button9.Font = new System.Drawing.Font("Consolas", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button9.Location = new System.Drawing.Point(355, 232);
            this.button9.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.button9.Name = "button9";
            this.button9.Size = new System.Drawing.Size(271, 29);
            this.button9.TabIndex = 12;
            this.button9.Text = "Test 6B inventory";
            this.button9.UseVisualStyleBackColor = true;
            this.button9.Click += new System.EventHandler(this.button9_Click);
            // 
            // button11
            // 
            this.button11.Font = new System.Drawing.Font("Consolas", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button11.Location = new System.Drawing.Point(53, 232);
            this.button11.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.button11.Name = "button11";
            this.button11.Size = new System.Drawing.Size(193, 29);
            this.button11.TabIndex = 14;
            this.button11.Text = "Test 6B read";
            this.button11.UseVisualStyleBackColor = true;
            this.button11.Click += new System.EventHandler(this.button11_Click);
            // 
            // cmbComPort
            // 
            this.cmbComPort.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbComPort.FormattingEnabled = true;
            this.cmbComPort.Items.AddRange(new object[] {
            "COM1",
            "COM2",
            "COM3",
            "COM4",
            "COM5",
            "COM6",
            "COM7",
            "COM8",
            "COM9",
            "COM10",
            "COM11",
            "COM12",
            "COM13",
            "COM14",
            "COM15",
            "COM16"});
            this.cmbComPort.Location = new System.Drawing.Point(127, 339);
            this.cmbComPort.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.cmbComPort.Name = "cmbComPort";
            this.cmbComPort.Size = new System.Drawing.Size(160, 23);
            this.cmbComPort.TabIndex = 20;
            this.cmbComPort.SelectedIndexChanged += new System.EventHandler(this.cmbComPort_SelectedIndexChanged);
            // 
            // cmbBaudrate
            // 
            this.cmbBaudrate.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmbBaudrate.FormattingEnabled = true;
            this.cmbBaudrate.Items.AddRange(new object[] {
            "38400",
            "115200",
            "921600"});
            this.cmbBaudrate.Location = new System.Drawing.Point(127, 409);
            this.cmbBaudrate.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.cmbBaudrate.Name = "cmbBaudrate";
            this.cmbBaudrate.Size = new System.Drawing.Size(160, 23);
            this.cmbBaudrate.TabIndex = 21;
            this.cmbBaudrate.SelectedIndexChanged += new System.EventHandler(this.cmbBaudrate_SelectedIndexChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(16, 342);
            this.label1.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(103, 15);
            this.label1.TabIndex = 22;
            this.label1.Text = "Serial Port:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(16, 412);
            this.label2.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(79, 15);
            this.label2.TabIndex = 23;
            this.label2.Text = "Baudrate:";
            // 
            // button13
            // 
            this.button13.Font = new System.Drawing.Font("Consolas", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button13.Location = new System.Drawing.Point(315, 89);
            this.button13.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.button13.Name = "button13";
            this.button13.Size = new System.Drawing.Size(367, 29);
            this.button13.TabIndex = 24;
            this.button13.Text = "Test get Buffer inventory tag";
            this.button13.UseVisualStyleBackColor = true;
            this.button13.Click += new System.EventHandler(this.button13_Click);
            // 
            // button7
            // 
            this.button7.Font = new System.Drawing.Font("Consolas", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.button7.Location = new System.Drawing.Point(364, 329);
            this.button7.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.button7.Name = "button7";
            this.button7.Size = new System.Drawing.Size(236, 29);
            this.button7.TabIndex = 25;
            this.button7.Text = "Test Reset Reader";
            this.button7.UseVisualStyleBackColor = true;
            this.button7.Click += new System.EventHandler(this.button7_Click);
            // 
            // btInventoryRealStop
            // 
            this.btInventoryRealStop.Enabled = false;
            this.btInventoryRealStop.Font = new System.Drawing.Font("Consolas", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btInventoryRealStop.Location = new System.Drawing.Point(239, 15);
            this.btInventoryRealStop.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.btInventoryRealStop.Name = "btInventoryRealStop";
            this.btInventoryRealStop.Size = new System.Drawing.Size(236, 29);
            this.btInventoryRealStop.TabIndex = 26;
            this.btInventoryRealStop.Text = "Test Inventory stop";
            this.btInventoryRealStop.UseVisualStyleBackColor = true;
            this.btInventoryRealStop.Click += new System.EventHandler(this.button2_Click_1);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(697, 476);
            this.Controls.Add(this.btInventoryRealStop);
            this.Controls.Add(this.button7);
            this.Controls.Add(this.button13);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.cmbBaudrate);
            this.Controls.Add(this.cmbComPort);
            this.Controls.Add(this.button11);
            this.Controls.Add(this.button9);
            this.Controls.Add(this.button8);
            this.Controls.Add(this.button6);
            this.Controls.Add(this.button5);
            this.Controls.Add(this.button4);
            this.Controls.Add(this.btInventoryRealStart);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.btnConnectRs232);
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnConnectRs232;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button btInventoryRealStart;
        private System.Windows.Forms.Button button4;
        private System.Windows.Forms.Button button5;
        private System.Windows.Forms.Button button6;
        private System.Windows.Forms.Button button8;
        private System.Windows.Forms.Button button9;
        private System.Windows.Forms.Button button11;
        private System.Windows.Forms.ComboBox cmbComPort;
        private System.Windows.Forms.ComboBox cmbBaudrate;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button button13;
        private System.Windows.Forms.Button button7;
        private System.Windows.Forms.Button btInventoryRealStop;
    }
}

