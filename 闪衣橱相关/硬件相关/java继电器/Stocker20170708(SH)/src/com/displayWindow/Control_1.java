package com.displayWindow;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.Information.StockerInfo;

public class Control_1 extends JPanel {

	StockerInfo stockerInfo = null;

	JLabel j1 = null;
	JLabel j2 = null;
	JLabel j3 = null;
	JLabel j4 = null;
	JLabel j5 = null;
	JLabel j6 = null;
	JLabel j7 = null;
	JLabel j8 = null;
	JLabel j9 = null;
	JLabel j10 = null;

	JLabel j11 = null;
	JLabel j21 = null;
	JLabel j31 = null;
	JLabel j41 = null;
	JLabel j51 = null;
	JLabel j61 = null;
	JLabel j71 = null;
	JLabel j81 = null;
	JLabel j91 = null;
	JLabel j101 = null;

	String currentWork_NO = null;
	String currentWork_Type = null;
	String currentWork_PalletNO = null;
	String currentWork_StartInfo = null;
	String currentWork_TargetInfo = null;
	String currentWork_Flag = null;
	String currentWork_ActPalletNO = null;
	String currentWork_Fragile=null;
	String currentWork_Size=null;

	public Control_1(StockerInfo stockerInfo) {

		this.stockerInfo = stockerInfo;
		this.setLayout(null);

		j1 = new JLabel("当前作业", JLabel.CENTER);
		j2 = new JLabel("当前作业号",JLabel.CENTER);
		j3 = new JLabel("类型",JLabel.CENTER);
		j4 = new JLabel("作业托盘号",JLabel.CENTER);
		j5 = new JLabel("实际托盘号",JLabel.CENTER);
		j6 = new JLabel("起始地址",JLabel.CENTER);
		j7 = new JLabel("目标地址",JLabel.CENTER);
		j8 = new JLabel("标志位",JLabel.CENTER);
		j9 = new JLabel("易碎级别",JLabel.CENTER);
		j10 = new JLabel("大小货属性",JLabel.CENTER);

		j21 = new JLabel(currentWork_NO,JLabel.CENTER);
		j31 = new JLabel(currentWork_Type,JLabel.CENTER);
		j41 = new JLabel(currentWork_PalletNO,JLabel.CENTER);
		j51 = new JLabel(currentWork_ActPalletNO,JLabel.CENTER);
		j61 = new JLabel(currentWork_StartInfo,JLabel.CENTER);
		j71 = new JLabel(currentWork_TargetInfo,JLabel.CENTER);
		j81 = new JLabel(currentWork_Flag,JLabel.CENTER);
		j91 = new JLabel(currentWork_Fragile,JLabel.CENTER);
		j101 = new JLabel(currentWork_Size,JLabel.CENTER);

		// j21=new JLabel("");
		// j31=new JLabel("");
		// j41=new JLabel("");
		// j51=new JLabel("");
		// j61=new JLabel("");
		// j71=new JLabel("");
		// j81=new JLabel("1");

		j2.setFont(new Font("宋体", 0, 12));
		j3.setFont(new Font("宋体", 0, 12));
		j4.setFont(new Font("宋体", 0, 12));
		j5.setFont(new Font("宋体", 0, 12));
		j6.setFont(new Font("宋体", 0, 12));
		j7.setFont(new Font("宋体", 0, 12));
		j8.setFont(new Font("宋体", 0, 12));
		j9.setFont(new Font("宋体", 0, 12));
		j10.setFont(new Font("宋体", 0, 12));
		j21.setFont(new Font("宋体", 1, 12));
		j31.setFont(new Font("宋体", 1, 12));
		j41.setFont(new Font("宋体", 1, 12));
		j51.setFont(new Font("宋体", 1, 12));
		j61.setFont(new Font("宋体", 1, 12));
		j71.setFont(new Font("宋体", 1, 12));
		j81.setFont(new Font("宋体", 1, 12));
		j91.setFont(new Font("宋体", 1, 12));
		j101.setFont(new Font("宋体", 1, 12));

		this.add(j1);
		j1.setBounds(290, 5, 80, 20);
		this.add(j2);
		j2.setBounds(5, 25, 60, 20);
		this.add(j3);
		j3.setBounds(60, 25, 100, 20);
		this.add(j4);
		j4.setBounds(150, 25, 80, 20);
		this.add(j5);
		j5.setBounds(230, 25, 80, 20);
		this.add(j6);
		j6.setBounds(305, 25, 80, 20);
		this.add(j7);
		j7.setBounds(395, 25, 80, 20);
		this.add(j8);
		j8.setBounds(460, 25, 80, 20);
		this.add(j9);
		j9.setBounds(520, 25, 80, 20);
		this.add(j10);
		j10.setBounds(585, 25, 80, 20);

		this.add(j21);
		j21.setBounds(5, 45, 60, 20);
		this.add(j31);
		j31.setBounds(60, 45, 100, 20);
		this.add(j41);
		j41.setBounds(150, 45, 80, 20);
		this.add(j51);
		j51.setBounds(230, 45, 80, 20);
		this.add(j61);
		j61.setBounds(305, 45, 80, 20);
		this.add(j71);
		j71.setBounds(395, 45, 80, 20);
		this.add(j81);
		j81.setBounds(460, 45, 80, 20);
		this.add(j91);
		j91.setBounds(520, 45, 80, 20);
		this.add(j101);
		j101.setBounds(585, 45, 80, 20);
	}

	public void show() {

		currentWork_Type = String.valueOf(stockerInfo.getCurrentWorkType());
		currentWork_NO = String.valueOf(stockerInfo.getCurrentWorkNO());
		currentWork_PalletNO = String.valueOf(stockerInfo
				.getCurrentWorkPalletNO());
		currentWork_Flag = String.valueOf(stockerInfo.getCurrentWorkFlag());
		// 轿厢有货才显示实际托盘号
		if (stockerInfo.isCarHasCargo()) {
			currentWork_ActPalletNO = String.valueOf(stockerInfo
					.getCurrentWorkActPalletNO());
		} else {
			currentWork_ActPalletNO = "无货";
		}

		// 作业地址的判断=0为站台，=1为货架(起始地址)
		if (stockerInfo.getCurrentWorkStartAddressStation() == 0) {
			currentWork_StartInfo = String.valueOf(stockerInfo.getCurrentWorkStartAddressPai())
					+ "" + String.valueOf(stockerInfo.getCurrentWorkStartAddressLie())
					+ "" + String.valueOf(stockerInfo.getCurrentWorkStartAddressCeng()) + "站台";
			if (stockerInfo.getCurrentWorkStartAddressCeng() == 0) {
				currentWork_StartInfo = "----";
			}
		} else if (stockerInfo.getCurrentWorkStartAddressStation() != 0) {
			currentWork_StartInfo = String.valueOf(stockerInfo.getCurrentWorkStartAddressCeng())
					+ "层" + String.valueOf(stockerInfo.getCurrentWorkStartAddressLie())
					+ "列" + String.valueOf(stockerInfo.getCurrentWorkStartAddressPai()) + "排";
		}
		// 作业地址的判断=0为站台，=1为货架(目标地址)
		if (stockerInfo.getCurrentWorkTargetAddressStation() == 0) {
			currentWork_TargetInfo = String.valueOf(stockerInfo.getCurrentWorkTargetAddressPai())
					+ "" + String.valueOf(stockerInfo.getCurrentWorkTargetAddressLie())
					+ "" + String.valueOf(stockerInfo.getCurrentWorkTargetAddressCeng()) + "站台";
			if (stockerInfo.getCurrentWorkTargetAddressCeng() == 0) {
				currentWork_TargetInfo = "----";
			}
		} else if (stockerInfo.getCurrentWorkTargetAddressStation() != 0) {
			currentWork_TargetInfo = String.valueOf(stockerInfo.getCurrentWorkTargetAddressCeng())
					+ "层"+ String.valueOf(stockerInfo.getCurrentWorkTargetAddressLie())
					+ "列" + String.valueOf(stockerInfo.getCurrentWorkTargetAddressPai()) + "排";
		}
		currentWork_Fragile=String.valueOf(stockerInfo.getCurrentWorkProperty()%16/4);
		currentWork_Size=String.valueOf(stockerInfo.getCurrentWorkProperty()%4);
		j21.setText(currentWork_NO);
		j31.setText(currentWork_Type);
		j41.setText(currentWork_PalletNO);
		j51.setText(currentWork_ActPalletNO);
		j61.setText(currentWork_StartInfo);
		j71.setText(currentWork_TargetInfo);
		j81.setText(currentWork_Flag);
//		System.out.println("currentWork_StartInfo="+currentWork_StartInfo+"currentWork_TargetInfo="+currentWork_TargetInfo);
//		System.out.println(stockerInfo.getCurrentWorkStartAddressStation());
		//作业属性中的易碎等级判断
		if (currentWork_Fragile.equals("0")){
			j91.setText("正常品");			
		}else if(currentWork_Fragile.equals("1")){
			j91.setText("一级易碎品");			
		}else if(currentWork_Fragile.equals("2")){
			j91.setText("二级易碎品");			
		}else if(currentWork_Fragile.equals("3")){
			j91.setText("三级易碎品");			
		}
		//作业属性中的大小货判断
		if(currentWork_Size.equals("0")){
			j101.setText("小型货物");			
		}else if(currentWork_Size.equals("1")){
			j101.setText("大型货物");			
		}else if(currentWork_Size.equals("2")){
			j101.setText("超大货物");			
		}else if(currentWork_Size.equals("3")){
			j101.setText("自动检测");			
		}
	}
}