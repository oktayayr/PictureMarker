package com.oktyayr.picturemarker.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;


/**
 * GraphicUtils contains methods for image processing
 *
 * @author  Oktay AYAR
 * @version 1.0.0
 * @since 1.0.0
 *
 * Date: 12.01.2016
 */
public class GraphicsUtil {
    /**
     * Crops given image circular
     *
     * @param origin          the original bitmap
     * @param radius          the radius of circle
     * @param scale           if true given bitmap will be scaled according to radius, otherwise not.
     * @return the cropped bitmap
     */
    public static Bitmap circularCrop(Bitmap origin, float radius, boolean scale) {
        final int color = 0xff424242;

        return circularCrop(origin, radius, scale, color);
    }

    /**
     * Crops given image circular
     *
     * @param origin          the original bitmap
     * @param radius          the radius of circle
     * @param scale           if true given bitmap will be scaled according to radius, otherwise not.
     * @param backgroundColor the background color of circle
     * @return the cropped bitmap
     */
    public static Bitmap circularCrop(Bitmap origin, float radius, boolean scale, int backgroundColor) {

        Bitmap output, scaled;
        Canvas canvas;
        Paint paint;
        Rect src, dest;

        if (radius > origin.getHeight() && radius > origin.getWidth())
            radius = origin.getHeight() > origin.getWidth() ? origin.getHeight() : origin.getWidth();

        // Create objects
        output = Bitmap.createBitmap((int) (radius * 2), (int) (radius * 2), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(output);
        paint = new Paint();

        paint.setAntiAlias(true);

        // Paint transparent
        canvas.drawColor(Color.TRANSPARENT);

        // Draw circle
        paint.setColor(backgroundColor);

        canvas.drawCircle(radius, radius, radius, paint);

        // Draw bitmap
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        if (scale) {
            if (origin.getHeight() == origin.getWidth()) {
                scaled = Bitmap.createScaledBitmap(origin, (int) (radius * 2), (int) (radius * 2), false);
                dest = new Rect(0, 0, scaled.getWidth(), scaled.getHeight());
            } else if (origin.getHeight() < origin.getWidth()) {
                float ratio;
                int newHeight;

                ratio = (float) output.getWidth() / (float) origin.getWidth();
                newHeight = (int) (origin.getHeight() * ratio);

                scaled = Bitmap.createScaledBitmap(origin, output.getWidth(), newHeight, false);

                dest = new Rect(0, (output.getHeight() - newHeight) / 2, scaled.getWidth(), ((output.getHeight() - newHeight) / 2) + newHeight);
            } else {
                float ratio;
                int newWidth;

                ratio = (float) output.getHeight() / (float) origin.getHeight();
                newWidth = (int) (origin.getWidth() * ratio);

                scaled = Bitmap.createScaledBitmap(origin, newWidth, output.getHeight(), false);

                dest = new Rect((output.getWidth() - newWidth) / 2, 0, ((output.getWidth() - newWidth) / 2) + newWidth, scaled.getHeight());
            }

            src = new Rect(0, 0, scaled.getWidth(), scaled.getHeight());
        } else {
            scaled = origin.copy(Bitmap.Config.ARGB_8888, true);
            int x1, x2, y1, y2;

            x1 = (int) (((float) origin.getWidth() / 2) - radius);
            x2 = x1 + (int) (2 * radius);
            y1 = (int) (((float) origin.getHeight() / 2) - radius);
            y2 = y1 + (int) (2 * radius);

            src = new Rect(x1, y1, x2, y2);
            dest = new Rect(0, 0, scaled.getWidth(), scaled.getHeight());
        }


        canvas.drawBitmap(scaled, src, dest, paint);

        return output;
    }
}
