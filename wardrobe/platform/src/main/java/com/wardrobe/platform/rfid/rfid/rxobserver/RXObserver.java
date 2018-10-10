package com.wardrobe.platform.rfid.rfid.rxobserver;

import com.wardrobe.platform.rfid.rfid.RFIDReaderHelper;
import com.wardrobe.platform.rfid.rfid.bean.MessageTran;
import com.wardrobe.platform.rfid.rfid.config.CMD;
import com.wardrobe.platform.rfid.rfid.config.ERROR;
import com.wardrobe.platform.rfid.rfid.config.HEAD;
import com.wardrobe.platform.rfid.rfid.rxobserver.bean.RXInventoryTag;
import com.wardrobe.platform.rfid.rfid.rxobserver.bean.RXOperationTag;
import com.wardrobe.platform.rfid.util.StringTool;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 8/24/2017.
 */

public class RXObserver implements Observer {
    private ReaderSetting m_curReaderSetting = ReaderSetting.newInstance();
    @Override
    public final void update(Observable o, Object arg) {
        if (arg instanceof MessageTran) {
            analyData((MessageTran) arg);
        }
    }

    private void analyData(MessageTran msgTran) {
        if (msgTran.getPacketType() != HEAD.HEAD) {
            return;
        }

        switch (msgTran.getCmd()) {
            case CMD.RESET:
                processReset(msgTran);
                break;
            case CMD.SET_UART_BAUDRATE:
                processSetUartBaudrate(msgTran);
                break;
            case CMD.GET_FIRMWARE_VERSION:
                processGetFirmwareVersion(msgTran);
                break;
            case CMD.SET_READER_ADDRESS:
                processSetReaderAddress(msgTran);
                break;
            case CMD.SET_WORK_ANTENNA:
                processSetWorkAntenna(msgTran);
                break;
            case CMD.GET_WORK_ANTENNA:
                processGetWorkAntenna(msgTran);
                break;
            case CMD.SET_OUTPUT_POWER:
                processSetOutputPower(msgTran);
                break;
            case CMD.GET_OUTPUT_POWER:
            case CMD.GET_OUTPUT_POWER_EIGHT:
                processGetOutputPower(msgTran);
                break;
            case CMD.SET_FREQUENCY_REGION:
                processSetFrequencyRegion(msgTran);
                break;
            case CMD.GET_FREQUENCY_REGION:
                processGetFrequencyRegion(msgTran);
                break;
            case CMD.SET_BEEPER_MODE:
                processSetBeeperMode(msgTran);
                break;
            case CMD.GET_READER_TEMPERATURE:
                processGetReaderTemperature(msgTran);
                break;
            case CMD.READ_GPIO_VALUE:
                processReadGpioValue(msgTran);
                break;
            case CMD.WRITE_GPIO_VALUE:
                processWriteGpioValue(msgTran);
                break;
            case CMD.SET_ANT_CONNECTION_DETECTOR:
                processSetAntConnectionDetector(msgTran);
                break;
            case CMD.GET_ANT_CONNECTION_DETECTOR:
                processGetAntConnectionDetector(msgTran);
                break;
            case CMD.SET_TEMPORARY_OUTPUT_POWER:
                processSetTemporaryOutputPower(msgTran);
                break;
            case CMD.SET_READER_IDENTIFIER:
                processSetReaderIdentifier(msgTran);
                break;
            case CMD.GET_READER_IDENTIFIER:
                processGetReaderIdentifier(msgTran);
                break;
            case CMD.SET_RF_LINK_PROFILE:
                processSetRfLinkProfile(msgTran);
                break;
            case CMD.GET_RF_LINK_PROFILE:
                processGetRfLinkProfile(msgTran);
                break;
            case CMD.GET_RF_PORT_RETURN_LOSS:
                processGetRfPortReturnLoss(msgTran);
                break;
            case CMD.INVENTORY:
                processInventory(msgTran);
                break;
            case CMD.READ_TAG:
                processReadTag(msgTran);
                break;
            case CMD.WRITE_TAG:
                processWriteTag(msgTran);
                break;
            case CMD.LOCK_TAG:
                processLockTag(msgTran);
                break;
            case CMD.KILL_TAG:
                processKillTag(msgTran);
                break;
            case CMD.SET_ACCESS_EPC_MATCH:
                processSetAccessEpcMatch(msgTran);
                break;
            case CMD.GET_ACCESS_EPC_MATCH:
                processGetAccessEpcMatch(msgTran);
                break;
            case CMD.REAL_TIME_INVENTORY:
                processRealTimeInventory(msgTran);
                break;
            case CMD.FAST_SWITCH_ANT_INVENTORY:
                processFastSwitchInventory(msgTran);
                break;
            case CMD.CUSTOMIZED_SESSION_TARGET_INVENTORY:
                processCustomizedSessionTargetInventory(msgTran);
                break;
            case CMD.SET_IMPINJ_FAST_TID:
                processSetImpinjFastTid(msgTran);
                break;
            case CMD.SET_AND_SAVE_IMPINJ_FAST_TID:
                processSetAndSaveImpinjFastTid(msgTran);
                break;
            case CMD.GET_IMPINJ_FAST_TID:
                processGetImpinjFastTid(msgTran);
                break;
            case CMD.ISO18000_6B_INVENTORY:
                processISO180006BInventory(msgTran);
                break;
            case CMD.ISO18000_6B_READ_TAG:
                processISO180006BReadTag(msgTran);
                break;
            case CMD.ISO18000_6B_WRITE_TAG:
                processISO180006BWriteTag(msgTran);
                break;
            case CMD.ISO18000_6B_LOCK_TAG:
                processISO180006BLockTag(msgTran);
                break;
            case CMD.ISO18000_6B_QUERY_LOCK_TAG:
                processISO180006BQueryLockTag(msgTran);
                break;
            case CMD.GET_INVENTORY_BUFFER:
                processGetInventoryBuffer(msgTran);
                break;
            case CMD.GET_AND_RESET_INVENTORY_BUFFER:
                processGetAndResetInventoryBuffer(msgTran);
                break;
            case CMD.GET_INVENTORY_BUFFER_TAG_COUNT:
                processGetInventoryBufferTagCount(msgTran);
                break;
            case CMD.RESET_INVENTORY_BUFFER:
                processResetInventoryBuffer(msgTran);
                break;
            case CMD.OPERATE_TAG_MASK:
                processTagMask(msgTran);
                break;
            default:
                break;
        }
    }

	/**
     * Parse all feedback of set command.
     *
     * @param msgTran
     */
    private void processSet(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            if (btAryData[0] == ERROR.SUCCESS) {
                m_curReaderSetting.btReadId = msgTran.getReadId();
                refreshSetting(m_curReaderSetting);
                onExeCMDStatus(btCmd,ERROR.SUCCESS);
                return;
            } else {
                onExeCMDStatus(btCmd,btAryData[0]);
            }
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processReset(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processSetUartBaudrate(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processGetFirmwareVersion(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();
        if (btAryData.length == 0x02) {
            m_curReaderSetting.btReadId = msgTran.getReadId();
            m_curReaderSetting.btMajor = btAryData[0];
            m_curReaderSetting.btMinor = btAryData[1];
            refreshSetting(m_curReaderSetting);
            onExeCMDStatus(btCmd,ERROR.SUCCESS);
            return;
        } else if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processSetReaderAddress(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processSetWorkAntenna(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            if (btAryData[0] == ERROR.SUCCESS) {
                m_curReaderSetting.btReadId = msgTran.getReadId();
                refreshSetting(m_curReaderSetting);
                onExeCMDStatus(btCmd,ERROR.SUCCESS);
                return;
            } else {
                onExeCMDStatus(btCmd,btAryData[0]);
            }
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processGetWorkAntenna(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            if (btAryData[0] == 0x00 || btAryData[0] == 0x01
                    || btAryData[0] == 0x02 || btAryData[0] == 0x03) {
                m_curReaderSetting.btReadId = msgTran.getReadId();
                m_curReaderSetting.btWorkAntenna = btAryData[0];
                refreshSetting(m_curReaderSetting);
                onExeCMDStatus(btCmd,ERROR.SUCCESS);
                return;
            } else {
                onExeCMDStatus(btCmd,btAryData[0]);
            }
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processSetOutputPower(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processGetOutputPower(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x04 || btAryData.length == 0x01 || btAryData.length == 0x08) {
            m_curReaderSetting.btReadId = msgTran.getReadId();
            m_curReaderSetting.btAryOutputPower = btAryData.clone();
            refreshSetting(m_curReaderSetting);
            onExeCMDStatus(btCmd,ERROR.SUCCESS);
            return;
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processSetFrequencyRegion(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processGetFrequencyRegion(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x03) {
            m_curReaderSetting.btReadId = msgTran.getReadId();
            m_curReaderSetting.btRegion = btAryData[0];
            m_curReaderSetting.btFrequencyStart = btAryData[1];
            m_curReaderSetting.btFrequencyEnd = btAryData[2];
            refreshSetting(m_curReaderSetting);
            onExeCMDStatus(btCmd,ERROR.SUCCESS);
            return;
        } else if (btAryData.length == 0x06) {
            m_curReaderSetting.btReadId = msgTran.getReadId();
            m_curReaderSetting.btRegion = btAryData[0];
            m_curReaderSetting.btUserDefineFrequencyInterval = btAryData[1];
            m_curReaderSetting.btUserDefineChannelQuantity = btAryData[2];
            m_curReaderSetting.nUserDefineStartFrequency = (btAryData[3] & 0xFF)
                    * 256
                    * 256
                    + (btAryData[4] & 0xFF)
                    * 256
                    + (btAryData[5] & 0xFF);
            refreshSetting(m_curReaderSetting);
            onExeCMDStatus(btCmd,ERROR.SUCCESS);
            return;
        } else if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processSetBeeperMode(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processGetReaderTemperature(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x02) {
            m_curReaderSetting.btReadId = msgTran.getReadId();
            m_curReaderSetting.btPlusMinus = btAryData[0];
            m_curReaderSetting.btTemperature = btAryData[1];
            refreshSetting(m_curReaderSetting);
            onExeCMDStatus(btCmd,ERROR.SUCCESS);
            return;
        } else if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processReadGpioValue(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x02) {
            m_curReaderSetting.btReadId = msgTran.getReadId();
            m_curReaderSetting.btGpio1Value = btAryData[0];
            m_curReaderSetting.btGpio2Value = btAryData[1];
            refreshSetting(m_curReaderSetting);
            onExeCMDStatus(btCmd,ERROR.SUCCESS);
            return;
        } else if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processWriteGpioValue(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processSetAntConnectionDetector(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processGetAntConnectionDetector(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            m_curReaderSetting.btReadId = msgTran.getReadId();
            m_curReaderSetting.btAntDetector = btAryData[0];
            refreshSetting(m_curReaderSetting);
            onExeCMDStatus(btCmd,ERROR.SUCCESS);
            return;
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processSetTemporaryOutputPower(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processSetReaderIdentifier(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processGetReaderIdentifier(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x0C) {
            m_curReaderSetting.btReadId = msgTran.getReadId();

            Arrays.fill(m_curReaderSetting.btAryReaderIdentifier, (byte) 0x00);
            System.arraycopy(btAryData, 0,
                    m_curReaderSetting.btAryReaderIdentifier, 0,
                    btAryData.length);
            refreshSetting(m_curReaderSetting);
            onExeCMDStatus(btCmd,ERROR.SUCCESS);
            return;
        } else if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processSetRfLinkProfile(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processGetRfLinkProfile(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            if ((btAryData[0] & 0xFF) >= 0xD0 && (btAryData[0] & 0xFF) <= 0xD3) {
                m_curReaderSetting.btReadId = msgTran.getReadId();
                m_curReaderSetting.btRfLinkProfile = btAryData[0];
                refreshSetting(m_curReaderSetting);
                onExeCMDStatus(btCmd,ERROR.SUCCESS);
                return;
            } else {
                onExeCMDStatus(btCmd,btAryData[0]);
            }
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processGetRfPortReturnLoss(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            m_curReaderSetting.btReadId = msgTran.getReadId();
            m_curReaderSetting.btReturnLoss = btAryData[0];
            refreshSetting(m_curReaderSetting);
            onExeCMDStatus(btCmd,ERROR.SUCCESS);
            return;
        } else if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processInventory(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();
        if (btAryData.length == 0x09) {
            RXInventoryTag.RXInventoryTagEnd end = new RXInventoryTag.RXInventoryTagEnd();
            end.mCurrentAnt = btAryData[0];
            end.mTagCount = (btAryData[1] & 0xFF) * 256
                    + (btAryData[2] & 0xFF);
            end.mReadRate = (btAryData[3] & 0xFF) * 256
                    + (btAryData[4] & 0xFF);
            end.mTotalRead = (btAryData[5] & 0xFF) * 256 * 256 * 256
                    + (btAryData[6] & 0xFF) * 256 * 256 + (btAryData[7] & 0xFF)
                    * 256 + (btAryData[8] & 0xFF);
            end.cmd = btCmd;
            onInventoryTagEnd(end);
            return;
        } else if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd, btAryData[0]);
        } else {
            onExeCMDStatus(btCmd, ERROR.UNKONW_ERROR);
        }
    }
    private int mOperationTagCount = 0;
    private void processReadTag(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else {
            mOperationTagCount++;
            int nLen = btAryData.length;
            int nDataLen = (btAryData[nLen - 3] & 0xFF);
            int nEpcLen = (btAryData[2] & 0xFF) - nDataLen - 4;

            String strPC = StringTool.byteArrayToString(btAryData, 3, 2);
            String strEPC = StringTool.byteArrayToString(btAryData, 5, nEpcLen);
            String strCRC = StringTool.byteArrayToString(btAryData,
                    5 + nEpcLen, 2);
            String strData = StringTool.byteArrayToString(btAryData,
                    7 + nEpcLen, nDataLen);

            byte btTemp = btAryData[nLen - 2];
            byte btAntId = (byte) ((btTemp & 0x03) + 1 + ((btAryData[nLen - 1] & 0xFF) >> 7) * 4);
            int nReadCount = btAryData[nLen - 1] & 0x7F;

            RXOperationTag tag = new RXOperationTag();
            tag.strPC = strPC;
            tag.strCRC = strCRC;
            tag.strEPC = strEPC;
            tag.strData = strData;
            tag.nDataLen = nDataLen;
            tag.btAntId = btAntId;
            tag.nOperateCount = nReadCount;
            tag.cmd = msgTran.getCmd();
            onOperationTag(tag);

            if (mOperationTagCount == ((btAryData[0] & 0xFF) * 256 + (btAryData[1] & 0xFF))) {
                mOperationTagCount = 0;
                onOperationTagEnd((btAryData[0] & 0xFF) * 256 + (btAryData[1] & 0xFF));
            }
        }
    }

    private void processWriteTag(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();
        if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd, btAryData[0]);
        } else {
            int nLen = btAryData.length;
            int nEpcLen = (btAryData[2] & 0xFF) - 4;

            if (btAryData[nLen - 3] != ERROR.SUCCESS) {
                onExeCMDStatus(btCmd, btAryData[nLen - 3]);
                return;
            }
            mOperationTagCount++;
            String strPC = StringTool.byteArrayToString(btAryData, 3, 2);
            String strEPC = StringTool.byteArrayToString(btAryData, 5, nEpcLen);
            String strCRC = StringTool.byteArrayToString(btAryData,
                    5 + nEpcLen, 2);
            // add by lei.li 2016/11/10 I do not know why i fix it.
            //String strData = "";
            String strData = StringTool.byteArrayToString(btAryData, 0,
            		btAryData.length);
            //add by lei.li 2016/1/17 I do not know why i fix it;
            byte btTemp = btAryData[nLen - 2];
            byte btAntId = (byte) ((btTemp & 0x03) + 1 + ((btAryData[nLen - 1] & 0xFF) >> 7) * 4);
            int nReadCount = btAryData[nLen - 1] & 0x7F;

            RXOperationTag tag = new RXOperationTag();
            tag.strPC = strPC;
            tag.strCRC = strCRC;
            tag.strEPC = strEPC;
            tag.strData = strData;
            tag.nDataLen = btAryData.length;
            tag.btAntId = btAntId;
            tag.nOperateCount = nReadCount;
            tag.cmd = msgTran.getCmd();
            onOperationTag(tag);
            if (mOperationTagCount == ((btAryData[0] & 0xFF) * 256 + (btAryData[1] & 0xFF))) {
                mOperationTagCount = 0;
                onOperationTagEnd((btAryData[0] & 0xFF) * 256 + (btAryData[1] & 0xFF));
            }
        }
    }

    private void processLockTag(MessageTran msgTran) {
        processWriteTag(msgTran);
    }

    private void processKillTag(MessageTran msgTran) {
        processWriteTag(msgTran);
    }

    private void processSetAccessEpcMatch(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processGetAccessEpcMatch(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();
        if (btAryData.length == 0x01) {
            if (btAryData[0] == 0x01) {
                onExeCMDStatus(btCmd,ERROR.FAIL);
                return;
            } else {
                onExeCMDStatus(btCmd,btAryData[0]);
            }
        } else {
            if (btAryData[0] == 0x00) {
                m_curReaderSetting.mMatchEpcValue = StringTool
                        .byteArrayToString(btAryData, 2, btAryData[1] & 0xFF);
                refreshSetting(m_curReaderSetting);
                onExeCMDStatus(btCmd,ERROR.SUCCESS);
                return;
            } else {
                onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
            }
        }
    }

    private void processRealTimeInventory(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else if (btAryData.length == 0x07) {
            RXInventoryTag.RXInventoryTagEnd end = new RXInventoryTag.RXInventoryTagEnd();
            end.mCurrentAnt = btAryData[0];
            end.mReadRate = (btAryData[1] & 0xFF) * 256
                    + (btAryData[2] & 0xFF);
            end.mTotalRead = (btAryData[3] & 0xFF) * 256 * 256
                    * 256 + (btAryData[4] & 0xFF) * 256 * 256
                    + (btAryData[5] & 0xFF) * 256 + (btAryData[6] & 0xFF);
            end.cmd = btCmd;
            onInventoryTagEnd(end);
        } else {
            int nLength = btAryData.length;
            int nEpcLength = nLength - 4;

            //add by lei.li 2017/1/11
            String strEPC = "";
            if (nEpcLength != 0) {
                strEPC = StringTool.byteArrayToString(btAryData, 3, nEpcLength);
            }
            //add by lei.li 2017/1/11
            String strPC = StringTool.byteArrayToString(btAryData, 1, 2);
            String strRSSI = String.valueOf(btAryData[nLength - 1] & 0x7F);
            byte btTemp = btAryData[0];
            byte btAntId = (byte) ((btTemp & 0x03) + 1 + ((btAryData[nLength - 1] & 0xFF) >> 7) * 4);

            byte btFreq = (byte) ((btTemp & 0xFF) >> 2);
            String strFreq = getFreqString(btFreq);

            RXInventoryTag tag = new RXInventoryTag();
                tag.strPC = strPC;
                tag.strEPC = strEPC;
                tag.strRSSI = strRSSI;
                tag.strFreq = strFreq;
                tag.btAntId = btAntId;
                tag.cmd = btCmd;
            onInventoryTag(tag);
        }
    }

    private void processFastSwitchInventory(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();
        if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else if (btAryData.length == 0x02) {
            onExeCMDStatus(btCmd,btAryData[1]);
        } else if (btAryData.length == 0x07) {
            // m_nSwitchTotal, m_nSwitchTime
            int nSwitchTotal = (btAryData[0] & 0xFF) * 255 * 255
                    + (btAryData[1] & 0xFF) * 255 + (btAryData[2] & 0xFF);
            int nSwitchTime = (btAryData[3] & 0xFF) * 255 * 255 * 255
                    + (btAryData[4] & 0xFF) * 255 * 255 + (btAryData[5] & 0xFF)
                    * 255 + (btAryData[6] & 0xFF);
            RXInventoryTag.RXFastSwitchAntInventoryTagEnd tagEnd = new RXInventoryTag.RXFastSwitchAntInventoryTagEnd();
            tagEnd.mTotalRead = nSwitchTotal;
            tagEnd.mCommandDuration = nSwitchTime;
            onFastSwitchAntInventoryTagEnd(tagEnd);
        } else {
            int nLength = btAryData.length;
            int nEpcLength = nLength - 4;

            String strEPC = StringTool.byteArrayToString(btAryData, 3,
                    nEpcLength);
            String strPC = StringTool.byteArrayToString(btAryData, 1, 2);
            String strRSSI = String.valueOf(btAryData[nLength - 1] & 0x7F);
            byte btTemp = btAryData[0];
            byte btAntId = (byte) ((btTemp & 0x03) + 1 + ((btAryData[nLength - 1] & 0xFF) >> 7) * 4);
            // String strAntId = String.valueOf(btAntId & 0xFF);

            byte btFreq = (byte) ((btTemp & 0xFF) >> 2);
            String strFreq = getFreqString(btFreq);
            RXInventoryTag tag = new RXInventoryTag();
                tag.strPC = strPC;
                tag.strEPC = strEPC;
                tag.strRSSI = strRSSI;
                tag.strFreq = strFreq;
                tag.btAntId = btAntId;
                tag.cmd = btCmd;
            onInventoryTag(tag);
        }
    }

    /**
     * processCustomizedSessionTargetInventory and processRealTimeInventory return consistent.
     *
     * @param msgTran Packet contents
     */
    private void processCustomizedSessionTargetInventory(MessageTran msgTran) {
        processRealTimeInventory(msgTran);
    }

    private void processSetImpinjFastTid(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processSetAndSaveImpinjFastTid(MessageTran msgTran) {
        processSet(msgTran);
    }

    private void processGetImpinjFastTid(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();
        if (btAryData.length == 0x01) {
            if (btAryData[0] == 0x00 || (btAryData[0] & 0xFF) == 0x8D) {
                m_curReaderSetting.btReadId = msgTran.getReadId();
                m_curReaderSetting.btMonzaStatus = btAryData[0];
                refreshSetting(m_curReaderSetting);
                onExeCMDStatus(btCmd,ERROR.SUCCESS);
                return;
            } else {
                onExeCMDStatus(btCmd,btAryData[0]);
            }
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processISO180006BInventory(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            if ((btAryData[0] & 0xFF) != 0xFF) {
                onExeCMDStatus(btCmd,btAryData[0]);
            }
        } else if (btAryData.length == 0x09) {
            String strUID = StringTool.byteArrayToString(btAryData, 1, 8);
            onInventory6BTag(btAryData[0],strUID);
        } else if (btAryData.length == 0x02) {
            onInventory6BTagEnd(btAryData[1] & 0xFF);
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processISO180006BReadTag(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();
        if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else {
            String strData = StringTool.byteArrayToString(btAryData, 1,
                    btAryData.length - 1);
            onRead6BTag(btAryData[0],strData);
        }
    }

    private void processISO180006BWriteTag(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else {
            onWrite6BTag(btAryData[0],btAryData[1]);
        }
    }

    private void processISO180006BLockTag(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,ERROR.FAIL);
        } else {
            onLock6BTag(btAryData[0],btAryData[1]);
        }
    }

    private void processISO180006BQueryLockTag(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else {
            onLockQuery6BTag(btAryData[0],btAryData[1]);
        }
    }

    private void processGetInventoryBuffer(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd,btAryData[0]);
        } else {
            int nDataLen = btAryData.length;
            int nEpcLen = (btAryData[2] & 0xFF) - 4;

            String strPC = StringTool.byteArrayToString(btAryData, 3, 2);
            String strEPC = StringTool.byteArrayToString(btAryData, 5, nEpcLen);
            String strCRC = StringTool.byteArrayToString(btAryData,
                    5 + nEpcLen, 2);
            String strRSSI = String.valueOf(btAryData[nDataLen - 3] & 0x7F);
            byte btTemp = btAryData[nDataLen - 2];
            byte btAntId = (byte) ((btTemp & 0x03) + 1 + ((btAryData[nDataLen - 3] & 0xFF) >> 7) * 4);
            int nReadCount = btAryData[nDataLen - 1] & 0xFF;

            RXInventoryTag tag = new RXInventoryTag();
            tag.strPC = strPC;
            tag.strCRC = strCRC;
            tag.strEPC = strEPC;
            tag.btAntId = btAntId;
            tag.strRSSI = strRSSI;
            tag.mReadCount = nReadCount;
            tag.cmd = btCmd;
            onInventoryTag(tag);
        }
    }

    /**
     * processGetAndResetInventoryBuffer and processGetInventoryBuffer return consistent.
     *
     * @param msgTran Packet contents
     */
    private void processGetAndResetInventoryBuffer(MessageTran msgTran) {
        processGetInventoryBuffer(msgTran);
    }

    private void processGetInventoryBufferTagCount(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x02) {
            onGetInventoryBufferTagCount((btAryData[0] & 0xFF) * 256 + (btAryData[1] & 0xFF));
        } else if (btAryData.length == 0x01) {
            onExeCMDStatus(btCmd, btAryData[0]);
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    private void processResetInventoryBuffer(MessageTran msgTran) {
        byte btCmd = msgTran.getCmd();
        byte[] btAryData = msgTran.getAryData();

        if (btAryData.length == 0x01) {
            if (btAryData[0] == ERROR.SUCCESS) {
                onExeCMDStatus(btCmd,ERROR.SUCCESS);
                return;
            } else {
                onExeCMDStatus(btCmd,btAryData[0]);
            }
        } else {
            onExeCMDStatus(btCmd,ERROR.UNKONW_ERROR);
        }
    }

    //Some question the reader will return all the mask setting.
    //private int mMaskQuantity = 0;

	private void processTagMask(MessageTran msgTran) {
		/*
        byte[] btAryData = msgTran.getAryData();
        if (btAryData.length == 1) {
            if (btAryData[0] == (byte) 0x10) {
                onExeCMDStatus(msgTran.getCmd(),ERROR.SUCCESS);
                return;
            } else if (btAryData[0] == (byte) 0x41) {
                onExeCMDStatus(msgTran.getCmd(),btAryData[0]);
            } else {
                onExeCMDStatus(msgTran.getCmd(),ERROR.UNKONW_ERROR);
            }
        } else {
            if (btAryData.length > 7) {
            	MaskMap map = new MaskMap(msgTran.getAryData());
            	if (!m_curReaderSetting.mMaskValue.contains(map)) {
            		m_curReaderSetting.mMaskValue.add(map);
            		mMaskQuantity++;
            		//System.out.format("Maskquantity %d", mMaskQuantity);
            	}
                if (mMaskQuantity == map.mMaskQuantity) {
                	mMaskQuantity = 0;
                	refreshSetting(m_curReaderSetting);
                }
            }
        }*/
		if (msgTran != null) {
    		this.onConfigTagMask(msgTran);
    	}
    }

    private String getFreqString(byte btFreq) {
        if (m_curReaderSetting.btRegion == 4) {
            float nExtraFrequency = (float) (btFreq & 0xFF)
                    * (m_curReaderSetting.btUserDefineFrequencyInterval & 0xFF)
                    * 10;
            float nstartFrequency = (float) ((float) (m_curReaderSetting.nUserDefineStartFrequency & 0xFF)) / 1000;
            float nStart = (float) (nstartFrequency + nExtraFrequency / 1000);
            String strTemp = String.format("%.3f", nStart);
            return strTemp;
        } else {
            if ((btFreq & 0xFF) < 0x07) {
                float nStart = (float) (865.00f + (float) (btFreq & 0xFF) * 0.5f);
                String strTemp = String.format("%.2f", nStart);

                return strTemp;
            } else {
                float nStart = (float) (902.00f + ((float) (btFreq & 0xFF) - 7) * 0.5f);
                String strTemp = String.format("%.2f", nStart);
                return strTemp;
            }
        }
    }

    /**
     * This method will callback when you send command to inquire the settings of reader.
     * @param readerSetting return the ReaderSetting instance it include the inquire parameters.
     */
    protected void refreshSetting(ReaderSetting readerSetting) {
    }

    /**
     * This method will callback when you send command to reader.Return the status of command.
     * @param cmd The execution command reference {@link #CMD}
     * @param status The execution status of command,0x10 is success,0x11 is failed.
     */
    protected void onExeCMDStatus(byte cmd,byte status) {
    }

    /**
     * This method will callback when you send inventory command.Return inventoried tag it will
     * be wrapped {@link RXInventoryTag} object.
     * @param tag The {@link RXInventoryTag} object contain tag information.
     */
    protected void onInventoryTag(RXInventoryTag tag) {

    }

    /**
     * This method will callback when inventory end.Return {@link RXInventoryTag.RXInventoryTagEnd}
     * contain end information.
     * @param tagEnd {@link RXInventoryTag.RXInventoryTagEnd} object.
     */
    protected void onInventoryTagEnd(RXInventoryTag.RXInventoryTagEnd tagEnd) {

    }

    /**
     * This method will callback when fast switch antenna inventory end.Return {@link RXInventoryTag.com.rfid.rxobserver.bean.RXInventoryTag.RXFastSwitchAntInventoryTagEnd}
     * contain end information.
     * @param tagEnd {@link RXInventoryTag.com.rfid.rxobserver.bean.RXInventoryTag.RXFastSwitchAntInventoryTagEnd} object.
     */
    protected void onFastSwitchAntInventoryTagEnd(RXInventoryTag.RXFastSwitchAntInventoryTagEnd tagEnd) {

    }

    /**
     * This method will callback when inventory 6B tag.
     * @param nAntID current antenna id.
     * @param strUID the inventory uid.
     */
    protected void onInventory6BTag(byte nAntID, String strUID) {
    }

    /**
     * This method will callback when inventory 6B tag end.
     * @param nTagCount the inventory 6B tag count.
     */
    protected void onInventory6BTagEnd(int nTagCount) {
    }

    /**
     * This method will callback when read 6B tag.
     * @param antID current antenna id.
     * @param strData read 6B tag data.
     */
    protected void onRead6BTag(byte antID, String strData) {
    }

    /**
     * This method will callback when write 6B tag.
     * @param nAntID current antenna id.
     * @param nWriteLen Write data length.
     */
    protected void onWrite6BTag(byte nAntID, byte nWriteLen) {
    }

    /**
     * This method will callback when lock 6B tag.
     * @param nAntID  current antenna id.
     * @param nStatus Lock 6B return status (0x00:The byte is successfully locked. 0xFE:The byte is already locked. 0xFF:The byte can¡¯t be locked)
     */
    protected void onLock6BTag(byte nAntID, byte nStatus) {
    }

    /**
     * This method will callback when lock query 6B tag.
     * @param nAntID current antenna id.
     * @param nStatus Lock 6B return status (0x00:The byte is not locked.. 0xFE:The byte is locked.)
     */
    protected void onLockQuery6BTag(byte nAntID, byte nStatus) {
    }

    /**
     *  This method will callback when get the reader inventory buffer tag count.
     * @param nTagCount the tag count in reader inventory buffer.
     */
    protected void onGetInventoryBufferTagCount(int nTagCount) {
    }

    /**
     * This method will callback when you send read,write,lock or kill command.Return information it will be wrapped
     * {@link RXOperationTag} object.It contain the information you want to get.
     */
    protected void onOperationTag(RXOperationTag tag) {
    }

    /**
     * This method will callback when operation tag command execute finish.
     * @param operationTagCount operation tag count.
     */
    protected void onOperationTagEnd(int operationTagCount) {
    }
    
    /**
     * The method will callback when execute the commands about tag mask include {@link RFIDReaderHelper#setTagMask(byte, byte, byte, byte, byte, byte, byte, byte[])}}
     * {@link RFIDReaderHelper#getTagMask(byte)},{@link RFIDReaderHelper#clearTagMask(byte, byte)}
     * 
     */
    protected void onConfigTagMask(MessageTran msgTran) {
    }
}
