package com.timprogrammiert.exceptions;

/**
 * @author tmatz
 */
public class FileNotExistsException extends Exception{

    public FileNotExistsException() {
        super("File not found!");
    }
}
