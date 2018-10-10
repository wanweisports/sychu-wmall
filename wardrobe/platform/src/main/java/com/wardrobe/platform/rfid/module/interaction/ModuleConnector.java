package com.wardrobe.platform.rfid.module.interaction;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * The interface manage the connector of module.
 */

public interface ModuleConnector {
    /**
     * Connection the reader through serial port.
     * @param port The serial port;
     * @param baud The baud rate;
     * @return if true connecting success,or failed.
     */
    ReaderHelper connectCom(final String port, final int baud);

    /**
     * Connection the reader through the network;
     * @param host The ip address;
     * @param port The prot;
     * @return if true connecting success,or failed.
     */
    ReaderHelper connectNet(final String host, final int port);

    /**
     * Different data transmission form can import via I/O stream. 
     * @param in read steam.
     * @param out write steam.
     * @return {@link #ReaderHelper} return the core class to operate Reader.
     */
    ReaderHelper connect(final InputStream in, final OutputStream out);
    /**
     * Verify the the connection is available or not.
     * @return if true connection available,or unavailable;
     */
    boolean isConnected();

    /**
     * Interrupt the connection;
     */
    void disConnect();
}
