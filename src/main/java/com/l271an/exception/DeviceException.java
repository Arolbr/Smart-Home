package com.l271an.exception;

public class DeviceException extends Exception{
    public DeviceException() {
        super();
    }

    public DeviceException(String message) {
        super(message);
    }

    public DeviceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceException(Throwable cause) {
        super(cause);
    }

    public DeviceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
