package com.Information;

public class RotateInfo {
	public int getPushDoorPosition() {
		return PushDoorPosition;
	}

	public void setPushDoorPosition(int pushDoorPosition) {
		PushDoorPosition = pushDoorPosition;
	}
	public int getPushDoorSpeed() {
		return PushDoorSpeed;
	}
	public void setPushDoorSpeed(int pushDoorSpeed) {
		PushDoorSpeed = pushDoorSpeed;
	}
	public int getRotateStationPosition() {
		return RotateStationPosition;
	}
	public void setRotateStationPosition(int rotateStationPosition) {
		RotateStationPosition = rotateStationPosition;
	}
	public int getRotateStationSpeed() {
		return RotateStationSpeed;
	}
	public void setRotateStationSpeed(int rotateStationSpeed) {
		RotateStationSpeed = rotateStationSpeed;
	}

	// 推拉门位置%MW100
	private int PushDoorPosition = 0;
	// 推拉门速度%MW102
	private int PushDoorSpeed = 0;
	// 旋转站台位置%MW104
	private int RotateStationPosition = 0;
	// 旋转站台速度%MW106
	private int RotateStationSpeed = 0;
	

	boolean networkDisconnected=false;//网线被拔掉、断网等

	public boolean isNetworkDisconnected() {
		return networkDisconnected;
	}
	public void setNetworkDisconnected(boolean networkDisconnected) {
		this.networkDisconnected = networkDisconnected;
	}


	
	


}
