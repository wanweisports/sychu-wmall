package com.wardrobe.platform.rfid.module.interaction;

/**
 * You can receive the raw data returned by module.In order to get the data information you must
 * resolve it according to the protocol of module.Then you can invoke method {@link #analyData(byte[])}
 * in {@link #DataPackageProcess} put the data package to it.The default implementation of {
 * @link #DataPackageParser} is {@link #ReaderDataPackageParser(RFID Reader) #TDScannerDataPackageParser
 * (2D Scanner)}
 */

public interface DataPackageParser {
    /**
     * The method can receive the raw data returned by module.In order to get the data information you must
     * resolve it according to the protocol of module.
     * @param btAryReceiveData The returned data by module.
     * @param dataPackageProcess The class process different data package return information.
     */
    void runReceiveDataCallback(byte[] btAryReceiveData, DataPackageProcess dataPackageProcess);
}
