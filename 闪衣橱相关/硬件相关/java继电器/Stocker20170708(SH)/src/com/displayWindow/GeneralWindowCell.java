package com.displayWindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.Information.StockerInfo;

public class GeneralWindowCell extends JPanel {

	private StockerInfo stockerInfo = null;

	// 作为字符串连接
	String strViewWhichStocker = null;
	String strViewFloorsRankRow = null;
	String strViewStatus = null;

	// 运行状况和故障代码的字符串显示
	String viewRunningState = null;
	String viewErrorCode = null;
	String strViewOperationMode = null;

	// 几号堆垛机
	JLabel viewWhichStocker = null;
	// 字符“当前位置”
	JLabel viewCurrentPosition = null;
	// 字符“X层X列X排”
	JLabel viewFloorsRankRow = null;
	// 字符“运行状况”
	JLabel viewRunStatus = null;
	// 运行还是故障
	JLabel viewRunningStatus = null;
	// 字符“故障代码”
	JLabel viewError = null;
	// 内容“故障代码”
	JLabel viewErrorCodeContent = null;
	// 字符“操作模式”
	JLabel viewOperatMode = null;
	// 内容“操作模式”
	JLabel viewOperationMode = null;

	public GeneralWindowCell(StockerInfo stockerInformation) {
		this.stockerInfo = stockerInformation;
		this.setLayout(null);
		this.setSize(150, 140);
		this.setOpaque(false);
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));

		// 作为字符串连接
		strViewWhichStocker = stockerInfo.getStockerNO() + "号堆垛机";
		strViewFloorsRankRow = stockerInfo.getFloors() + "层"
				+ stockerInfo.getRow() + "列" + stockerInfo.getRank() + "排";
		strViewStatus = String.valueOf(stockerInfo.getStatus());
		strViewOperationMode = String.valueOf(stockerInfo.getOperationMode());

		viewWhichStocker = new JLabel(strViewWhichStocker);
		viewFloorsRankRow = new JLabel(strViewFloorsRankRow);
		viewRunningStatus = new JLabel(viewRunningState);
		viewErrorCode = String.valueOf(stockerInfo.getErrorCode());
		viewErrorCodeContent = new JLabel(viewErrorCode);
		viewOperationMode = new JLabel(strViewOperationMode);

		viewCurrentPosition = new JLabel("当前位置：");
		viewRunStatus = new JLabel("运行状况：");
		viewError = new JLabel("故障代码：");
		viewOperatMode = new JLabel("操作模式：");

		// 设置字体
		viewWhichStocker.setFont(new Font("宋体", 0, 12));
		// viewFloorsRankRow.setFont(new Font("宋体", 0, 12));
		viewCurrentPosition.setFont(new Font("宋体", 0, 12));
		viewRunStatus.setFont(new Font("宋体", 0, 12));
		viewError.setFont(new Font("宋体", 0, 12));
		viewOperatMode.setFont(new Font("宋体", 0, 12));

		// X号堆垛机
		this.add(viewWhichStocker);
		viewWhichStocker.setBounds(48, 0, 70, 28);

		// 当前位置
		this.add(viewCurrentPosition);
		viewCurrentPosition.setBounds(17, 28, 60, 28);
		// X层X列X排
		this.add(viewFloorsRankRow);
		viewFloorsRankRow.setBounds(72, 28, 75, 28);

		// 运行状况
		this.add(viewRunStatus);
		viewRunStatus.setBounds(17, 56, 60, 28);
		// 运行状况内容
		this.add(viewRunningStatus);
		viewRunningStatus.setBounds(72, 56, 100, 28);

		// 故障代码
		this.add(viewError);
		viewError.setBounds(17, 84, 60, 28);
		this.add(viewErrorCodeContent);
		viewErrorCodeContent.setBounds(72, 84, 75, 28);
		;
		// 操作模式
		this.add(viewOperatMode);
		viewOperatMode.setBounds(17, 112, 60, 28);
		this.add(viewOperationMode);
		viewOperationMode.setBounds(72, 112, 75, 28);
	}

	public void ShowInfo() {
		strViewWhichStocker = stockerInfo.getStockerNO() + "号堆垛机";
		strViewFloorsRankRow = stockerInfo.getFloors() + "层"
				+ stockerInfo.getRow() + "列" + stockerInfo.getRank() + "排";

		// 运行状态的字符
		strViewStatus = String.valueOf(stockerInfo.getStatus());
		// 操作模式的字符
		strViewOperationMode = String.valueOf(stockerInfo.getOperationMode());
		// 堆垛机号
		viewWhichStocker.setText(strViewWhichStocker);
		// 层列排
		viewFloorsRankRow.setText(strViewFloorsRankRow);
		viewFloorsRankRow.setForeground(Color.black);
		// 故障代码
		if (stockerInfo.getErrorCode() != 0) {
			viewErrorCodeContent.setForeground(Color.red);
			// 闪烁
			if (viewErrorCodeContent.getText().length() == 0) {
				viewErrorCodeContent.setText(stockerInfo.getErrorInfo());
			} else {
				viewErrorCodeContent.setText("");
			}
		} else {
			viewErrorCodeContent.setForeground(Color.black);
			viewErrorCodeContent.setText(stockerInfo.getErrorInfo());
		}
		// 运行状态
		viewRunningStatus.setText(strViewStatus);
		if (stockerInfo.getStatus().equals("None")) {
			viewRunningStatus.setForeground(Color.blue);
		} else {
			viewRunningStatus.setForeground(Color.green);
		}
		if(strViewStatus.length()>6){
			viewRunningStatus.setFont(new Font("宋体", 0, 10));
		}
		// 操作模式
		viewOperationMode.setText(strViewOperationMode);
		viewOperationMode.setForeground(Color.black);
		// 连接情况显示
		if (stockerInfo.isNetworkDisconnected()) {
			viewFloorsRankRow.setText("连接中……");
			viewFloorsRankRow.setForeground(Color.gray);
			viewErrorCodeContent.setText("连接中……");
			viewErrorCodeContent.setForeground(Color.gray);
			viewRunningStatus.setText("连接中……");
			viewRunningStatus.setForeground(Color.gray);
			viewOperationMode.setText("连接中……");
			viewOperationMode.setForeground(Color.gray);
		}
	}
}
