package de.pp.copyhelper;

public class CopyHelperException extends Exception {

    public CopyHelperException() {
    }

    public CopyHelperException(String message) {
        super(message);
    }

    public CopyHelperException(Exception e) {
        super(e);
    }

}
