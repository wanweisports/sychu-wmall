package com.wardrobe.platform.rfid.rfid;

import com.wardrobe.platform.rfid.module.interaction.ModuleConnector;
import com.wardrobe.platform.rfid.module.interaction.ReaderHelper;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * The implementation class of ModuleConnector.
 *
 */
public class ReaderConnector implements ModuleConnector {
	private  final String HOSTNAME_REGEXP = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
			+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
			+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
			+ "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
	
	private RFIDReaderHelper mRFIDReaderHelper;
	private Socket mSocket;
	private InetSocketAddress mRemoteAddr;
	private SerialPort mSeialPort = null;
	private final String USER = "USER";
	
	@Override
	public ReaderHelper connectCom(final String port, final int baud) {
		try {
			 CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(port);
			 mSeialPort = (SerialPort) portIdentifier.open(USER,5000);
			 mSeialPort.setSerialPortParams(baud,  
	                    SerialPort.DATABITS_8,
	                    SerialPort.STOPBITS_1,
	                    SerialPort.PARITY_NONE);
			if (mSeialPort == null) {
				return null;
			}
			return connect(mSeialPort.getInputStream(),mSeialPort.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ReaderHelper connectNet(final String host, final int port) {

		if (host.matches(HOSTNAME_REGEXP))
			;
		else {
			return null;
		}
		
		try {
			mRemoteAddr = new InetSocketAddress(host, port);
			mSocket = new Socket();
		} catch (Exception e1) {
			return null;
		}

		try {
			mSocket.connect(mRemoteAddr, 4000);
			return connect(mSocket.getInputStream(),mSocket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public ReaderHelper connect(InputStream in,OutputStream out) {
		mRFIDReaderHelper = new RFIDReaderHelper();
		try {
			mRFIDReaderHelper.setReader(in,out,new ReaderDataPackageParser(),new ReaderDataPackageProcess());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
		return mRFIDReaderHelper;
	}

	@Override
	public boolean isConnected() {
		return (mRFIDReaderHelper != null ? mRFIDReaderHelper.isAlive() : false);
	}

	@Override
	public void disConnect() {
		if (mRFIDReaderHelper != null) {
			mRFIDReaderHelper.signOut();
			mRFIDReaderHelper = null;
		}
		try {
			if (mSocket != null) {
				mSocket.close();
				mSocket = null;
			}
			if (mSeialPort != null) {
				mSeialPort.close();
				mSeialPort = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mRemoteAddr = null;
	}

}
