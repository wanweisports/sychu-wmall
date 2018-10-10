package com.wardrobe.platform.rfid.rfid.bean;

import com.wardrobe.platform.rfid.rfid.config.HEAD;

/**
 * Object of describing data package;
 * @author Administrator
 *
 */
public class MessageTran {
	private byte btPacketType;     //head of data, defauld value is 0xA0 
    private byte btDataLen;        //Length of data package, which means the byte quantity after the "length", not including the "length" 
    private byte btReadId;         //Address of reader 
    private byte btCmd;            //The commend code of data package 
    private byte[] btAryData;      //The commend specification of data package, some commends without specification  
    private byte btCheck;          //Checksum 
    private byte[] btAryTranData;  //Complete data package 
    
    /**
     * MessageTran default constructor
     */
    public MessageTran() {
	}

    /**
     * MessageTran Constructor 
     * @param btReadId    address of reader  
     * @param btCmd       command code of data package  
     * @param btAryData   command parameters of data package, some commands without parameters  
     */
    public MessageTran(byte btReadId, byte btCmd, byte[] btAryData) {
        int nLen = btAryData.length;

        this.btPacketType = HEAD.HEAD;
        this.btDataLen = (byte)(nLen + 3);
        this.btReadId = btReadId;
        this.btCmd = btCmd;

        this.btAryData = new byte[nLen];
        System.arraycopy(btAryData, 0, this.btAryData, 0, btAryData.length);
        //btAryData.CopyTo(this.btAryData, 0);

        this.btAryTranData = new byte[nLen + 5];
        this.btAryTranData[0] = this.btPacketType;
        this.btAryTranData[1] = this.btDataLen;
        this.btAryTranData[2] = this.btReadId;
        this.btAryTranData[3] = this.btCmd;
        System.arraycopy(this.btAryData, 0, this.btAryTranData, 4, this.btAryData.length);
        //this.btAryData.CopyTo(this.btAryTranData, 4);

        this.btCheck = checkSum(this.btAryTranData, 0, nLen + 4);
        this.btAryTranData[nLen + 4] = this.btCheck;
    }

    /**
     * MessageTran Constructor
      * @param btReadId   address of reader 
     * @param btCmd       read command  
     */
    public MessageTran(byte btReadId, byte btCmd) {
        this.btPacketType = HEAD.HEAD;
        this.btDataLen = 0x03;
        this.btReadId = btReadId;
        this.btCmd = btCmd;

        this.btAryTranData = new byte[5];
        this.btAryTranData[0] = this.btPacketType;
        this.btAryTranData[1] = this.btDataLen;
        this.btAryTranData[2] = this.btReadId;
        this.btAryTranData[3] = this.btCmd;

        this.btCheck = checkSum(this.btAryTranData, 0, 4);
        this.btAryTranData[4] = this.btCheck;
    }

    /**
     * MessageTran constructor 
     * @param btAryTranData   complete data package  
     */
    public MessageTran(byte[] btAryTranData) {
        int nLen = btAryTranData.length;

        this.btAryTranData = new byte[nLen];
        System.arraycopy(btAryTranData, 0, this.btAryTranData, 0, btAryTranData.length);
        //btAryTranData.CopyTo(this.btAryTranData, 0);


        byte btCK = checkSum(this.btAryTranData, 0, this.btAryTranData.length - 1);
        if (btCK != btAryTranData[nLen - 1]) {
            return;
        }

        this.btPacketType = btAryTranData[0];
        this.btDataLen = btAryTranData[1];
        this.btReadId = btAryTranData[2];
        this.btCmd = btAryTranData[3];
        this.btCheck = btAryTranData[nLen - 1];

        if (nLen > 5) {
            this.btAryData = new byte[nLen - 5];
            for (int nloop = 0; nloop < nLen - 5; nloop++ ) {
                this.btAryData[nloop] = btAryTranData[4 + nloop];
            }
        }
    }
    
    /**
     * Obtain complete data package
     * @return	 Data package 
     */
    public byte[] getAryTranData() {
            return this.btAryTranData;
    }

    /**
     * Obtain command parameters of data package, some command without parameters 
     * @return	 commends parameters 
     */
    public byte[] getAryData() {
            return this.btAryData;
    }

    /**
     * Obtain address of reader  
     * @return	Address of reader   
     */
    public byte getReadId() {
            return this.btReadId;
    }

    /**
     * Obtain command of data package  
     * @return	 command  
     */
    public byte getCmd() {
            return this.btCmd;
    }

    /**
     * Obain head of data, default 0xA0  
     * @return	head of data   
     */
    public byte getPacketType() {
            return this.btPacketType;
    }
    
    /**
     * Check head of data 
     * @return	 Result of checking 
     */
    public boolean checkPacketType() {
        return this.btPacketType == HEAD.HEAD;
    }

    /**
     * Calculate checksum  
     * @param btAryBuffer   data	
     * @param nStartPos	    start position	
     * @param nLen	        Checking length		
     * @return	            Checksum  
     */
    public byte checkSum(byte[] btAryBuffer, int nStartPos, int nLen) {
        byte btSum = 0x00;

        for (int nloop = nStartPos; nloop < nStartPos + nLen; nloop++ ) {
            btSum += btAryBuffer[nloop];
        }
        return (byte)(((~btSum) + 1) & 0xFF);
    }
}
