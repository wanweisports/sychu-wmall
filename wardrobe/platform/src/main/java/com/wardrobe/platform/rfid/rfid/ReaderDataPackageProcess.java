package com.wardrobe.platform.rfid.rfid;

import com.wardrobe.platform.rfid.module.interaction.DataPackageProcess;
import com.wardrobe.platform.rfid.rfid.bean.MessageTran;

/**
 * The implementation class of DataPackageProcess.
 */

public class ReaderDataPackageProcess extends DataPackageProcess {
    @Override
    public void analyData(byte[] btPackage) {
        MessageTran msgTran = new MessageTran(btPackage);
        if (msgTran != null) {
            setChanged();
            notifyObservers(msgTran);
        }
    }
}
