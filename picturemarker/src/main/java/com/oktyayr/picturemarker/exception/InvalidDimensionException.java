package com.oktyayr.picturemarker.exception;

/**
 * InvalidDimensionException is thrown when marker dimension is invalid.
 * For example, if cursor width is wider than image size, then the exception is thrown
 *
 * @author  Oktay AYAR
 * @version 1.0.0
 * @since 1.0.0
 *
 * Date: 11.01.2016
 */
public class InvalidDimensionException extends Exception {
    public InvalidDimensionException(String detailedMessage)
    {
        super(detailedMessage);
    }
}
