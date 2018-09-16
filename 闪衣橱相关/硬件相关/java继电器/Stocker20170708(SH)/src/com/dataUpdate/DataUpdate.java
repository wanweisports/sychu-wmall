package com.dataUpdate;

import com.Information.StockerInfo;
import com.SunToolKit.DifferTypeDataSwitch;
import com.modbus.Modbus;


public class DataUpdate extends Thread{
	
	private Modbus modbus=null;
	private StockerInfo StockerInfo=null;
	/**
	 * 数据更新类
	 * @param stockerNo 堆垛机号
	 * @param modbus 此堆垛机的modbus连接
	 */
	public DataUpdate(int stockerNo,Modbus mod){
		StockerInfo=new StockerInfo();
		StockerInfo.setStockerNO(stockerNo);
		this.modbus=mod;
	}
	public void connect(){

		this.start();
	}
	
	public StockerInfo getStockerInfo()
	{
		return StockerInfo;
	}
	
	public void run(){
		while (true){
			StockerInfo.setNetworkDisconnected(modbus.isNetworkDisconnected());
			
			//330---25
			//当前作业号MD330			
			StockerInfo.setCurrentWorkNO(DifferTypeDataSwitch.IntToDint(modbus.getDatablocks().get(2).data[0], modbus.getDatablocks().get(2).data[1]));
			//当前作业类型MW332
			StockerInfo.setCurrentWorkType(modbus.getDatablocks().get(2).data[2]);
			//当前作业标志位MW333
			StockerInfo.setCurrentWorkFlag(modbus.getDatablocks().get(2).data[3]);			
			//作业托盘号MD334
			StockerInfo.setCurrentWorkPalletNO(DifferTypeDataSwitch.IntToDint(modbus.getDatablocks().get(2).data[4], modbus.getDatablocks().get(2).data[5]));
			//作业起始地址（站台）MW336
			StockerInfo.setCurrentWorkStartAddressStation(modbus.getDatablocks().get(2).data[6]);
			//作业起始地址（排）MW337
			StockerInfo.setCurrentWorkStartAddressPai(modbus.getDatablocks().get(2).data[7]);
			//作业起始地址（列）MW338
			StockerInfo.setCurrentWorkStartAddressLie(modbus.getDatablocks().get(2).data[8]);
			//作业起始地址（层）MW339
			StockerInfo.setCurrentWorkStartAddressCeng(modbus.getDatablocks().get(2).data[9]);
			//作业目标地址（站台）MW340
			StockerInfo.setCurrentWorkTargetAddressStation(modbus.getDatablocks().get(2).data[10]);
			//作业目标地址（排）MW341
			StockerInfo.setCurrentWorkTargetAddressPai(modbus.getDatablocks().get(2).data[11]);
			//作业目标地址（列）MW342
			StockerInfo.setCurrentWorkTargetAddressLie(modbus.getDatablocks().get(2).data[12]);
			//作业目标地址（层）MW343
			StockerInfo.setCurrentWorkTargetAddressCeng(modbus.getDatablocks().get(2).data[13]);
			//实际托盘号MD344
			StockerInfo.setCurrentWorkActPalletNO(DifferTypeDataSwitch.IntToDint(modbus.getDatablocks().get(2).data[14], modbus.getDatablocks().get(2).data[15]));
			//作业属性MW346
			StockerInfo.setCurrentWorkProperty(modbus.getDatablocks().get(2).data[16]);
			//MW347-MW348空闲
			
			//运行状况	%MW349
			StockerInfo.setStatus(modbus.getDatablocks().get(2).data[19]);
			//操作模式	%MW350
			StockerInfo.setOperationMode(modbus.getDatablocks().get(2).data[20]);
			//故障代码	%MW351
			StockerInfo.setErrorCode(modbus.getDatablocks().get(2).data[21]);
			//层		%MW352
			StockerInfo.setFloors(modbus.getDatablocks().get(2).data[22]);
			//排		%MW353
			StockerInfo.setRank(modbus.getDatablocks().get(2).data[23]);
			//列		%MW354
			StockerInfo.setRow(modbus.getDatablocks().get(2).data[24]);
			
			//300---10
		    //堆垛机水平伺服实际位置	%MD150:DINT;
			StockerInfo.setxServoValue(DifferTypeDataSwitch.IntToDint(modbus.getDatablocks().get(1).data[0], modbus.getDatablocks().get(1).data[1]));
		    //堆垛机激光实际位置		%MD151:DINT;
			StockerInfo.setxLaserValue((modbus.getDatablocks().get(1).data[2]& 0xffff)  + (modbus.getDatablocks().get(1).data[3]& 0xffff)* 65536 );
		    //堆垛机垂直伺服实际位置	%MD152:DINT;
			StockerInfo.setzServoValue((modbus.getDatablocks().get(1).data[4]& 0xffff)  + (modbus.getDatablocks().get(1).data[5]& 0xffff)* 65536 );
		    //堆垛机垂直激光实际位置	%MD153:DINT;
			StockerInfo.setzLaserValue((modbus.getDatablocks().get(1).data[6]& 0xffff)  + (modbus.getDatablocks().get(1).data[7]& 0xffff)* 65536 );
		    //货叉的实际位置		%MD154:DINT;	
			StockerInfo.setyServoValue((modbus.getDatablocks().get(1).data[8]& 0xffff)  + (modbus.getDatablocks().get(1).data[9]& 0xffff)* 65536 );

			//MW10--13
			//正在取货
			StockerInfo.setPuttingInCargo(DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[0], 2));
			//正在放货
			StockerInfo.setTakingOffCargo(DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[0], 3));
			//水平停准位
			StockerInfo.setxStopOK(DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[0], 10));
			//System.out.println("停准位="+DisposeDifTypeData.IntToBool(modbus.getDatablocks().get(0).data[0], 10));
			//水平向前中
			StockerInfo.setxForwardMoving((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[1], 12)));
			//水平向后中
			StockerInfo.setxBackwardMoving((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[1], 13)));
			//轿厢向上中
			StockerInfo.setzUpstairMoving((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[2], 12)));
			//轿厢向下中
			StockerInfo.setzDownstairMoving((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[2], 13)));			

			//货叉向左收
			StockerInfo.setyDrawBackToLeftMoving((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[3], 7)));
			//货叉向右收
			StockerInfo.setyDrawBackToRightMoving((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[3], 8)));
			//货叉向左伸
			StockerInfo.setyStretchOutToLeftMoving((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[3], 9)));
			//货叉向右伸
			StockerInfo.setyStretchOutToRightMoving((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[3], 10)));

			//货叉在左远位
			StockerInfo.setyIsOnLeftFar((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[3], 11)));
			//货叉在左近位
			StockerInfo.setyIsOnLeftNear((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[3], 12)));
			//货叉在中位
			StockerInfo.setyIsOnMiddle((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(0).data[3], 13)));
			//货叉在右近位
			StockerInfo.setyIsOnRightNear((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(2).data[3], 14)));
			//货叉在右远位
			StockerInfo.setyIsOnRightFar((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(2).data[3], 15)));

			//MW1007----7
			//垂直高位
			StockerInfo.setzIsOnHighPosition((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[6], 2)));
			//垂直低位
			StockerInfo.setzIsOnLowPosition((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[6], 3)));
			//抽屉
			StockerInfo.setDrawerOpened((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[0], 15)));
			//水平前限位
			StockerInfo.setxLimitForward((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[1], 0)));
			//水平后限位
			StockerInfo.setxLimitBackward((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[1], 1)));
			//垂直上限位
			StockerInfo.setzLimitHigh((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[1], 2)));
			//垂直下限位
			StockerInfo.setzLimitLow((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[1], 3)));

			//左限宽1
			StockerInfo.setLimitSwitchWidthLeft1((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[2], 0)));
			//左限宽2
			StockerInfo.setLimitSwitchWidthLeft2((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[2], 1)));
			//右限宽1
			StockerInfo.setLimitSwitchWidthRight1((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[2], 2)));
			//右限宽2
			StockerInfo.setLimitSwitchWidthRight2((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[2], 3)));
			//左近探低位
			StockerInfo.setLimitSwitchNearLeftLow((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[2], 6)));
			//左远探
			StockerInfo.setLimitSwitchFarLeft((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[2], 7)));
			//右近探低位
			StockerInfo.setLimitSwitchNearRightLow((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[2], 8)));
			//右远探
			StockerInfo.setLimitSwitchFarRight((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[2], 9)));			
			//有无货
			StockerInfo.setCarHasCargo((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[2], 10)));		
			//左近探高
			StockerInfo.setLimitSwitchNearLeftHigh((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[2], 13)));		
			//右近探高
			StockerInfo.setLimitSwitchNearRightHigh((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[2], 14)));
			
			//绿灯指示
			StockerInfo.setLightGreen((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[7], 0)));
			//红灯指示
			StockerInfo.setLightRed((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[7], 1)));
			//黄灯指示
			StockerInfo.setLightYellow((DifferTypeDataSwitch.IntToBool(modbus.getDatablocks().get(4).data[7], 2)));
			
			//357---9
			//水平下部速度
			StockerInfo.setxSpeedDown(modbus.getDatablocks().get(3).data[0]);
			//垂直速度
			StockerInfo.setzSpeed(modbus.getDatablocks().get(3).data[1]);
			//货叉速度
			StockerInfo.setySpeed(modbus.getDatablocks().get(3).data[2]);
			//倾角仪
			StockerInfo.setInclinometerValue(modbus.getDatablocks().get(3).data[3]);
			//PLC058当前负载
			StockerInfo.setPLCOverLoad(modbus.getDatablocks().get(3).data[5]);
			//重载补偿
			StockerInfo.setOverLoadOffset(modbus.getDatablocks().get(3).data[6]);
			//水平上部速度
			StockerInfo.setxSpeedUp(modbus.getDatablocks().get(3).data[8]);	
			

//			System.out.println("MW30:X4="+modbus.getDatablocks().get(5).data[0]);
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}
	}
}
