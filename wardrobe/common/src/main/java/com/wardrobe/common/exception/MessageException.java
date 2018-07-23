package com.wardrobe.common.exception;

import java.util.HashMap;
import java.util.Map;

public class MessageException extends RuntimeException {
    public Map<String,String> dataMap = new HashMap<String,String>();

    public MessageException() {
        super();
    }

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String dataKey,String dataValue,String message) {
        super(message);
        dataMap.put(dataKey,dataValue);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageException(Throwable cause) {
        super(cause);
    }

    protected MessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
