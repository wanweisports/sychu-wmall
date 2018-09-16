package com.displayWindow;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.Information.RotateInfo;
import com.Information.StockerInfo;
import com.modbus.Modbus;
import com.modbus.Modbus2;

public class RotateWindow extends JPanel {
	
	// 以下是Jlable标题
	JLabel TitlePushDoorPosition = null;
	JLabel TitlePushDoorSpeed = null;
	JLabel TitleRotateStationPosition = null;
	JLabel TitleRotateStationSpeed = null;
	
	JLabel TitleDoorOpenedPercent = null;
	JLabel TitleRotateStationToKWPercent = null;
	
	// 以下是Jlable显示
	JLabel ViewPushDoorPosition = null;
	JLabel ViewPushDoorSpeed = null;
	JLabel ViewRotateStationPosition = null;
	JLabel ViewRotateStationSpeed = null;
	
	JLabel ViewDoorOpenedPercent = null;
	JLabel ViewRotateStationToKWPercent = null;	
	JLabel ViewToKW_CD = null;
	JLabel ViewOpeningOrClosing = null;
	
	// 以下是字符
	String StrPushDoorPosition = null;
	String StrPushDoorSpeed = null;
	String StrRotateStationPosition = null;
	String StrRotateStationSpeed = null;
	
	String StrDoorOpenedPercent = null;
	String StrRotateStationToKWPercent = null;
	String StrToKW_CD = null;
	String StrOpeningOrClosing = null;
	String StrOpened = null;
	String StrKWed = null;

	//modbus连接
	Modbus2 modbus2 = null;
	RotateInfo rotateInfo=null; 

	public RotateWindow(Modbus2 mod2,RotateInfo rotateInfo) {

		this.modbus2 = mod2;
		this.rotateInfo = rotateInfo;
		this.setSize(1366, 500);
		this.setLayout(null);
		this.setOpaque(false);
		
		// 为Jlable标题赋汉字
		TitlePushDoorPosition=new JLabel("推拉门位置");
		TitlePushDoorSpeed=new JLabel("推拉门速度");
		TitleRotateStationPosition=new JLabel("旋转台位置");
		TitleRotateStationSpeed=new JLabel("旋转台速度");
		TitleDoorOpenedPercent=new JLabel("推拉门关闭百分比");
		TitleRotateStationToKWPercent=new JLabel("旋转台旋转到库内百分比");

		ViewPushDoorPosition = new JLabel(StrPushDoorPosition);
		ViewPushDoorSpeed = new JLabel(StrPushDoorSpeed);
		ViewRotateStationPosition = new JLabel(StrRotateStationPosition);
		ViewRotateStationSpeed = new JLabel(StrRotateStationSpeed);
		ViewDoorOpenedPercent = new JLabel(StrDoorOpenedPercent);
		ViewRotateStationToKWPercent = new JLabel(StrRotateStationToKWPercent);
		ViewToKW_CD = new JLabel(StrToKW_CD);
		ViewOpeningOrClosing = new JLabel(StrOpeningOrClosing);
		
		// 设置字体
		TitlePushDoorPosition.setFont(new Font("宋体", 0, 12));
		TitlePushDoorSpeed.setFont(new Font("宋体", 0, 12));
		TitleRotateStationPosition.setFont(new Font("宋体", 0, 12));
		TitleRotateStationSpeed.setFont(new Font("宋体", 0, 12));
		TitleDoorOpenedPercent.setFont(new Font("宋体", 0, 12));
		TitleRotateStationToKWPercent.setFont(new Font("宋体", 0, 12));
		
		ViewPushDoorPosition.setFont(new Font("Times New Roman", 1, 16));
		ViewPushDoorSpeed.setFont(new Font("Times New Roman", 1, 16));
		ViewRotateStationPosition.setFont(new Font("Times New Roman", 1, 16));
		ViewRotateStationSpeed.setFont(new Font("Times New Roman", 1, 16));
		ViewDoorOpenedPercent.setFont(new Font("Times New Roman", 1, 16));
		ViewRotateStationToKWPercent.setFont(new Font("Times New Roman", 1, 16));
		ViewToKW_CD.setFont(new Font("宋体", 1, 18));
		ViewOpeningOrClosing.setFont(new Font("宋体", 1, 18));
		
		this.add(TitleRotateStationPosition);
		TitleRotateStationPosition.setBounds(500, 48, 70, 28);
		this.add(TitleRotateStationSpeed);
		TitleRotateStationSpeed.setBounds(500, 78, 70, 28);
		this.add(TitlePushDoorPosition);
		TitlePushDoorPosition.setBounds(500, 148, 70, 28);
		this.add(TitlePushDoorSpeed);
		TitlePushDoorSpeed.setBounds(500, 178, 70, 28);		

		this.add(ViewRotateStationPosition);
		ViewRotateStationPosition.setBounds(580, 48, 70, 28);
		this.add(ViewRotateStationSpeed);
		ViewRotateStationSpeed.setBounds(580, 78, 70, 28);
		this.add(ViewPushDoorPosition);
		ViewPushDoorPosition.setBounds(580, 148, 70, 28);
		this.add(ViewPushDoorSpeed);
		ViewPushDoorSpeed.setBounds(580, 178, 70, 28);		

		this.add(TitleRotateStationToKWPercent);
		TitleRotateStationToKWPercent.setBounds(660, 48, 170, 28);
		this.add(TitleDoorOpenedPercent);
		TitleDoorOpenedPercent.setBounds(660, 148, 170, 28);	
		this.add(ViewRotateStationToKWPercent);
		ViewRotateStationToKWPercent.setBounds(700, 78, 170, 28);
		this.add(ViewDoorOpenedPercent);
		ViewDoorOpenedPercent.setBounds(700, 178, 170, 28);
		this.add(ViewToKW_CD);
		ViewToKW_CD.setBounds(800, 78, 170, 28);
		this.add(ViewOpeningOrClosing);
		ViewOpeningOrClosing.setBounds(800, 178, 170, 28);
		
	}

	public void ShowInfo() {
		// 获得字符
		StrRotateStationPosition = String.valueOf(rotateInfo.getRotateStationPosition());
		StrRotateStationSpeed = String.valueOf(rotateInfo.getRotateStationSpeed());
		StrPushDoorPosition = String.valueOf(rotateInfo.getPushDoorPosition());
		StrPushDoorSpeed = String.valueOf(rotateInfo.getPushDoorSpeed());

		//将获得的字符实时赋值给显示
		ViewPushDoorPosition.setText(StrPushDoorPosition);
		ViewPushDoorSpeed.setText(StrPushDoorSpeed);
		ViewRotateStationPosition.setText(StrRotateStationPosition);
		ViewRotateStationSpeed.setText(StrRotateStationSpeed);
		ViewDoorOpenedPercent.setText(StrDoorOpenedPercent);
		ViewRotateStationToKWPercent.setText(StrRotateStationToKWPercent);
		ViewToKW_CD.setText(StrToKW_CD);
		ViewOpeningOrClosing.setText(StrOpeningOrClosing);
		
		//旋转台伺服值范围-51458 -- 1788325
		//推拉门私服值范围-29827 -- 2394000		
		StrRotateStationToKWPercent=String.valueOf(modbus2.getDatablocks().get(0).data[8])+"  %";
		StrDoorOpenedPercent=String.valueOf(modbus2.getDatablocks().get(0).data[9])+"  %";
	
		if (modbus2.getDatablocks().get(0).data[8]==100){
			StrToKW_CD="已旋转到库内位置";
		}else if (modbus2.getDatablocks().get(0).data[8]==0){
			StrToKW_CD="已旋转到库外位置";
		}
		
		if (modbus2.getDatablocks().get(0).data[9]==100){
			StrOpeningOrClosing="门已完全关闭";
		}else if (modbus2.getDatablocks().get(0).data[9]==0){
			StrOpeningOrClosing="门已完全打开";
		}
		
		
		if (rotateInfo.getRotateStationSpeed()>30 ){
			StrToKW_CD="正在旋转至库外";			
		}else if (rotateInfo.getRotateStationSpeed()<-30 ){
			StrToKW_CD="正在旋转至库内";			
		}
		
		if (rotateInfo.getPushDoorSpeed()>30 ){
			StrOpeningOrClosing="正在关门中";			
		}else if (rotateInfo.getPushDoorSpeed()<-30 ){
			StrOpeningOrClosing="正在开门中";			
		}	
	}

}
