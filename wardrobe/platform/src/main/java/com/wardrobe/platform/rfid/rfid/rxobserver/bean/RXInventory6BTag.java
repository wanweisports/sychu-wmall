package com.wardrobe.platform.rfid.rfid.rxobserver.bean;

/**
 * Wrapper class of ISO180006B tag return data package.
 */

public class RXInventory6BTag {
    /** Inventory antenna id*/
    public byte mAntID;
    /** 6B tag UID*/
    public String mUID;
    /** cmd distinguish which command return.*/
    public byte cmd;
    /** The reader address of return data*/
    public byte btReaderId;

    /**
     * Default constructor
     */
    public RXInventory6BTag() {
        this.mAntID = 0x00;
        this.mUID = "";
        this.cmd = 0x00;
        this.btReaderId = 0x00;
    }
}
