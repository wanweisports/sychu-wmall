package com.css;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class BorderStyle1PixGray{// 加1像素边框，方法重载。
//	public static void CreateLineBorder() {
//		setBorder(BorderFactory.createLineBorder(Color.gray, 1));
//	}
	public static void CreateLineBorder(JPanel j) {
		j.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
	}
	public static void CreateLineBorder(JLabel j) {
		j.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
	}
	
}

