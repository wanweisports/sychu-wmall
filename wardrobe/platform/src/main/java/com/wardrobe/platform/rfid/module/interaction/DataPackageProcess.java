package com.wardrobe.platform.rfid.module.interaction;

import java.util.Observable;

/**
 * The return data process class.This class resolve the return data package to
 * find the information you want to get.The method {@link #analyData(byte[])}
 * receive the complete data package returned by module. Then you can process
 * the data package by yourself.The default implement is {@link #ReaderDataPackageProcess
 * (RFID Reader) #TDScannerDataPackageProcess(2D Scanner)}
 */

public abstract class DataPackageProcess extends Observable {
    /**
     * The data return method,you can receive the return data
     * if you implements this method.
     * @param btPackage the module return data package.
     */
    public abstract void analyData(byte[] btPackage);
}