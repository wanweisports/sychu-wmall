package com.dataBlock;

/**
 * 写多个寄存器的数据块
 * 
 * @author SunPengFei *
 */
public class WriteMultiRegisterDataBlock extends BasicDataBlock {
	
	int value1=0;
	int value2=0;
	int value3=0;
	int value4=0;
	int byteNum=8;

	public WriteMultiRegisterDataBlock(int ref, int length, int value1,int value2,int value3,int value4) {
		// 发出的请求
		request = new byte[21];
		// 用来接收并存储数据的data
		data = new int[6];
		//寄存器起始地址
		this.ref=ref;
		//寄存器数量
		this.length=length;
		//字节数
//		this.byteNum=byteNum;
		//写入的连续值
		this.value1=value1;
		//写入的连续值
		this.value2=value2;
		//写入的连续值
		this.value3=value3;
		//写入的连续值
		this.value4=value4;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	public void setValue3(int value3) {
		this.value3 = value3;
	}

	public void setValue4(int value4) {
		this.value4 = value4;
	}

	/**
	 * MBAP报文头包括7个字节： 1、事务元标识符 （2字节）默认设成0 2、协议标识符 （2字节）=0为MODBUS协议 3、长度
	 * （2字节）后边数据的长度，包括单元标识符1字节 4、单元标识符 （1字节）255和0都可以，但建议设成0
	 * ******************************************************************************** 
	 * 发送和接收示例：
	 * 写多个寄存器发送（0x） 0C AA 	00 00 	00 06 	01 		10 		00 01 	00 04	8		00 0A 01 02 00 0A 01 02		
	 * 					发送序号 	默认		数据长度 	地址号 	功能码 	开始地址 	长度		字节数	寄存器值
	 * 写多个寄存器接收（0x） 0C AA	00 00 	00 06 	01 		10		00 00 	00 04 
	 * 					发送序号	 默认 	数据长度	地址号 	功能码 	输出地址 	长度	 
	 * 失败的接收 （0x） 	0C AA 	00 00 	00 09 	01 		90 				01/02/03/04
	 */
	@Override
	public byte[] getRequest() {
		request[0] = 0x00; // 事务元标识符
		request[1] = 0x00; // 事务元标识符
		request[2] = 0x00; // 协议标识符
		request[3] = 0x00; // 协议标识符
		request[4] = 0x00; // 长度
		request[5] = 0x0f; // 长度15
		request[6] = 0x00; // 单元标识符
		request[7] = 0x10; // 功能码10，写多个寄存器
		request[8] = (byte) (ref / 256); // 寄存器地址高8位
		request[9] = (byte) (ref % 256); // 寄存器地址低8位
		
		request[10] = (byte) (length / 256); // 寄存器数量的高8位
		request[11] = (byte) (length % 256); // 寄存器数量的低8位
		
		request[12] = (byte) (byteNum); // 字节数
		
		request[13] = (byte) (value1 / 256); // 写入值1的高8位
		request[14] = (byte) (value1 % 256); // 写入值1的低8位
		
		request[15] = (byte) (value2 / 256); // 写入值2的高8位
		request[16] = (byte) (value2 % 256); // 写入值2的低8位
		
		request[17] = (byte) (value3 / 256); // 写入值3的高8位
		request[18] = (byte) (value3 % 256); // 写入值3的低8位
		
		request[19] = (byte) (value4 / 256); // 写入值4的高8位
		request[20] = (byte) (value4 % 256); // 写入值4的低8位
		
		return request;		
	}

	/**
	 * 对数据进行处理，将数据写入到data[]中，以下给出一个写单个寄存器的示例：
	 * 写单个寄存器接收（0x） 0C AA 	00 00 	00 06 	01 		03 		06 00 	03 00 
	 * 					发送序号 	默认 		数据长度 	地址号 	功能码 	输出地址 	接收到的字节
	 * bytes[9]是第10位，bytes[2*i+9]可以取到第10位、12位…… bytes[2*i+9] & 0xff) *256 的意思是： 
	 * 取（得到的响应报文中的）寄存器高8位（保持符号位不变，通过&符号实现） 并左移8位（通过*256实现）
	 * **************************************************************
	 * 将套接字响应到的字节数组解析后存到data数组中 因为java语言中没有无符号数，所以int类型的数取值范围是-32768~32767
	 * 但是由两个无符号（通过&0xff实现）的byte组成一个int后，得到的是一个无符号的数
	 * 所以需要判断，如果大于32767，那么，减去65536即可得到正确的值
	 */
	@Override
	public void setResponse(byte[] bytes) {
		// 获得响应，判断响应是否合法
		if (bytes[7] == 0x10) {
			response = new byte[6];
		}else if(bytes[7] == 0x90){
			System.out.println("出现异常功能码0x90，代码"+bytes[8]);
		}
		this.response = bytes;
		
		data[0] = 0;
	}
}
