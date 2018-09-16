package com.modbus;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import com.dataBlock.*;

/**
 * Modbus类实现Runnable接口 *
 * 
 * @author SunPengFei
 * 
 */
public class Modbus extends Thread {
	
	boolean tempFlag=false;
	//30
	int valueWillWrite30 = 0;
	public void setValueWillWrite30(int valueWillWrite30) {
//		if (tempFlag){
//			System.out.println("valueWillWrite30传到Modbus通讯里了");
//		}
		this.valueWillWrite30 = valueWillWrite30;
	}
	int valueWillWriteOld30 = 0;
	//31
	int valueWillWrite31 = 0;
	public void setValueWillWrite31(int valueWillWrite31) {
		this.valueWillWrite31 = valueWillWrite31;
	}
	int valueWillWriteOld31 = 0;
	//32
	int valueWillWrite32 = 0;
	public void setValueWillWrite32(int valueWillWrite32) {
		this.valueWillWrite32 = valueWillWrite32;
	}
	int valueWillWriteOld32 = 0;
	//33
	int valueWillWrite33 = 0;
	public void setValueWillWrite33(int valueWillWrite33) {
		this.valueWillWrite33 = valueWillWrite33;
	}
	int valueWillWriteOld33 = 0;
	//34
	int valueWillWrite34 = 0;
	public void setValueWillWrite34(int valueWillWrite34) {
		this.valueWillWrite34 = valueWillWrite34;
	}
	int valueWillWriteOld34 = 0;
	//34_2
	int valueWillWrite34_2 = 0;
	public void setValueWillWrite34_2(int valueWillWrite34_2) {
		this.valueWillWrite34_2 = valueWillWrite34_2;
	}
	int valueWillWriteOld34_2 = 0;
	//34_3
	int valueWillWrite34_3 = 0;
	public void setValueWillWrite34_3(int valueWillWrite34_3) {
		this.valueWillWrite34_3 = valueWillWrite34_3;
	}
	int valueWillWriteOld34_3 = 0;
	//36
	int valueWillWrite36 = 0;
	public void setValueWillWrite36(int valueWillWrite36) {
		this.valueWillWrite36 = valueWillWrite36;
	}
	int valueWillWriteOld36 = 0;
	//36
	int valueWillWrite37 = 0;
	//700
	int valueWillWrite700 = 0;
	public void setValueWillWrite700(int valueWillWrite700) {
		this.valueWillWrite700 = valueWillWrite700;
	}
	int valueWillWriteOld700 = 0;

	//写528
	int value528=0;
	int value529=0;
	int value530=0;
	int value531=0;
	int value532=0;
	int value533=0;
	int value534=0;
	int value535=0;
	
	int value528_old=0;
	int value529_old=0;
	int value530_old=0;
	int value531_old=0;
	int value532_old=0;
	int value533_old=0;
	int value534_old=0;
	int value535_old=0;
	
	public void setValue528(int value528) {
		this.value528 = value528;
	}
	public void setValue529(int value529) {
		this.value529 = value529;
	}
	public void setValue530(int value530) {
		this.value530 = value530;
	}
	public void setValue531(int value531) {
		this.value531 = value531;
	}
	public void setValue532(int value532) {
		this.value532 = value532;
	}
	public void setValue533(int value533) {
		this.value533 = value533;
	}
	public void setValue534(int value534) {
		this.value534 = value534;
	}
	public void setValue535(int value535) {
		this.value535 = value535;
	}
	
	// datablocks中的各个对象，包含了Modbus在连接时需要的
	// 请求request和Modbus得到的响应将要存放的data[]数组
	ArrayList<BasicDataBlock> datablocks = null;

	public ArrayList<BasicDataBlock> getDatablocks() {
		return datablocks;
	}

	// 实例化一个ReadMultiRegisterDataBlock，其中10_4的意思是从寄存器MW10开始，向后连续读4个
	ReadMultiRegisterDataBlock readMultiRegisterDB10_10 = null;
	ReadMultiRegisterDataBlock readMultiRegisterDB300_10 = null;
	ReadMultiRegisterDataBlock readMultiRegisterDB330_25 = null;//330_25
	ReadMultiRegisterDataBlock readMultiRegisterDB357_9 = null;
	ReadMultiRegisterDataBlock readMultiRegisterDB1006_8 = null;
	ReadMultiRegisterDataBlock readMultiRegisterDB30_7 = null;
	ReadMultiRegisterDataBlock readMultiRegisterDB528_8 = null;
	ReadMultiRegisterDataBlock readMultiRegisterDB700_1 = null;

	WriteSingleRegisterDataBlock writeSingleRegisterDataBlock30_1 = null;
	WriteSingleRegisterDataBlock writeSingleRegisterDataBlock31_1 = null;
	WriteSingleRegisterDataBlock writeSingleRegisterDataBlock33_1 = null;
	WriteSingleRegisterDataBlock writeSingleRegisterDataBlock34_1 = null;
	WriteSingleRegisterDataBlock writeSingleRegisterDataBlock36_1 = null;
	WriteSingleRegisterDataBlock writeSingleRegisterDataBlock700_1 = null;

	WriteMultiRegisterDataBlock writeMultiRegisterDataBlock528_4=null;
	WriteMultiRegisterDataBlock writeMultiRegisterDataBlock532_4=null;	

	WriteSingleRegisterDataBlock writeSingleRegisterDataBlock32_1 = null;
	WriteSingleRegisterDataBlock writeSingleRegisterDataBlock37_1 = null;

	// 远程设备的IP地址
	String DeviceIP = null;
	// 与PLC连接的Socket
	Socket PLCSocket = null;
	// 输出流，要发送的请求（Request）通过输出流发出
	OutputStream Request = null;
	// 输入流，得到字节流作为响应（Response）使用
	InputStream Response = null;
	// 窗口正在关闭
	boolean windowsClosing = false;
	// 读到响应后，输入流临时使用
	byte buffer[] = null;
	int times = 0;
	boolean networkDisconnected = false;

	public boolean isNetworkDisconnected() {
		return networkDisconnected;
	}

	/**
	 * 实现Modbus的具体连接
	 */
	public Modbus(String DeviceIP) {
		this.DeviceIP = DeviceIP;

		datablocks = new ArrayList<BasicDataBlock>();

		readMultiRegisterDB10_10 = new ReadMultiRegisterDataBlock(10, 10);
		readMultiRegisterDB300_10 = new ReadMultiRegisterDataBlock(300, 10);
		readMultiRegisterDB330_25 = new ReadMultiRegisterDataBlock(330,25);
		readMultiRegisterDB357_9 = new ReadMultiRegisterDataBlock(357, 9);
		readMultiRegisterDB1006_8 = new ReadMultiRegisterDataBlock(1006, 8);
		readMultiRegisterDB30_7 = new ReadMultiRegisterDataBlock(30, 7);
		readMultiRegisterDB528_8 = new ReadMultiRegisterDataBlock(528, 8);
		readMultiRegisterDB700_1 = new ReadMultiRegisterDataBlock(700, 1);

		writeSingleRegisterDataBlock30_1 = new WriteSingleRegisterDataBlock(30,valueWillWrite30);
		writeSingleRegisterDataBlock31_1 = new WriteSingleRegisterDataBlock(31,valueWillWrite31);
		writeSingleRegisterDataBlock33_1 = new WriteSingleRegisterDataBlock(33,valueWillWrite33);
		writeSingleRegisterDataBlock34_1 = new WriteSingleRegisterDataBlock(34,valueWillWrite34);
		writeSingleRegisterDataBlock36_1 = new WriteSingleRegisterDataBlock(36,valueWillWrite36);
		writeSingleRegisterDataBlock700_1 = new WriteSingleRegisterDataBlock(700,valueWillWrite700);
		
		writeMultiRegisterDataBlock528_4=new WriteMultiRegisterDataBlock(528, 4, value528, value529, value530, value531);
		writeMultiRegisterDataBlock532_4=new WriteMultiRegisterDataBlock(532, 4, value532, value533, value534, value535);

		writeSingleRegisterDataBlock32_1 = new WriteSingleRegisterDataBlock(32,valueWillWrite32);
		writeSingleRegisterDataBlock37_1 = new WriteSingleRegisterDataBlock(37,valueWillWrite37);
		// System.out.println("构造的时候valueWillWrite="+valueWillWrite);
		datablocks.add(readMultiRegisterDB10_10);//0
		datablocks.add(readMultiRegisterDB300_10);//1
		datablocks.add(readMultiRegisterDB330_25);//2
		datablocks.add(readMultiRegisterDB357_9);//3
		datablocks.add(readMultiRegisterDB1006_8);//4
		datablocks.add(readMultiRegisterDB30_7);//5
		datablocks.add(readMultiRegisterDB528_8);//6
		datablocks.add(readMultiRegisterDB700_1);//**7

		datablocks.add(writeSingleRegisterDataBlock30_1);//8
		datablocks.add(writeSingleRegisterDataBlock31_1);//**9
		datablocks.add(writeSingleRegisterDataBlock33_1);//10
		datablocks.add(writeSingleRegisterDataBlock34_1);//11
		datablocks.add(writeSingleRegisterDataBlock36_1);//**12
		datablocks.add(writeSingleRegisterDataBlock700_1);//**13

		datablocks.add(writeMultiRegisterDataBlock528_4);//14
		datablocks.add(writeMultiRegisterDataBlock532_4);//15

		datablocks.add(writeSingleRegisterDataBlock32_1);//16
		datablocks.add(writeSingleRegisterDataBlock37_1);//17
	}

	public void connect() {
		this.start();
	}

	/**
	 * 重写的run()函数中，主要包括：1、建立Socket。2、发送请求并接收响应。3、
	 */
	@Override
	public void run() {

		// 如果套接字（Socket）没有创建，那么创建套接字（Socket），如果套接字（Socket）断开连接，那么重新创建新的连接
		while (PLCSocket == null && !windowsClosing) {
			reconnect();
		}

		/**
		 * datablocks.get(i).getRequest()得到一个请求（request）
		 * 它的内容是一个byte[12]数组，将不同的请求（request）写到 OutputStream输出流中，形成字节流
		 * （OutputStream）.write(byte[] b) 将 b.length 个字节从指定的 byte 数组写入此输出流。
		 * 之所以定义buffer = new byte[1024];目的是因为发送的请求都是byte[12]数组，但因为需要读寄
		 * 存器的长度不同，导致返回的响应中，有用的数据比byte[12]数组长度要长（读寄存器长度-1）*2个，比如 发送和接收示例：
		 * 读3个寄存器发送（0x） 0C AA 00 00 00 06 01 03 00 00 00 03（request共12字节） 发送序号
		 * 默认 数据长度 地址号 功能码 开始地址 读长度 0~65535 0~125 读3个寄存器接收（0x） 0C AA 00 00 00 09
		 * 01 03 06 00 03 00 02 00 01 发送序号 默认 数据长度 地址号 功能码 错误代码 失败的接收 （0x） 0C AA
		 * 00 00 00 09 01 83 01/02/03/04
		 * 所以，这个1KB的缓冲区，能够接收1024-10=1014个字节，因为读长度范围是0~125，所以有的人也会定义 buffer = new
		 * byte[261]，即（126*2+9）。
		 */
		// 有了连接后，只要窗口没有关闭，那么就开始读写数据
		while (!windowsClosing) {
			if (Request == null || Response == null) {
				System.out.println("sendRequest或者readResponse=null");
				break;
			}
			// 开始读数据
			try {
				//与PLC的心跳位，可以保证点动时的安全性
				valueWillWrite37=(int) Math.round(Math.random() * (4096 - 1) + 1);

//				 System.out.println("valueWillWrite30="+valueWillWrite30+"valueWillWriteOld30="+valueWillWriteOld30);

//				System.out.println("value532="+value532+",value533="+value533+",value534="+value534+",value535="+value535);
			
				for (int i = 0; i < datablocks.size(); i++) {

					// 在内存中开辟一块缓冲区，大小是1024个字节，即1KB,实际上因为modbus读多个
					// 寄存器的数量是0~125，所以有人不用1024，而写成261。（126*2+9）
					buffer = new byte[1024];
					// 将不同的请求（request）按循环分别写到OutputStream输出流

					if (i < 8) {
						Request.write(datablocks.get(i).getRequest());
						// 将每次循环得到的响应写到buffer中
						// read(byte[] b) 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。
						Response.read(buffer);
						// 将每次循环得到的响应写到data[]中
						datablocks.get(i).setResponse(buffer);
					} else if ((i == 8) &&
							(valueWillWrite30 != valueWillWriteOld30)) {
						writeSingleRegisterDataBlock30_1
								.setValue(valueWillWrite30);
						Request.write(datablocks.get(i).getRequest());
						Response.read(buffer);
						datablocks.get(i).setResponse(buffer);
						tempFlag=true;
//						System.out.println("执行了写寄存器,把"+valueWillWrite30+"写进去了");
					} else if ((i == 9)) {
//							&& (valueWillWrite31 != valueWillWriteOld31)) {
						writeSingleRegisterDataBlock31_1
								.setValue(valueWillWrite31);
						Request.write(datablocks.get(i).getRequest());
						Response.read(buffer);
						datablocks.get(i).setResponse(buffer);
						//tempFlag=true;
					} else if (i == 10
							&& valueWillWrite33 != valueWillWriteOld33) {
						writeSingleRegisterDataBlock33_1
								.setValue(valueWillWrite33);
						Request.write(datablocks.get(i).getRequest());
						Response.read(buffer);
						datablocks.get(i).setResponse(buffer);
						tempFlag=true;
					} else if (i == 11 
							&& (valueWillWrite34 != valueWillWriteOld34 || valueWillWrite34_2 != valueWillWriteOld34_2 || valueWillWrite34_3 != valueWillWriteOld34_3)) {
						
						if (valueWillWrite34 != valueWillWriteOld34){
							writeSingleRegisterDataBlock34_1.setValue(valueWillWrite34);							
						}
						if (valueWillWrite34_2 != valueWillWriteOld34_2){
							writeSingleRegisterDataBlock34_1.setValue(valueWillWrite34_2);							
						}
						if (valueWillWrite34_3 != valueWillWriteOld34_3){
							writeSingleRegisterDataBlock34_1.setValue(valueWillWrite34_3);							
						}
						Request.write(datablocks.get(i).getRequest());
						Response.read(buffer);
						datablocks.get(i).setResponse(buffer);
						tempFlag=true;
//						System.out.println("modbus里边的i==9执行了,valueWillWrite34="+valueWillWrite34+",valueWillWriteOld34="+valueWillWriteOld34);
//						System.out.println("valueWillWrite34_3="+valueWillWrite34_3+",valueWillWriteOld34_3="+valueWillWriteOld34_3);
					}else if (i == 12
							&& valueWillWrite36 != valueWillWriteOld36) {
						writeSingleRegisterDataBlock36_1
								.setValue(valueWillWrite36);
						Request.write(datablocks.get(i).getRequest());
						Response.read(buffer);
						datablocks.get(i).setResponse(buffer);
						tempFlag=true;
					}else if (i == 13
							&& valueWillWrite700 != valueWillWriteOld700) {
						writeSingleRegisterDataBlock700_1
								.setValue(valueWillWrite700);
						Request.write(datablocks.get(i).getRequest());
						Response.read(buffer);
						datablocks.get(i).setResponse(buffer);
						tempFlag=true;
					}					
					else if (i == 14 && ((value528 != value528_old)||(value529 != value529_old)||(value530 != value530_old)||(value531 != value531_old))) {
						writeMultiRegisterDataBlock528_4.setValue1(value528);
						writeMultiRegisterDataBlock528_4.setValue2(value529);
						writeMultiRegisterDataBlock528_4.setValue3(value530);
						writeMultiRegisterDataBlock528_4.setValue4(value531);
						Request.write(datablocks.get(i).getRequest());
						Response.read(buffer);
						datablocks.get(i).setResponse(buffer);
						tempFlag=true;
					}else if (i == 15 && ((value532 != value532_old)||(value533 != value533_old)||(value534 != value534_old)||(value535 != value535_old))) {

//						System.out.println("111执行了i=10");
						writeMultiRegisterDataBlock532_4.setValue1(value532);
						writeMultiRegisterDataBlock532_4.setValue2(value533);
						writeMultiRegisterDataBlock532_4.setValue3(value534);
						writeMultiRegisterDataBlock532_4.setValue4(value535);
						Request.write(datablocks.get(i).getRequest());
						Response.read(buffer);
						datablocks.get(i).setResponse(buffer);
						tempFlag=true;
//						System.out.println("value532="+value532+",value533="+value533+",value534="+value534+",value535="+value535);
					}else if (i == 16) {
//							&& valueWillWrite32 != valueWillWriteOld32) {
						writeSingleRegisterDataBlock32_1
								.setValue(valueWillWrite32);
						Request.write(datablocks.get(i).getRequest());
						Response.read(buffer);
						datablocks.get(i).setResponse(buffer);
						//tempFlag=true;
					}else if (i == 17) {
						writeSingleRegisterDataBlock37_1
								.setValue(valueWillWrite37);
						Request.write(datablocks.get(i).getRequest());
						Response.read(buffer);
						datablocks.get(i).setResponse(buffer);
						//tempFlag=true;
					}

					if (windowsClosing) {
						break;
					}

					// 每50毫秒更新一次数据
					Thread.sleep(50);
				}
				
				if (tempFlag){

					valueWillWriteOld30 = valueWillWrite30;
					valueWillWriteOld31 = valueWillWrite31;
					valueWillWriteOld32 = valueWillWrite32;
					valueWillWriteOld33 = valueWillWrite33;
					valueWillWriteOld34 = valueWillWrite34;
					valueWillWriteOld34_2 = valueWillWrite34_2;
					valueWillWriteOld34_3 = valueWillWrite34_3;
					valueWillWriteOld36 = valueWillWrite36;
					valueWillWriteOld700 = valueWillWrite700;

					value528_old = value528;
					value529_old = value529;
					value530_old = value530;
					value531_old = value531;
					value532_old = value532;
					value533_old = value533;
					value534_old = value534;
					value535_old = value535;
					
					tempFlag=false;
//					System.out.println("old=new");
				}
				if (windowsClosing) {
					break;
				}
			} catch (Exception e) {
				// System.out.println(this.DeviceIP + "连接异常，将在10秒后重连");
				// 网络没有连接或异常
				networkDisconnected = true;
				while (times < 10) {
					try {
						Thread.sleep(1000);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (windowsClosing) {
						break;
					}
					times++;
				}
				PLCSocket = null;
				times = 0;
				reconnect();
			}
		}
		// run()里的代码在关闭窗体后，通过windowsClosed=false ，触发本程序的close()
		windowsClosing = false;
	}

	/**
	 * modbus的连接方法如下：创建并设置Socket ，
	 */
	private void reconnect() {
		try {
			if (PLCSocket == null) {
				PLCSocket = new Socket();
				PLCSocket.setSoTimeout(3000);
				PLCSocket.connect(new InetSocketAddress(DeviceIP, 502), 3000);
				// System.out.println(DeviceIP + "首次成功建立新的套接字。");
				// 网络正常
				networkDisconnected = false;
				// 发出的请求，Socket.getOutputStream() 返回此套接字的输入流。
				Request = PLCSocket.getOutputStream();
				// 读入的数据，Socket.getInputStream() 返回此套接字的输入流。
				Response = PLCSocket.getInputStream();
			}
		} catch (Exception e) {
			// System.out.println(this.DeviceIP + "连接超时，将在10秒后重连");
			// 网络没有连接或异常
			networkDisconnected = true;
			while (times < 10 && !windowsClosing) {
				try {
					Thread.sleep(1000);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (windowsClosing) {
					break;
				}
				times++;
			}
			times = 0;
			PLCSocket = null;
		}

	}

	/**
	 * modbus的关闭方法如下： 分三个步骤，关闭输入流、输出流、套接字。具体实现：点了窗体的关闭后， windowsClosing置为true
	 * ，开始等待，while循环将暂时是一个死循环，等待run函数里最后一行windowsClosing变为false后，向下执行。
	 * 目的是为了等待所有线程执行完毕在关闭Socket 。
	 */
	public void close() {
		try {
			windowsClosing = true;
			while (windowsClosing) {
			}
			if (Request != null) {
				// 关闭此输出流。释放与该流关联的所有系统资源，这个是输出流OutputStram使用中必须要做的。
				Request.close();
			}
			if (Response != null) {
				// 关闭此输入流。
				Response.close();
			}
			if (PLCSocket != null) {
				if (PLCSocket.isConnected()) {
					// 关闭套接字
					PLCSocket.close();
					// System.out.println(this.DeviceIP + "套接字已成功关闭。");
				}
			} else {
				// System.out.println(this.DeviceIP + "套接字从未创建，不用关闭。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
