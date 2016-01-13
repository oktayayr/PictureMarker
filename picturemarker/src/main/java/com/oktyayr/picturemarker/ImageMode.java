package com.oktyayr.picturemarker;

/**
 * ImageMode determines mode of image that is put into {@link PictureMarker PictureMarker}<br/><br/>
 *
 * {@link #STRECT STRECT} Determines that given image will be scaled and strected.<br/>
 * {@link #FIT FIT} Determines that given image will be fit into marker according to its height or width.<br/>
 * {@link #CENTER_ZOOM CENTER ZOOM} Determines that given image will be cropped from its center according to {@link PictureMarker#imageSize imageSize}
 * and cropped image will be put into marker<br/>
 *
 * @author Oktay AYAR <11.1.2016>
 * @version 1.0.0
 * @since 1.0.0
 */
public enum ImageMode {
    STRECT,
    CENTER_ZOOM,
    FIT
}
