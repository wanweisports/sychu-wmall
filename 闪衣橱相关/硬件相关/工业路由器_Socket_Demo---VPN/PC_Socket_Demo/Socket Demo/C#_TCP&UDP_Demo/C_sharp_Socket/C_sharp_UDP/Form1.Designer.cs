namespace UDP_Server
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
            this.ListenButton = new System.Windows.Forms.Button();
            this.StopButton = new System.Windows.Forms.Button();
            this.SendButton = new System.Windows.Forms.Button();
            this.listBox1 = new System.Windows.Forms.ListBox();
            this.textEdit = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.textBox_ip = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // ListenButton
            // 
            this.ListenButton.Location = new System.Drawing.Point(38, 209);
            this.ListenButton.Name = "ListenButton";
            this.ListenButton.Size = new System.Drawing.Size(75, 23);
            this.ListenButton.TabIndex = 6;
            this.ListenButton.Text = "监听";
            this.ListenButton.UseVisualStyleBackColor = true;
            this.ListenButton.Click += new System.EventHandler(this.ListenButton_Click);
            // 
            // StopButton
            // 
            this.StopButton.Location = new System.Drawing.Point(165, 209);
            this.StopButton.Name = "StopButton";
            this.StopButton.Size = new System.Drawing.Size(75, 23);
            this.StopButton.TabIndex = 7;
            this.StopButton.Text = "关闭监听";
            this.StopButton.UseVisualStyleBackColor = true;
            this.StopButton.Click += new System.EventHandler(this.StopButton_Click);
            // 
            // SendButton
            // 
            this.SendButton.Location = new System.Drawing.Point(93, 125);
            this.SendButton.Name = "SendButton";
            this.SendButton.Size = new System.Drawing.Size(75, 35);
            this.SendButton.TabIndex = 8;
            this.SendButton.Text = "发送";
            this.SendButton.UseVisualStyleBackColor = true;
            this.SendButton.Click += new System.EventHandler(this.SendButton_Click);
            // 
            // listBox1
            // 
            this.listBox1.FormattingEnabled = true;
            this.listBox1.ItemHeight = 12;
            this.listBox1.Location = new System.Drawing.Point(299, 31);
            this.listBox1.Name = "listBox1";
            this.listBox1.Size = new System.Drawing.Size(252, 196);
            this.listBox1.TabIndex = 9;
            // 
            // textEdit
            // 
            this.textEdit.Location = new System.Drawing.Point(38, 97);
            this.textEdit.Name = "textEdit";
            this.textEdit.Size = new System.Drawing.Size(202, 21);
            this.textEdit.TabIndex = 10;
            this.textEdit.Text = "123";
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(45, 186);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(173, 20);
            this.label1.TabIndex = 11;
            this.label1.Text = "端口8081，可以程序里自定义";
            // 
            // label2
            // 
            this.label2.Location = new System.Drawing.Point(36, 78);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(173, 16);
            this.label2.TabIndex = 12;
            this.label2.Text = "要发送的内容：";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // label3
            // 
            this.label3.Location = new System.Drawing.Point(36, 35);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(204, 16);
            this.label3.TabIndex = 14;
            this.label3.Text = "发送的目标IP：(发送的端口8081)";
            // 
            // textBox_ip
            // 
            this.textBox_ip.Location = new System.Drawing.Point(38, 54);
            this.textBox_ip.Name = "textBox_ip";
            this.textBox_ip.Size = new System.Drawing.Size(202, 21);
            this.textBox_ip.TabIndex = 13;
            this.textBox_ip.Text = "192.168.0.59";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(589, 274);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.textBox_ip);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.textEdit);
            this.Controls.Add(this.listBox1);
            this.Controls.Add(this.SendButton);
            this.Controls.Add(this.StopButton);
            this.Controls.Add(this.ListenButton);
            this.Name = "Form1";
            this.Text = "Form1";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Form1_FormClosed);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button ListenButton;
        private System.Windows.Forms.Button StopButton;
        private System.Windows.Forms.Button SendButton;
        private System.Windows.Forms.ListBox listBox1;
        private System.Windows.Forms.TextBox textEdit;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox textBox_ip;
    }
}

