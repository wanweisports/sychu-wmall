package com.SunToolKit;

public class WriteAnyoneBit {

	/**
	 * 此方法实现了将读取到的一个字中某一位置位true
	 * 
	 * @param Int1
	 *            要进行写操作的原始数据
	 * @param WhichBit
	 *            第几位置位true
	 * @return
	 */
	public static int WriteAnyoneBitTrue(int Int1, int WhichBit) {
		int intResult = 0;
		switch (WhichBit) {
		case 0:
			intResult = (Int1 & 0xffff) | (0x0001);// 第0位
			return intResult;
		case 1:
			intResult = (Int1 & 0xffff) | (0x0002);// 第1位
			return intResult;
		case 2:
			intResult = (Int1 & 0xffff) | (0x0004);// 第2位
			return intResult;
		case 3:
			intResult = (Int1 & 0xffff) | (0x0008);// 第3位
			return intResult;
		case 4:
			intResult = (Int1 & 0xffff) | (0x0010);// 第4位
			return intResult;
		case 5:
			intResult = (Int1 & 0xffff) | (0x0020);// 第5位
			return intResult;
		case 6:
			intResult = (Int1 & 0xffff) | (0x0040);// 第6位
			return intResult;
		case 7:
			intResult = (Int1 & 0xffff) | (0x0080);// 第7位
			return intResult;
		case 8:
			intResult = (Int1 & 0xffff) | (0x0100);// 第8位
			return intResult;
		case 9:
			intResult = (Int1 & 0xffff) | (0x0200);// 第9位
			return intResult;
		case 10:
			intResult = (Int1 & 0xffff) | (0x0400);// 第10位
			return intResult;
		case 11:
			intResult = (Int1 & 0xffff) | (0x0800);// 第11位
			return intResult;
		case 12:
			intResult = (Int1 & 0xffff) | (0x1000);// 第12位
			return intResult;
		case 13:
			intResult = (Int1 & 0xffff) | (0x2000);// 第13位
			return intResult;
		case 14:
			intResult = (Int1 & 0xffff) | (0x4000);// 第14位
			return intResult;
		case 15:
			intResult = (Int1 & 0xffff) | (0x8000);// 第15位
			return intResult;
		default:
			return 0x0000;

		}
	}

	public static int WriteAnyoneBitFalse(int Int1, int WhichBit) {
		int intResult = 0;
		switch (WhichBit) {
		case 0:
			intResult = (Int1 & 0xffff) & (0xfffe);// 第0位
			return intResult;
		case 1:
			intResult = (Int1 & 0xffff) & (0xfffd);// 第1位
			return intResult;
		case 2:
			intResult = (Int1 & 0xffff) & (0xfffb);// 第2位
			return intResult;
		case 3:
			intResult = (Int1 & 0xffff) & (0xfff7);// 第3位
			return intResult;
		case 4:
			intResult = (Int1 & 0xffff) & (0xffef);// 第4位
			return intResult;
		case 5:
			intResult = (Int1 & 0xffff) & (0xffdf);// 第5位
			return intResult;
		case 6:
			intResult = (Int1 & 0xffff) & (0xffbf);// 第6位
			return intResult;
		case 7:
			intResult = (Int1 & 0xffff) & (0xff7f);// 第7位
			return intResult;
		case 8:
			intResult = (Int1 & 0xffff) & (0xfeff);// 第8位
			return intResult;
		case 9:
			intResult = (Int1 & 0xffff) & (0xfdff);// 第9位
			return intResult;
		case 10:
			intResult = (Int1 & 0xffff) & (0xfbff);// 第10位
			return intResult;
		case 11:
			intResult = (Int1 & 0xffff) & (0xf7ff);// 第11位
			return intResult;
		case 12:
			intResult = (Int1 & 0xffff) & (0xefff);// 第12位
			return intResult;
		case 13:
			intResult = (Int1 & 0xffff) & (0xdfff);// 第13位
			return intResult;
		case 14:
			intResult = (Int1 & 0xffff) & (0xbfff);// 第14位
			return intResult;
		case 15:
			intResult = (Int1 & 0xffff) & (0x7fff);// 第15位
			return intResult;
		default:
			return 0x0000;
		}
	}
}
