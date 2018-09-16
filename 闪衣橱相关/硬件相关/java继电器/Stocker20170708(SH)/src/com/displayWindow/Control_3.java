package com.displayWindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.Information.StockerInfo;
import com.SunToolKit.*;
import com.css.BorderStyle1PixGray;
import com.modbus.Modbus;

public class Control_3 extends JPanel {

	boolean JogForward;
	boolean JogBackward;

	StockerInfo stockerInfo = null;
	Modbus modbus = null;
	int delayTime = 500;

	Image im1 = null;
	Image im2 = null;

	JLabel j1 = null;
	JLabel j2 = null;
	JLabel j3 = null;
	JLabel j31 = null;
	JLabel j4 = null;
	JLabel j41 = null;
	JLabel j5 = null;
	JLabel j6 = null;
	JLabel j7 = null;
	JLabel j8 = null;

	String stockerNO = null;

	// 遮罩层，用来控制手自动旋钮
	JLabel div = null;

	JButton jb1 = null;
	JButton jb2 = null;
	JButton jb3 = null;
	JButton jb4 = null;
	JButton jb5 = null;
	JButton jb6 = null;
	JButton jb7 = null;
	JButton jb8 = null;
	JButton jb9 = null;
	JButton jb10 = null;
	JButton jb11 = null;
	JButton jb12 = null;
	JButton jb13 = null;
	JButton jb14 = null;
	boolean resetDone =false;
	boolean startDone =false;
	boolean stopDone =false;
	boolean clearDone =false;
	boolean middleDone =false;
	boolean secWriteDone =false;

	// MW30:X0 HMI自动 (位翻转)
	// MW30:X1 HMI启动 (点击瞬间ON)
	// MW30:X2 HMI停止 (点击瞬间ON)
	// MW30:X3 异常复位 (点击瞬间ON)
	// MW30:X4 强制屏蔽 (位翻转)
	// MW30:X5 手动维修 (位翻转)
	// MW30:X6 禁用软限位
	// MW30:X7 强制清除作业 (点击瞬间ON)
	// MW30:X8 取消限宽 (位翻转)
	// MW30:X9 取消限高 (位翻转)
	// MW30:X10 屏蔽障碍物检查 (位翻转)
	// MW30:X11 无
	// MW30:X12 已人工卸货 (点击置位，释放复位)
	// MW30:X13 读托盘号 (点击瞬间ON)
	// MW30:X14 示教启动 (点击瞬间ON)
	// MW30:X15 示教停止 (点击瞬间ON)

	// MW33:X2 货叉回中位 (点击瞬间ON)

	int valueWillWrite30 = 0;
	int valueWillWrite31 = 0;
	int valueWillWrite32 = 0;
	int valueWillWrite33 = 0;
	int valueWillWrite36 = 0;

	// 手自动切换的中间变量
	boolean temp = false;

	// 手动维修的中间变量
	boolean manualMaintenanceTemp = false;
	boolean HMIAutoTemp = false;
	boolean useWidthLimitTemp = false;
	boolean useHighLimitTemp = false;
	boolean eStopTemp = false;

	public Control_3(StockerInfo stockerInfo, Modbus mod) {

		this.stockerInfo = stockerInfo;
		this.modbus = mod;

		im1 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/images/rotaryButton1.png"));
		im2 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/images/rotaryButton2.png"));

		stockerNO = String.valueOf(stockerInfo.getStockerNO());

		j1 = new JLabel("手动");
		j2 = new JLabel("自动");
		j3 = new JLabel("有作业", JLabel.CENTER);
		j31 = new JLabel("无作业", JLabel.CENTER);
		j4 = new JLabel("运行中", JLabel.CENTER);
		j41 = new JLabel("停止中", JLabel.CENTER);
		j5 = new JLabel("堆垛机号", JLabel.CENTER);
		j6 = new JLabel(stockerNO, JLabel.CENTER);
		j7 = new JLabel("HMI");
		j8 = new JLabel("HMI");
		j7.setFont(new Font("宋体", 0, 12));
		j8.setFont(new Font("宋体", 0, 12));

		div = new JLabel();

		j1.setFont(new Font("宋体", 0, 12));
		j2.setFont(new Font("宋体", 0, 12));

		jb1 = new JButton("启动");
		jb2 = new JButton("停止");
		jb3 = new JButton("故障复位");
		jb4 = new JButton("手动维修");
		jb5 = new JButton("急停");
		jb6 = new JButton("清除作业");
		jb7 = new JButton("货叉回中");
		jb8 = new JButton("禁用限宽");
		jb9 = new JButton("禁用限高");
		jb10 = new JButton("激光反写");
		jb11 = new JButton("点动前进");
		jb12 = new JButton("点动后退");
		jb13 = new JButton("点动上升");
		jb14 = new JButton("点动下降");

		jb1.setFocusPainted(false);
		jb2.setFocusPainted(false);
		jb3.setFocusPainted(false);
		jb4.setFocusPainted(false);
		jb5.setFocusPainted(false);
		jb6.setFocusPainted(false);
		jb7.setFocusPainted(false);
		jb8.setFocusPainted(false);
		jb9.setFocusPainted(false);
		jb10.setFocusPainted(false);
		jb11.setFocusPainted(false);
		jb12.setFocusPainted(false);
		jb13.setFocusPainted(false);
		jb14.setFocusPainted(false);

		this.setLayout(null);
		this.setOpaque(false);
		BorderStyle1PixGray.CreateLineBorder(this);

		this.add(j1);
		j1.setBounds(147, 35, 60, 30);
		this.add(j2);
		j2.setBounds(230, 35, 60, 30);

		this.add(j7);
		j7.setBounds(150, 20, 60, 30);
		this.add(j8);
		j8.setBounds(233, 20, 60, 30);

		// 有作业
		this.add(j3);
		this.add(j31);

		// 停止中
		this.add(j4);
		this.add(j41);

		// 注意堆垛机号
		this.add(j5);
		j5.setBounds(5, 45, 120, 14);
		j5.setFont(new Font("宋体", 0, 12));
		// 堆垛机号
		this.add(j6);
		j6.setBounds(5, 48, 120, 120);
		j6.setFont(new Font("宋体", 1, 100));

		this.add(div);
		div.setBounds(165, 10, 70, 70);
		// MW30:X0 HMI自动 (位翻转)
		div.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!HMIAutoTemp) {
					valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
							.getDatablocks().get(5).data[0], 0);

					
				} else if (HMIAutoTemp) {
					valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitFalse(
							modbus.getDatablocks().get(5).data[0], 0);
				}
			}

			public void mouseReleased(MouseEvent e) {
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				HMIAutoTemp = !HMIAutoTemp;
			}
		});

		this.add(jb1);
		jb1.setBounds(150, 90, 90, 30);
		this.add(jb2);
		jb2.setBounds(250, 90, 90, 30);
		this.add(jb3);
		// jb3.setBounds(20, 70, 90, 30);
		jb3.setBounds(350, 90, 90, 30);
		this.add(jb4);
		jb4.setBounds(450, 90, 90, 30);
		this.add(jb5);
		jb5.setBounds(150, 140, 90, 30);

		this.add(jb6);
		jb6.setBounds(550, 90, 90, 30);
		this.add(jb7);
		jb7.setBounds(250, 140, 90, 30);
		this.add(jb8);
		jb8.setBounds(350, 140, 90, 30);
		this.add(jb9);
		jb9.setBounds(450, 140, 90, 30);
		this.add(jb10);
		jb10.setBounds(550, 140, 90, 30);
		this.add(jb11);
		jb11.setBounds(450, 50, 90, 30);
		this.add(jb12);
		jb12.setBounds(550, 50, 90, 30);
		this.add(jb13);
		jb13.setBounds(450, 10, 90, 30);
		this.add(jb14);
		jb14.setBounds(550, 10, 90, 30);

		// MW30:X1 启动 (点击瞬间ON)
		jb1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[0], 1);
				waitSomeTime();
			}

//			public void mouseReleased(MouseEvent e) {
//				valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
//						.getDatablocks().get(5).data[0], 1);
//			}
		});

		// MW30:X2 停止 (点击瞬间ON)
		jb2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[0], 2);
				waitSomeTime();
			}
		});

		// MW30:X3 异常复位 (点击瞬间ON)
		jb3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[0], 3);
				waitSomeTime();
			}
//			public void mouseReleased(MouseEvent e) {				
//			}
		});

		// MW30:X5 手动维修 (位翻转)
		jb4.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!manualMaintenanceTemp) {
					valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
							.getDatablocks().get(5).data[0], 5);
				} else if (manualMaintenanceTemp) {
					valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitFalse(
							modbus.getDatablocks().get(5).data[0], 5);
				}
			}

			public void mouseReleased(MouseEvent e) {
				manualMaintenanceTemp = !manualMaintenanceTemp;
			}
		});

		// MW31:X15 急停 (位翻转)
		jb5.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!eStopTemp) {
					valueWillWrite31 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
							.getDatablocks().get(5).data[1], 15);
				} else if (eStopTemp) {
					valueWillWrite31 = WriteAnyoneBit.WriteAnyoneBitFalse(
							modbus.getDatablocks().get(5).data[1], 15);
				}
			}

			public void mouseReleased(MouseEvent e) {
				eStopTemp = !eStopTemp;
			}
		});

		// MW30:X7 强制清除作业 (点击瞬间ON)
		jb6.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[0], 7);
				waitSomeTime();
			}
		});

		// MW33:X2 货叉回中位 (点击瞬间ON)
		jb7.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite33 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[3], 2);
				waitSomeTime();
			}
		});

		// MW30:X8 启用限宽 (位翻转)
		jb8.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!useWidthLimitTemp) {
					valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
							.getDatablocks().get(5).data[0], 8);
				} else if (useWidthLimitTemp) {
					valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitFalse(
							modbus.getDatablocks().get(5).data[0], 8);
				}
			}

			public void mouseReleased(MouseEvent e) {
				useWidthLimitTemp = !useWidthLimitTemp;
			}
		});

		// MW30:X9 启用限高 (位翻转)
		jb9.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (!useHighLimitTemp) {
					valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
							.getDatablocks().get(5).data[0], 9);
				} else if (useHighLimitTemp) {
					valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitFalse(
							modbus.getDatablocks().get(5).data[0], 9);
				}
			}

			public void mouseReleased(MouseEvent e) {
				useHighLimitTemp = !useHighLimitTemp;
			}
		});

		// MW36:X2 激光反写 (点击瞬间ON)
		jb10.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite36 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[6], 2);
				waitSomeTime();
			}
		});

		// 点动前进 (点击瞬间ON)MW31:X7
		jb11.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite31 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[1], 7);
			}

			public void mouseReleased(MouseEvent e) {
				valueWillWrite31 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
						.getDatablocks().get(5).data[1], 7);
			}
		});

		// 点动后退 (点击瞬间ON)MW31:X8
		jb12.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite31 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[1], 8);
			}

			public void mouseReleased(MouseEvent e) {
				valueWillWrite31 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
						.getDatablocks().get(5).data[1], 8);
			}
		});
		// 点动上升 (点击瞬间ON)MW32:X7
		jb13.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite32 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[2], 7);
			}

			public void mouseReleased(MouseEvent e) {
				valueWillWrite32 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
						.getDatablocks().get(5).data[2], 7);
			}
		});

		// 点动下降 (点击瞬间ON)MW32:X8
		jb14.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				valueWillWrite32 = WriteAnyoneBit.WriteAnyoneBitTrue(modbus
						.getDatablocks().get(5).data[2], 8);
			}

			public void mouseReleased(MouseEvent e) {
				valueWillWrite32 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
						.getDatablocks().get(5).data[2], 8);
			}
		});
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (!DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[0], 0)) {
			g.drawImage(im1, 170, 15, 60, 60, this);
		} else {
			g.drawImage(im2, 170, 15, 60, 60, this);
		}
	}

	public void show() {
		modbus.setValueWillWrite30(valueWillWrite30);
		modbus.setValueWillWrite31(valueWillWrite31);
		modbus.setValueWillWrite32(valueWillWrite32);
		modbus.setValueWillWrite33(valueWillWrite33);
		modbus.setValueWillWrite36(valueWillWrite36);
		
		//复位已生效
		resetDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[0], 3);
		if (resetDone){
			valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[0], 3);
			resetDone=false;
		}
		//启动已生效
		startDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[0], 1);
		if (startDone){
			valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[0], 1);
			startDone=false;
		}
		//停止已生效
		stopDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[0], 2);
		if (stopDone){
			valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[0], 2);
			stopDone=false;
		}
		//清作业已生效
		clearDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[0], 7);
		if (clearDone){
			valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[0], 7);
			clearDone=false;
		}
		//回中位已生效
		middleDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[3], 2);
		if (middleDone){
			valueWillWrite33 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[3], 2);
			middleDone=false;
		}

		//激光反写已生效
		secWriteDone = GetAnyDataOneBit.GetOneBit(modbus
				.getDatablocks().get(5).data[6], 2);
		if (secWriteDone){
			valueWillWrite36 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
				.getDatablocks().get(5).data[6], 2);
			secWriteDone=false;
		}

		// 有作业
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(0).data[0], 5)) {
			j3.setBounds(300, 30, 60, 30);
			j31.setBounds(3000, 30, 60, 30);
			j3.setOpaque(true);
			j3.setBackground(Color.green);
			j3.setBorder(BorderFactory.createLineBorder(Color.gray, 1));

		} else {
			j31.setBounds(300, 30, 60, 30);
			j3.setBounds(3000, 30, 60, 30);
			j31.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		}

		// 停止中
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(0).data[0], 4)) {
			j4.setBounds(370, 30, 60, 30);
			j41.setBounds(3700, 30, 60, 30);
			j4.setOpaque(true);
			j4.setBackground(Color.green);
			j4.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		} else {
			j41.setBounds(370, 30, 60, 30);
			j4.setBounds(3700, 30, 60, 30);
			j41.setOpaque(true);
			j41.setBackground(Color.orange);
			j41.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		}

		// 启动颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[0], 1)) {
			jb1.setBackground(Color.green);
			try {
				Thread.sleep(delayTime);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
//			valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
//					.getDatablocks().get(5).data[0], 1);
		} else {
			jb1.setBackground(null);
		}

		// 停止颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[0], 2)) {
			jb2.setBackground(Color.green);
			try {
				Thread.sleep(delayTime);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
					.getDatablocks().get(5).data[0], 2);
		} else {
			jb2.setBackground(null);
		}

		// 故障复位颜色指示
		if (stockerInfo.getErrorCode() != 0) {
			jb3.setBackground(Color.red);
		} else {
			jb3.setBackground(null);
		}

		// 手动维修按钮的颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[0], 5)) {
			jb4.setBackground(Color.orange);
		} else {
			jb4.setBackground(null);
		}

		// 急停按钮的颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[1], 15)) {
			jb5.setBackground(Color.red);
		} else {
			jb5.setBackground(null);
		}

		// 清除作业按钮的颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[0], 7)) {
			jb6.setBackground(Color.green);
			try {
				Thread.sleep(delayTime);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			valueWillWrite30 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
					.getDatablocks().get(5).data[0], 7);
		} else {
			jb6.setBackground(null);
		}

		// 货叉回中按钮的颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[3], 2)) {
			jb7.setBackground(Color.green);
			try {
				Thread.sleep(delayTime);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			valueWillWrite33 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
					.getDatablocks().get(5).data[3], 2);
		} else {
			jb7.setBackground(null);
		}

		// 启用限宽按钮的颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[0], 8)) {
			jb8.setBackground(Color.red);
		} else {
			jb8.setBackground(null);
		}
		// 启用限高按钮的颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[0], 9)) {
			jb9.setBackground(Color.red);
		} else {
			jb9.setBackground(null);
		}

		// 激光反写按钮的颜色指示
		if (DifferTypeDataSwitch.IntToBool(
				modbus.getDatablocks().get(5).data[6], 2)) {
			jb10.setBackground(Color.green);
			try {
				Thread.sleep(delayTime);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			valueWillWrite36 = WriteAnyoneBit.WriteAnyoneBitFalse(modbus
					.getDatablocks().get(5).data[6], 2);
		} else {
			jb10.setBackground(null);
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
