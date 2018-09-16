package com.displayWindow;

import java.awt.*;

import javax.swing.*;

import com.Information.StockerInfo;
import com.css.BorderStyle1PixGray;


public class WindowArrows extends JPanel{
	
	JPanel jX=null;
	JPanel jY=null;
	JPanel jZ=null;

	JLabel a1 = null;// 后限位
	JLabel a2 = null;// 停准
	JLabel a3 = null;// 前限位
	JLabel a4 = null;// 上限位
	JLabel a5 = null;// 高位
	JLabel a6 = null;// 低位
	JLabel a7 = null;// 下限位
	JLabel a8 = null;// 中位
	JLabel a81 = null;// 左远
	JLabel a82 = null;// 左近
	JLabel a83 = null;// 右近
	JLabel a84 = null;// 右远
	JLabel a9 = null;// 取货中
	JLabel a10 = null;// 放货中
	JLabel a11 = null;// 无货	
	JLabel a12 = null;// 有货
	JLabel a13 = null;// 抽屉未关	
	JLabel a14 = null;// 抽屉已关

	JLabel b0 = null;//左限宽1	MW1008.0
	JLabel b1 = null;//左限宽2	MW1008.1
	JLabel b2 = null;//右限宽1	MW1008.2
	JLabel b3 = null;//右限宽2	MW1008.3
	JLabel b4 = null;//左近探低	MW1008.6
	JLabel b5 = null;//左远探		MW1008.7
	JLabel b6 = null;//右近探低	MW1008.8
	JLabel b7 = null;//右远探		MW1008.9
	JLabel b8 = null;//左近探高	MW1008.13
	JLabel b9 = null;//右近探高	MW1008.14
	
	// 水平箭头指示
	Image im1 = null;
	Image im2 = null;
	// 垂直箭头指示
	Image im3 = null;
	Image im4 = null;
	// 货叉左侧动作箭头指示
	Image im5 = null;
	Image im6 = null;
	// 货叉右侧动作箭头指示
	Image im7 = null;
	Image im8 = null;

	boolean temp1 = true;
	boolean temp2 = true;
	boolean temp3 = true;
	boolean temp4 = true;
	boolean temp5 = true;
	boolean temp6 = true;
	boolean temp7 = true;
	boolean temp8 = true;
	
	StockerInfo stockerInfo = null;

	public WindowArrows(StockerInfo stockerInfo){
		
		this.stockerInfo = stockerInfo;	
		this.setBounds(0, 0, 683, 207);
		this.setLayout(null);
		this.setOpaque(false);
		
		jX=new JPanel();
		jX.setLayout(null);
		jX.setOpaque(false);
		BorderStyle1PixGray.CreateLineBorder(jX);
		

		jZ=new JPanel();
		jZ.setLayout(null);
		jZ.setOpaque(false);
		BorderStyle1PixGray.CreateLineBorder(jZ);
		
		jY=new JPanel();
		jY.setLayout(null);
		jY.setOpaque(false);
		BorderStyle1PixGray.CreateLineBorder(jY);

		a1 = new JLabel("后限位", JLabel.CENTER);// 后限位
		a2 = new JLabel("停准", JLabel.CENTER);// 停准
		a3 = new JLabel("前限位", JLabel.CENTER);// 前限位
		a4 = new JLabel("上限位", JLabel.CENTER);// 上限位
		a5 = new JLabel("高位", JLabel.CENTER);// 高位
		a6 = new JLabel("低位", JLabel.CENTER);// 低位
		a7 = new JLabel("下限位", JLabel.CENTER);// 下限位
		a8 = new JLabel("货叉中位", JLabel.CENTER);// 中
		a81 = new JLabel("左远位", JLabel.CENTER);// 左远位
		a82 = new JLabel("左近位", JLabel.CENTER);// 左近位
		a83 = new JLabel("右近位", JLabel.CENTER);// 右近位
		a84 = new JLabel("右远位", JLabel.CENTER);// 右远位
		a9 = new JLabel("取货中", JLabel.CENTER);// 取货中
		a10 = new JLabel("放货中", JLabel.CENTER);// 放货中
		a11 = new JLabel("无货", JLabel.CENTER);// 有无货
		a12 = new JLabel("有货", JLabel.CENTER);// 有无货
		a13 = new JLabel("抽屉未关", JLabel.CENTER);// 有无货
		a14 = new JLabel("抽屉已关", JLabel.CENTER);// 有无货
		
		b0 = new JLabel("左限宽1", JLabel.CENTER);//左限宽1	MW1008.0
		b1 = new JLabel("左限宽2", JLabel.CENTER);//左限宽2	MW1008.1
		b2 = new JLabel("右限宽1", JLabel.CENTER);//右限宽1	MW1008.2
		b3 = new JLabel("右限宽2", JLabel.CENTER);//右限宽2	MW1008.3
		b4 = new JLabel("左近探低位", JLabel.CENTER);//左近探低	MW1008.6
		b5 = new JLabel("左远探", JLabel.CENTER);//左远探		MW1008.7
		b6 = new JLabel("右近探低位", JLabel.CENTER);//右近探低	MW1008.8
		b7 = new JLabel("右远探", JLabel.CENTER);//右远探		MW1008.9
		b8 = new JLabel("左近探高位", JLabel.CENTER);//左近探高	MW1008.13
		b9 = new JLabel("右近探高位", JLabel.CENTER);//右近探高	MW1008.14
		
		// 水平后退的箭头
		im1 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/images/arrow_left.png"));
		// 水平前进的箭头
		im2 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/images/arrow_right.png"));
		// 垂直向上的箭头
		im3 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/images/arrow_up.png"));
		// 垂直向下的箭头
		im4 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/images/arrow_down.png"));
		// 货叉左侧动作箭头指示
		im5 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/images/arrow_left.png"));
		im6 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/images/arrow_right.png"));
		// 货叉右侧动作箭头指示
		im7 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/images/arrow_left.png"));
		im8 = Toolkit.getDefaultToolkit().getImage(
				Panel.class.getResource("/images/arrow_right.png"));
		
		jX.add(a1);
		jX.add(a2);
		jX.add(a3);
		jX.add(a13);
		jX.add(a14);
		this.add(jX);
		jX.setBounds(0, 0, 683, 60);		

		jZ.add(a4);
		jZ.add(a5);
		jZ.add(a6);
		jZ.add(a7);
		this.add(jZ);
		jZ.setBounds(0, 62, 180, 266);		

		jY.add(a8);
		jY.add(a81);
		jY.add(a82);
		jY.add(a83);
		jY.add(a84);
		jY.add(a9);
		jY.add(a10);	
		jY.add(a11);		
		jY.add(a12);
		jY.add(b0);	
		jY.add(b1);	
		jY.add(b2);	
		jY.add(b3);	
		jY.add(b4);	
		jY.add(b5);	
		jY.add(b6);	
		jY.add(b7);	
		jY.add(b8);	
		jY.add(b9);
		this.add(jY);
		jY.setBounds(182, 62, 501, 266);		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if (stockerInfo.isxBackwardMoving() && temp1) {
			g.drawImage(im1, 75, 20, 170, 18, this);
			temp1 = false;
		} else {
			temp1 = true;
		}
		if (stockerInfo.isxForwardMoving() && temp2) {
			g.drawImage(im2, 335, 20, 170, 18, this);
			temp2 = false;
		} else {
			temp2 = true;
		}
		if (stockerInfo.iszUpstairMoving() && temp3) {
			g.drawImage(im3, 117, 71, 16, 250, this);
			temp3 = false;
		} else {
			temp3 = true;
		}
		if (stockerInfo.iszDownstairMoving() && temp4) {
			g.drawImage(im4, 117, 71, 16, 250, this);
			temp4 = false;
		} else {
			temp4 = true;
		}

		if (stockerInfo.isyStretchOutToLeftMoving() && temp5) {
			g.drawImage(im5, 220, 115, 200, 15, this);
			temp5 = false;
		} else {
			temp5 = true;
		}
		if (stockerInfo.isyDrawBackToLeftMoving() && temp6) {
			g.drawImage(im6, 220, 115, 200, 15, this);
			temp6 = false;
		} else {
			temp6 = true;
		}
		if (stockerInfo.isyDrawBackToRightMoving() && temp7) {
			g.drawImage(im7, 430, 115, 200, 15, this);
			temp7 = false;
		} else {
			temp7 = true;
		}
		if (stockerInfo.isyStretchOutToRightMoving() && temp8) {
			g.drawImage(im8, 430, 115, 200, 15, this);
			temp8 = false;
		} else {
			temp8 = true;
		}
	}
	public void show(){
	
		// 后限位实时显示		
		a1.setBounds(18, 18, 54, 24);
		if (this.stockerInfo.isxLimitBackward()) {
			a1.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a1);
		} else {		
			a1.setOpaque(true);
			a1.setBackground(Color.RED);
			BorderStyle1PixGray.CreateLineBorder(a1);
		}
		
		//停准位实时显示		
		a2.setBounds(260, 18, 64, 24);
		if (this.stockerInfo.isxStopOK()) {	
			a2.setOpaque(true);
			a2.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(a2);
		} else {
			a2.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a2);
		}
		
		// 前限位实时显示
		a3.setBounds(512, 18, 54, 24);
		if (this.stockerInfo.isxLimitForward()) {
			a3.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a3);
		} else {	
			a3.setOpaque(true);
			a3.setBackground(Color.RED);
			BorderStyle1PixGray.CreateLineBorder(a3);
		}
		
		//抽屉状态显示
		if (this.stockerInfo.isDrawerOpened()) {
			a14.setBounds(590, 18, 74, 24);
			a13.setBounds(-100, -100, 54, 24);	
			a14.setOpaque(true);
			a14.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(a14);
		} else {
			a13.setBounds(590, 18, 74, 24);
			a14.setBounds(-100, -100, 54, 24);	
			a13.setOpaque(true);
			a13.setBackground(Color.orange);
			BorderStyle1PixGray.CreateLineBorder(a13);
		}
		
		//垂直上限位
		a4.setBounds(18, 20, 54, 24);
		if (this.stockerInfo.iszLimitHigh()) {
			a4.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a4);
		} else {	
			a4.setOpaque(true);
			a4.setBackground(Color.RED);
			BorderStyle1PixGray.CreateLineBorder(a4);
		}
		//垂直下限位
		a7.setBounds(18, 226, 54, 24);
		if (this.stockerInfo.iszLimitLow()) {
			a7.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a7);
		} else {	
			a7.setOpaque(true);
			a7.setBackground(Color.RED);
			BorderStyle1PixGray.CreateLineBorder(a7);
		}

		//垂直高位实时显示
		a5.setBounds(18, 108, 54, 24);
		if (this.stockerInfo.iszIsOnHighPosition()) {	
			a5.setOpaque(true);
			a5.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(a5);
		} else {
			a5.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a5);
		}
		//垂直低位实时显示
		a6.setBounds(18, 134, 54, 24);
		if (this.stockerInfo.iszIsOnLowPosition()) {	
			a6.setOpaque(true);
			a6.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(a6);
		} else {
			a6.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a6);
		}		
		
		//货叉中位实时显示
		a8.setBounds(217, 17, 64, 24);
		if (this.stockerInfo.isyIsOnMiddle()) {	
			a8.setOpaque(true);
			a8.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(a8);
		} else {
			a8.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a8);
		}
		
		a81.setBounds(40, 17, 64, 24);// 左远位实时显示
		if (this.stockerInfo.isyIsOnLeftFar()) {	
			a81.setOpaque(true);
			a81.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(a81);
		} else {
			a81.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a81);
		}
		
		a82.setBounds(130, 17, 64, 24);// 左近位实时显示
		if (this.stockerInfo.isyIsOnLeftNear()) {	
			a82.setOpaque(true);
			a82.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(a82);
		} else {
			a82.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a82);
		}
		
		a83.setBounds(305, 17, 64, 24);// 右近位实时显示
		if (this.stockerInfo.isyIsOnRightNear()) {	
			a83.setOpaque(true);
			a83.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(a83);
		} else {
			a83.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a83);
		}
		
		a84.setBounds(395, 17, 64, 24);// 右远位实时显示
		if (this.stockerInfo.isyIsOnRightFar()) {	
			a84.setOpaque(true);
			a84.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(a84);
		} else {
			a84.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a84);
		}
		
		//正在取货实时显示	
		a9.setBounds(272, 90, 54, 24);
		if (this.stockerInfo.isPuttingInCargo()) {	
			a9.setOpaque(true);
			a9.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(a9);
		} else {
			a9.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a9);
		}
		

		//正在放货实时显示
		a10.setBounds(175, 90, 54, 24);
		if (this.stockerInfo.isTakingOffCargo()) {
			a10.setOpaque(true);
			a10.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(a10);
		} else {
			a10.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a10);
		}
		
		//有无货实时显示
		if (this.stockerInfo.isCarHasCargo()) {
			a12.setBounds(223, 160, 54, 54);
			a11.setBounds(-100, -100, 54, 54);
			a12.setOpaque(true);
			a12.setBackground(Color.green);
			BorderStyle1PixGray.CreateLineBorder(a12);
		} else {
			a11.setBounds(223, 160, 54, 54);
			a12.setBounds(-100, -100, 54, 54);
			a11.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(a11);
		}
		
		//左限宽1实时显示
		b0.setBounds(106, 150, 60, 24);
		if (this.stockerInfo.isLimitSwitchWidthLeft1()) {
			b0.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(b0);
		} else {
			b0.setOpaque(true);
			b0.setBackground(Color.red);
			BorderStyle1PixGray.CreateLineBorder(b0);
		}
		
		//左限宽2实时显示
		b1.setBounds(106, 200, 60, 24);
		if (this.stockerInfo.isLimitSwitchWidthLeft2()) {
			b1.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(b1);
		} else {
			b1.setOpaque(true);
			b1.setBackground(Color.red);
			BorderStyle1PixGray.CreateLineBorder(b1);
		}
		
		//右限宽1实时显示
		b2.setBounds(333, 150, 60, 24);
		if (this.stockerInfo.isLimitSwitchWidthRight1()) {
			b2.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(b2);
		} else {
			b2.setOpaque(true);
			b2.setBackground(Color.red);
			BorderStyle1PixGray.CreateLineBorder(b2);
		}
		
		//右限宽2实时显示
		b3.setBounds(333, 200, 60, 24);
		if (this.stockerInfo.isLimitSwitchWidthRight2()) {
			b3.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(b3);
		} else {
			b3.setOpaque(true);
			b3.setBackground(Color.red);
			BorderStyle1PixGray.CreateLineBorder(b3);
		}
		
		//左远探实时显示
		b5.setBounds(156, 175, 54, 24);
		if (this.stockerInfo.isLimitSwitchFarLeft()) {
			b5.setOpaque(true);
			b5.setBackground(Color.orange);
			BorderStyle1PixGray.CreateLineBorder(b5);
		} else {
			b5.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(b5);
		}
		
		//右远探实时显示
		b7.setBounds(289, 175, 54, 24);
		if (this.stockerInfo.isLimitSwitchFarRight()) {
			b7.setOpaque(true);
			b7.setBackground(Color.orange);
			BorderStyle1PixGray.CreateLineBorder(b7);
		} else {
			b7.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(b7);
		}
		
		//左近探低位实时显示
		b8.setBounds(32, 125, 80, 24);
		if (this.stockerInfo.isLimitSwitchNearLeftLow()) {
			b8.setOpaque(true);
			b8.setBackground(Color.orange);
			BorderStyle1PixGray.CreateLineBorder(b8);
		} else {
			b8.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(b8);
		}
		
		//右近探低位实时显示
		b9.setBounds(388, 125, 80, 24);
		if (this.stockerInfo.isLimitSwitchNearRightLow()) {
			b9.setOpaque(true);
			b9.setBackground(Color.orange);
			BorderStyle1PixGray.CreateLineBorder(b9);
		} else {
			b9.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(b9);
		}
		
		//左近探高位实时显示
		b4.setBounds(32, 225, 80, 24);
		if (this.stockerInfo.isLimitSwitchNearLeftHigh()) {
			b4.setOpaque(true);
			b4.setBackground(Color.orange);
			BorderStyle1PixGray.CreateLineBorder(b4);
		} else {
			b4.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(b4);
		}
		
		//右近探高位实时显示
		b6.setBounds(388, 225, 80, 24);
		if (this.stockerInfo.isLimitSwitchNearRightHigh()) {
			b6.setOpaque(true);
			b6.setBackground(Color.orange);
			BorderStyle1PixGray.CreateLineBorder(b6);
		} else {
			b6.setOpaque(false);
			BorderStyle1PixGray.CreateLineBorder(b6);
		}
	}


}