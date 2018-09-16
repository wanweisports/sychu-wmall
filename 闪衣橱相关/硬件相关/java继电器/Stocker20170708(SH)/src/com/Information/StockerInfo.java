package com.Information;

public class StockerInfo {

	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * 获得运行状态 * @return
	 */
	public String getStatus() {
		switch (status) {
		case 0:
			return "None";
		case 1:
			return "未启动";
		case 2:
			return "待命中";
		case 3:
			return "移向取货地址";
		case 4:
			return "等待取货";
		case 5:
			return "取货伸叉中";
		case 6:
			return "取货上移动中";
		case 7:
			return "取货收叉中";
		case 8:
			return "检查RFID中";
		case 9:
			return "移向卸货地址";
		case 10:
			return "等待卸货";
		case 11:
			return "卸货伸叉中";
		case 12:
			return "卸货下降中";
		case 13:
			return "卸货收叉中";
		case 14:
			return "水平自动回零中";
		case 15:
			return "垂直自动回零中";
		case 16:
			return "自苏醒中";
		default:
			return String.valueOf(status);
		}
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 获得故障代码 * @return
	 */
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 获得堆垛机号 * @return
	 */
	public int getStockerNO() {
		return stockerNO;
	}

	public void setStockerNO(int stockerNO) {
		this.stockerNO = stockerNO;
	}

	/**
	 * 获得操作模式 * @return
	 */
	public String getOperationMode() {
		switch (operationMode) {

		case 0:
			return "无模式";
		case 1:
			return "WMS自动";
		case 2:
			return "SCADA自动";
		case 3:
			return "SCADA手动";
		case 4:
			return "HMI自动";
		case 5:
			return "本地手动";
		case 6:
			return "手动维修";
		default:
			return "";
		}
	}

	public void setOperationMode(int operationMode) {
		this.operationMode = operationMode;
	}

	/**
	 * 获得故障信息 * @return
	 */
	public String getErrorInfo() {
		switch (errorCode) {

		case 0:
			return "无故障";
		case 101:
			return "货叉轴故障";
		case 102:
			return "限宽";
		case 103:
			return "限高";
		case 105:
			return "货叉力矩过大";
		case 106:
			return "货叉中位错误";
		case 107:
			return "货叉掉线";
		case 108:
			return "垂直轴故障";
		case 109:
			return "水平轴故障";
		case 110:
			return "货叉不同步";
		case 111:
			return "急停";
		case 112:
			return "货叉未在中心";
		case 113:
			return "取货站台异常";
		case 114:
			return "卸货站台异常";
		case 115:
			return "RFID读取失败";
		case 116:
			return "水平激光错误";
		case 117:
			return "垂直激光错误";
		case 118:
			return "取货轿厢有货";
		case 119:
			return "卸货轿厢无货";
		case 120:
			return "左近探无货";
		case 121:
			return "右近探无货";
		case 122:
			return "左近探有货";
		case 123:
			return "右近探有货";
		case 124:
			return "左远探有货";
		case 125:
			return "右远探有货";
		case 126:
			return "取货失败";
		case 127:
			return "卸货失败";
		case 128:
			return "托盘号错误";
		case 129:
			return "手动作业错误";
		case 132:
			return "自动作业错误";
		case 133:
			return "取货左近探有货";
		case 134:
			return "取货右近端有货";
		case 135:
			return "取货运行故障";
		case 136:
			return "取货上升故障";
		case 137:
			return "卸货运行故障";
		case 138:
			return "卸货下降故障";
		case 139:
			return "已人工卸货";
		case 140:
			return "左近探光电损坏";
		case 141:
			return "左远探光电损坏";
		case 142:
			return "右近探光电损坏";
		case 143:
			return "右远探光电损坏";
		case 144:
			return "轿厢有货光电损坏";
		case 145:
			return "水平伺服扭矩过大";
		case 146:
			return "垂直伺服扭矩过大";
		case 147:
			return "水平前限位";
		case 148:
			return "水平后限位";
		case 149:
			return "垂直上限位";
		case 150:
			return "垂直下限位";
		case 151:
			return "水平回零中断";
		case 152:
			return "垂直回零中断";
		case 153:
			return "货叉回零故障";
		case 154:
			return "货叉故障";
		case 155:
			return "面板旋钮位置异常";
		case 156:
			return "系统断电报警";
		case 157:
			return "水平伺服掉轴";
		case 158:
			return "垂直伺服掉轴";
		case 159:
			return "水平激光掉轴";
		case 160:
			return "垂直激光掉轴";
		case 161:
			return "倾角仪掉线";
		case 162:
			return "倾角仪故障";
		case 163:
			return "堆垛机倾斜";
		case 164:
			return "水平激光速度错误";
		case 165:
			return "刹车继电器故障";
		case 166:
			return "操作平台没有关闭";
		case 167:
			return "操作模式错误";
		case 168:
			return "作业异常完成";
		case 169:
			return "堆垛机前方有障碍";
		case 170:
			return "堆垛机后方有障碍";
		case 171:
			return "作业高度属性异常";
		case 172:
			return "操作类型错误";
		case 173:
			return "作业号错误";
		case 174:
			return "取货位置错误";
		case 175:
			return "卸货位置错误";
		case 176:
			return "高度属性错误";
		case 177:
			return "上部行走电机异常";
		case 253:
			return "堆垛机掉线";
		case 254:
			return "系统报警";
		case 255:
			return "系统故障";
		default:
			return String.valueOf(errorCode);
		}
	}

	public int getCurrentWorkNO() {
		return currentWorkNO;
	}

	public void setCurrentWorkNO(int currentWorkNO) {
		this.currentWorkNO = currentWorkNO;
	}

	public String getCurrentWorkType() {
		switch (currentWorkType) {

		case 0:
			return "无作业";
		case 1:
			return "WMS入库";
		case 2:
			return "WMS出库";
		case 3:
			return "WMS移库";
		case 5:
			return "WMS站站出库";
		case 11:
			return "SCADA移动";
		case 12:
			return "SCADA定点取货";
		case 13:
			return "SCADA定点放货";
		case 14:
			return "SCADA入库";
		case 15:
			return "SCADA出库";
		case 16:
			return "SCADA移库";
		case 17:
			return "SCADA站站出库";
		default:
			return String.valueOf(currentWorkType);
		}
	}

	public void setCurrentWorkType(int currentWorkType) {
		this.currentWorkType = currentWorkType;
	}

	public int getCurrentWorkFlag() {
		return currentWorkFlag;
	}

	public void setCurrentWorkFlag(int currentWorkFlag) {
		this.currentWorkFlag = currentWorkFlag;
	}

	public int getCurrentWorkPalletNO() {
		return currentWorkPalletNO;
	}

	public void setCurrentWorkPalletNO(int currentWorkPalletNO) {
		this.currentWorkPalletNO = currentWorkPalletNO;
	}

	public int getCurrentWorkStartAddressStation() {
		return currentWorkStartAddressStation;
	}

	public void setCurrentWorkStartAddressStation(
			int currentWorkStartAddressStation) {
		this.currentWorkStartAddressStation = currentWorkStartAddressStation;
	}

	public int getCurrentWorkStartAddressPai() {
		return currentWorkStartAddressPai;
	}

	public void setCurrentWorkStartAddressPai(int currentWorkStartAddressPai) {
		this.currentWorkStartAddressPai = currentWorkStartAddressPai;
	}

	public int getCurrentWorkStartAddressLie() {
		return currentWorkStartAddressLie;
	}

	public void setCurrentWorkStartAddressLie(int currentWorkStartAddressLie) {
		this.currentWorkStartAddressLie = currentWorkStartAddressLie;
	}

	public int getCurrentWorkStartAddressCeng() {
		return currentWorkStartAddressCeng;
	}

	public void setCurrentWorkStartAddressCeng(int currentWorkStartAddressCeng) {
		this.currentWorkStartAddressCeng = currentWorkStartAddressCeng;
	}

	public int getCurrentWorkTargetAddressStation() {
		return currentWorkTargetAddressStation;
	}

	public void setCurrentWorkTargetAddressStation(
			int currentWorkTargetAddressStation) {
		this.currentWorkTargetAddressStation = currentWorkTargetAddressStation;
	}

	public int getCurrentWorkTargetAddressPai() {
		return currentWorkTargetAddressPai;
	}

	public void setCurrentWorkTargetAddressPai(int currentWorkTargetAddressPai) {
		this.currentWorkTargetAddressPai = currentWorkTargetAddressPai;
	}

	public int getCurrentWorkTargetAddressLie() {
		return currentWorkTargetAddressLie;
	}

	public void setCurrentWorkTargetAddressLie(int currentWorkTargetAddressLie) {
		this.currentWorkTargetAddressLie = currentWorkTargetAddressLie;
	}

	public int getCurrentWorkTargetAddressCeng() {
		return currentWorkTargetAddressCeng;
	}

	public void setCurrentWorkTargetAddressCeng(int currentWorkTargetAddressCeng) {
		this.currentWorkTargetAddressCeng = currentWorkTargetAddressCeng;
	}

	public int getCurrentWorkActPalletNO() {
		return currentWorkActPalletNO;
	}

	public void setCurrentWorkActPalletNO(int currentWorkActPalletNO) {
		this.currentWorkActPalletNO = currentWorkActPalletNO;
	}

	public int getCurrentWorkProperty() {
		return currentWorkProperty;
	}

	public void setCurrentWorkProperty(int currentWorkProperty) {
		this.currentWorkProperty = currentWorkProperty;
	}

	// 堆垛机号(固定构造)
	private int stockerNO = 0;
	// 当前作业号MD330
	private int currentWorkNO = 0;
	// 当前作业类型MW332
	private int currentWorkType = 0;
	// 当前作业类型MW333
	private int currentWorkFlag = 0;
	// 作业托盘号MD334
	private int currentWorkPalletNO = 0;
	// 作业起始地址（站台）MW336
	private int currentWorkStartAddressStation = 0;
	// 作业起始地址（排）MW337
	private int currentWorkStartAddressPai = 0;
	// 作业起始地址（列）MW338
	private int currentWorkStartAddressLie = 0;
	// 作业起始地址（层）MW339
	private int currentWorkStartAddressCeng = 0;
	// 作业目标地址（站台）MW340
	private int currentWorkTargetAddressStation = 0;
	// 作业目标地址（排）MW341
	private int currentWorkTargetAddressPai = 0;
	// 作业目标地址（列）MW342
	private int currentWorkTargetAddressLie = 0;
	// 作业目标地址（层）MW343
	private int currentWorkTargetAddressCeng = 0;
	// 实际托盘号MD344
	private int currentWorkActPalletNO = 0;
	// 作业属性MW346
	private int currentWorkProperty = 0;

	// MW347-MW348空闲

	// 运行状况%MW349
	private int status = 0;
	// 操作模式%MW350
	private int operationMode = 0;
	// 堆垛机的故障代码显示%MW351
	private int errorCode = 0;
	// 堆垛机的层显示%MW352
	private int floors = 0;
	// 堆垛机的排显示%MW353
	private int rank = 0;
	// 堆垛机的列显示%MW354
	private int row = 0;

	public int getxServoValue() {
		return xServoValue;
	}

	public void setxServoValue(int xServoValue) {
		this.xServoValue = xServoValue;
	}

	public int getxLaserValue() {
		return xLaserValue;
	}

	public void setxLaserValue(int xLaserValue) {
		this.xLaserValue = xLaserValue;
	}

	public int getzServoValue() {
		return zServoValue;
	}

	public void setzServoValue(int zServoValue) {
		this.zServoValue = zServoValue;
	}

	public int getzLaserValue() {
		return zLaserValue;
	}

	public void setzLaserValue(int zLaserValue) {
		this.zLaserValue = zLaserValue;
	}

	public int getyServoValue() {
		return yServoValue;
	}

	public void setyServoValue(int yServoValue) {
		this.yServoValue = yServoValue;
	}

	// 堆垛机水平伺服实际位置 %MD150:DINT;
	private int xServoValue = 0;
	// 堆垛机激光实际位置 %MD151:DINT;
	private int xLaserValue = 0;
	// 堆垛机垂直伺服实际位置 %MD152:DINT;
	private int zServoValue = 0;
	// 堆垛机垂直激光实际位置 %MD153:DINT;
	private int zLaserValue = 0;
	// 货叉的实际位置 %MD154:DINT;
	private int yServoValue = 0;

	// 从MW10开始，读4个字
	public int getMW10() {
		return MW10;
	}

	public void setMW10(int mW10) {
		MW10 = mW10;
	}

	public int getMW11() {
		return MW11;
	}

	public void setMW11(int mW11) {
		MW11 = mW11;
	}

	public int getMW12() {
		return MW12;
	}

	public void setMW12(int mW12) {
		MW12 = mW12;
	}

	public int getMW13() {
		return MW13;
	}

	public void setMW13(int mW13) {
		MW13 = mW13;
	}

	// 从MW10开始，读4个字
	private int MW10 = 0;
	private int MW11 = 0;
	private int MW12 = 0;
	private int MW13 = 0;

	public boolean isPuttingInCargo() {
		return puttingInCargo;
	}

	public void setPuttingInCargo(boolean puttingInCargo) {
		this.puttingInCargo = puttingInCargo;
	}

	public boolean isTakingOffCargo() {
		return takingOffCargo;
	}

	public void setTakingOffCargo(boolean takingOffCargo) {
		this.takingOffCargo = takingOffCargo;
	}

	public boolean isxStopOK() {
		return xStopOK;
	}

	public void setxStopOK(boolean xStopOK) {
		this.xStopOK = xStopOK;
	}

	public boolean isxForwardMoving() {
		return xForwardMoving;
	}

	public void setxForwardMoving(boolean xForwardMoving) {
		this.xForwardMoving = xForwardMoving;
	}

	public boolean isxBackwardMoving() {
		return xBackwardMoving;
	}

	public void setxBackwardMoving(boolean xBackwardMoving) {
		this.xBackwardMoving = xBackwardMoving;
	}

	public boolean iszUpstairMoving() {
		return zUpstairMoving;
	}

	public void setzUpstairMoving(boolean zUpstairMoving) {
		this.zUpstairMoving = zUpstairMoving;
	}

	public boolean iszDownstairMoving() {
		return zDownstairMoving;
	}

	public void setzDownstairMoving(boolean zDownstairMoving) {
		this.zDownstairMoving = zDownstairMoving;
	}

	public boolean isyDrawBackToLeftMoving() {
		return yDrawBackToLeftMoving;
	}

	public void setyDrawBackToLeftMoving(boolean yDrawBackToLeftMoving) {
		this.yDrawBackToLeftMoving = yDrawBackToLeftMoving;
	}

	public boolean isyDrawBackToRightMoving() {
		return yDrawBackToRightMoving;
	}

	public void setyDrawBackToRightMoving(boolean yDrawBackToRightMoving) {
		this.yDrawBackToRightMoving = yDrawBackToRightMoving;
	}

	public boolean isyStretchOutToLeftMoving() {
		return yStretchOutToLeftMoving;
	}

	public void setyStretchOutToLeftMoving(boolean yStretchOutToLeftMoving) {
		this.yStretchOutToLeftMoving = yStretchOutToLeftMoving;
	}

	public boolean isyStretchOutToRightMoving() {
		return yStretchOutToRightMoving;
	}

	public void setyStretchOutToRightMoving(boolean yStretchOutToRightMoving) {
		this.yStretchOutToRightMoving = yStretchOutToRightMoving;
	}

	public boolean isyIsOnLeftFar() {
		return yIsOnLeftFar;
	}

	public void setyIsOnLeftFar(boolean yIsOnLeftFar) {
		this.yIsOnLeftFar = yIsOnLeftFar;
	}

	public boolean isyIsOnLeftNear() {
		return yIsOnLeftNear;
	}

	public void setyIsOnLeftNear(boolean yIsOnLeftNear) {
		this.yIsOnLeftNear = yIsOnLeftNear;
	}

	public boolean isyIsOnMiddle() {
		return yIsOnMiddle;
	}

	public void setyIsOnMiddle(boolean yIsOnMiddle) {
		this.yIsOnMiddle = yIsOnMiddle;
	}

	public boolean isyIsOnRightNear() {
		return yIsOnRightNear;
	}

	public void setyIsOnRightNear(boolean yIsOnRightNear) {
		this.yIsOnRightNear = yIsOnRightNear;
	}

	public boolean isyIsOnRightFar() {
		return yIsOnRightFar;
	}

	public void setyIsOnRightFar(boolean yIsOnRightFar) {
		this.yIsOnRightFar = yIsOnRightFar;
	}

	private boolean puttingInCargo = false; // 堆垛机正在取货 %MX20.2:BOOL;
	private boolean takingOffCargo = false; // 堆垛机正在放货 %MX20.3:BOOL;
	private boolean xStopOK = false; // 堆垛机水平停准位%MX20.10:BOOL;
	private boolean xForwardMoving = false; // 水平向前移动中%MX22.12:BOOL;
	private boolean xBackwardMoving = false; // 水平向后移动中%MX22.13:BOOL;
	private boolean zUpstairMoving = false; // 轿厢在上升中%MX24.12:BOOL;
	private boolean zDownstairMoving = false; // 轿厢在下降中%MX24.13:BOOL;

	private boolean yDrawBackToLeftMoving = false; // 货叉正在向左收 %MX26.7:BOOL;
	private boolean yDrawBackToRightMoving = false; // 货叉正在向右收 %MX26.8:BOOL;
	private boolean yStretchOutToLeftMoving = false; // 货叉正在向左伸 %MX26.9:BOOL;
	private boolean yStretchOutToRightMoving = false; // 货叉正在向右伸 %MX26.10:BOOL;

	private boolean yIsOnLeftFar = false; // 货叉在左远位 %MX26.11:BOOL;
	private boolean yIsOnLeftNear = false; // 货叉在左近位 %MX26.12:BOOL;
	private boolean yIsOnMiddle = false; // 货叉在中位 %MX26.13:BOOL;
	private boolean yIsOnRightNear = false; // 货叉在右近位 %MX26.14:BOOL;
	private boolean yIsOnRightFar = false; // 货叉在右远位 %MX26.15:BOOL;

	public boolean iszIsOnHighPosition() {
		return zIsOnHighPosition;
	}

	public void setzIsOnHighPosition(boolean zIsOnHighPosition) {
		this.zIsOnHighPosition = zIsOnHighPosition;
	}

	public boolean iszIsOnLowPosition() {
		return zIsOnLowPosition;
	}

	public void setzIsOnLowPosition(boolean zIsOnLowPosition) {
		this.zIsOnLowPosition = zIsOnLowPosition;
	}

	public boolean isLightGreen() {
		return lightGreen;
	}

	public void setLightGreen(boolean lightGreen) {
		this.lightGreen = lightGreen;
	}

	public boolean isLightRed() {
		return lightRed;
	}

	public void setLightRed(boolean lightRed) {
		this.lightRed = lightRed;
	}

	public boolean isLightYellow() {
		return lightYellow;
	}

	public void setLightYellow(boolean lightYellow) {
		this.lightYellow = lightYellow;
	}

	public void setDrawerOpened(boolean drawerOpened) {
		this.drawerOpened = drawerOpened;
	}

	public boolean isDrawerOpened() {
		return drawerOpened;
	}

	public boolean isxLimitForward() {
		return xLimitForward;
	}

	public void setxLimitForward(boolean xLimitForward) {
		this.xLimitForward = xLimitForward;
	}

	public boolean isxLimitBackward() {
		return xLimitBackward;
	}

	public void setxLimitBackward(boolean xLimitBackward) {
		this.xLimitBackward = xLimitBackward;
	}

	public boolean iszLimitHigh() {
		return zLimitHigh;
	}

	public void setzLimitHigh(boolean zLimitHigh) {
		this.zLimitHigh = zLimitHigh;
	}

	public boolean iszLimitLow() {
		return zLimitLow;
	}

	public void setzLimitLow(boolean zLimitLow) {
		this.zLimitLow = zLimitLow;
	}

	public boolean isCarHasCargo() {
		return carHasCargo;
	}

	public void setCarHasCargo(boolean carHasCargo) {
		this.carHasCargo = carHasCargo;
	}

	private boolean zIsOnHighPosition = false;// 垂直高位mw1012.2
	private boolean zIsOnLowPosition = false; // 垂直低位mw1012.3

	private boolean lightGreen = false; // 绿mw1013.0
	private boolean lightRed = false; // 红mw1013.1
	private boolean lightYellow = false; // 黄mw1013.2

	private boolean drawerOpened = false; // 抽屉mw1006.15
	private boolean xLimitForward = false; // 水平前限位mw1007.0
	private boolean xLimitBackward = false; // 水平后限位mw1007.1
	private boolean zLimitHigh = false; // 垂直上限位mw1007.2
	private boolean zLimitLow = false; // 垂直下限位mw1007.3

	public boolean isLimitSwitchWidthLeft1() {
		return limitSwitchWidthLeft1;
	}

	public void setLimitSwitchWidthLeft1(boolean limitSwitchWidthLeft1) {
		this.limitSwitchWidthLeft1 = limitSwitchWidthLeft1;
	}

	public boolean isLimitSwitchWidthLeft2() {
		return limitSwitchWidthLeft2;
	}

	public void setLimitSwitchWidthLeft2(boolean limitSwitchWidthLeft2) {
		this.limitSwitchWidthLeft2 = limitSwitchWidthLeft2;
	}

	public boolean isLimitSwitchWidthRight1() {
		return limitSwitchWidthRight1;
	}

	public void setLimitSwitchWidthRight1(boolean limitSwitchWidthRight1) {
		this.limitSwitchWidthRight1 = limitSwitchWidthRight1;
	}

	public boolean isLimitSwitchWidthRight2() {
		return limitSwitchWidthRight2;
	}

	public void setLimitSwitchWidthRight2(boolean limitSwitchWidthRight2) {
		this.limitSwitchWidthRight2 = limitSwitchWidthRight2;
	}

	public boolean isLimitSwitchNearLeftLow() {
		return limitSwitchNearLeftLow;
	}

	public void setLimitSwitchNearLeftLow(boolean limitSwitchNearLeftLow) {
		this.limitSwitchNearLeftLow = limitSwitchNearLeftLow;
	}

	public boolean isLimitSwitchFarLeft() {
		return limitSwitchFarLeft;
	}

	public void setLimitSwitchFarLeft(boolean limitSwitchFarLeft) {
		this.limitSwitchFarLeft = limitSwitchFarLeft;
	}

	public boolean isLimitSwitchNearRightLow() {
		return limitSwitchNearRightLow;
	}

	public void setLimitSwitchNearRightLow(boolean limitSwitchNearRightLow) {
		this.limitSwitchNearRightLow = limitSwitchNearRightLow;
	}

	public boolean isLimitSwitchFarRight() {
		return limitSwitchFarRight;
	}

	public void setLimitSwitchFarRight(boolean limitSwitchFarRight) {
		this.limitSwitchFarRight = limitSwitchFarRight;
	}

	public boolean isLimitSwitchNearLeftHigh() {
		return limitSwitchNearLeftHigh;
	}

	public void setLimitSwitchNearLeftHigh(boolean limitSwitchNearLeftHigh) {
		this.limitSwitchNearLeftHigh = limitSwitchNearLeftHigh;
	}

	public boolean isLimitSwitchNearRightHigh() {
		return limitSwitchNearRightHigh;
	}

	public void setLimitSwitchNearRightHigh(boolean limitSwitchNearRightHigh) {
		this.limitSwitchNearRightHigh = limitSwitchNearRightHigh;
	}

	private boolean limitSwitchWidthLeft1 = false; // 左限宽1 MW1008.0
	private boolean limitSwitchWidthLeft2 = false; // 左限宽2 MW1008.1
	private boolean limitSwitchWidthRight1 = false; // 右限宽1 MW1008.2
	private boolean limitSwitchWidthRight2 = false; // 右限宽2 MW1008.3
	private boolean limitSwitchNearLeftLow = false; // 左近探低 MW1008.6
	private boolean limitSwitchFarLeft = false; // 左远探 MW1008.7
	private boolean limitSwitchNearRightLow = false; // 右近探低 MW1008.8
	private boolean limitSwitchFarRight = false; // 右远探 MW1008.9
	private boolean carHasCargo = false; // 有无货 MW1008.10
	private boolean limitSwitchNearLeftHigh = false; // 左近探高 MW1008.13
	private boolean limitSwitchNearRightHigh = false; // 右近探高 MW1008.14

	public int getxSpeedDown() {
		return xSpeedDown;
	}

	public void setxSpeedDown(int xSpeedDown) {
		this.xSpeedDown = xSpeedDown;
	}

	public int getzSpeed() {
		return zSpeed;
	}

	public void setzSpeed(int zSpeed) {
		this.zSpeed = zSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public int getInclinometerValue() {
		return inclinometerValue;
	}

	public void setInclinometerValue(int inclinometerValue) {
		this.inclinometerValue = inclinometerValue;
	}

	public int getPLCOverLoad() {
		return PLCOverLoad;
	}

	public void setPLCOverLoad(int pLCOverLoad) {
		PLCOverLoad = pLCOverLoad;
	}

	public int getOverLoadOffset() {
		return OverLoadOffset;
	}

	public void setOverLoadOffset(int overLoadOffset) {
		OverLoadOffset = overLoadOffset;
	}

	public int getxSpeedUp() {
		return xSpeedUp;
	}

	public void setxSpeedUp(int xSpeedUp) {
		this.xSpeedUp = xSpeedUp;
	}

	private int xSpeedDown = 0; // 水平下部速度mw357
	private int zSpeed = 0; // 垂直速度mw358
	private int ySpeed = 0; // 货叉速度mw359
	private int inclinometerValue = 0;// 倾角仪mw360
	private int PLCOverLoad = 0; // PLC058当前负载362
	private int OverLoadOffset = 0; // 重载补偿mw363
	private int xSpeedUp = 0; // 水平上部速度mw365

	public boolean isNetworkDisconnected() {
		return networkDisconnected;
	}

	public void setNetworkDisconnected(boolean networkDisconnected) {
		this.networkDisconnected = networkDisconnected;
	}

	boolean networkDisconnected = false;// 网线被拔掉、断网等

}
