package com.displayWindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.Information.StockerInfo;
import com.SunToolKit.DifferTypeDataSwitch;
import com.SunToolKit.GetAnyDataOneBit;
import com.SunToolKit.WriteAnyoneBit;
import com.css.BorderStyle1PixGray;
import com.modbus.Modbus;

public class Control_4 extends JPanel {

	StockerInfo stockerInfo = null;
	Modbus modbus = null;
	int delayTime=1000;
	
	// 左远的中间变量
	boolean leftFarTemp = false;
	boolean leftNearTemp = false;
	boolean rightFarTemp = false;
	boolean rightNearTemp = false;

	int valueWillWrite34 = 0;
	
	String text1=null;
	String text2=null;
	String text3=null;
	String text4=null;
	
	//写528
	int value528=0;//列
	int value529=0;//层
	int value530=0;//排
	int value531=0;//模式

	JLabel j1 = null;
	JLabel j2 = null;
	JLabel j3 = null;
	JLabel j4 = null;
	JLabel j9 = null;
	JLabel j91 = null;
	
	JLabel j11 = null;
	JLabel j21 = null;
	JLabel j31 = null;
	JLabel j41 = null;
	
	String area=null;
	String setFloor=null;
	String setRank=null;
	String setRow=null;

	JComboBox jcb1 = null;
	JComboBox jcb2 = null;
	JComboBox jcb3 = null;
	JComboBox jcb4 = null;

	JButton jb1 = null;
	JButton jb2 = null;
	JButton jb3 = null;
	JButton jb4 = null;
	JButton jb5 = null;
	JButton jb6 = null;

	boolean startDone=false;
	boolean stopDone=false;

	public Control_4(StockerInfo stockerInfo, Modbus mod) {

		this.stockerInfo = stockerInfo;
		this.modbus = mod;

		j1 = new JLabel("自动移库作业区域选择");
		j2 = new JLabel("设定层");
		j3 = new JLabel("设定列");
		j4 = new JLabel("设定排");
		j9 = new JLabel("检查中",JLabel.CENTER);
		j91 = new JLabel("停止中",JLabel.CENTER);

		j11 = new JLabel(area);
		j21 = new JLabel(setFloor);
		j31 = new JLabel(setRank);
		j41 = new JLabel(setRow);

		j1.setFont(new Font("宋体", 0, 12));
		j2.setFont(new Font("宋体", 0, 12));
		j3.setFont(new Font("宋体", 0, 12));
		j4.setFont(new Font("宋体", 0, 12));

		j11.setFont(new Font("宋体", 1, 22));
		j21.setFont(new Font("宋体", 1, 22));
		j31.setFont(new Font("宋体", 1, 22));
		j41.setFont(new Font("宋体", 1, 22));

		String[] area1 = { "0：未选择", "1：最后一列", "2：第二至三列", "3：四至倒数第二列", "4：第一列",
		"5：所有列" };
		String[] area2 = { "未选择", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "11", "12", "13", "14", };
		String[] area3 = { "未选择", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30", "31", "32", "33", "34", "35", "36", "37", "38", "39",
				"40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
				"50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
				"60", "61", "62", "63", "64" };
		String[] area4 = { "未选择", "1", "2", "3", "4" };

		jcb1 = new JComboBox(area1);
		jcb2 = new JComboBox(area2);
		jcb3 = new JComboBox(area3);
		jcb4 = new JComboBox(area4);

//		jrb1 = new JCheckBox();
//		jrb2 = new JCheckBox();
//		jrb3 = new JCheckBox();
//		jrb4 = new JCheckBox();
		
//		jrb1.setOpaque(false);
//		jrb2.setOpaque(false);
//		jrb3.setOpaque(false);
//		jrb4.setOpaque(false);

		jb1 = new JButton("启动");
		jb2 = new JButton("停止");
		jb3 = new JButton("左远");
		jb4 = new JButton("左近");
		jb5 = new JButton("右近");
		jb6 = new JButton("右远");

		jb1.setFocusPainted(false);
		jb2.setFocusPainted(false);
		jb3.setFocusPainted(false);
		jb4.setFocusPainted(false);
		jb5.setFocusPainted(false);
		jb6.setFocusPainted(false);

		this.setLayout(null);
		this.setOpaque(false);
		BorderStyle1PixGray.CreateLineBorder(this);

		this.add(j1);
		j1.setBounds(30, 20, 150, 30);
		this.add(j2);
		j2.setBounds(195, 20, 60, 30);
		this.add(j3);
		j3.setBounds(270, 20, 60, 30);
		this.add(j4);
		j4.setBounds(350, 20, 60, 30);

		this.add(j11);
		j11.setBounds(155, 20, 150, 30);
		this.add(j21);
		j21.setBounds(235, 20, 150, 30);
		this.add(j31);
		j31.setBounds(310, 20, 150, 30);
		this.add(j41);
		j41.setBounds(390, 20, 150, 30);
		
		this.add(j9);
		this.add(j91);

		this.add(jcb1);
		jcb1.setBounds(30, 60, 150, 25);
		this.add(jcb2);
		jcb2.setBounds(185, 60, 70, 25);
		this.add(jcb3);
		jcb3.setBounds(260, 60, 70, 25);
		this.add(jcb4);
		jcb4.setBounds(335, 60, 70, 25);

		//%MW531模式选择
		jcb1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 取到现在的值
					text1 = (String) jcb1.getSelectedItem();
					if (text1.equals("0：未选择")){
						value531=0;
					}else if(text1.equals("1：最后一列")){
						value531=1;
					}else if(text1.equals("2：第二至三列")){
						value531=2;
					}else if(text1.equals("3：四至倒数第二列")){
						value531=3;
					}else if(text1.equals("4：第一列")){
						value531=4;
					}else if(text1.equals("5：所有列")){
						value531=5;
					}
//					System.out.println("执行了value535="+value535);
					/**
					 * 设定层 %MW532 0-14 设定列 %MW533 1-64 左右 %MW534 左=0 右=1 模式 %MW535 0-5
					 */
				}
			}
		});
		
		//设定层 %MW529
		jcb2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 取到现在的值
					text2 = (String) jcb2.getSelectedItem();
					if (text2.equals("未选择")){
						value529=0;
					}else if(text2.equals("1")){
						value529=1;
					}else if(text2.equals("2")){
						value529=2;
					}else if(text2.equals("3")){
						value529=3;
					}else if(text2.equals("4")){
						value529=4;
					}else if(text2.equals("5")){
						value529=5;
					}else if(text2.equals("6")){
						value529=6;
					}else if(text2.equals("7")){
						value529=7;
					}else if(text2.equals("8")){
						value529=8;
					}else if(text2.equals("9")){
						value529=9;
					}else if(text2.equals("10")){
						value529=10;
					}else if(text2.equals("11")){
						value529=11;
					}else if(text2.equals("12")){
						value529=12;
					}else if(text2.equals("13")){
						value529=13;
					}else if(text2.equals("14")){
						value529=14;
					}
//					System.out.println("执行了value529="+value529);
				}
			}
		});
		//设定列 %MW528
		jcb3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 取到现在的值
					text3 = (String) jcb3.getSelectedItem();
					if (text3.equals("未选择")){
						value528=0;
					}else if(text3.equals("1")){
						value528=1;
					}else if(text3.equals("2")){
						value528=2;
					}else if(text3.equals("3")){
						value528=3;
					}else if(text3.equals("4")){
						value528=4;
					}else if(text3.equals("5")){
						value528=5;
					}else if(text3.equals("6")){
						value528=6;
					}else if(text3.equals("7")){
						value528=7;
					}else if(text3.equals("8")){
						value528=8;
					}else if(text3.equals("9")){
						value528=9;
					}else if(text3.equals("10")){
						value528=10;
					}else if(text3.equals("11")){
						value528=11;
					}else if(text3.equals("12")){
						value528=12;
					}else if(text3.equals("13")){
						value528=13;
					}else if(text3.equals("14")){
						value528=14;
					}else if(text3.equals("15")){
						value528=15;
					}else if(text3.equals("16")){
						value528=16;
					}else if(text3.equals("17")){
						value528=17;
					}else if(text3.equals("18")){
						value528=18;
					}else if(text3.equals("19")){
						value528=19;
					}else if(text3.equals("20")){
						value528=20;
					}else if(text3.equals("21")){
						value528=21;
					}else if(text3.equals("22")){
						value528=22;
					}else if(text3.equals("23")){
						value528=23;
					}else if(text3.equals("24")){
						value528=24;
					}else if(text3.equals("25")){
						value528=25;
					}else if(text3.equals("26")){
						value528=26;
					}else if(text3.equals("27")){
						value528=27;
					}else if(text3.equals("28")){
						value528=28;
					}else if(text3.equals("29")){
						value528=29;
					}else if(text3.equals("30")){
						value528=30;
					}else if(text3.equals("31")){
						value528=31;
					}else if(text3.equals("32")){
						value528=32;
					}else if(text3.equals("33")){
						value528=33;
					}else if(text3.equals("34")){
						value528=34;
					}else if(text3.equals("35")){
						value528=35;
					}else if(text3.equals("36")){
						value528=36;
					}else if(text3.equals("37")){
						value528=37;
					}else if(text3.equals("38")){
						value528=38;
					}else if(text3.equals("39")){
						value528=39;
					}else if(text3.equals("40")){
						value528=40;
					}else if(text3.equals("41")){
						value528=41;
					}else if(text3.equals("42")){
						value528=42;
					}else if(text3.equals("43")){
						value528=43;
					}else if(text3.equals("44")){
						value528=44;
					}else if(text3.equals("45")){
						value528=45;
					}else if(text3.equals("46")){
						value528=46;
					}else if(text3.equals("47")){
						value528=47;
					}else if(text3.equals("48")){
						value528=48;
					}else if(text3.equals("49")){
						value528=49;
					}else if(text3.equals("50")){
						value528=50;
					}else if(text3.equals("51")){
						value528=51;
					}else if(text3.equals("52")){
						value528=52;
					}else if(text3.equals("53")){
						value528=53;
					}else if(text3.equals("54")){
						value528=54;
					}else if(text3.equals("55")){
						value528=55;
					}else if(text3.equals("56")){
						value528=56;
					}else if(text3.equals("57")){
						value528=57;
					}else if(text3.equals("58")){
						value528=58;
					}else if(text3.equals("59")){
						value528=59;
					}else if(text3.equals("60")){
						value528=60;
					}else if(text3.equals("61")){
						value528=61;
					}else if(text3.equals("62")){
						value528=62;
					}else if(text3.equals("63")){
						value528=63;
					}else if(text3.equals("64")){
						value528=64;
					}
//					System.out.println("执行了value528="+value528);
				}
			}
		});
		//设定排 %MW530
		jcb4.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 取到现在的值
					text4 = (String) jcb4.getSelectedItem();
					if (text4.equals("未选择")){
						value530=0;
					}else if(text4.equals("1")){
						value530=1;
					}else if(text4.equals("2")){
						value530=2;
					}else if(text4.equals("3")){
						value530=3;
					}else if(text4.equals("4")){
						value530=4;
					}
//					System.out.println("执行了value530="+value530);
				}
			}
		});
//		this.add(jrb1);
//		jrb1.setBounds(450, 6, 25, 25);
//		this.add(jrb2);
//		jrb2.setBounds(450, 26, 25, 25);
//		this.add(jrb3);
//		jrb3.setBounds(450, 46, 25, 25);
//		this.add(jrb4);
//		jrb4.setBounds(450, 66, 25, 25);

		this.add(jb1);
		jb1.setBounds(500, 15, 70, 30);
		this.add(jb2);
		jb2.setBounds(500, 55, 70, 30);
		this.add(jb3);
		jb3.setBounds(420, 6, 70, 20);
		this.add(jb4);
		jb4.setBounds(420, 28, 70, 20);
		this.add(jb5);
		jb5.setBounds(420, 50, 70, 20);
		this.add(jb6);
		jb6.setBounds(420, 72, 70, 20);		

		// MW34:X3 启动货架测试 (点击瞬间ON)
		jb1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[4], 3);
				waitSomeTime();
			}
//			public void mouseReleased(MouseEvent e) {
//				try {
//					Thread.sleep(delayTime);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
//						.getDatablocks().get(5).data[4], 3);
////				System.out.println("松开了启动按钮，把"+valueWillWrite34+"传过去了");
//			}
		});		

		// MW34:X4 停止货架测试 (点击瞬间ON)
		jb2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[4], 4);
				waitSomeTime();
			}
//			public void mouseReleased(MouseEvent e) {
//				try {
//					Thread.sleep(delayTime);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
//						.getDatablocks().get(5).data[4], 4);
//			}
		});
		
		// MW34:X5 左远 (位翻转)
		jb3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!leftFarTemp) {
					valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
							.getDatablocks().get(5).data[4], 5);
				} else if (leftFarTemp) {
					valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
							.getDatablocks().get(5).data[4], 5);
				}
			}
			public void mouseReleased(MouseEvent e) {
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				leftFarTemp = !leftFarTemp;
			}
		});
		// MW34:X6 左近 (位翻转)
		jb4.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!leftNearTemp) {
					valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
							.getDatablocks().get(5).data[4], 6);
				} else if (leftNearTemp) {
					valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
							.getDatablocks().get(5).data[4], 6);
				}
			}
			public void mouseReleased(MouseEvent e) {
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				leftNearTemp = !leftNearTemp;
			}
		});

		// MW34:X7 右近 (位翻转)
		jb5.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!rightNearTemp) {
					valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
							.getDatablocks().get(5).data[4], 7);
				} else if (rightNearTemp) {
					valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
							.getDatablocks().get(5).data[4], 7);
				}
			}
			public void mouseReleased(MouseEvent e) {
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				rightNearTemp = !rightNearTemp;
			}
		});

		// MW34:X8 右远 (位翻转)
		jb6.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!rightFarTemp) {
					valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
							.getDatablocks().get(5).data[4], 8);
				} else if (rightFarTemp) {
					valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
							.getDatablocks().get(5).data[4], 8);
				}
			}
			public void mouseReleased(MouseEvent e) {
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				rightFarTemp = !rightFarTemp;
			}
		});
	}

	public void show() {
		modbus.setValueWillWrite34(valueWillWrite34);
		modbus.setValue528(value528);
		modbus.setValue529(value529);
		modbus.setValue530(value530);
		modbus.setValue531(value531);

		area=String.valueOf(modbus.getDatablocks().get(6).data[3]);//531//528.8
		setFloor=String.valueOf(modbus.getDatablocks().get(6).data[1]);//529
		setRank=String.valueOf(modbus.getDatablocks().get(6).data[0]);//528
		setRow=String.valueOf(modbus.getDatablocks().get(6).data[2]);//530
		
		j11.setText(area);
		j21.setText(setFloor);
		j31.setText(setRank);
		j41.setText(setRow);
		
		//启动已生效
		startDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[4], 3);
		if (startDone){
			valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[4], 3);
			startDone=false;
		}
		//停止已生效
		stopDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[4], 4);
		if (stopDone){
			valueWillWrite34 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[4], 4);
			stopDone=false;
		}
		
		//检查指示灯
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(0).data[9], 0)) {
			j91.setBounds(5800, 36, 70, 30);
			j9.setBounds(580, 36, 70, 30);		
			j9.setOpaque(true);
			j9.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(j9);
		} else {
			j9.setBounds(5800, 36, 70, 30);
			j91.setBounds(580, 36, 70, 30);
			j91.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(j91);
		}		
		
		// 启动颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[4], 3)) {
			jb1.setBackground(Color.green);
		} else {
			jb1.setBackground(null);
		}
		// 停止颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[4], 4)) {
			jb2.setBackground(Color.green);
		} else {
			jb2.setBackground(null);
		}
		// 左远颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[4], 5)) {
			jb3.setBackground(Color.green);
		} else {
			jb3.setBackground(null);
		}
		// 左远颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[4], 6)) {
			jb4.setBackground(Color.green);
		} else {
			jb4.setBackground(null);
		}
		// 左远颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[4], 7)) {
			jb5.setBackground(Color.green);
		} else {
			jb5.setBackground(null);
		}
		// 左远颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[4], 8)) {
			jb6.setBackground(Color.green);
		} else {
			jb6.setBackground(null);
		}
	}

	public void waitSomeTime(){
		try {
			Thread.sleep(2000);
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
	}
}