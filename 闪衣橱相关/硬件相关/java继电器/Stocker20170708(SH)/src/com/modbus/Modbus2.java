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
public class Modbus2 extends Thread {
	
	// datablocks中的各个对象，包含了Modbus在连接时需要的
	// 请求request和Modbus得到的响应将要存放的data[]数组
	ArrayList<BasicDataBlock> datablocks = null;

	public ArrayList<BasicDataBlock> getDatablocks() {
		return datablocks;
	}

	// 实例化一个ReadMultiRegisterDataBlock，其中100_8的意思是从寄存器MW100开始，向后连续读8个
	ReadMultiRegisterDataBlock readMultiRegisterDB100_10 = null;
	ReadMultiRegisterDataBlock readMultiRegisterDB1000_8 = null;
	ReadMultiRegisterDataBlock readMultiRegisterDB2000_6 = null;

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
	public Modbus2(String DeviceIP) {
		this.DeviceIP = DeviceIP;

		datablocks = new ArrayList<BasicDataBlock>();

		readMultiRegisterDB100_10 = new ReadMultiRegisterDataBlock(100, 10);
//		readMultiRegisterDB1000_8 = new ReadMultiRegisterDataBlock(1000, 8);
//		readMultiRegisterDB2000_6 = new ReadMultiRegisterDataBlock(2000, 6);
		datablocks.add(readMultiRegisterDB100_10);
//		datablocks.add(readMultiRegisterDB1000_8);
//		datablocks.add(readMultiRegisterDB2000_6);
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

		// 有了连接后，只要窗口没有关闭，那么就开始读写数据
		while (!windowsClosing) {
			if (Request == null || Response == null) {
				System.out.println("sendRequest或者readResponse=null");
				break;
			}
			// 开始读数据
			try {

				// System.out.println("set后的valueWillWrite="+valueWillWrite);

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
					} 

					if (windowsClosing) {
						break;
					}

					// 每50毫秒更新一次数据
					Thread.sleep(50);
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
