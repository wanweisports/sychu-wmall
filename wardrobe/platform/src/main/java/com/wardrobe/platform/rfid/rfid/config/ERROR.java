package com.wardrobe.platform.rfid.rfid.config;

/**
 * Regarding UHF error code and description, please refer to Serial_Protocol_User's_Guide_V2.38_en  
 * 
 *
 */
public class ERROR {
	public final static byte SUCCESS = 0x10;
	public final static byte FAIL = 0x11;
	public final static byte MCU_RESET_ERROR = 0x20;
	public final static byte CW_ON_ERROR = 0x21;
	public final static byte ANTENNA_MISSING_ERROR = 0x22;
	public final static byte WRITE_FLASH_ERROR = 0x23;
	public final static byte READ_FLASH_ERROR = 0x24;
	public final static byte SET_OUTPUT_POWER_ERROR = 0x25;
	public final static byte TAG_INVENTORY_ERROR = 0x31;
	public final static byte TAG_READ_ERROR = 0x32;
	public final static byte TAG_WRITE_ERROR = 0x33;
	public final static byte TAG_LOCK_ERROR = 0x34;
	public final static byte TAG_KILL_ERROR = 0x35;
	public final static byte NO_TAG_ERROR = 0x36;
	public final static byte INVENTORY_OK_BUT_ACCESS_FAIL = 0x37;
	public final static byte BUFFER_IS_EMPTY_ERROR = 0x38;
	public final static byte ACCESS_OR_PASSWORD_ERROR = 0x40;
	public final static byte PARAMETER_INVALID = 0x41;
	public final static byte PARAMETER_INVALID_WORDCNT_TOO_LONG = 0x42;
	public final static byte PARAMETER_INVALID_MEMBANK_OUT_OF_RANGE = 0x43;
	public final static byte PARAMETER_INVALID_LOCK_REGION_OUT_OF_RANGE = 0x44;
	public final static byte PARAMETER_INVALID_LOCK_ACTION_OUT_OF_RANGE = 0x45;
	public final static byte PARAMETER_READER_ADDRESS_INVALID = 0x46;
	public final static byte PARAMETER_INVALID_ANTENNA_ID_OUT_OF_RANGE = 0x47;
	public final static byte PARAMETER_INVALID_OUTPUT_POWER_OUT_OF_RANGE = 0x48;
	public final static byte PARAMETER_INVALID_FREQUENCY_REGION_OUT_OF_RANGE = 0x49;
	public final static byte PARAMETER_INVALID_BAUDRATE_OUT_OF_RANGE = 0x4A;
	public final static byte PARAMETER_BEEPER_MODE_OUT_OF_RANGE = 0x4B;
	public final static byte PARAMETER_EPC_MATCH_LEN_TOO_LONG = 0x4C;
	public final static byte PARAMETER_EPC_MATCH_LEN_ERROR = 0x4D;
	public final static byte PARAMETER_INVALID_EPC_MATCH_MODE = 0x4E;
	public final static byte PARAMETER_INVALID_FREQUENCY_RANGE = 0x4F;
	public final static byte FAIL_TO_GET_RN16_FROM_TAG = 0x50;
	public final static byte PARAMETER_INVALID_DRM_MODE = 0x51;
	public final static byte PLL_LOCK_FAIL = 0x52;
	public final static byte RF_CHIP_FAIL_TO_RESPONSE = 0x53;
	public final static byte FAIL_TO_ACHIEVE_DESIRED_OUTPUT_POWER = 0x54;
	public final static byte COPYRIGHT_AUTHENTICATION_FAIL = 0x55;
	public final static byte SPECTRUM_REGULATION_ERROR = 0x56;
	public final static byte OUTPUT_POWER_TOO_LOW = 0x57;
	public final static byte UNKONW_ERROR = 0x58;

	/**
	 * The description of error code.
	 * @param btErrorCode the error code.
	 * @return String the description of error code.
	 */
	public static String format(byte btErrorCode)
	{
		String strErrorCode = "";
		switch (btErrorCode)
		{
			case SUCCESS:
				strErrorCode = "Command succeeded.";
				break;
			case FAIL:
				strErrorCode = "Command failed.";
				break;
			case MCU_RESET_ERROR:
				strErrorCode = "CPU reset error.";
				break;
			case CW_ON_ERROR:
				strErrorCode = "Turn on CW error.";
				break;
			case ANTENNA_MISSING_ERROR:
				strErrorCode = "Antenna is missing.";
				break;
			case WRITE_FLASH_ERROR:
				strErrorCode = "Write flash error.";
				break;
			case READ_FLASH_ERROR:
				strErrorCode = "Read flash error.";
				break;
			case SET_OUTPUT_POWER_ERROR:
				strErrorCode = "Set output power error.";
				break;
			case TAG_INVENTORY_ERROR:
				strErrorCode = "Error occurred when inventory.";
				break;
			case TAG_READ_ERROR:
				strErrorCode = "Error occurred when read.";
				break;
			case TAG_WRITE_ERROR:
				strErrorCode = "Error occurred when write.";
				break;
			case TAG_LOCK_ERROR:
				strErrorCode = "Error occurred when lock.";
				break;
			case TAG_KILL_ERROR:
				strErrorCode = "Error occurred when kill.";
				break;
			case NO_TAG_ERROR:
				strErrorCode = "There is no tag to be operated.";
				break;
			case INVENTORY_OK_BUT_ACCESS_FAIL:
				strErrorCode = "Tag Inventoried but access failed.";
				break;
			case BUFFER_IS_EMPTY_ERROR:
				strErrorCode = "Buffer is empty.";
				break;
			case ACCESS_OR_PASSWORD_ERROR:
				strErrorCode = "Access failed or wrong password.";
				break;
			case PARAMETER_INVALID:
				strErrorCode = "Invalid parameter.";
				break;
			case PARAMETER_INVALID_WORDCNT_TOO_LONG:
				strErrorCode = "WordCnt is too long.";
				break;
			case PARAMETER_INVALID_MEMBANK_OUT_OF_RANGE:
				strErrorCode = "MemBank out of range.";
				break;
			case PARAMETER_INVALID_LOCK_REGION_OUT_OF_RANGE:
				strErrorCode = "Lock region out of range.";
				break;
			case PARAMETER_INVALID_LOCK_ACTION_OUT_OF_RANGE:
				strErrorCode = "LockType out of range.";
				break;
			case PARAMETER_READER_ADDRESS_INVALID:
				strErrorCode = "Invalid reader address.";
				break;
			case PARAMETER_INVALID_ANTENNA_ID_OUT_OF_RANGE:
				strErrorCode = "AntennaID out of range.";
				break;
			case PARAMETER_INVALID_OUTPUT_POWER_OUT_OF_RANGE:
				strErrorCode = "Output power out of range.";
				break;
			case PARAMETER_INVALID_FREQUENCY_REGION_OUT_OF_RANGE:
				strErrorCode = "Frequency region out of range.";
				break;
			case PARAMETER_INVALID_BAUDRATE_OUT_OF_RANGE:
				strErrorCode = "Baud rate out of range.";
				break;
			case PARAMETER_BEEPER_MODE_OUT_OF_RANGE:
				strErrorCode = "Buzzer behavior out of range.";
				break;
			case PARAMETER_EPC_MATCH_LEN_TOO_LONG:
				strErrorCode = "EPC match is too long.";
				break;
			case PARAMETER_EPC_MATCH_LEN_ERROR:
				strErrorCode = "EPC match length wrong.";
				break;
			case PARAMETER_INVALID_EPC_MATCH_MODE:
				strErrorCode = "Invalid EPC match mode.";
				break;
			case PARAMETER_INVALID_FREQUENCY_RANGE:
				strErrorCode = "Invalid frequency range.";
				break;
			case FAIL_TO_GET_RN16_FROM_TAG:
				strErrorCode = "Failed to receive RN16 from tag.";
				break;
			case PARAMETER_INVALID_DRM_MODE:
				strErrorCode = "Invalid DRM mode.";
				break;
			case PLL_LOCK_FAIL:
				strErrorCode = "PLL can not lock.";
				break;
			case RF_CHIP_FAIL_TO_RESPONSE:
				strErrorCode = "No response from RF chip.";
				break;
			case FAIL_TO_ACHIEVE_DESIRED_OUTPUT_POWER:
				strErrorCode = "Can’t achieve desired output power level.";
				break;
			case COPYRIGHT_AUTHENTICATION_FAIL:
				strErrorCode = "Can’t authenticate firmware copyright.";
				break;
			case SPECTRUM_REGULATION_ERROR:
				strErrorCode = "Spectrum regulation wrong.";
				break;
			case OUTPUT_POWER_TOO_LOW:
				strErrorCode = "Output power too low.";
				break;
			default:
				strErrorCode = "Unknown Error";
				break;
		}
		return strErrorCode;
	}
}
