package com.timprogrammiert.exceptions;

/**
 * @author tmatz
 */
public class NotADirectoryException extends Exception{

    public NotADirectoryException(String wrongObjectName) {
        super(wrongObjectName + " is a File!");
    }
}
