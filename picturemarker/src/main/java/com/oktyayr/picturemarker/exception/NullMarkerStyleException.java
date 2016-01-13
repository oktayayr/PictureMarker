package com.oktyayr.picturemarker.exception;

/**
 * NullImageException is thrown marker style of {@link com.oktyayr.picturemarker.PictureMarker PictureMarker} is null
 *
 * @author  Oktay AYAR <11.1.2016>
 * @version 1.0.0
 * @since 1.0.0
 */
public class NullMarkerStyleException extends NullPointerException {
    public NullMarkerStyleException()
    {
        super("Marker style has not been set");
    }
}
