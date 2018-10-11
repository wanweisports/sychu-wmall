package com.wardrobe.platform.rfid.rfid.config;

/**
 * Regarding UHF return code and description, please refer to Serial_Protocol_User's_Guide_V2.38_en  
 *
 */
public class CMD {
	public final static byte RESET = 0x70;
	public final static byte SET_UART_BAUDRATE = 0x71;
	public final static byte GET_FIRMWARE_VERSION = 0x72;
	public final static byte SET_READER_ADDRESS = 0x73;
	public final static byte SET_WORK_ANTENNA = 0x74;
	public final static byte GET_WORK_ANTENNA = 0x75;
	public final static byte SET_OUTPUT_POWER = 0x76;
	public final static byte GET_OUTPUT_POWER = 0x77;
	public final static byte SET_FREQUENCY_REGION = 0x78;
	public final static byte GET_FREQUENCY_REGION = 0x79;
	public final static byte SET_BEEPER_MODE = 0x7A;
	public final static byte GET_READER_TEMPERATURE = 0x7B;
	public final static byte READ_GPIO_VALUE = 0x60;
	public final static byte WRITE_GPIO_VALUE = 0x61;
	public final static byte SET_ANT_CONNECTION_DETECTOR = 0x62;
	public final static byte GET_ANT_CONNECTION_DETECTOR = 0x63;
	public final static byte SET_TEMPORARY_OUTPUT_POWER = 0x66;
	public final static byte SET_READER_IDENTIFIER = 0x67;
	public final static byte GET_READER_IDENTIFIER = 0x68;
	public final static byte SET_RF_LINK_PROFILE = 0x69;
	public final static byte GET_RF_LINK_PROFILE = 0x6A;
	public final static byte GET_RF_PORT_RETURN_LOSS = 0x7E;
	public final static byte INVENTORY = (byte) 0x80;
	public final static byte READ_TAG = (byte) 0x81;
	public final static byte WRITE_TAG = (byte) 0x82;
	public final static byte LOCK_TAG = (byte) 0x83;
	public final static byte KILL_TAG = (byte) 0x84;
	public final static byte SET_ACCESS_EPC_MATCH = (byte) 0x85;
	public final static byte GET_ACCESS_EPC_MATCH = (byte) 0x86;
	public final static byte REAL_TIME_INVENTORY = (byte) 0x89;
	public final static byte FAST_SWITCH_ANT_INVENTORY = (byte) 0x8A;
	public final static byte CUSTOMIZED_SESSION_TARGET_INVENTORY = (byte) 0x8B;
	public final static byte SET_IMPINJ_FAST_TID = (byte) 0x8C;
	public final static byte SET_AND_SAVE_IMPINJ_FAST_TID = (byte) 0x8D;
	public final static byte GET_IMPINJ_FAST_TID = (byte) 0x8E;
	public final static byte ISO18000_6B_INVENTORY = (byte) 0xB0;
	public final static byte ISO18000_6B_READ_TAG = (byte) 0xB1;
	public final static byte ISO18000_6B_WRITE_TAG = (byte) 0xB2;
	public final static byte ISO18000_6B_LOCK_TAG = (byte) 0xB3;
	public final static byte ISO18000_6B_QUERY_LOCK_TAG = (byte) 0xB4;
	public final static byte GET_INVENTORY_BUFFER = (byte) 0x90;
	public final static byte GET_AND_RESET_INVENTORY_BUFFER = (byte) 0x91;
	public final static byte GET_INVENTORY_BUFFER_TAG_COUNT = (byte) 0x92;
	public final static byte RESET_INVENTORY_BUFFER = (byte) 0x93;
    public final static byte OPERATE_TAG_MASK = (byte) 0x98;
    public final static byte GET_OUTPUT_POWER_EIGHT = (byte) 0x97;

    /**
     * The description of command.
     * @param btCmd the command code.
     * @return String the description of command.
     */
    public static String format(byte btCmd)
    {
        String strCmd = "";
        switch (btCmd)
        {
            case RESET:
                strCmd = "Reset reader.";
                break;
            case SET_UART_BAUDRATE:
                strCmd = "Set baud rate of serial port.";
                break;
            case GET_FIRMWARE_VERSION:
                strCmd = "Get firmware version.";
                break;
            case SET_READER_ADDRESS:
                strCmd = "Set reader’s address.";
                break;
            case SET_WORK_ANTENNA:
                strCmd = "Set working antenna.";
                break;
            case GET_WORK_ANTENNA:
                strCmd = "Query current working antenna.";
                break;
            case SET_OUTPUT_POWER:
                strCmd = "Set RF output power.";
                break;
            case GET_OUTPUT_POWER:
                strCmd = "Query current RF output power.";
                break;
            case SET_FREQUENCY_REGION:
                strCmd = "Set RF frequency spectrum.";
                break;
            case GET_FREQUENCY_REGION:
                strCmd = "Query RF frequency spectrum.";
                break;
            case SET_BEEPER_MODE:
                strCmd = "Set reader’s buzzer hehavior.";
                break;
            case GET_READER_TEMPERATURE:
                strCmd = "Check reader’s internal temperature.";
                break;
            case READ_GPIO_VALUE:
                strCmd = "Get GPIO1, GPIO2 status.";
                break;
            case WRITE_GPIO_VALUE:
                strCmd = "Set GPIO3, GPIO4 status.";
                break;
            case SET_ANT_CONNECTION_DETECTOR:
                strCmd = "Set antenna detector status.";
                break;
            case GET_ANT_CONNECTION_DETECTOR:
                strCmd = "Get antenna detector status.";
                break;
            case SET_TEMPORARY_OUTPUT_POWER:
                strCmd = "Set RF power without saving to flash.";
                break;
            case SET_READER_IDENTIFIER:
                strCmd = "Set reader’s identification bytes.";
                break;
            case GET_READER_IDENTIFIER:
                strCmd = "Get reader’s identification bytes.";
                break;
            case SET_RF_LINK_PROFILE:
                strCmd = "Set RF link profile.";
                break;
            case GET_RF_LINK_PROFILE:
                strCmd = "Get RF link profile.";
                break;
            case GET_RF_PORT_RETURN_LOSS:
                strCmd = "Get current antenna port’s return loss.";
                break;
            case INVENTORY:
                strCmd = "Inventory EPC C1G2 tags to buffer.";
                break;
            case READ_TAG:
                strCmd = "Read EPC C1G2 tag(s).";
                break;
            case WRITE_TAG:
                strCmd = "Write EPC C1G2 tag(s).";
                break;
            case LOCK_TAG:
                strCmd = "Lock EPC C1G2 tag(s).";
                break;
            case KILL_TAG:
                strCmd = "Kill EPC C1G2 tag(s).";
                break;
            case SET_ACCESS_EPC_MATCH:
                strCmd = "Set tag access filter by EPC.";
                break;
            case GET_ACCESS_EPC_MATCH:
                strCmd = "Query access filter by EPC.";
                break;
            case REAL_TIME_INVENTORY:
                strCmd = "Inventory tags in real time mode.";
                break;
            case FAST_SWITCH_ANT_INVENTORY:
                strCmd = "Real time inventory with fast ant switch.";
                break;
            case CUSTOMIZED_SESSION_TARGET_INVENTORY:
                strCmd = "Inventory with desired session and inventoried flag.";
                break;
            case SET_IMPINJ_FAST_TID:
                strCmd = "Set impinj FastTID function(Without saving to FLASH).";
                break;
            case SET_AND_SAVE_IMPINJ_FAST_TID:
                strCmd = "Set impinj FastTID function(Save to FLASH).";
                break;
            case GET_IMPINJ_FAST_TID:
                strCmd = "Get current FastTID setting.";
                break;
            case ISO18000_6B_INVENTORY:
                strCmd = "Inventory 18000-6B tag(s).";
                break;
            case ISO18000_6B_READ_TAG:
                strCmd = "Read 18000-6B tag.";
                break;
            case ISO18000_6B_WRITE_TAG:
                strCmd = "Write 18000-6B tag.";
                break;
            case ISO18000_6B_LOCK_TAG:
                strCmd = "Lock 18000-6B tag data byte.";
                break;
            case ISO18000_6B_QUERY_LOCK_TAG:
                strCmd = "Query lock 18000-6B tag data byte.";
                break;
            case GET_INVENTORY_BUFFER:
                strCmd = "Get buffered data without clearing.";
                break;
            case GET_AND_RESET_INVENTORY_BUFFER:
                strCmd = "Get and clear buffered data.";
                break;
            case GET_INVENTORY_BUFFER_TAG_COUNT:
                strCmd = "Query how many tags are buffered.";
                break;
            case RESET_INVENTORY_BUFFER:
                strCmd = "Clear buffer.";
                break;
            default:
                strCmd = "Unknown error.";
                break;
        }
        return strCmd;
    }
}
