#pragma once

namespace RelayControl {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;
	using namespace System::Net;
	using namespace System::Net::Sockets;
	using namespace System::Text;

	/// <summary>
	/// Form1 摘要
	/// </summary>
	public ref class Form1 : public System::Windows::Forms::Form
	{
	public:
		Form1(void)
		{
			InitializeComponent();
			//
			//TODO: 在此处添加构造函数代码
			//
		}

	protected:
		/// <summary>
		/// 清理所有正在使用的资源。
		/// </summary>
		~Form1()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::Button^  button1;
	protected: 
	private: System::Windows::Forms::Button^  button2;
	private: System::Windows::Forms::Button^  button3;
	private: System::Windows::Forms::Button^  button4;
	private: System::Windows::Forms::Button^  button5;
	private: System::Windows::Forms::Button^  button6;
	private: System::Windows::Forms::Button^  button7;
	private: System::Windows::Forms::Button^  button8;
	private: System::Windows::Forms::GroupBox^  groupBox2;
	private: System::Windows::Forms::GroupBox^  groupBoxSet;
	private: System::Windows::Forms::Label^  label2;
	private: System::Windows::Forms::Label^  label1;
	private: System::Windows::Forms::Label^  label8;
	private: System::Windows::Forms::Label^  label7;
	private: System::Windows::Forms::Label^  label6;
	private: System::Windows::Forms::Label^  label5;
	private: System::Windows::Forms::Label^  label4;
	private: System::Windows::Forms::Label^  label3;
	private: System::Windows::Forms::GroupBox^  groupBox1;
	private: System::Windows::Forms::Button^  buttonConnect;
	private: System::Windows::Forms::Timer^  timer1;
	private: System::Windows::Forms::Label^  label10;
	private: System::Windows::Forms::Label^  label9;

	private: System::Windows::Forms::TextBox^  textBoxIp;
	private: System::Windows::Forms::NumericUpDown^  numericUpDown1;
	private: System::Windows::Forms::PictureBox^  pictureBox1;
	private: System::Windows::Forms::PictureBox^  pictureBox2;
	private: System::Windows::Forms::Label^  label11;
	private: System::Windows::Forms::PictureBox^  pictureBox3;
	private: System::Windows::Forms::PictureBox^  pictureBox10;
	private: System::Windows::Forms::PictureBox^  pictureBox9;
	private: System::Windows::Forms::PictureBox^  pictureBox8;
	private: System::Windows::Forms::PictureBox^  pictureBox7;
	private: System::Windows::Forms::PictureBox^  pictureBox6;
	private: System::Windows::Forms::PictureBox^  pictureBox5;
	private: System::Windows::Forms::PictureBox^  pictureBox4;
	private: System::Windows::Forms::Label^  label12;
	private: System::Windows::Forms::PictureBox^  pictureBox11;
	private: System::Windows::Forms::PictureBox^  pictureBox12;
	private: System::ComponentModel::IContainer^  components;


	protected: 


	protected: 



	private:
		/// <summary>
		/// 必需的设计器变量。
		/// </summary>


#pragma region Windows Form Designer generated code
		/// <summary>
		/// 设计器支持所需的方法 - 不要
		/// 使用代码编辑器修改此方法的内容。
		/// </summary>
		void InitializeComponent(void)
		{
			this->components = (gcnew System::ComponentModel::Container());
			System::ComponentModel::ComponentResourceManager^  resources = (gcnew System::ComponentModel::ComponentResourceManager(Form1::typeid));
			this->button1 = (gcnew System::Windows::Forms::Button());
			this->button2 = (gcnew System::Windows::Forms::Button());
			this->button3 = (gcnew System::Windows::Forms::Button());
			this->button4 = (gcnew System::Windows::Forms::Button());
			this->button5 = (gcnew System::Windows::Forms::Button());
			this->button6 = (gcnew System::Windows::Forms::Button());
			this->button7 = (gcnew System::Windows::Forms::Button());
			this->button8 = (gcnew System::Windows::Forms::Button());
			this->groupBox2 = (gcnew System::Windows::Forms::GroupBox());
			this->pictureBox2 = (gcnew System::Windows::Forms::PictureBox());
			this->label8 = (gcnew System::Windows::Forms::Label());
			this->pictureBox1 = (gcnew System::Windows::Forms::PictureBox());
			this->label7 = (gcnew System::Windows::Forms::Label());
			this->label6 = (gcnew System::Windows::Forms::Label());
			this->label5 = (gcnew System::Windows::Forms::Label());
			this->label4 = (gcnew System::Windows::Forms::Label());
			this->label3 = (gcnew System::Windows::Forms::Label());
			this->label2 = (gcnew System::Windows::Forms::Label());
			this->label1 = (gcnew System::Windows::Forms::Label());
			this->groupBoxSet = (gcnew System::Windows::Forms::GroupBox());
			this->numericUpDown1 = (gcnew System::Windows::Forms::NumericUpDown());
			this->label10 = (gcnew System::Windows::Forms::Label());
			this->label9 = (gcnew System::Windows::Forms::Label());
			this->buttonConnect = (gcnew System::Windows::Forms::Button());
			this->textBoxIp = (gcnew System::Windows::Forms::TextBox());
			this->groupBox1 = (gcnew System::Windows::Forms::GroupBox());
			this->pictureBox10 = (gcnew System::Windows::Forms::PictureBox());
			this->pictureBox9 = (gcnew System::Windows::Forms::PictureBox());
			this->pictureBox8 = (gcnew System::Windows::Forms::PictureBox());
			this->pictureBox7 = (gcnew System::Windows::Forms::PictureBox());
			this->pictureBox6 = (gcnew System::Windows::Forms::PictureBox());
			this->pictureBox5 = (gcnew System::Windows::Forms::PictureBox());
			this->pictureBox4 = (gcnew System::Windows::Forms::PictureBox());
			this->label12 = (gcnew System::Windows::Forms::Label());
			this->pictureBox3 = (gcnew System::Windows::Forms::PictureBox());
			this->timer1 = (gcnew System::Windows::Forms::Timer(this->components));
			this->label11 = (gcnew System::Windows::Forms::Label());
			this->pictureBox11 = (gcnew System::Windows::Forms::PictureBox());
			this->pictureBox12 = (gcnew System::Windows::Forms::PictureBox());
			this->groupBox2->SuspendLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox2))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox1))->BeginInit();
			this->groupBoxSet->SuspendLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->numericUpDown1))->BeginInit();
			this->groupBox1->SuspendLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox10))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox9))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox8))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox7))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox6))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox5))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox4))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox3))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox11))->BeginInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox12))->BeginInit();
			this->SuspendLayout();
			// 
			// button1
			// 
			this->button1->BackColor = System::Drawing::SystemColors::GradientActiveCaption;
			this->button1->Font = (gcnew System::Drawing::Font(L"楷体", 14.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(134)));
			this->button1->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"button1.Image")));
			this->button1->Location = System::Drawing::Point(50, 55);
			this->button1->Name = L"button1";
			this->button1->Size = System::Drawing::Size(110, 110);
			this->button1->TabIndex = 0;
			this->button1->TextAlign = System::Drawing::ContentAlignment::BottomCenter;
			this->button1->UseVisualStyleBackColor = false;
			this->button1->Click += gcnew System::EventHandler(this, &Form1::button1_Click);
			// 
			// button2
			// 
			this->button2->BackColor = System::Drawing::SystemColors::GradientActiveCaption;
			this->button2->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"button2.Image")));
			this->button2->Location = System::Drawing::Point(237, 55);
			this->button2->Name = L"button2";
			this->button2->Size = System::Drawing::Size(110, 110);
			this->button2->TabIndex = 1;
			this->button2->UseVisualStyleBackColor = false;
			this->button2->Click += gcnew System::EventHandler(this, &Form1::button2_Click);
			// 
			// button3
			// 
			this->button3->BackColor = System::Drawing::SystemColors::GradientActiveCaption;
			this->button3->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"button3.Image")));
			this->button3->Location = System::Drawing::Point(424, 55);
			this->button3->Name = L"button3";
			this->button3->Size = System::Drawing::Size(110, 110);
			this->button3->TabIndex = 2;
			this->button3->UseVisualStyleBackColor = false;
			this->button3->Click += gcnew System::EventHandler(this, &Form1::button3_Click);
			// 
			// button4
			// 
			this->button4->BackColor = System::Drawing::SystemColors::GradientActiveCaption;
			this->button4->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"button4.Image")));
			this->button4->Location = System::Drawing::Point(611, 55);
			this->button4->Name = L"button4";
			this->button4->Size = System::Drawing::Size(110, 110);
			this->button4->TabIndex = 3;
			this->button4->UseVisualStyleBackColor = false;
			this->button4->Click += gcnew System::EventHandler(this, &Form1::button4_Click);
			// 
			// button5
			// 
			this->button5->BackColor = System::Drawing::SystemColors::GradientActiveCaption;
			this->button5->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"button5.Image")));
			this->button5->Location = System::Drawing::Point(50, 241);
			this->button5->Name = L"button5";
			this->button5->Size = System::Drawing::Size(110, 110);
			this->button5->TabIndex = 4;
			this->button5->UseVisualStyleBackColor = false;
			this->button5->Click += gcnew System::EventHandler(this, &Form1::button5_Click);
			// 
			// button6
			// 
			this->button6->BackColor = System::Drawing::SystemColors::GradientActiveCaption;
			this->button6->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"button6.Image")));
			this->button6->Location = System::Drawing::Point(237, 241);
			this->button6->Name = L"button6";
			this->button6->Size = System::Drawing::Size(110, 110);
			this->button6->TabIndex = 5;
			this->button6->UseVisualStyleBackColor = false;
			this->button6->Click += gcnew System::EventHandler(this, &Form1::button6_Click);
			// 
			// button7
			// 
			this->button7->BackColor = System::Drawing::SystemColors::GradientActiveCaption;
			this->button7->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"button7.Image")));
			this->button7->Location = System::Drawing::Point(424, 241);
			this->button7->Name = L"button7";
			this->button7->Size = System::Drawing::Size(110, 110);
			this->button7->TabIndex = 6;
			this->button7->UseVisualStyleBackColor = false;
			this->button7->Click += gcnew System::EventHandler(this, &Form1::button7_Click);
			// 
			// button8
			// 
			this->button8->BackColor = System::Drawing::SystemColors::GradientActiveCaption;
			this->button8->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"button8.Image")));
			this->button8->Location = System::Drawing::Point(611, 241);
			this->button8->Name = L"button8";
			this->button8->Size = System::Drawing::Size(110, 110);
			this->button8->TabIndex = 7;
			this->button8->UseVisualStyleBackColor = false;
			this->button8->Click += gcnew System::EventHandler(this, &Form1::button8_Click);
			// 
			// groupBox2
			// 
			this->groupBox2->Controls->Add(this->pictureBox2);
			this->groupBox2->Controls->Add(this->label8);
			this->groupBox2->Controls->Add(this->pictureBox1);
			this->groupBox2->Controls->Add(this->label7);
			this->groupBox2->Controls->Add(this->label6);
			this->groupBox2->Controls->Add(this->label5);
			this->groupBox2->Controls->Add(this->label4);
			this->groupBox2->Controls->Add(this->label3);
			this->groupBox2->Controls->Add(this->label2);
			this->groupBox2->Controls->Add(this->label1);
			this->groupBox2->Controls->Add(this->button8);
			this->groupBox2->Controls->Add(this->button7);
			this->groupBox2->Controls->Add(this->button6);
			this->groupBox2->Controls->Add(this->button5);
			this->groupBox2->Controls->Add(this->button4);
			this->groupBox2->Controls->Add(this->button3);
			this->groupBox2->Controls->Add(this->button2);
			this->groupBox2->Controls->Add(this->button1);
			this->groupBox2->Location = System::Drawing::Point(12, 134);
			this->groupBox2->Name = L"groupBox2";
			this->groupBox2->Size = System::Drawing::Size(760, 403);
			this->groupBox2->TabIndex = 2;
			this->groupBox2->TabStop = false;
			this->groupBox2->Text = L"输出端口";
			// 
			// pictureBox2
			// 
			this->pictureBox2->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox2.Image")));
			this->pictureBox2->InitialImage = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox2.InitialImage")));
			this->pictureBox2->Location = System::Drawing::Point(278, 22);
			this->pictureBox2->Name = L"pictureBox2";
			this->pictureBox2->Size = System::Drawing::Size(100, 130);
			this->pictureBox2->TabIndex = 4;
			this->pictureBox2->TabStop = false;
			this->pictureBox2->Visible = false;
			// 
			// label8
			// 
			this->label8->AutoSize = true;
			this->label8->Font = (gcnew System::Drawing::Font(L"楷体", 15, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(134)));
			this->label8->Location = System::Drawing::Point(636, 354);
			this->label8->Name = L"label8";
			this->label8->Size = System::Drawing::Size(59, 20);
			this->label8->TabIndex = 15;
			this->label8->Text = L"输出8";
			// 
			// pictureBox1
			// 
			this->pictureBox1->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox1.Image")));
			this->pictureBox1->InitialImage = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox1.InitialImage")));
			this->pictureBox1->Location = System::Drawing::Point(148, 22);
			this->pictureBox1->Name = L"pictureBox1";
			this->pictureBox1->Size = System::Drawing::Size(100, 130);
			this->pictureBox1->TabIndex = 3;
			this->pictureBox1->TabStop = false;
			this->pictureBox1->Visible = false;
			// 
			// label7
			// 
			this->label7->AutoSize = true;
			this->label7->Font = (gcnew System::Drawing::Font(L"楷体", 15, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(134)));
			this->label7->Location = System::Drawing::Point(451, 354);
			this->label7->Name = L"label7";
			this->label7->Size = System::Drawing::Size(59, 20);
			this->label7->TabIndex = 14;
			this->label7->Text = L"输出7";
			// 
			// label6
			// 
			this->label6->AutoSize = true;
			this->label6->Font = (gcnew System::Drawing::Font(L"楷体", 15, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(134)));
			this->label6->Location = System::Drawing::Point(258, 354);
			this->label6->Name = L"label6";
			this->label6->Size = System::Drawing::Size(59, 20);
			this->label6->TabIndex = 13;
			this->label6->Text = L"输出6";
			// 
			// label5
			// 
			this->label5->AutoSize = true;
			this->label5->Font = (gcnew System::Drawing::Font(L"楷体", 15, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(134)));
			this->label5->Location = System::Drawing::Point(73, 354);
			this->label5->Name = L"label5";
			this->label5->Size = System::Drawing::Size(59, 20);
			this->label5->TabIndex = 12;
			this->label5->Text = L"输出5";
			// 
			// label4
			// 
			this->label4->AutoSize = true;
			this->label4->Font = (gcnew System::Drawing::Font(L"楷体", 15, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(134)));
			this->label4->Location = System::Drawing::Point(636, 168);
			this->label4->Name = L"label4";
			this->label4->Size = System::Drawing::Size(59, 20);
			this->label4->TabIndex = 11;
			this->label4->Text = L"输出4";
			// 
			// label3
			// 
			this->label3->AutoSize = true;
			this->label3->Font = (gcnew System::Drawing::Font(L"楷体", 15, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(134)));
			this->label3->Location = System::Drawing::Point(451, 168);
			this->label3->Name = L"label3";
			this->label3->Size = System::Drawing::Size(59, 20);
			this->label3->TabIndex = 10;
			this->label3->Text = L"输出3";
			// 
			// label2
			// 
			this->label2->AutoSize = true;
			this->label2->Font = (gcnew System::Drawing::Font(L"楷体", 15, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(134)));
			this->label2->Location = System::Drawing::Point(258, 168);
			this->label2->Name = L"label2";
			this->label2->Size = System::Drawing::Size(59, 20);
			this->label2->TabIndex = 9;
			this->label2->Text = L"输出2";
			// 
			// label1
			// 
			this->label1->AutoSize = true;
			this->label1->Font = (gcnew System::Drawing::Font(L"楷体", 15, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(134)));
			this->label1->Location = System::Drawing::Point(73, 168);
			this->label1->Name = L"label1";
			this->label1->Size = System::Drawing::Size(59, 20);
			this->label1->TabIndex = 8;
			this->label1->Text = L"输出1";
			// 
			// groupBoxSet
			// 
			this->groupBoxSet->Controls->Add(this->numericUpDown1);
			this->groupBoxSet->Controls->Add(this->label10);
			this->groupBoxSet->Controls->Add(this->label9);
			this->groupBoxSet->Controls->Add(this->buttonConnect);
			this->groupBoxSet->Controls->Add(this->textBoxIp);
			this->groupBoxSet->Location = System::Drawing::Point(12, 52);
			this->groupBoxSet->Name = L"groupBoxSet";
			this->groupBoxSet->Size = System::Drawing::Size(384, 65);
			this->groupBoxSet->TabIndex = 0;
			this->groupBoxSet->TabStop = false;
			this->groupBoxSet->Text = L"网络配置";
			// 
			// numericUpDown1
			// 
			this->numericUpDown1->Location = System::Drawing::Point(218, 30);
			this->numericUpDown1->Maximum = System::Decimal(gcnew cli::array< System::Int32 >(4) {65535, 0, 0, 0});
			this->numericUpDown1->Name = L"numericUpDown1";
			this->numericUpDown1->Size = System::Drawing::Size(61, 23);
			this->numericUpDown1->TabIndex = 3;
			this->numericUpDown1->Value = System::Decimal(gcnew cli::array< System::Int32 >(4) {1234, 0, 0, 0});
			// 
			// label10
			// 
			this->label10->AutoSize = true;
			this->label10->Location = System::Drawing::Point(184, 33);
			this->label10->Name = L"label10";
			this->label10->Size = System::Drawing::Size(35, 14);
			this->label10->TabIndex = 4;
			this->label10->Text = L"端口";
			// 
			// label9
			// 
			this->label9->AutoSize = true;
			this->label9->Location = System::Drawing::Point(9, 33);
			this->label9->Name = L"label9";
			this->label9->Size = System::Drawing::Size(49, 14);
			this->label9->TabIndex = 3;
			this->label9->Text = L"IP地址";
			// 
			// buttonConnect
			// 
			this->buttonConnect->Location = System::Drawing::Point(286, 25);
			this->buttonConnect->Name = L"buttonConnect";
			this->buttonConnect->Size = System::Drawing::Size(92, 31);
			this->buttonConnect->TabIndex = 0;
			this->buttonConnect->Text = L"连接网络";
			this->buttonConnect->UseVisualStyleBackColor = true;
			this->buttonConnect->Click += gcnew System::EventHandler(this, &Form1::buttonConnect_Click);
			// 
			// textBoxIp
			// 
			this->textBoxIp->Location = System::Drawing::Point(64, 30);
			this->textBoxIp->Name = L"textBoxIp";
			this->textBoxIp->Size = System::Drawing::Size(112, 23);
			this->textBoxIp->TabIndex = 1;
			this->textBoxIp->Text = L"192.168.1.166";
			// 
			// groupBox1
			// 
			this->groupBox1->Controls->Add(this->pictureBox10);
			this->groupBox1->Controls->Add(this->pictureBox9);
			this->groupBox1->Controls->Add(this->pictureBox8);
			this->groupBox1->Controls->Add(this->pictureBox7);
			this->groupBox1->Controls->Add(this->pictureBox6);
			this->groupBox1->Controls->Add(this->pictureBox5);
			this->groupBox1->Controls->Add(this->pictureBox4);
			this->groupBox1->Controls->Add(this->label12);
			this->groupBox1->Controls->Add(this->pictureBox3);
			this->groupBox1->Location = System::Drawing::Point(402, 52);
			this->groupBox1->Name = L"groupBox1";
			this->groupBox1->Size = System::Drawing::Size(370, 65);
			this->groupBox1->TabIndex = 1;
			this->groupBox1->TabStop = false;
			this->groupBox1->Text = L"输入状态";
			// 
			// pictureBox10
			// 
			this->pictureBox10->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox10.Image")));
			this->pictureBox10->Location = System::Drawing::Point(330, 20);
			this->pictureBox10->Name = L"pictureBox10";
			this->pictureBox10->Size = System::Drawing::Size(20, 20);
			this->pictureBox10->TabIndex = 10;
			this->pictureBox10->TabStop = false;
			// 
			// pictureBox9
			// 
			this->pictureBox9->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox9.Image")));
			this->pictureBox9->Location = System::Drawing::Point(287, 20);
			this->pictureBox9->Name = L"pictureBox9";
			this->pictureBox9->Size = System::Drawing::Size(20, 20);
			this->pictureBox9->TabIndex = 9;
			this->pictureBox9->TabStop = false;
			// 
			// pictureBox8
			// 
			this->pictureBox8->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox8.Image")));
			this->pictureBox8->Location = System::Drawing::Point(244, 20);
			this->pictureBox8->Name = L"pictureBox8";
			this->pictureBox8->Size = System::Drawing::Size(20, 20);
			this->pictureBox8->TabIndex = 8;
			this->pictureBox8->TabStop = false;
			// 
			// pictureBox7
			// 
			this->pictureBox7->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox7.Image")));
			this->pictureBox7->Location = System::Drawing::Point(201, 20);
			this->pictureBox7->Name = L"pictureBox7";
			this->pictureBox7->Size = System::Drawing::Size(20, 20);
			this->pictureBox7->TabIndex = 7;
			this->pictureBox7->TabStop = false;
			// 
			// pictureBox6
			// 
			this->pictureBox6->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox6.Image")));
			this->pictureBox6->Location = System::Drawing::Point(158, 20);
			this->pictureBox6->Name = L"pictureBox6";
			this->pictureBox6->Size = System::Drawing::Size(20, 20);
			this->pictureBox6->TabIndex = 6;
			this->pictureBox6->TabStop = false;
			// 
			// pictureBox5
			// 
			this->pictureBox5->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox5.Image")));
			this->pictureBox5->Location = System::Drawing::Point(115, 20);
			this->pictureBox5->Name = L"pictureBox5";
			this->pictureBox5->Size = System::Drawing::Size(20, 20);
			this->pictureBox5->TabIndex = 5;
			this->pictureBox5->TabStop = false;
			// 
			// pictureBox4
			// 
			this->pictureBox4->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox4.Image")));
			this->pictureBox4->Location = System::Drawing::Point(72, 20);
			this->pictureBox4->Name = L"pictureBox4";
			this->pictureBox4->Size = System::Drawing::Size(20, 20);
			this->pictureBox4->TabIndex = 4;
			this->pictureBox4->TabStop = false;
			// 
			// label12
			// 
			this->label12->AutoSize = true;
			this->label12->Font = (gcnew System::Drawing::Font(L"宋体", 14.25F, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(134)));
			this->label12->Location = System::Drawing::Point(25, 43);
			this->label12->Name = L"label12";
			this->label12->Size = System::Drawing::Size(339, 19);
			this->label12->TabIndex = 1;
			this->label12->Text = L"1   2   3   4   5   6   7   8 ";
			// 
			// pictureBox3
			// 
			this->pictureBox3->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox3.Image")));
			this->pictureBox3->Location = System::Drawing::Point(29, 20);
			this->pictureBox3->Name = L"pictureBox3";
			this->pictureBox3->Size = System::Drawing::Size(20, 20);
			this->pictureBox3->TabIndex = 0;
			this->pictureBox3->TabStop = false;
			// 
			// timer1
			// 
			this->timer1->Interval = 700;
			this->timer1->Tick += gcnew System::EventHandler(this, &Form1::timer1_Tick);
			// 
			// label11
			// 
			this->label11->AutoSize = true;
			this->label11->Font = (gcnew System::Drawing::Font(L"宋体", 18, System::Drawing::FontStyle::Bold, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(134)));
			this->label11->Location = System::Drawing::Point(20, 9);
			this->label11->Name = L"label11";
			this->label11->Size = System::Drawing::Size(174, 24);
			this->label11->TabIndex = 3;
			this->label11->Text = L"Web网络继电器";
			// 
			// pictureBox11
			// 
			this->pictureBox11->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox11.Image")));
			this->pictureBox11->Location = System::Drawing::Point(573, 26);
			this->pictureBox11->Name = L"pictureBox11";
			this->pictureBox11->Size = System::Drawing::Size(20, 20);
			this->pictureBox11->TabIndex = 11;
			this->pictureBox11->TabStop = false;
			this->pictureBox11->Visible = false;
			// 
			// pictureBox12
			// 
			this->pictureBox12->Image = (cli::safe_cast<System::Drawing::Image^  >(resources->GetObject(L"pictureBox12.Image")));
			this->pictureBox12->Location = System::Drawing::Point(613, 26);
			this->pictureBox12->Name = L"pictureBox12";
			this->pictureBox12->Size = System::Drawing::Size(20, 20);
			this->pictureBox12->TabIndex = 12;
			this->pictureBox12->TabStop = false;
			this->pictureBox12->Visible = false;
			// 
			// Form1
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(7, 14);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(784, 562);
			this->Controls->Add(this->pictureBox12);
			this->Controls->Add(this->pictureBox11);
			this->Controls->Add(this->label11);
			this->Controls->Add(this->groupBox1);
			this->Controls->Add(this->groupBox2);
			this->Controls->Add(this->groupBoxSet);
			this->Font = (gcnew System::Drawing::Font(L"宋体", 10.5F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point, 
				static_cast<System::Byte>(134)));
			this->Icon = (cli::safe_cast<System::Drawing::Icon^  >(resources->GetObject(L"$this.Icon")));
			this->Margin = System::Windows::Forms::Padding(4);
			this->Name = L"Form1";
			this->Text = L"网络控制V1.0-Beta";
			this->groupBox2->ResumeLayout(false);
			this->groupBox2->PerformLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox2))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox1))->EndInit();
			this->groupBoxSet->ResumeLayout(false);
			this->groupBoxSet->PerformLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->numericUpDown1))->EndInit();
			this->groupBox1->ResumeLayout(false);
			this->groupBox1->PerformLayout();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox10))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox9))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox8))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox7))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox6))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox5))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox4))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox3))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox11))->EndInit();
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^  >(this->pictureBox12))->EndInit();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
		public:
			static Int32 ConnectFlag=0;
			TcpClient^tcpClient;
			NetworkStream ^stream;
			static array <int>^RelayState=gcnew array <int>(16);
			int sendBytes;
           static array<unsigned char>^readBuf=gcnew array<unsigned char>(1024);
		   static array<unsigned char>^sendBuf=gcnew array<unsigned char>(1024);
	private: System::Void groupBox1_Enter(System::Object^  sender, System::EventArgs^  e) {
			 }

			 void OnOffSend(int ch,int data)  //ch --0--7  data 0 or 1
			 {
			   if (1==ConnectFlag)
			   {
				   if (0==RelayState[ch])
				   {
					   switch(ch)
					   {
					     case 0:this->button1->Image=pictureBox2->Image;break;
					     case 1:this->button2->Image=pictureBox2->Image;break;
						 case 2:this->button3->Image=pictureBox2->Image;break;
						 case 3:this->button4->Image=pictureBox2->Image;break;
						 case 4:this->button5->Image=pictureBox2->Image;break;
						 case 5:this->button6->Image=pictureBox2->Image;break;
						 case 6:this->button7->Image=pictureBox2->Image;break;
						 case 7:this->button8->Image=pictureBox2->Image;break;
					   }
					   RelayState[ch]=1;
					   sendBuf[0]='D';sendBuf[1]=ch+'1';
					   stream->Write(sendBuf,0,2);
				   }
				   else
				   {
					   switch(ch)
					   {
					   case 0:this->button1->Image=pictureBox1->Image;break;
					   case 1:this->button2->Image=pictureBox1->Image;break;
					   case 2:this->button3->Image=pictureBox1->Image;break;
					   case 3:this->button4->Image=pictureBox1->Image;break;
					   case 4:this->button5->Image=pictureBox1->Image;break;
					   case 5:this->button6->Image=pictureBox1->Image;break;
					   case 6:this->button7->Image=pictureBox1->Image;break;
					   case 7:this->button8->Image=pictureBox1->Image;break;
					   }
					   RelayState[ch]=0;
					   //this->button1->Image=pictureBox1->Image;
					   sendBuf[0]='L';sendBuf[1]=ch+'1';
					   stream->Write(sendBuf,0,2);
				   }
			   }
			   else MessageBox::Show(L"请先连接网络...");//未连接
			 }
		void GetStateBoard(void)
		{
			sendBuf[0]='d';sendBuf[1]='u';sendBuf[2]='m';sendBuf[3]='p';
			stream->Write(sendBuf,0,4);
			sendBytes=stream->Read(readBuf,0,readBuf->Length);
			if(sendBytes<61)
				return;
			int j=0;
			for (int i=0;i<16;i++)
			{
				while(j<200)
				{
					if ((readBuf[j]==0x0D)&&(readBuf[j+1]==0x0A))
					{
					  if(i<8)   //0--7 继电器状态 8--15 输入接口状态
					  {
						if(readBuf[j+8]=='f'){RelayState[i]=1;}  //Off--1
						if(readBuf[j+8]=='n'){ RelayState[i]=0; }                 //On --0 
					  }
					  else
					  {
						  if(readBuf[j+3]=='L'){RelayState[i]=1;}  //Off--1  //没有输入 和 关闭时 是 1  否则是 0
						  if(readBuf[j+3]=='H'){ RelayState[i]=0; }                 //On --0 
					  }
						j++;
						break;
					}
					j++;
				}
			}
		}
private: System::Void buttonConnect_Click(System::Object^  sender, System::EventArgs^  e) {
			 if(0==ConnectFlag)
			 {
				 try{
                 tcpClient=gcnew TcpClient();
				 tcpClient->Connect(IPAddress::Parse(textBoxIp->Text),Int32::Parse(this->numericUpDown1->Value.ToString()));//
				 stream=tcpClient->GetStream();
				 ConnectFlag=1;
				 //Send Cmd R/dump for init RelayState[8]
				 GetStateBoard();
				 if(RelayState[0]==0)this->button1->Image=pictureBox1->Image;
				 else this->button1->Image=pictureBox2->Image;
				 if(RelayState[1]==0)this->button2->Image=pictureBox1->Image;
				 else this->button2->Image=pictureBox2->Image;
				 if(RelayState[2]==0)this->button3->Image=pictureBox1->Image;
				 else this->button3->Image=pictureBox2->Image;
				 if(RelayState[3]==0)this->button4->Image=pictureBox1->Image;
				 else this->button4->Image=pictureBox2->Image;
				 if(RelayState[4]==0)this->button5->Image=pictureBox1->Image;
				 else this->button5->Image=pictureBox2->Image;
				 if(RelayState[5]==0)this->button6->Image=pictureBox1->Image;
				 else this->button6->Image=pictureBox2->Image;
				 if(RelayState[6]==0)this->button7->Image=pictureBox1->Image;
				 else this->button7->Image=pictureBox2->Image;
				 if(RelayState[7]==0)this->button8->Image=pictureBox1->Image;
				 else this->button8->Image=pictureBox2->Image;
			     this->buttonConnect->Text="断开网络";
				 timer1->Enabled=true;
			 }
			 catch(SocketException ^e){
				 //弹出窗口 连接失败
				 MessageBox::Show(L"网络连接失败...");
			   }
			 }
			 else
			 {
				 timer1->Enabled=false;
				 stream->Close();
			    tcpClient->Close();
				this->buttonConnect->Text="连接网络";
				ConnectFlag=0;
			 }
		 }
private: System::Void timer1_Tick(System::Object^  sender, System::EventArgs^  e) {
			 GetStateBoard();
			  GetStateBoard();
			 if(RelayState[8]==1)pictureBox3->Image=pictureBox11->Image;
			 if(RelayState[8]==0) pictureBox3->Image=pictureBox12->Image;
			 if(RelayState[9]==1)pictureBox4->Image=pictureBox11->Image;
			 else pictureBox4->Image=pictureBox12->Image;
			 if(RelayState[10]==1)pictureBox5->Image=pictureBox11->Image;
			 else pictureBox5->Image=pictureBox12->Image;
			 if(RelayState[11]==1)pictureBox6->Image=pictureBox11->Image;
			 else pictureBox6->Image=pictureBox12->Image;
			 if(RelayState[12]==1)pictureBox7->Image=pictureBox11->Image;
			 else pictureBox7->Image=pictureBox12->Image;
			 if(RelayState[13]==1)pictureBox8->Image=pictureBox11->Image;
			 else pictureBox8->Image=pictureBox12->Image;
			 if(RelayState[14]==1)pictureBox9->Image=pictureBox11->Image;
			 else pictureBox9->Image=pictureBox12->Image;
			 if(RelayState[15]==1)pictureBox10->Image=pictureBox11->Image;
			 else pictureBox10->Image=pictureBox12->Image;

	//		 --------------------------------------------------------------
			 if(RelayState[0]==0)this->button1->Image=pictureBox1->Image;
			 else this->button1->Image=pictureBox2->Image;
			 if(RelayState[1]==0)this->button2->Image=pictureBox1->Image;
			 else this->button2->Image=pictureBox2->Image;
			 if(RelayState[2]==0)this->button3->Image=pictureBox1->Image;
			 else this->button3->Image=pictureBox2->Image;
			 if(RelayState[3]==0)this->button4->Image=pictureBox1->Image;
			 else this->button4->Image=pictureBox2->Image;
			 if(RelayState[4]==0)this->button5->Image=pictureBox1->Image;
			 else this->button5->Image=pictureBox2->Image;
			 if(RelayState[5]==0)this->button6->Image=pictureBox1->Image;
			 else this->button6->Image=pictureBox2->Image;
			 if(RelayState[6]==0)this->button7->Image=pictureBox1->Image;
			 else this->button7->Image=pictureBox2->Image;
			 if(RelayState[7]==0)this->button8->Image=pictureBox1->Image;
			 else this->button8->Image=pictureBox2->Image;

		 }
private: System::Void button1_Click(System::Object^  sender, System::EventArgs^  e) 
		 {
			timer1->Enabled=false;
		    OnOffSend(0,RelayState[0]);
			timer1->Enabled=true;
		 }
private: System::Void button2_Click(System::Object^  sender, System::EventArgs^  e) {
			timer1->Enabled=false;
            OnOffSend(1,RelayState[1]);
			timer1->Enabled=true;
		 }
private: System::Void button3_Click(System::Object^  sender, System::EventArgs^  e) {
			 OnOffSend(2,RelayState[2]);
		 }
private: System::Void button4_Click(System::Object^  sender, System::EventArgs^  e) {
			 OnOffSend(3,RelayState[3]);
		 }
private: System::Void button5_Click(System::Object^  sender, System::EventArgs^  e) {
			 OnOffSend(4,RelayState[4]);
		 }
private: System::Void button6_Click(System::Object^  sender, System::EventArgs^  e) {
			 OnOffSend(5,RelayState[5]);
		 }
private: System::Void button7_Click(System::Object^  sender, System::EventArgs^  e) {
			 OnOffSend(6,RelayState[6]);
		 }
private: System::Void button8_Click(System::Object^  sender, System::EventArgs^  e) {
			 OnOffSend(7,RelayState[7]);
		 }
};
}

