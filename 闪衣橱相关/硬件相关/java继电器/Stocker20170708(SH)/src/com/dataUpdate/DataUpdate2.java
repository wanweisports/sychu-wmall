package com.dataUpdate;

import com.Information.RotateInfo;
import com.SunToolKit.DifferTypeDataSwitch;
import com.modbus.Modbus2;


public class DataUpdate2 extends Thread{
	
	private Modbus2 modbus2=null;
	private RotateInfo RotateInfo=null;

	private int temp0;
	private int temp1;
	private int temp2;
	private int temp3;
	private int temp4;
	private int temp5;
	private int temp6;
	private int temp7;

	public DataUpdate2(Modbus2 mod2){
		RotateInfo=new RotateInfo();
		this.modbus2=mod2;
	}
	public void connect(){
		this.start();
	}
	
	public RotateInfo getRotateInfo()
	{
		return RotateInfo;
	}
	
	public void run(){
		while (true){
			RotateInfo.setNetworkDisconnected(modbus2.isNetworkDisconnected());
			
			//推拉门位置	%MW100
			temp0=modbus2.getDatablocks().get(0).data[0];
			temp1=modbus2.getDatablocks().get(0).data[1];			
			RotateInfo.setPushDoorPosition(DifferTypeDataSwitch.IntToDint(temp0, temp1));
			//推拉门速度	%MW102
			temp2=modbus2.getDatablocks().get(0).data[2];
			temp3=modbus2.getDatablocks().get(0).data[3];
			RotateInfo.setPushDoorSpeed(DifferTypeDataSwitch.IntToDint(temp2, temp3));
			//旋转台位置	%MW104
			temp4=modbus2.getDatablocks().get(0).data[4];
			temp5=modbus2.getDatablocks().get(0).data[5];			
			RotateInfo.setRotateStationPosition(DifferTypeDataSwitch.IntToDint(temp4, temp5));			
			//旋转台速度	%MW106
			temp6=modbus2.getDatablocks().get(0).data[6];
			temp7=modbus2.getDatablocks().get(0).data[7];
			RotateInfo.setRotateStationSpeed(DifferTypeDataSwitch.IntToDint(temp6, temp7));
			
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}
	}
}
