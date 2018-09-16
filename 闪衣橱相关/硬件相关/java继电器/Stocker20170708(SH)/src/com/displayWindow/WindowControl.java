package com.displayWindow;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.Information.StockerInfo;
import com.css.BorderStyle1PixGray;
import com.modbus.Modbus;

public class WindowControl extends JPanel{

	StockerInfo stockerInfo = null;
	Modbus modbus=null;

	Control_1 control_1=null;
	Control_2 control_2=null;
	Control_3 control_3=null;
	Control_4 control_4=null;
	Control_5 control_5=null;
	Control_6 control_6=null;
	
	public WindowControl(StockerInfo stockerInfo,Modbus mod){

		this.stockerInfo = stockerInfo;
		this.setLayout(null);
		this.setOpaque(false);
		this.modbus=mod;
		
		control_1=new Control_1(stockerInfo);
		control_2=new Control_2(stockerInfo);	
		control_3=new Control_3(stockerInfo, modbus);
		control_4=new Control_4(stockerInfo, modbus);
		control_5=new Control_5(stockerInfo, modbus);
		control_6=new Control_6(stockerInfo, modbus);
		
		control_1.setOpaque(false);
		control_2.setOpaque(false);
		control_3.setOpaque(false);
		control_4.setOpaque(false);
		control_5.setOpaque(false);	
		control_6.setOpaque(false);		

		this.add(control_1);
		control_1.setBounds(0, 0, 662,76);
		BorderStyle1PixGray.CreateLineBorder(control_1);
//		this.add(control_2);
//		control_2.setBounds(0, 78, 662,78);
//		BorderStyle1PixGray.CreateLineBorder(control_2);
		this.add(control_3);
		control_3.setBounds(0, 78, 662,190);
		BorderStyle1PixGray.CreateLineBorder(control_3);
		this.add(control_4);
		control_4.setBounds(0, 270, 662, 100);
		BorderStyle1PixGray.CreateLineBorder(control_4);
		this.add(control_5);
		control_5.setBounds(0, 372, 662, 100);
		BorderStyle1PixGray.CreateLineBorder(control_5);
		this.add(control_6);
		control_6.setBounds(0, 474, 662, 39);
		BorderStyle1PixGray.CreateLineBorder(control_6);
	}
	public void show(){

		control_1.show();
//		control_2.show();
		control_3.show();
		control_4.show();
		control_5.show();
		control_6.show();
	}
}