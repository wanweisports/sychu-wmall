package com.displayWindow;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.dataUpdate.DataUpdate;
import com.dataUpdate.DataUpdate2;
import com.modbus.Modbus;
import com.modbus.Modbus2;

/**
 * 主显示窗口，包含两部分： 1、概况部分generalWindow（包括每个堆垛机的概况）。
 * 2、详细部分detailWindow（选项卡形式，每个选项卡内包括堆垛机各自的详细情况）。
 * 
 * @author SunPengFei
 * 
 */
public class MainWindow extends JFrame implements Runnable, WindowListener {
	// 概况部分
	private JPanel generalWindow = null;
	// 选项卡部分
	private JTabbedPane detailWindow = null;

	// 堆垛机概况（堆垛机号，层，列，排，运行状况，错误代码等）
	private GeneralWindowCell generalWindowCell_1 = null;
	private GeneralWindowCell generalWindowCell_2 = null;
	private GeneralWindowCell generalWindowCell_3 = null;
	private GeneralWindowCell generalWindowCell_4 = null;
	private GeneralWindowCell generalWindowCell_5 = null;
	private GeneralWindowCell generalWindowCell_6 = null;
	private GeneralWindowCell generalWindowCell_7 = null;
	private GeneralWindowCell generalWindowCell_8 = null;
	private GeneralWindowCell generalWindowCell_9 = null;

	// 堆垛机详细情况（各种数值显示，箭头显示，按钮操作等）
	private DetailWindowCell detailWindowCell_1 = null;
	private DetailWindowCell detailWindowCell_2 = null;
	private DetailWindowCell detailWindowCell_3 = null;
	private DetailWindowCell detailWindowCell_4 = null;
	private DetailWindowCell detailWindowCell_5 = null;
	private DetailWindowCell detailWindowCell_6 = null;
	private DetailWindowCell detailWindowCell_7 = null;
	private DetailWindowCell detailWindowCell_8 = null;
	private DetailWindowCell detailWindowCell_9 = null;
	
	//旋转站台
	private RotateWindow rotateWindow = null;	
	
	//自学习相关
//	private StudySelf studySelf=null;

	// MODBUS连接
	static Modbus modbus1 = null;
	static Modbus modbus2 = null;
	static Modbus modbus3 = null;
	static Modbus modbus4 = null;
	static Modbus modbus5 = null;
	static Modbus modbus6 = null;
	static Modbus modbus7 = null;
	static Modbus modbus8 = null;
	static Modbus modbus9 = null;
	
	static Modbus2 modbus2_1 = null;

	// 数据更新
	static DataUpdate dataUpdate1 = null;
	static DataUpdate dataUpdate2 = null;
	static DataUpdate dataUpdate3 = null;
	static DataUpdate dataUpdate4 = null;
	static DataUpdate dataUpdate5 = null;
	static DataUpdate dataUpdate6 = null;
	static DataUpdate dataUpdate7 = null;
	static DataUpdate dataUpdate8 = null;
	static DataUpdate dataUpdate9 = null;

	static DataUpdate2 dataUpdate2_1 = null;
	

	// 主函数
	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		Thread t1 = new Thread(mainWindow);
		t1.start();
		modbus();
		update();
	}

	// 构造函数
	public MainWindow() {

		// 添加MODBUS连接
//		 modbus1 = new Modbus("127.0.0.1");
////		 modbus2 = modbus1;
////		 modbus3 = modbus1;
////		 modbus4 = modbus1;
////		 modbus5 = modbus1;
////		 modbus6 = modbus1;
////		 modbus7 = modbus1;
////		 modbus8 = modbus1;
////		 modbus9 = modbus1;
//		 modbus1 = new Modbus("127.0.0.1");
//		 modbus2 = new Modbus("127.0.0.1");
//		 modbus3 = new Modbus("127.0.0.1");
//		 modbus4 = new Modbus("127.0.0.1");
//		 modbus5 = new Modbus("127.0.0.1");
//		 modbus6 = new Modbus("127.0.0.1");
//		 modbus7 = new Modbus("127.0.0.1");
//		 modbus8 = new Modbus("127.0.0.1");
//		 modbus9 = new Modbus("127.0.0.1");
		modbus1 = new Modbus("192.168.21.111");
//		modbus2 = new Modbus("192.168.21.112");
//		modbus3 = new Modbus("192.168.21.113");
//		modbus4 = new Modbus("192.168.21.114");
//		modbus5 = new Modbus("192.168.21.115");
//		modbus6 = new Modbus("192.168.21.116");
//		modbus7 = new Modbus("192.168.21.117");
//		modbus8 = new Modbus("192.168.21.118");
//		modbus9 = new Modbus("192.168.21.119");		
//
//		modbus2_1 = new Modbus2("192.168.21.65");

		// 添加数据更新
		dataUpdate1 = new DataUpdate(1, modbus1);
//		dataUpdate2 = new DataUpdate(2, modbus2);
//		dataUpdate3 = new DataUpdate(3, modbus3);
//		dataUpdate4 = new DataUpdate(4, modbus4);
//		dataUpdate5 = new DataUpdate(5, modbus5);
//		dataUpdate6 = new DataUpdate(6, modbus6);
//		dataUpdate7 = new DataUpdate(7, modbus7);
//		dataUpdate8 = new DataUpdate(8, modbus8);
//		dataUpdate9 = new DataUpdate(9, modbus9);
		
//		dataUpdate2_1 = new DataUpdate2(modbus2_1);
		
		// 概况部分
		generalWindow = new JPanel();
		generalWindow.setLayout(null);
		generalWindow.setOpaque(false);

		// 详细部分选项卡
		UIManager.put("TabbedPane.contentOpaque", false);
		detailWindow = new JTabbedPane(JTabbedPane.TOP);
//		detailWindow.setOpaque(false);

		// 设置布局管理器
		this.setLayout(null);
		this.add(generalWindow);
		generalWindow.setBounds(0, 0, 1366, 140);
		this.add(detailWindow);
		detailWindow.setBounds(0, 140, 1366, 560);

		generalWindowCell_1 = new GeneralWindowCell(dataUpdate1
				.getStockerInfo());
//		generalWindowCell_2 = new GeneralWindowCell(dataUpdate2
//				.getStockerInfo());
//		generalWindowCell_3 = new GeneralWindowCell(dataUpdate3
//				.getStockerInfo());
//		generalWindowCell_4 = new GeneralWindowCell(dataUpdate4
//				.getStockerInfo());
//		generalWindowCell_5 = new GeneralWindowCell(dataUpdate5
//				.getStockerInfo());
//		generalWindowCell_6 = new GeneralWindowCell(dataUpdate6
//				.getStockerInfo());
//		generalWindowCell_7 = new GeneralWindowCell(dataUpdate7
//				.getStockerInfo());
//		generalWindowCell_8 = new GeneralWindowCell(dataUpdate8
//				.getStockerInfo());
//		generalWindowCell_9 = new GeneralWindowCell(dataUpdate9
//				.getStockerInfo());

		generalWindow.add(generalWindowCell_1);
		generalWindowCell_1.setBounds(0, 0, 150, 140);
//		generalWindow.add(generalWindowCell_2);
//		generalWindowCell_2.setBounds(150, 0, 150, 140);
//		generalWindow.add(generalWindowCell_3);
//		generalWindowCell_3.setBounds(300, 0, 150, 140);
//		generalWindow.add(generalWindowCell_4);
//		generalWindowCell_4.setBounds(450, 0, 150, 140);
//		generalWindow.add(generalWindowCell_5);
//		generalWindowCell_5.setBounds(600, 0, 150, 140);
//		generalWindow.add(generalWindowCell_6);
//		generalWindowCell_6.setBounds(750, 0, 150, 140);
//		generalWindow.add(generalWindowCell_7);
//		generalWindowCell_7.setBounds(900, 0, 150, 140);
//		generalWindow.add(generalWindowCell_8);
//		generalWindowCell_8.setBounds(1050, 0, 150, 140);
//		generalWindow.add(generalWindowCell_9);
//		generalWindowCell_9.setBounds(1200, 0, 150, 140);

		// 选项卡里的详细信息内容
		detailWindowCell_1 = new DetailWindowCell(dataUpdate1.getStockerInfo(), modbus1);
//		detailWindowCell_2 = new DetailWindowCell(dataUpdate2.getStockerInfo(), modbus2);
//		detailWindowCell_3 = new DetailWindowCell(dataUpdate3.getStockerInfo(), modbus3);
//		detailWindowCell_4 = new DetailWindowCell(dataUpdate4.getStockerInfo(), modbus4);
//		detailWindowCell_5 = new DetailWindowCell(dataUpdate5.getStockerInfo(), modbus5);
//		detailWindowCell_6 = new DetailWindowCell(dataUpdate6.getStockerInfo(), modbus6);
//		detailWindowCell_7 = new DetailWindowCell(dataUpdate7.getStockerInfo(), modbus7);
//		detailWindowCell_8 = new DetailWindowCell(dataUpdate8.getStockerInfo(), modbus8);
//		detailWindowCell_9 = new DetailWindowCell(dataUpdate9.getStockerInfo(), modbus9);
		
//		rotateWindow = new RotateWindow(modbus2_1,dataUpdate2_1.getRotateInfo());
		
//		studySelf=new StudySelf();

		detailWindow.addTab("1号", detailWindowCell_1);	
//		detailWindow.addTab("2号", detailWindowCell_2);
//		detailWindow.addTab("3号", detailWindowCell_3);
//		detailWindow.addTab("4号", detailWindowCell_4);
//		detailWindow.addTab("5号", detailWindowCell_5);
//		detailWindow.addTab("6号", detailWindowCell_6);
//		detailWindow.addTab("7号", detailWindowCell_7);
//		detailWindow.addTab("8号", detailWindowCell_8);
//		detailWindow.addTab("9号", detailWindowCell_9);
		
		detailWindow.addTab("旋转站台", rotateWindow);
		
		

//		JLabel j1=null;
//		j1=new JLabel("系统时间：2017年1月21日  11时59分23秒");
//		this.add(j1);
//		j1.setBounds(1100, 138, 300, 30);

		// 背景图片
		ImageIcon background = new ImageIcon(getClass().getResource("/images/backGround.png"));
		//ImageIcon background = new ImageIcon("src/images/backGround.png");
		// 把背景图片显示在一个标签里面
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 1366, 700);
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		// 把背景图片添加到分层窗格的最底层作为背景
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		
		
		

		// 标题
		this.setTitle("堆垛机管理系统（上海项目）");
		// 窗体大小
		this.setSize(1366, 720);
		// 初始位置
		this.setLocation(0, 0);
		// 关闭窗体后，也关闭JUM
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 是否显示
		this.setVisible(true);
		this.addWindowListener(this);
	}

	public static void modbus() {
		modbus1.connect();
//		modbus2.connect();
//		modbus3.connect();
//		modbus4.connect();
//		modbus5.connect();
//		modbus6.connect();
//		modbus7.connect();
//		modbus8.connect();
//		modbus9.connect();
//		
//		modbus2_1.connect();		
	}

	public static void update() {
		dataUpdate1.connect();
//		dataUpdate2.connect();
//		dataUpdate3.connect();
//		dataUpdate4.connect();
//		dataUpdate5.connect();
//		dataUpdate6.connect();
//		dataUpdate7.connect();
//		dataUpdate8.connect();
//		dataUpdate9.connect();
//
//		dataUpdate2_1.connect();
		
	}

	public void run() {
		while (true) {
			generalWindowCell_1.ShowInfo();
//			generalWindowCell_2.ShowInfo();
//			generalWindowCell_3.ShowInfo();
//			generalWindowCell_4.ShowInfo();
//			generalWindowCell_5.ShowInfo();
//			generalWindowCell_6.ShowInfo();
//			generalWindowCell_7.ShowInfo();
//			generalWindowCell_8.ShowInfo();
//			generalWindowCell_9.ShowInfo();

			detailWindowCell_1.ShowInfo();
//			detailWindowCell_2.ShowInfo();
//			detailWindowCell_3.ShowInfo();
//			detailWindowCell_4.ShowInfo();
//			detailWindowCell_5.ShowInfo();
//			detailWindowCell_6.ShowInfo();
//			detailWindowCell_7.ShowInfo();
//			detailWindowCell_8.ShowInfo();
//			detailWindowCell_9.ShowInfo();
			
//			rotateWindow.ShowInfo();
			try {
				Thread.sleep(150);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		
		
		modbus1.close();
//		modbus2.close();
//		modbus3.close();
//		modbus4.close();
//		modbus5.close();
//		modbus6.close();
//		modbus7.close();
//		modbus8.close();
//		modbus9.close();
//		
//		modbus2_1.close();
		
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}
}
