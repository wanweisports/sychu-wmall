unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ComCtrls, ExtCtrls, ScktComp;

type
  TForm1 = class(TForm)
    GroupBox1: TGroupBox;
    Label1: TLabel;
    Edit1: TEdit;
    Label2: TLabel;
    Edit2: TEdit;
    Button1: TButton;
    GroupBox2: TGroupBox;
    CheckBox1: TCheckBox;
    CheckBox2: TCheckBox;
    CheckBox3: TCheckBox;
    CheckBox4: TCheckBox;
    CheckBox5: TCheckBox;
    CheckBox6: TCheckBox;
    CheckBox7: TCheckBox;
    CheckBox8: TCheckBox;
    CheckBox9: TCheckBox;
    CheckBox10: TCheckBox;
    CheckBox11: TCheckBox;
    CheckBox12: TCheckBox;
    CheckBox13: TCheckBox;
    CheckBox14: TCheckBox;
    CheckBox15: TCheckBox;
    CheckBox16: TCheckBox;
    CheckBox17: TCheckBox;
    CheckBox18: TCheckBox;
    CheckBox19: TCheckBox;
    CheckBox20: TCheckBox;
    CheckBox21: TCheckBox;
    CheckBox22: TCheckBox;
    CheckBox23: TCheckBox;
    CheckBox24: TCheckBox;
    CheckBox25: TCheckBox;
    CheckBox26: TCheckBox;
    CheckBox27: TCheckBox;
    CheckBox28: TCheckBox;
    CheckBox29: TCheckBox;
    CheckBox30: TCheckBox;
    CheckBox31: TCheckBox;
    CheckBox32: TCheckBox;
    GroupBox3: TGroupBox;
    Label3: TLabel;
    Edit3: TEdit;
    Label4: TLabel;
    Label5: TLabel;
    Edit4: TEdit;
    Label6: TLabel;
    Button2: TButton;
    Button3: TButton;
    Edit5: TEdit;
    Edit6: TEdit;
    Label7: TLabel;
    GroupBox4: TGroupBox;
    StatusBar1: TStatusBar;
    GroupBox5: TGroupBox;
    Label8: TLabel;
    Button4: TButton;
    Button5: TButton;
    Button6: TButton;
    Button7: TButton;
    Memo1: TMemo;
    Memo2: TMemo;
    Timer1: TTimer;
    Timer2: TTimer;
    ClientSocket1: TClientSocket;
    procedure Edit1KeyPress(Sender: TObject; var Key: Char);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure FormCreate(Sender: TObject);

    procedure Button1Click(Sender: TObject);
    procedure Edit2KeyPress(Sender: TObject; var Key: Char);
    procedure Button7Click(Sender: TObject);
    procedure CheckBox1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure ClientSocket1Connecting(Sender: TObject;
      Socket: TCustomWinSocket);
    procedure ClientSocket1Disconnect(Sender: TObject;
      Socket: TCustomWinSocket);
    procedure ClientSocket1Error(Sender: TObject; Socket: TCustomWinSocket;
      ErrorEvent: TErrorEvent; var ErrorCode: Integer);
    procedure ClientSocket1Read(Sender: TObject; Socket: TCustomWinSocket);
    procedure Button3Click(Sender: TObject);
    procedure ClientSocket1Connect(Sender: TObject;
      Socket: TCustomWinSocket);
    procedure Button4Click(Sender: TObject);
    procedure Button5Click(Sender: TObject);
    procedure Button6Click(Sender: TObject);
    procedure CheckBox2Click(Sender: TObject);
    procedure CheckBox3Click(Sender: TObject);
    procedure CheckBox4Click(Sender: TObject);
    procedure CheckBox5Click(Sender: TObject);
    procedure CheckBox6Click(Sender: TObject);
    procedure CheckBox7Click(Sender: TObject);
    procedure CheckBox8Click(Sender: TObject);
    procedure CheckBox9Click(Sender: TObject);
    procedure CheckBox10Click(Sender: TObject);
    procedure CheckBox11Click(Sender: TObject);
    procedure CheckBox12Click(Sender: TObject);
    procedure CheckBox13Click(Sender: TObject);
    procedure CheckBox14Click(Sender: TObject);
    procedure CheckBox15Click(Sender: TObject);
    procedure CheckBox16Click(Sender: TObject);
    procedure CheckBox17Click(Sender: TObject);
    procedure CheckBox18Click(Sender: TObject);
    procedure CheckBox19Click(Sender: TObject);
    procedure CheckBox20Click(Sender: TObject);
    procedure CheckBox21Click(Sender: TObject);
    procedure CheckBox22Click(Sender: TObject);
    procedure CheckBox23Click(Sender: TObject);
    procedure CheckBox24Click(Sender: TObject);
    procedure CheckBox25Click(Sender: TObject);
    procedure CheckBox26Click(Sender: TObject);
    procedure CheckBox27Click(Sender: TObject);
    procedure CheckBox28Click(Sender: TObject);
    procedure CheckBox29Click(Sender: TObject);
    procedure CheckBox30Click(Sender: TObject);
    procedure CheckBox31Click(Sender: TObject);
    procedure CheckBox32Click(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);
    procedure Timer2Timer(Sender: TObject);
  private
    procedure get_data;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;
  control_datal, control_data2, control_data3, control_data4: Integer;
implementation

{$R *.dfm}
{$R windowsxp}

procedure TForm1.get_data;

begin
  try
    control_datal := 0;
    control_data2 := 0;
    control_data3 := 0;
    control_data4 := 0;
    if CheckBox1.Checked = True then control_datal := control_datal + 1;
    if CheckBox2.Checked = True then control_datal := control_datal + 2;
    if CheckBox3.Checked = True then control_datal := control_datal + 4;
    if CheckBox4.Checked = True then control_datal := control_datal + 8;
    if CheckBox5.Checked = True then control_datal := control_datal + 16;
    if CheckBox6.Checked = True then control_datal := control_datal + 32;
    if CheckBox7.Checked = True then control_datal := control_datal + 64;
    if CheckBox8.Checked = True then control_datal := control_datal + 128;

    if CheckBox9.Checked = True then control_data2 := control_data2 + 1;
    if CheckBox10.Checked = True then control_data2 := control_data2 + 2;
    if CheckBox11.Checked = True then control_data2 := control_data2 + 4;
    if CheckBox12.Checked = True then control_data2 := control_data2 + 8;
    if CheckBox13.Checked = True then control_data2 := control_data2 + 16;
    if CheckBox14.Checked = True then control_data2 := control_data2 + 32;
    if CheckBox15.Checked = True then control_data2 := control_data2 + 64;
    if CheckBox16.Checked = True then control_data2 := control_data2 + 128;

    if CheckBox17.Checked = True then control_data3 := control_data3 + 1;
    if CheckBox18.Checked = True then control_data3 := control_data3 + 2;
    if CheckBox19.Checked = True then control_data3 := control_data3 + 4;
    if CheckBox20.Checked = True then control_data3 := control_data3 + 8;
    if CheckBox21.Checked = True then control_data3 := control_data3 + 16;
    if CheckBox22.Checked = True then control_data3 := control_data3 + 32;
    if CheckBox23.Checked = True then control_data3 := control_data3 + 64;
    if CheckBox24.Checked = True then control_data3 := control_data3 + 128;

    if CheckBox25.Checked = True then control_data4 := control_data4 + 1;
    if CheckBox26.Checked = True then control_data4 := control_data4 + 2;
    if CheckBox27.Checked = True then control_data4 := control_data4 + 4;
    if CheckBox28.Checked = True then control_data4 := control_data4 + 8;
    if CheckBox29.Checked = True then control_data4 := control_data4 + 16;
    if CheckBox30.Checked = True then control_data4 := control_data4 + 32;
    if CheckBox31.Checked = True then control_data4 := control_data4 + 64;
    if CheckBox32.Checked = True then control_data4 := control_data4 + 128;
  except
    Exit;
  end;
end;

procedure TForm1.Edit1KeyPress(Sender: TObject; var Key: Char);
begin
  try
    if not (key in ['0'..'9', '.', #8, #13]) then Key := #0;
    if Key = #13 then
      Edit2.SetFocus;
  except
    Exit;
  end;
end;

procedure TForm1.FormClose(Sender: TObject; var Action: TCloseAction);
begin
  Action := caFree;
  //FreeAndNil(Form1);
end;

procedure TForm1.FormCreate(Sender: TObject);
begin
  try
   // Edit1.Clear;
   // Edit2.Clear;
    Edit3.Clear;
    Edit4.Clear;
    Edit5.Clear;
    Edit6.Clear;
    Memo1.Clear;
    Memo2.Clear;
  except
    Exit;
  end;
end;

procedure TForm1.Button1Click(Sender: TObject);
begin



  ClientSocket1.Address := Edit1.Text;

  ClientSocket1.Port := StrToInt(Edit2.Text);

 // ClientSocket1.Active := True;
  try
    ClientSocket1.Active := True;
  except
    ShowMessage('连接失败');
    Exit;
  end;
end;

procedure TForm1.Edit2KeyPress(Sender: TObject; var Key: Char);
begin
  try
    if not (key in ['0'..'9', #8, #13]) then Key := #0;
    if Key = #13 then
      Button1.SetFocus;
  except
    Exit;
  end;
end;

procedure TForm1.Button7Click(Sender: TObject);
begin
  Application.Terminate;
end;

procedure TForm1.CheckBox1Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.Button2Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  send_buf := 'Get_temp_stactus';
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.ClientSocket1Connecting(Sender: TObject;
  Socket: TCustomWinSocket);
begin
  StatusBar1.Panels.Items[0].Text := '正在连接中';
end;

procedure TForm1.ClientSocket1Disconnect(Sender: TObject;
  Socket: TCustomWinSocket);
begin
  StatusBar1.Panels.Items[0].Text := '服务器连接中断';
end;

procedure TForm1.ClientSocket1Error(Sender: TObject;
  Socket: TCustomWinSocket; ErrorEvent: TErrorEvent;
  var ErrorCode: Integer);
begin
  StatusBar1.Panels.Items[0].Text := '服务器连接失败';
end;

procedure TForm1.ClientSocket1Read(Sender: TObject;
  Socket: TCustomWinSocket);
var
  temp, Substr: AnsiString;
  input_state, temp_L: Integer;
begin

  temp := ClientSocket1.Socket.ReceiveText();
  Memo1.Lines.Add(temp);
    //==========================继电器控制成功
  Substr := Copy(temp, 1, 16);
  if Substr = 'Relay-control-ok' then
  begin
    StatusBar1.Panels.Items[0].Text := '继电器控制成功';
  end;
    //============================获得当前继电器状态
  Substr := Copy(temp, 1, 6);
  if Substr = 'Relay=' then
  begin
    temp_L := Length(temp);
    Edit5.Text := Copy(temp, 7, temp_L - 6);
  end;

    //============================获得当前温湿度
  if Substr = 'temp=' then
  begin
    Edit3.Text := Copy(temp, 6, 5);
    Edit4.Text := Copy(temp, 17, 2);
  end;

    //============================获得当前开关量输入值
  Substr := Copy(temp, 1, 6);
  if Substr = 'input=' then
  begin
    temp_L := Length(temp);
    Edit6.Text := Copy(temp, 7, 2);
  end;

end;

procedure TForm1.Button3Click(Sender: TObject);
begin
  ClientSocket1.Active := False;
  ClientSocket1.Close;
end;

procedure TForm1.ClientSocket1Connect(Sender: TObject;
  Socket: TCustomWinSocket);
begin
  StatusBar1.Panels.Items[0].Text := '已连接';
end;

procedure TForm1.Button4Click(Sender: TObject);
begin
  ClientSocket1.Socket.SendText(Memo2.Text);
end;

procedure TForm1.Button5Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  //================================获得当前继电器状态
  send_buf := 'Get_relay_status';
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.Button6Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  send_buf := 'Get_relay_status';
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox2Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox3Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox4Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox5Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox6Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox7Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox8Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox9Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox10Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox11Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox12Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox13Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox14Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox15Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox16Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox17Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox18Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox19Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox20Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox21Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox22Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox23Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox24Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox25Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox26Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox27Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox28Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox29Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox30Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox31Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.CheckBox32Click(Sender: TObject);
var
  send_buf: AnsiString;
begin
  get_data;
  send_buf := 'relay=' + IntToHex(control_datal, 2) + ',' + IntToHex(control_data2, 2) + ',' + IntToHex(control_data3, 2) + ',' + IntToHex(control_data4, 2);
  ClientSocket1.Socket.SendText(send_buf);
end;

procedure TForm1.Timer1Timer(Sender: TObject);
begin
  StatusBar1.Panels.Items[1].Text := DateTimeToStr(Now());
end;

procedure TForm1.Timer2Timer(Sender: TObject);
begin
  StatusBar1.Panels.Items[0].Text := '';
end;

end.

 