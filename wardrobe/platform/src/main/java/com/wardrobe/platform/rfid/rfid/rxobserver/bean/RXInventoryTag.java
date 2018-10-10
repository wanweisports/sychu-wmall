package com.wardrobe.platform.rfid.rfid.rxobserver.bean;

/**
 * Created by Administrator on 8/24/2017.
 */

public class RXInventoryTag {
    /** PC value*/
    public String strPC;
    /** CRC value*/
    public String strCRC;
    /** EPC value*/
    public String strEPC;
    /** antenna ID*/
    public byte btAntId;
    /** RSSI value*/
    public String strRSSI;
    /**carrier frequency*/
    public String strFreq;
    /** Inventory count only used in Get_inventory_buffer command */
    public int mReadCount;
    /** Distinguish the return tag data from which command cmd_get_inventory_buffer
     *  cmd_real_time_inventory or cmd_customized_session_target_inventory;
     * */
    public byte cmd;
    /**
     * Defaulted constructor
     */
    public RXInventoryTag() {
        strPC = "";
        strCRC = "";
        strEPC = "";
        btAntId = 0;
        strRSSI = "";
        strFreq = "";
        cmd = 0;
    }

    /**
     * Wrapper class of inventory end data package.
     */
    public static class RXInventoryTagEnd {
        /**Current work antenna*/
        public int mCurrentAnt;
        /** Tag count distinguish used EPC only use by inventory command*/
        public int mTagCount;
        /**Read rate*/
        public int mReadRate;
        /** The total tag count*/
        public int mTotalRead;
        /** cmd distinguish which command return.*/
        public byte cmd;
        /** The reader address of return data*/
        public byte btReaderId;

        public RXInventoryTagEnd() {
            this.mCurrentAnt = 0;
            this.mTagCount = 0;
            this.mReadRate = 0;
            this.mTotalRead = 0;
            this.cmd = 0;
            this.btReaderId = 0;
        }
    }

    /**
     * Wrapper class of fast switch antenna end data package.
     */
    public static class RXFastSwitchAntInventoryTagEnd {
    	/** */
        public int mTotalRead;
        public int mCommandDuration;
        /** The reader address of return data*/
        public byte btReaderId;
        public RXFastSwitchAntInventoryTagEnd () {
            this.mTotalRead = 0;
            this.mCommandDuration = 0;
        }
    }

}
