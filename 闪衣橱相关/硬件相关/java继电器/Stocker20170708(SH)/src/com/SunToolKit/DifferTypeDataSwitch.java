package com.SunToolKit;

import java.util.Calendar;



public class DifferTypeDataSwitch {
	

	
	
	/**
	 * 将两个INT类型的值，转换成DINT类型的值
	 */
	public static int IntToDint(int Int1 ,int Int2){
		int intResult;
		intResult=(Int1 & 0xffff) + (Int2* 65536 );
		return intResult;
	}
	
	/**
	 * 取出一个INT类型的值的任意一位Bit
	 */
	public static Boolean IntToBool(int Int1,int WhichBit){	
		int Int2=Int1 &0xffff;
		String[] array =new String[32];
		for (int i=0;i<32;i++){
			array[i]=Integer.toBinaryString(Int2 & 0x0001);
			Int2 =(int)(Int2 >> 1);
		}
		if (array[WhichBit].equals("1")){
			return true;
		}else{
			return false;			
		}		
	}
	
	/**
	 * 获取系统时间
	 */
	public static String getSystemTime(){
		Calendar ca=Calendar.getInstance();
		int year=ca.get(Calendar.YEAR);
		int month=ca.get(Calendar.MONTH);
		int day=ca.get(Calendar.DATE);
		int hour=ca.get(Calendar.HOUR);
		int minute=ca.get(Calendar.MINUTE);
		int second=ca.get(Calendar.SECOND);		
		String systemTime=year+"年"+(month+1)+"月"+day+"日"+hour+"时"+minute+"分"+second+"秒";
		return systemTime;		
	}

}
