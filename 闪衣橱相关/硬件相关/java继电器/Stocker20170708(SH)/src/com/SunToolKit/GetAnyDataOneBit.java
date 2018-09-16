package com.SunToolKit;

public class GetAnyDataOneBit {

	/**
	 * 此方法实现了读取到一个字中某一位
	 * 
	 * @param Int1
	 *            要进行读取的数据
	 * @param WhichBit
	 *            要读取第几位
	 * @return
	 */
	public static boolean GetOneBit(int Int1, int WhichBit) {
		int intResult = 0;
		switch (WhichBit) {
		case 0:
			intResult = Int1 & 0x0001;// 第0位
			if (intResult==0x0001){
				return true;				
			}else{
				return false;				
			}
		case 1:
			intResult = Int1 & 0x0002;// 第1位
			if (intResult==0x0002){
				return true;				
			}else{
				return false;				
			}
		case 2:
			intResult = Int1 & 0x0004;// 第2位
			if (intResult==0x0004){
				return true;				
			}else{
				return false;				
			}
		case 3:
			intResult = Int1 & 0x0008;// 第3位
			if (intResult==0x0008){
				return true;				
			}else{
				return false;				
			}
		case 4:
			intResult = Int1 & 0x0010;// 第4位
			if (intResult==0x0010){
				return true;				
			}else{
				return false;				
			}
		case 5:
			intResult = Int1 & 0x0020;// 第5位
			if (intResult==0x0020){
				return true;				
			}else{
				return false;				
			}
		case 6:
			intResult = Int1 & 0x0040;// 第6位
			if (intResult==0x0040){
				return true;				
			}else{
				return false;				
			}
		case 7:
			intResult = Int1 & 0x0080;// 第7位
			if (intResult==0x0080){
				return true;				
			}else{
				return false;				
			}
		case 8:
			intResult = Int1 & 0x0100;// 第8位
			if (intResult==0x0100){
				return true;				
			}else{
				return false;				
			}
		case 9:
			intResult = Int1 & 0x0200;// 第9位
			if (intResult==0x0200){
				return true;				
			}else{
				return false;				
			}
		case 10:
			intResult = Int1 & 0x0400;// 第10位
			if (intResult==0x0400){
				return true;				
			}else{
				return false;				
			}
		case 11:
			intResult = Int1 & 0x0800;// 第11位
			if (intResult==0x0800){
				return true;				
			}else{
				return false;				
			}
		case 12:
			intResult = Int1 & 0x1000;// 第12位
			if (intResult==0x1000){
				return true;				
			}else{
				return false;				
			}
		case 13:
			intResult = Int1 & 0x2000;// 第13位
			if (intResult==0x2000){
				return true;				
			}else{
				return false;				
			}
		case 14:
			intResult = Int1 & 0x4000;// 第14位
			if (intResult==0x4000){
				return true;				
			}else{
				return false;				
			}
		case 15:
			intResult = Int1 & 0x8000;// 第15位
			if (intResult==0x8000){
				return true;				
			}else{
				return false;				
			}
		default:
			return false;
		}
	}
}
