package com.wardrobe.platform.rfid.rfid.rxobserver.bean;

/**
 * Wrapper class of operation tag return data package.
 */

public class RXOperationTag {
    /** PC value*/
    public String strPC;
    /** CRC value*/
    public String strCRC;
    /** EPC value*/
    public String strEPC;
    /** Data in total */
    public String strData;
    /** Length of data */
    public int nDataLen;
    /** Antenna ID*/
    public byte btAntId;
    /** Operating times */
    public int nOperateCount;
    /** Operating command*/
    public byte cmd;
    /**
     *Operations (including read,write,lock and kill)tag default constructor
     */
    public RXOperationTag() {
        strPC = "";
        strCRC = "";
        strEPC = "";
        strData = "";
        nDataLen = 0;
        btAntId = 0;
        nOperateCount = 0;
        cmd = 0;
    }
}
