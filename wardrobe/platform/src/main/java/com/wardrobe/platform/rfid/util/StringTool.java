package com.wardrobe.platform.rfid.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * The tool class about byte array and String convert.
 *
 */

public class StringTool {

	/**
	 * Convert the hex string to byte array;
	 * @param strHexValue the hex string value.
	 * @return byte[] the resolved value.
	 */
	public static byte[] stringToByteArray(String strHexValue) {
		String[] strAryHex = strHexValue.split(" ");
        byte[] btAryHex = new byte[strAryHex.length];

        try {
			int nIndex = 0;
			for (String strTemp : strAryHex) {
			    btAryHex[nIndex] = (byte) Integer.parseInt(strTemp, 16);
			    nIndex++;
			}
        } catch (NumberFormatException e) {

        }

        return btAryHex;
    }

	/**
	 * Convert the Hex string array to byte array 
	 * @param strAryHex the hex string value.
	 * @param nLen the needed parse length.
	 * @return byte[] the resolved value.
	 */
    public static byte[] stringArrayToByteArray(String[] strAryHex, int nLen) {
    	if (strAryHex == null) return null;

    	if (strAryHex.length < nLen) {
    		nLen = strAryHex.length;
    	}

    	byte[] btAryHex = new byte[nLen];

    	try {
    		for (int i = 0; i < nLen; i++) {
    			btAryHex[i] = (byte) Integer.parseInt(strAryHex[i], 16);
    		}
    	} catch (NumberFormatException e) {
	
        }

    	return btAryHex;
    }

	/**
	 * Convert the Hex string array to byte array
	 * @param btAryHex the hex string value.
	 * @param nIndex the starting parse position.
	 * @param nLen the needed parse length.
	 * @return byte[] the resolved value.
	 */
    public static String byteArrayToString(byte[] btAryHex, int nIndex, int nLen) {
    	if (nIndex + nLen > btAryHex.length) {
    		nLen = btAryHex.length - nIndex;
    	}

    	String strResult = String.format("%02X", btAryHex[nIndex]);
    	for (int nloop = nIndex + 1; nloop < nIndex + nLen; nloop++ ) {
    		String strTemp = String.format(" %02X", btAryHex[nloop]);

    		strResult += strTemp;
    	}

    	return strResult;
    }

	/**
	 * Convert the String to StringArray
	 * @param strValue the src string
	 * @param nLen needed convert length
	 * @return String[] the parsed value.
	 */
    public static String[] stringToStringArray(String strValue, int nLen) {
        String[] strAryResult = null;

        if (strValue != null && !strValue.equals("")) {
            ArrayList<String> strListResult = new ArrayList<String>();
            String strTemp = "";
            int nTemp = 0;

            for (int nloop = 0; nloop < strValue.length(); nloop++) {
                if (strValue.charAt(nloop) == ' ') {
                    continue;
                } else {
                    nTemp++;
                    
                    if (!Pattern.compile("^(([A-F])*([a-f])*(\\d)*)$")
                    		.matcher(strValue.substring(nloop, nloop + 1))
                    		.matches()) {
                        return strAryResult;
                    }

                    strTemp += strValue.substring(nloop, nloop + 1);

                    if ((nTemp == nLen) || (nloop == strValue.length() - 1 
                    		&& (strTemp != null && !strTemp.equals("")))) {
                        strListResult.add(strTemp);
                        nTemp = 0;
                        strTemp = "";
                    }
                }
            }

            if (strListResult.size() > 0) {
            	strAryResult = new String[strListResult.size()];
                for (int i = 0; i < strAryResult.length; i++) {
                	strAryResult[i] = strListResult.get(i);
                }
            }
        }

        return strAryResult;
    }

	/**
	 * Convert the byte array to special encode char array.
	 * @param bytes the needed byte array.
	 * @param encoding the encoding format.
     * @return char[] the converted char array.
     */
	@Deprecated
	public static char[] getChars (byte[] bytes,String encoding) {
		Charset cs = Charset.forName (encoding);
		ByteBuffer bb = ByteBuffer.allocate (bytes.length);
		bb.put (bytes);
		bb.flip ();
		CharBuffer cb = cs.decode (bb);
		return cb.array();
	}

	/**
	 * resolve the bytes String data to ASCII.String like this "2f3Eab"
	 * @param hexString the needed parse data.
	 * @return String the return value.
     */
	@Deprecated
	public static String toASCIIString(String hexString){
		StringBuilder stringBuilder = new StringBuilder();
		int i = 0;
		while (i < hexString.length()){
			stringBuilder.append((char)Integer.parseInt(hexString.substring(i,i + 2),16));
			i = i +2;
		}
		return stringBuilder.toString();
	}

	/**
	 * Resolve the bytes String data to ASCII.String like this "2f3Eab"
	 * @param hexString the needed parse data.
	 * @return String the return value.
	 */
	public static String hexStringToASCIIString(String hexString) {
		StringBuilder stringBuilder = new StringBuilder();
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		for (int i = 0; i < hexString.length(); i = i + 2){
			char high = hexString.charAt(i);
			char low = hexString.charAt(i+1);

			char no = (char)(charToInt(high)* 16 + charToInt(low));
			stringBuilder.append(no);
		}
		return stringBuilder.toString();
	}
	
	/**
	 * Convert the char 1-f to int value; 
	 * @param c the needed converter character.
	 * @return int the converted value.
	 */

	private static int charToInt(char c){
		int num = -1;
		num = "0123456789ABCDEF".indexOf(String.valueOf(c));
		if (num < 0)
			num = "0123456789abcdef".indexOf(String.valueOf(c));
		return num;
	}

	/**
	 * Get the sub bytes of the parent bytes;
	 * @param bytes parent bytes;
	 * @param start start position;
	 * @param end end position;
     * @return the child bytes;
     */
	public static byte[] subBytes(byte[] bytes, int start, int end){
        byte[] subBytes = new byte[end - start];
		System.arraycopy(bytes,start,subBytes,0,end - start);
		return subBytes;
	}

	/**
	 * Get the child position in their parents string 
	 * @param parentBytes parent bytes;
	 * @param childBytes child bytes;
	 * @param startPosition start scan position;
     * @return if -1 child not in parent string,or the child first position found in parent;
     */
	public static int subBytesContains(byte[] parentBytes, byte[] childBytes, int startPosition){
		if (parentBytes.length < childBytes.length)
			return -1;
		for (int i = startPosition; i < parentBytes.length; i++){
			for (int j = 0; j < childBytes.length; j++){
				 if (parentBytes[i + j] == childBytes[j]){
					 if (j == childBytes.length - 1)
						 return i;
					 continue;
				 } else {
					 break;
				 }
			}
		}
		return -1;
	}

	/**
	 * Convert hexString to byte array.
	 * @param hexString to bytes
	 * @return The converted byte array.
	 */
	public static byte[] hexStringToBytes(String hexString){
		byte[] bytes = new byte[hexString.length()/2];
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		for (int i = 0; i < hexString.length(); i = i + 2){
			char high = hexString.charAt(i);
			char low = hexString.charAt(i+1);

			bytes[i/2] = (byte) (charToInt(high)* 16 + charToInt(low));
		}
		return bytes;
	}
	
	/**
	 * Compare the both byte array
	 * @param first the first byte array
	 * @param second the second byte array 
	 * @return if true first equals second ,otherwise unequal 
	 */
	public static boolean compareBytes(byte[] first,byte[] second){
		if (first.length != second.length)
			return false;
		for (int i = 0; i < first.length; i++){
			if (first[i] != second[i])
				return false;
		}
		return true;
	}

	/**
	 * Convert Ascii string to byte array.
	 * @param string the needed converter data.
	 * @return byte[] the converted value.
     */
	public static byte[] asciiStringToBytes(String string){
		byte[] result = new byte[string.length()];
		for (int i = 0; i < string.length(); i++) {
			result[i] = (byte) string.charAt(i);
		}
		return result;
	}
}
