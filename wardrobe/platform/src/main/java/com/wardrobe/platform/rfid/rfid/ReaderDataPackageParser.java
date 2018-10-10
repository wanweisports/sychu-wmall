package com.wardrobe.platform.rfid.rfid;

import com.wardrobe.platform.rfid.module.interaction.DataPackageParser;
import com.wardrobe.platform.rfid.module.interaction.DataPackageProcess;
import com.wardrobe.platform.rfid.rfid.config.HEAD;

import java.util.Arrays;

/**
 * The implementation class of DataPackageParser.
 */

public class ReaderDataPackageParser implements DataPackageParser {
    private byte[] m_btAryBuffer = new byte[4096];
    private int m_nLength = 0;
    @Override
    public void runReceiveDataCallback(byte[] btAryReceiveData, DataPackageProcess dataPackageProcess) {
        try {
            int nCount = btAryReceiveData.length;
            byte[] btAryBuffer = new byte[nCount + m_nLength];

            System.arraycopy(m_btAryBuffer, 0, btAryBuffer, 0, m_nLength);
            System.arraycopy(btAryReceiveData, 0, btAryBuffer, m_nLength,
                    btAryReceiveData.length);
            int nIndex = 0;
            int nMarkIndex = 0;
            for (int nLoop = 0; nLoop < btAryBuffer.length; nLoop++) {
                if (btAryBuffer.length > nLoop + 1) {
                    if (btAryBuffer[nLoop] == HEAD.HEAD) {
                        int nLen = btAryBuffer[nLoop + 1] & 0xFF;
                        if (nLoop + 1 + nLen < btAryBuffer.length) {
                            byte[] btAryAnaly = new byte[nLen + 2];
                            System.arraycopy(btAryBuffer, nLoop, btAryAnaly, 0,
                                    nLen + 2);
                            dataPackageProcess.analyData(btAryAnaly);
                            nLoop += 1 + nLen;
                            nIndex = nLoop + 1;
                        } else {
                            nLoop += 1 + nLen;
                        }
                    } else {
                        nMarkIndex = nLoop;
                    }
                }
            }

            if (nIndex < nMarkIndex) {
                nIndex = nMarkIndex + 1;
            }

            if (nIndex < btAryBuffer.length) {
                m_nLength = btAryBuffer.length - nIndex;
                Arrays.fill(m_btAryBuffer, 0, 4096, (byte) 0);
                System.arraycopy(btAryBuffer, nIndex, m_btAryBuffer, 0,
                        btAryBuffer.length - nIndex);
            } else {
                m_nLength = 0;
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}
