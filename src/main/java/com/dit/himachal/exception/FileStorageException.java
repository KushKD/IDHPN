package com.dit.himachal.exception;

import com.dit.himachal.apicontroller.API;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStorageException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5276564705339824862L;

    private static final Logger logger = LoggerFactory.getLogger(FileStorageException.class);

	public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}