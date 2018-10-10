package com.wardrobe.platform.rfid.main.test;

import java.io.*;

public class Main {
	
	static File AimFile = new File("D:\\code.txt");
	static BufferedWriter write = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new Runnable(){
			public void run() {
				try {
					write = new BufferedWriter(new FileWriter(AimFile));
					copyFilesToFile(new File("D:\\code"));
					write.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!------Done!");
			}
		}).start();

	}
	
	static void  testStringPre() {
		String str = "";
		for (int i =0; i < 1000000; i++) {
			for (int j = 0; j < 10; j++) {
				str += "ab";
			}
			str = "";
		}
	}
	
	static void  testStringBuildPre() {
		StringBuilder str = new StringBuilder();
		for (int i =0; i < 1000000; i++) {
			for (int j = 0; j < 10; j++) {
				str.append( "ab");
			}
			str.subSequence(0, 0);
		}
	}
	
	static void copyFilesToFile(File floder) throws IOException {
		if (floder.isDirectory()) {
			for (File f : floder.listFiles()) {
				copyFilesToFile(f);
			}
		} else if (floder.getName().endsWith(".xml") 
				   || floder.getName().endsWith(".java")
				   || floder.getName().endsWith(".h")
				   || floder.getName().endsWith(".c")) {
			BufferedReader reader = new BufferedReader(new FileReader(floder));
			String str = reader.readLine();
			while(str != null) {
				write.write(str + "\r\n");
				str = reader.readLine();
			}
			System.out.println("reader file :" + floder.getName() + "--- read finish!");
		    reader.close();
		}
	}

}