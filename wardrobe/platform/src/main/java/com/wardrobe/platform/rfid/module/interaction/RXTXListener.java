package com.wardrobe.platform.rfid.module.interaction;

/**
 * Implement this interface who want to listen sending data to reader,receiving data from reader,and the connection missing with reader.
 */
public interface RXTXListener {
	/**
     * This method will callback when the reader receive the data returned by reader.
     * You can monitor the raw data return from reader via override it.
     * And this method running in child thread.
     *
     * @param btAryReceiveData return data.
     */
    void reciveData(byte[] btAryReceiveData);

    /**
     * This method will callback when the reader send the data returned by reader.
     * You can monitor the raw data send to reader via override it.
     *
     * @param btArySendData the send data to reader.
     */
    void sendData(byte[] btArySendData);

    /**
     * This method will callback when the monitor thread of data returned by reader is quit.
     * You can monitor it to do something when the monitor thread of data returned by reader quit.
     * And this method running in child thread.
     */
    void onLostConnect();
}
