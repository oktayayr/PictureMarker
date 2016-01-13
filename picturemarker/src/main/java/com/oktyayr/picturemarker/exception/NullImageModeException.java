package com.oktyayr.picturemarker.exception;

/**
 * NullImageException is thrown image mode of {@link com.oktyayr.picturemarker.PictureMarker PictureMarker} is null
 *
 * @author  Oktay AYAR <11.1.2016>
 * @version 1.0.0
 * @since 1.0.0
 */
public class NullImageModeException extends NullPointerException{
    public  NullImageModeException()
    {
        super("Image mode has not been set");
    }
}
