package com.displayWindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.Information.StockerInfo;
import com.SunToolKit.DifferTypeDataSwitch;
import com.SunToolKit.GetAnyDataOneBit;
import com.SunToolKit.WriteAnyoneBit;
import com.css.BorderStyle1PixGray;
import com.modbus.Modbus;

public class Control_5 extends JPanel {

	StockerInfo stockerInfo = null;
	Modbus modbus = null;
	int delayTime = 1000;
	int valueWillWrite34_2 = 0;
	// int value=0;
	String text1 = null;
	String text2 = null;
	String text3 = null;

	// 写532
	int value532 = 0;
	int value533 = 0;
	int value534 = 0x00;
	int value535 = 0;

	JLabel j1 = null;
	JLabel j2 = null;
	JLabel j3 = null;
	
	JLabel j11 = null;
	JLabel j21 = null;
	JLabel j31 = null;
	
	String area=null;
	String setFloor=null;
	String setRank=null;

	JComboBox jcb1 = null;
	JComboBox jcb2 = null;
	JComboBox jcb3 = null;

	// JRadioButton jrb1=null;
	// JRadioButton jrb2=null;

	JButton jb1 = null;
	JButton jb2 = null;
	JButton jb3 = null;
	JButton jb4 = null;
	JButton jb5 = null;
	JButton jb6 = null;
	JButton jb7 = null;
	
	boolean upDone =false;
	boolean downDone =false;
	boolean lastDone =false;
	boolean nextDone =false;
	boolean goDone =false;

	public Control_5(StockerInfo stockerInfo, Modbus mod) {

		this.stockerInfo = stockerInfo;
		this.modbus = mod;

		j1 = new JLabel("人工校验货位区域选择");
		j2 = new JLabel("设定层");
		j3 = new JLabel("设定列");

		area=String.valueOf(modbus.getDatablocks().get(6).data[7]);//535//528.8
		setFloor=String.valueOf(modbus.getDatablocks().get(6).data[4]);//532
		setRank=String.valueOf(modbus.getDatablocks().get(6).data[5]);//533
		
		j11 = new JLabel(area);
		j21 = new JLabel(setFloor);
		j31 = new JLabel(setRank);

		j1.setFont(new Font("宋体", 0, 12));
		j2.setFont(new Font("宋体", 0, 12));
		j3.setFont(new Font("宋体", 0, 12));

		j11.setFont(new Font("宋体", 1, 22));
		j21.setFont(new Font("宋体", 1, 22));
		j31.setFont(new Font("宋体", 1, 22));

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

		jcb1 = new JComboBox(area1);
		jcb2 = new JComboBox(area2);
		jcb3 = new JComboBox(area3);

		jb1 = new JButton("Last");
		jb2 = new JButton("Next");
		jb3 = new JButton("Up");
		jb4 = new JButton("Down");
		jb5 = new JButton("GO");
		jb6 = new JButton("左侧");
		jb7 = new JButton("右侧");

		jb1.setFocusPainted(false);
		jb2.setFocusPainted(false);
		jb3.setFocusPainted(false);
		jb4.setFocusPainted(false);
		jb5.setFocusPainted(false);
		jb6.setFocusPainted(false);
		jb7.setFocusPainted(false);

		this.setLayout(null);
		this.setOpaque(false);
		BorderStyle1PixGray.CreateLineBorder(this);

		this.add(j1);
		j1.setBounds(30, 20, 150, 30);
		this.add(j2);
		j2.setBounds(195, 20, 60, 30);
		this.add(j3);
		j3.setBounds(270, 20, 60, 30);

		this.add(j11);
		j11.setBounds(155, 20, 150, 30);
		this.add(j21);
		j21.setBounds(235, 20, 150, 30);
		this.add(j31);
		j31.setBounds(310, 20, 150, 30);

		this.add(jcb1);
		jcb1.setBounds(30, 60, 150, 25);
		this.add(jcb2);
		jcb2.setBounds(185, 60, 70, 25);
		this.add(jcb3);
		jcb3.setBounds(260, 60, 70, 25);

		// %MW535模式选择
		jcb1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 取到现在的值
					text1 = (String) jcb1.getSelectedItem();
					if (text1.equals("0：未选择")) {
						value535 = 0;
					} else if (text1.equals("1：最后一列")) {
						value535 = 1;
					} else if (text1.equals("2：第二至三列")) {
						value535 = 2;
					} else if (text1.equals("3：四至倒数第二列")) {
						value535 = 3;
					} else if (text1.equals("4：第一列")) {
						value535 = 4;
					} else if (text1.equals("5：所有列")) {
						value535 = 5;
					}
					// System.out.println("执行了value535="+value535);
					/**
					 * 设定层 %MW532 0-14 设定列 %MW533 1-64 左右 %MW534 左=0 右=1 模式
					 * %MW535 0-5
					 */
				}
			}
		});

		// 设定层 %MW532
		jcb2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 取到现在的值
					text2 = (String) jcb2.getSelectedItem();
					if (text2.equals("未选择")) {
						value532 = 0;
					} else if (text2.equals("1")) {
						value532 = 1;
					} else if (text2.equals("2")) {
						value532 = 2;
					} else if (text2.equals("3")) {
						value532 = 3;
					} else if (text2.equals("4")) {
						value532 = 4;
					} else if (text2.equals("5")) {
						value532 = 5;
					} else if (text2.equals("6")) {
						value532 = 6;
					} else if (text2.equals("7")) {
						value532 = 7;
					} else if (text2.equals("8")) {
						value532 = 8;
					} else if (text2.equals("9")) {
						value532 = 9;
					} else if (text2.equals("10")) {
						value532 = 10;
					} else if (text2.equals("11")) {
						value532 = 11;
					} else if (text2.equals("12")) {
						value532 = 12;
					} else if (text2.equals("13")) {
						value532 = 13;
					} else if (text2.equals("14")) {
						value532 = 14;
					}
					// System.out.println("执行了value532="+value532);
				}
			}
		});
		// 设定列 %MW533
		jcb3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 取到现在的值
					text3 = (String) jcb3.getSelectedItem();
					if (text3.equals("未选择")) {
						value533 = 0;
					} else if (text3.equals("1")) {
						value533 = 1;
					} else if (text3.equals("2")) {
						value533 = 2;
					} else if (text3.equals("3")) {
						value533 = 3;
					} else if (text3.equals("4")) {
						value533 = 4;
					} else if (text3.equals("5")) {
						value533 = 5;
					} else if (text3.equals("6")) {
						value533 = 6;
					} else if (text3.equals("7")) {
						value533 = 7;
					} else if (text3.equals("8")) {
						value533 = 8;
					} else if (text3.equals("9")) {
						value533 = 9;
					} else if (text3.equals("10")) {
						value533 = 10;
					} else if (text3.equals("11")) {
						value533 = 11;
					} else if (text3.equals("12")) {
						value533 = 12;
					} else if (text3.equals("13")) {
						value533 = 13;
					} else if (text3.equals("14")) {
						value533 = 14;
					} else if (text3.equals("15")) {
						value533 = 15;
					} else if (text3.equals("16")) {
						value533 = 16;
					} else if (text3.equals("17")) {
						value533 = 17;
					} else if (text3.equals("18")) {
						value533 = 18;
					} else if (text3.equals("19")) {
						value533 = 19;
					} else if (text3.equals("20")) {
						value533 = 20;
					} else if (text3.equals("21")) {
						value533 = 21;
					} else if (text3.equals("22")) {
						value533 = 22;
					} else if (text3.equals("23")) {
						value533 = 23;
					} else if (text3.equals("24")) {
						value533 = 24;
					} else if (text3.equals("25")) {
						value533 = 25;
					} else if (text3.equals("26")) {
						value533 = 26;
					} else if (text3.equals("27")) {
						value533 = 27;
					} else if (text3.equals("28")) {
						value533 = 28;
					} else if (text3.equals("29")) {
						value533 = 29;
					} else if (text3.equals("30")) {
						value533 = 30;
					} else if (text3.equals("31")) {
						value533 = 31;
					} else if (text3.equals("32")) {
						value533 = 32;
					} else if (text3.equals("33")) {
						value533 = 33;
					} else if (text3.equals("34")) {
						value533 = 34;
					} else if (text3.equals("35")) {
						value533 = 35;
					} else if (text3.equals("36")) {
						value533 = 36;
					} else if (text3.equals("37")) {
						value533 = 37;
					} else if (text3.equals("38")) {
						value533 = 38;
					} else if (text3.equals("39")) {
						value533 = 39;
					} else if (text3.equals("40")) {
						value533 = 40;
					} else if (text3.equals("41")) {
						value533 = 41;
					} else if (text3.equals("42")) {
						value533 = 42;
					} else if (text3.equals("43")) {
						value533 = 43;
					} else if (text3.equals("44")) {
						value533 = 44;
					} else if (text3.equals("45")) {
						value533 = 45;
					} else if (text3.equals("46")) {
						value533 = 46;
					} else if (text3.equals("47")) {
						value533 = 47;
					} else if (text3.equals("48")) {
						value533 = 48;
					} else if (text3.equals("49")) {
						value533 = 49;
					} else if (text3.equals("50")) {
						value533 = 50;
					} else if (text3.equals("51")) {
						value533 = 51;
					} else if (text3.equals("52")) {
						value533 = 52;
					} else if (text3.equals("53")) {
						value533 = 53;
					} else if (text3.equals("54")) {
						value533 = 54;
					} else if (text3.equals("55")) {
						value533 = 55;
					} else if (text3.equals("56")) {
						value533 = 56;
					} else if (text3.equals("57")) {
						value533 = 57;
					} else if (text3.equals("58")) {
						value533 = 58;
					} else if (text3.equals("59")) {
						value533 = 59;
					} else if (text3.equals("60")) {
						value533 = 60;
					} else if (text3.equals("61")) {
						value533 = 61;
					} else if (text3.equals("62")) {
						value533 = 62;
					} else if (text3.equals("63")) {
						value533 = 63;
					} else if (text3.equals("64")) {
						value533 = 64;
					}
					// System.out.println("执行了value533="+value533);
				}
			}
		});
		// this.add(jrb1);
		// jrb1.setBounds(390, 23, 25, 25);
		// this.add(jrb2);
		// jrb2.setBounds(390, 53, 25, 25);

		this.add(jb1);
		jb1.setBounds(435, 35, 70, 30);
		this.add(jb2);
		jb2.setBounds(575, 35, 70, 30);
		this.add(jb3);
		jb3.setBounds(505, 5, 70, 30);
		this.add(jb4);
		jb4.setBounds(505, 65, 70, 30);
		this.add(jb5);
		jb5.setBounds(505, 35, 70, 30);
		this.add(jb6);
		jb6.setBounds(345, 30, 70, 20);
		this.add(jb7);
		jb7.setBounds(345, 51, 70, 20);

		// 按钮GO %MW34:X11 (点击瞬间ON)
		jb5.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[4], 11);
				waitSomeTime();
			}
//
//			public void mouseReleased(MouseEvent e) {
//				try {
//					Thread.sleep(delayTime);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
//						.getDatablocks().get(5).data[4], 11);
//			}
		});
		// 按钮下一层 %MW34:X12 (点击瞬间ON)
		jb4.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[4], 12);
				waitSomeTime();
			}
//
//			public void mouseReleased(MouseEvent e) {
//				try {
//					Thread.sleep(delayTime);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
//						.getDatablocks().get(5).data[4], 12);
//			}
		});
		// 按钮上一层 %MW34:X13 (点击瞬间ON)
		jb3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[4], 13);
				waitSomeTime();
			}
//
//			public void mouseReleased(MouseEvent e) {
//				try {
//					Thread.sleep(delayTime);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
//						.getDatablocks().get(5).data[4], 13);
//			}
		});
		// 按钮下一列next %MW34:X14 (点击瞬间ON)
		jb2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[4], 14);
				waitSomeTime();
			}
//
//			public void mouseReleased(MouseEvent e) {
//				try {
//					Thread.sleep(delayTime);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
//						.getDatablocks().get(5).data[4], 14);
//			}
		});
		// 按钮上一列（last） %MW34:X15 (点击瞬间ON)
		jb1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[4], 15);
				waitSomeTime();
			}
//
//			public void mouseReleased(MouseEvent e) {
//				try {
//					Thread.sleep(delayTime);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
//						.getDatablocks().get(5).data[4], 15);
//			}
		});

		// MW534 左右 (点击瞬间ON)
		jb6.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				value534 = 0x00;
			}
		});
		jb7.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				value534 = 0x01;
			}
		});
	}

	public void show() {
		modbus.setValueWillWrite34_2(valueWillWrite34_2);
		modbus.setValue532(value532);
		modbus.setValue533(value533);
		modbus.setValue534(value534);
		modbus.setValue535(value535);

		area=String.valueOf(modbus.getDatablocks().get(6).data[7]);//535//528.8
		setFloor=String.valueOf(modbus.getDatablocks().get(6).data[4]);//532
		setRank=String.valueOf(modbus.getDatablocks().get(6).data[5]);//533
		
		j11.setText(area);
		j21.setText(setFloor);
		j31.setText(setRank);
		
		//GO已生效
		goDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[4], 11);
		if (goDone){
			waitSomeTime2();
			valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[4], 11);
			goDone=false;
		}

		//down已生效
		downDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[4], 12);
		if (downDone){
			waitSomeTime2();
			valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[4], 12);
			downDone=false;
		}


		//up已生效
		upDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[4], 13);
		if (upDone){
			waitSomeTime2();
			valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[4], 13);
			upDone=false;
		}
		

		//next已生效
		nextDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[4], 14);
		if (nextDone){
			waitSomeTime2();
			valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[4], 14);
			nextDone=false;
		}

		//last已生效
		lastDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[4], 15);
		if (lastDone){
			waitSomeTime2();
			valueWillWrite34_2 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[4], 15);
			lastDone=false;
		}
		// 按钮last颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[4], 15)) {
			jb1.setBackground(Color.green);
		} else {
			jb1.setBackground(null);
		}
		// 按钮next颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[4], 14)) {
			jb2.setBackground(Color.green);
		} else {
			jb2.setBackground(null);
		}
		// 按钮up颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[4], 13)) {
			jb3.setBackground(Color.green);
		} else {
			jb3.setBackground(null);
		}
		// 按钮down颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[4], 12)) {
			jb4.setBackground(Color.green);
		} else {
			jb4.setBackground(null);
		}
		// 按钮GO颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[4], 11)) {
			jb5.setBackground(Color.green);
		} else {
			jb5.setBackground(null);
		}
//		System.out.println("左右按钮值534="+modbus.getDatablocks().get(6).data[6]);
		// 左侧按钮的颜色指示
		if (modbus.getDatablocks().get(6).data[6] == 0) {
			jb6.setBackground(Color.green);
		} else if (modbus.getDatablocks().get(6).data[6] == 1) {
			jb6.setBackground(null);
		}
		// 右侧按钮的颜色指示
		if (modbus.getDatablocks().get(6).data[6] == 0) {
			jb7.setBackground(null);
		} else if (modbus.getDatablocks().get(6).data[6] == 1) {
			jb7.setBackground(Color.green);
		}

		if (modbus.getDatablocks().get(6).data[7] == 1) {
			jcb3.setSelectedItem("测试最后1列");
		}
	}
	public void waitSomeTime(){
		try {
			Thread.sleep(2000);
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
	}
	public void waitSomeTime2(){
		try {
			Thread.sleep(500);
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
	}
}