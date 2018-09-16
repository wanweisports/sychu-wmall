package com.displayWindow;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.Information.StockerInfo;
import com.modbus.Modbus;

public class DetailWindowCell extends JPanel{
	
	
	StockerInfo stockerInfo = null;
	Modbus modbus=null;
	WindowXYZ windowXYZ = null;
	WindowArrows windowArrows=null;
	WindowControl windowControl=null;

	public DetailWindowCell(StockerInfo stockerInfo,Modbus mod) {
		
		this.stockerInfo = stockerInfo;	
		this.modbus=mod;
		this.setSize(1366,500);
		this.setLayout(null);
		this.setOpaque(false);
		
		windowXYZ=new WindowXYZ(this.stockerInfo);
		windowArrows=new WindowArrows(this.stockerInfo);
		windowControl=new WindowControl(this.stockerInfo, modbus);
		this.add(windowXYZ);
		windowXYZ.setBounds(0, 0, 683, 183);
		this.add(windowArrows);
		windowArrows.setBounds(0, 185, 683, 330);
		this.add(windowControl);
		windowControl.setBounds(685, 0, 662, 513);
	}

	public void ShowInfo() {
		windowXYZ.show();
		windowArrows.show();
		windowControl.show();
		repaint();
	}

}