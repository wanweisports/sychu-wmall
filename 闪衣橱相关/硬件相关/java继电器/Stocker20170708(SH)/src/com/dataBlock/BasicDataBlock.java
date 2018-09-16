/**在实际操作中，由于要读（写）的寄存器地址不连续，为了不引起资源浪费，需要按区域读（写），
 * 所以定义两个类 ReadDataBlock 和 WriteDataBlock （继承BasicDataBlock父类），
 * 分别用来在Modbus连接过程中， 通过for循环，实现按区域读（写）寄存器，并得到其结果
 * result[] 的功能。
 * *********************************************************
 * MODBUS TCP/IP 的ADU包括3部分
 * 1、MBAP报文头
 * 2、功能码
 * 3、数据
 * 其中，2和3组成PDU
 * 
 * @author SunPengFei 
 */
package com.dataBlock;

/**
 * 基础数据块（父类）
 * @author SunPengFei
 */
public class BasicDataBlock {

	// 开始读(写)寄存器的偏移量（从哪个寄存器地址开始读、写）
	int ref = 0;

	// 读(写)寄存器的长度
	int length = 0;

	// 写寄存器的字节数
//	int byteNum = 0;
	
	// 写单个寄存器的值
	int value = 0;	
	
	/**
	 * 传入写入值（一般为写单个寄存器时用）
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}

	// 用来发送请求（request）的byte数组
	byte request[] = null;

	// 用来接收响应（response）的byte数组
	byte response[] = null;

	/**
	 * 从DataBlock（ReadDataBlock或WriteDataBlock）中得到请求， 用来在modbus通讯时发送请求使用。
	 * @return 返回一个（request）byte数组
	 */
	public byte[] getRequest() {
		return request;
	}

	/**
	 * 得到已经赋值的（response）byte数组，以便用来进行数据的解析和使用
	 * @return 返回一个（response）byte数组
	 */
	public byte[] getResponse() {
		return response;
	}

	/**
	 * 将套接字读到的数据写到（response）byte数组
	 * @return 返回一个（response）byte数组
	 */
	public void setResponse(byte[] response) {
		this.response = response;
	}

	/**
	 * 用来存放结果的int[]数组
	 */
	public int[] data = null;
	
	//构造函数
	public BasicDataBlock(){
		
	}

}
