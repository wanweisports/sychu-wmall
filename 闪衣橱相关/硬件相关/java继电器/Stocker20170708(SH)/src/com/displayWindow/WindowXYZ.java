package com.displayWindow;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.Information.StockerInfo;
import com.css.BorderStyle1PixGray;

public class WindowXYZ  extends JPanel{
	
	// 三个轴的详细数值显示面板
	JLabel v1 = null;// 水平动作
	JLabel v2 = null;// 垂直动作
	JLabel v3 = null;// 货叉动作
	JLabel v4 = null;// 水平位置
	JLabel v41 = null;// 水平位置
	JLabel v5 = null;// 水平激光
	JLabel v51 = null;// 水平激光
	JLabel v6 = null;// 上部速度
	JLabel v61=null;//上部速度
	JLabel v7 = null;// 下部速度
	JLabel v71=null;//下部速度
	JLabel v8 = null;// 倾角仪
	JLabel v81 = null;// 倾角仪
	JLabel v9 = null;// 垂直位置
	JLabel v91 = null;// 垂直位置
	JLabel v10 = null;// 垂直激光
	JLabel v101 = null;// 垂直激光
	JLabel v11 = null;// 垂直速度
	JLabel v111=null;//垂直速度
	JLabel v12 = null;// 重载补偿
	JLabel v121 = null;// 重载补偿
	JLabel v13 = null;// 位置
	JLabel v131 = null;// 位置
	JLabel v14 = null;// 速度
	JLabel v141 = null;// 速度
	JLabel v15 = null;// PLC当前负载百分比
	JLabel v151 = null;// PLC当前负载百分比
	

	String stringv41 = null;
	String stringv51 = null;
	String stringv61 = null;
	String stringv71 = null;
	String stringv81 = null;
	String stringv91 = null;
	String stringv101 = null;
	String stringv111 = null;
	String stringv121 = null;
	String stringv131 = null;
	String stringv141 = null;
	String stringv151 = null;

	StockerInfo stockerInfo = null;
	public WindowXYZ(StockerInfo stockerInfo){

		this.stockerInfo = stockerInfo;
//		xyzValues = new JPanel();

		// 左边第二部分（各种激光私服值）
		this.setBounds(0, 122, 683, 183);
		this.setLayout(null);
		this.setOpaque(false);
		BorderStyle1PixGray.CreateLineBorder(this);

		v1 = new JLabel("水平动作", JLabel.CENTER);// 水平动作
		v2 = new JLabel("垂直动作", JLabel.CENTER);// 垂直动作
		v3 = new JLabel("货叉动作", JLabel.CENTER);// 货叉动作
		v4 = new JLabel("水平位置", JLabel.CENTER);// 水平位置
		v4.setFont(new Font("宋体", 0, 12));
		v41 = new JLabel(stringv41, JLabel.CENTER);// 水平位置
		v5 = new JLabel("水平激光", JLabel.CENTER);// 水平激光
		v5.setFont(new Font("宋体", 0, 12));
		v51 = new JLabel(stringv51, JLabel.CENTER);// 水平激光
		v6 = new JLabel("上部速度", JLabel.CENTER);// 上部速度
		v6.setFont(new Font("宋体", 0, 12));
		v61 = new JLabel(stringv61, JLabel.CENTER);// 上部速度
		v7 = new JLabel("下部速度", JLabel.CENTER);// 下部速度
		v7.setFont(new Font("宋体", 0, 12));
		v71 = new JLabel(stringv71, JLabel.CENTER);// 下部速度
		v8 = new JLabel("倾 角 仪", JLabel.CENTER);// 倾角仪
		v8.setFont(new Font("宋体", 0, 12));
		v81 = new JLabel(stringv81, JLabel.CENTER);// 倾角仪		
		v9 = new JLabel("垂直位置", JLabel.CENTER);// 垂直位置
		v9.setFont(new Font("宋体", 0, 12));
		v91 = new JLabel(stringv91, JLabel.CENTER);// 垂直位置
		v10 = new JLabel("垂直激光", JLabel.CENTER);// 垂直激光
		v10.setFont(new Font("宋体", 0, 12));
		v101 = new JLabel(stringv101, JLabel.CENTER);// 垂直激光
		v11 = new JLabel("垂直速度", JLabel.CENTER);// 垂直速度
		v11.setFont(new Font("宋体", 0, 12));
		v111 = new JLabel(stringv111, JLabel.CENTER);// 垂直速度
		v12 = new JLabel("重载补偿", JLabel.CENTER);// 重载补偿
		v12.setFont(new Font("宋体", 0, 12));
		v121 = new JLabel(stringv121, JLabel.CENTER);// 重载补偿		
		v13 = new JLabel("位    置", JLabel.CENTER);// 位置
		v13.setFont(new Font("宋体", 0, 12));
		v131 = new JLabel(stringv131, JLabel.CENTER);// 位置		
		v14 = new JLabel("速    度", JLabel.CENTER);// 速度
		v14.setFont(new Font("宋体", 0, 12));
		v141 = new JLabel(stringv141, JLabel.CENTER);// 速度	
		v15 = new JLabel("PLC负载", JLabel.CENTER);// 速度
		v15.setFont(new Font("宋体", 0, 12));
		v151 = new JLabel(stringv141, JLabel.CENTER);//PLC当前负载

		this.add(v1);
		v1.setBounds(80, 0, 80, 30);
		this.add(v2);
		v2.setBounds(300, 0, 80, 30);
		this.add(v3);
		v3.setBounds(522, 0, 80, 30);
		this.add(v4);
		v4.setBounds(0, 30, 80, 30);
		this.add(v41);
		v41.setBounds(80, 30, 80, 30);
		v41.setFont(new Font("Times New Roman", 1, 16));
		this.add(v5);
		v5.setBounds(0, 60, 80, 30);
		this.add(v51);
		v51.setBounds(80, 60, 80, 30);
		v51.setFont(new Font("Times New Roman", 1, 16));
		this.add(v6);
		v6.setBounds(0, 90, 80, 30);
		this.add(v61);//上部速度
		v61.setBounds(80, 90, 80, 30);
		v61.setFont(new Font("Times New Roman", 1, 16));
		this.add(v7);
		v7.setBounds(0, 120, 80, 30);
		this.add(v71);//下部速度
		v71.setBounds(80, 120, 80, 30);
		v71.setFont(new Font("Times New Roman", 1, 16));
		this.add(v8);
		v8.setBounds(0, 150, 80, 30);
		this.add(v81);//倾角仪
		v81.setBounds(80, 150, 80, 30);
		v81.setFont(new Font("Times New Roman", 1, 16));
		this.add(v9);
		v9.setBounds(220, 30, 80, 30);
		this.add(v91);
		v91.setBounds(310, 30, 80, 30);
		v91.setFont(new Font("Times New Roman", 1, 16));
		this.add(v10);
		v10.setBounds(220, 60, 80, 30);
		this.add(v101);
		v101.setBounds(310, 60, 80, 30);
		v101.setFont(new Font("Times New Roman", 1, 16));
		this.add(v11);
		v11.setBounds(220, 90, 80, 30);
		this.add(v111);//垂直速度
		v111.setBounds(300, 90, 80, 30);
		v111.setFont(new Font("Times New Roman", 1, 16));		
		this.add(v12);
		v12.setBounds(220, 120, 80, 30);
		this.add(v121);//重载补偿
		v121.setBounds(300, 120, 80, 30);
		v121.setFont(new Font("Times New Roman", 1, 16));		
		this.add(v13);
		v13.setBounds(442, 30, 80, 30);
		this.add(v131);
		v131.setBounds(522, 30, 80, 30);
		v131.setFont(new Font("Times New Roman", 1, 16));
		this.add(v14);
		v14.setBounds(442, 60, 80, 30);		
		this.add(v15);
		v15.setBounds(442, 150, 80, 30);	
		
		this.add(v141);//货叉速度
		v141.setBounds(522, 60, 80, 30);
		v141.setFont(new Font("Times New Roman", 1, 16));
		
		this.add(v151);//PLC负载
		v151.setBounds(522, 150, 80, 30);
		v151.setFont(new Font("Times New Roman", 1, 16));


	}
	public void show(){
		stringv61 = String
		.valueOf(Float.valueOf(stockerInfo.getxSpeedUp()))+"  r/m";
		stringv71 =String
		.valueOf(Float.valueOf(stockerInfo.getxSpeedDown()))+"  r/m";
		stringv81 =String
		.valueOf(Float.valueOf(stockerInfo.getInclinometerValue())/100);
		stringv111 =String
		.valueOf(Float.valueOf(stockerInfo.getzSpeed()))+"  r/m";
		stringv121 =String
		.valueOf(Float.valueOf(stockerInfo.getOverLoadOffset()));
		stringv41 = String
				.valueOf(Float.valueOf(stockerInfo.getxServoValue()) / 100);
		stringv51 = String.valueOf(stockerInfo.getxLaserValue())+"  mm";
		stringv91 = String
				.valueOf(Float.valueOf(stockerInfo.getzServoValue()) / 100);
		stringv101 = String.valueOf(stockerInfo.getzLaserValue())+"  mm";
		stringv131 = String
				.valueOf(Float.valueOf(stockerInfo.getyServoValue()) / 1000);
		stringv141 = String.valueOf(stockerInfo.getySpeed())+"  r/m";
		stringv151 = String.valueOf(stockerInfo.getPLCOverLoad())+"  %";
		
		v41.setText(stringv41);// 水平私服值
		v51.setText(stringv51);// 水平激光值
		v61.setText(stringv61);// 上部速度
		v71.setText(stringv71);// 下部速度
		v81.setText(stringv81);// 倾角仪
		v91.setText(stringv91);// 垂直伺服值
		v101.setText(stringv101);// 垂直激光值
		v111.setText(stringv111);// 垂直速度
		v121.setText(stringv121);// 重载补偿
		v131.setText(stringv131);// 货叉伺服值
		v141.setText(stringv141);// 货叉伺服值
		v151.setText(stringv151);// 货叉伺服值
	}
}