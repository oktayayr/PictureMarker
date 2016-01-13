package com.oktyayr.picturemarker.exception;

/**
 * NullImageException is thrown image of {@link com.oktyayr.picturemarker.PictureMarker PictureMarker} is null
 *
 * @author  Oktay AYAR
 * @version 1.0.0
 * @since 1.0.0
 *
 * Date: 11.01.2016
 */
public class NullImageException extends NullPointerException {
    public  NullImageException()
    {
        super("Image for marker has not been set");
    }
}
