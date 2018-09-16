package com.displayWindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.Information.StockerInfo;
import com.css.BorderStyle1PixGray;

//下发作业相关 没有继续做
//下发作业相关 没有继续做
//下发作业相关 没有继续做
//下发作业相关 没有继续做
//下发作业相关 没有继续做
//下发作业相关 没有继续做
//下发作业相关 没有继续做
//下发作业相关 没有继续做
public class Control_2 extends JPanel {

	StockerInfo stockerInfo = null;
	
//	JLabel j1=null;
//	JLabel j2=null;
//	JLabel j3=null;
//	JLabel j4=null;
//	JLabel j5=null;
//	JLabel j6=null;
//	JLabel j7=null;
//	JLabel j8=null;
//	JLabel j9=null;
//	JLabel j10=null;	

//	JComboBox jcb1 = null;
	
	JButton jb1=null;

	public Control_2(StockerInfo stockerInfo) {

		this.stockerInfo = stockerInfo;
		this.setLayout(null);
		

//		String[] area1 = { "1", "2", "3", "4", "5",	"6", "7", "8", "9", "10" };
//		
//		j1=new JLabel("历史作业记录",JLabel.CENTER);
//		j2=new JLabel("序号");
//		j3=new JLabel("作业号");
//		j4=new JLabel("类型");
//		j5=new JLabel("托盘号");
//		j6=new JLabel("下发时间");
//		j7=new JLabel("上报时间");
//		j8=new JLabel("起始地址");
//		j9=new JLabel("目标地址");
//		j10=new JLabel("故障代码");	
//
//		j2.setFont(new Font("宋体",0,12));
//		j3.setFont(new Font("宋体",0,12));
//		j4.setFont(new Font("宋体",0,12));
//		j5.setFont(new Font("宋体",0,12));
//		j6.setFont(new Font("宋体",0,12));
//		j7.setFont(new Font("宋体",0,12));
//		j8.setFont(new Font("宋体",0,12));
//		j9.setFont(new Font("宋体",0,12));
//		j10.setFont(new Font("宋体",0,12));
//		
//		jcb1=new JComboBox(area1);
//		
//		this.add(j1);
//		j1.setBounds(0, 5, 662, 20);
//		this.add(j2);
//		j2.setBounds(10, 25, 40, 20);
//		this.add(j3);
//		j3.setBounds(50, 25, 40, 20);
//		this.add(j4);
//		j4.setBounds(120, 25, 40, 20);
//		this.add(j5);
//		j5.setBounds(180, 25, 40, 20);
//		this.add(j6);
//		j6.setBounds(250, 25, 80, 20);
//		this.add(j7);
//		j7.setBounds(340, 25, 80, 20);
//		this.add(j8);
//		j8.setBounds(420, 25, 80, 20);
//		this.add(j9);
//		j9.setBounds(520, 25, 80, 20);
//		this.add(j10);
//		j10.setBounds(600, 25, 80, 20);
//		
//		this.add(jcb1);
//		jcb1.setBounds(10, 45, 50, 20);
		
		jb1=new JButton("下发作业");
		jb1.setFocusPainted(false);
		
		

		this.add(jb1);
		jb1.setBounds(40, 25, 90, 30);
		

	}


	public void show() {
	}
}