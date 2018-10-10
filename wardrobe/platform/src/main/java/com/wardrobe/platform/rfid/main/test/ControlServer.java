package com.wardrobe.platform.rfid.main.test;

import gnu.io.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControlServer implements Runnable {
	private ServerSocket mServer ;
	private Socket mSocket;
	private ConnectCom mConn;
	
	public ControlServer() throws IOException {
		mServer = new ServerSocket(9000);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				mSocket = mServer.accept();
				mConn = new ConnectCom(mSocket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mConn.terimal();
			}
		}
	}
	
	class ConnectCom {
		Socket mSocket;
		ExecutorService mExecutorService = Executors.newFixedThreadPool(2);
		SerialPort mSeialPort;
		ConnectCom(final Socket mSocket) {
			CommPortIdentifier portIdentifier;
			try {
				portIdentifier = CommPortIdentifier.getPortIdentifier("COM7");
				 mSeialPort = (SerialPort) portIdentifier.open("USER",5000);
				 mSeialPort.setSerialPortParams(115200,  
		                    SerialPort.DATABITS_8,
		                    SerialPort.STOPBITS_1,
		                    SerialPort.PARITY_NONE);
				 mExecutorService.submit(new Runnable() {
					 byte[] buff = new byte[1024];
					 @Override
						public void run() {
							// TODO Auto-generated method stub
							int b;
							try {
								b = mSeialPort.getInputStream().read(buff);
								System.out.println("Write to client!" + b);
								while (!(b < 0)) {
									 mSocket.getOutputStream().write(buff, 0, b);
									 b = mSeialPort.getInputStream().read(buff);
								 }
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								ConnectCom.this.terimal();
							}
						 }
				 });
				 
				 mExecutorService.submit(new Runnable() {
					 byte[] buff = new byte[1024];
					 @Override
						public void run() {
							// TODO Auto-generated method stub
							int b;
							try {
								b = mSocket.getInputStream().read(buff);
								while (b > 0) {
									mSeialPort.getOutputStream().write(buff, 0, b);
									 b = mSocket.getInputStream().read(buff);
									 System.out.println("Write to Reader!");
								 }
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								ConnectCom.this.terimal();
							}
						 }
				 });
			} catch (NoSuchPortException | PortInUseException | UnsupportedCommOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.terimal();
			}
		}
		
		public void terimal() {
			if (mExecutorService != null) {
				mExecutorService.shutdown();
			}
			
			if (mSocket != null) {
				try {
					mSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mSocket = null;
			}
			
			if (mSeialPort != null) {
				mSeialPort.close();
				mSeialPort = null;
			}
		}
	}

}
