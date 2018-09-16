package com.displayWindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.Information.StockerInfo;
import com.SunToolKit.DifferTypeDataSwitch;
import com.SunToolKit.WriteAnyoneBit;
import com.css.BorderStyle1PixGray;
import com.modbus.Modbus;

public class Control_6 extends JPanel{

	StockerInfo stockerInfo = null;
	Modbus modbus = null;	
	int delayTime=500;
	
	JButton jb1=null;
	JButton jb2=null;
	JButton jb3=null;

	int valueWillWrite34_3=0;
	int valueWillWrite700=0;
	boolean wakeSelfTemp=false;
	
	public Control_6(StockerInfo stockerInfo, Modbus mod) {

		this.stockerInfo = stockerInfo;
		this.modbus = mod;
		
		jb1=new JButton("自学习启");
		jb2=new JButton("自学习停");
		jb3=new JButton("自苏醒");		
		
		jb1.setFocusPainted(false);
		jb2.setFocusPainted(false);
		jb3.setFocusPainted(false);		
		
		this.setLayout(null);
		this.setOpaque(false);
		BorderStyle1PixGray.CreateLineBorder(this);
		
		this.add(jb1);
		jb1.setBounds(30, 5, 90, 30);
		this.add(jb2);
		jb2.setBounds(140, 5, 90, 30);
		this.add(jb3);
		jb3.setBounds(250, 5, 90, 30);
		
		// MW34:X0 自学习启动 (点击瞬间ON)
		jb1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite34_3 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[4], 0);
			}
			public void mouseReleased(MouseEvent e) {
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				valueWillWrite34_3 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
						.getDatablocks().get(5).data[4], 0);
			}
		});
		// MW34:X1 自学习停止 (点击瞬间ON)
		jb2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite34_3 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[4], 1);
			}
//			public void mouseReleased(MouseEvent e) {
//				try {
//					Thread.sleep(delayTime);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				valueWillWrite34_3 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
//						.getDatablocks().get(5).data[4], 1);
//			}
		});

		// MW700:X0 自苏醒 (位翻转)
		jb3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!wakeSelfTemp) {
					valueWillWrite700 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
							.getDatablocks().get(7).data[0], 0);
				} else if (wakeSelfTemp) {
					valueWillWrite700 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
							.getDatablocks().get(7).data[0], 0);
				}
			}
			public void mouseReleased(MouseEvent e) {
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				wakeSelfTemp = !wakeSelfTemp;
			}
		});
	}
	public void show(){
		modbus.setValueWillWrite34_3(valueWillWrite34_3);
		modbus.setValueWillWrite700(valueWillWrite700);
		
		// 自学习启动按钮的颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(0).data[0], 9)) {
			jb1.setBackground(Color.green);
		} else {
			jb1.setBackground(null);
		}
		
		// 自学习停止按钮的颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[4], 1)) {
			jb2.setBackground(Color.red);
			try {
				Thread.sleep(delayTime);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			valueWillWrite34_3 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
					.getDatablocks().get(5).data[4], 1);
		} else {
			jb2.setBackground(null);
		}
		
		// 自苏醒按钮的颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(7).data[0], 0)) {
			jb3.setBackground(Color.green);
		} else {
			jb3.setBackground(null);
		}
	}
}