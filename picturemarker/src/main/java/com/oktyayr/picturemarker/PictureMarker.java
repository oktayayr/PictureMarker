package com.oktyayr.picturemarker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.View;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oktyayr.picturemarker.exception.InvalidDimensionException;
import com.oktyayr.picturemarker.exception.NullImageException;
import com.oktyayr.picturemarker.exception.NullImageModeException;
import com.oktyayr.picturemarker.exception.NullMarkerStyleException;
import com.oktyayr.picturemarker.util.GraphicsUtil;
import com.oktyayr.picturemarker.util.MathUtils;

/**
 * Picture Marker is marker that can contains image inside in.
 *
 * @author Oktay AYAR
 * @version 1.0.2
 * @since 1.0.0
 * <br>
 * Date: 17.01.2016
 */
public class PictureMarker{
    private final String LOG_TAG = PictureMarker.class.getSimpleName();

    private Context context;

    private Bitmap image;
    private View iconView;
    private MarkerStyle markerStyle;
    private ImageMode imageMode;

    private int borderColor;
    private int scrapAreaColor;

    private float imageSize;
    private float borderWidth;
    private float cornerRadius;

    private float cursorHeight;
    private float cursorWidth;

    /**
     * Constructs new Picture Marker with given bitmap
     *
     * @param context {@link android.content.Context Context} instance to gather screen density
     * @param image Image that will be shown on marker
     */
    public PictureMarker(Context context, Bitmap image) {
        // Set bitmap
        this.image = image;

        // Set defaults
        markerStyle = MarkerStyle.SQUARE;
        imageMode = ImageMode.FIT;

        borderColor = Color.BLUE;
        scrapAreaColor = Color.WHITE;

        imageSize = 25.0f;
        borderWidth = 2.0f;
        cornerRadius = 2.0f;

        cursorHeight = imageSize / 2;
        cursorWidth = (imageSize + (2 * borderWidth)) / 2;
    }

    /**
     * Instantiates a new Picture marker with given {@link android.view.View View}.
     *
     * @param iconView the icon view for marker
     */
    public PictureMarker(View iconView) {
        this.iconView = iconView;
    }

    /**
     * Creates {@link com.google.android.gms.maps.model.MarkerOptions MarkerOptions} according to properties
     *
     * @return {@link com.google.android.gms.maps.model.MarkerOptions MarkerOptions}
     *
     * @throws IllegalStateException when both image and view are set
     * @throws InvalidDimensionException when cursor width is wider than image size
     * @throws NullImageException        when marker image is null
     * @throws NullImageModeException    when image mode is null.
     * @throws NullMarkerStyleException  when marker style is null
     */
    public MarkerOptions build() throws InvalidDimensionException, NullImageException,
            NullImageModeException, NullMarkerStyleException, IllegalStateException {
        // Create marker options
        MarkerOptions opt;
        Bitmap bmp;

        if (image != null && iconView != null) {
            throw new IllegalStateException();
        } else if (image != null) {
            float screenDensity;

            screenDensity = context.getResources().getDisplayMetrics().density;

            bmp = createMarkerBitmap(screenDensity);

            opt = new MarkerOptions().anchor(0.5f, 1.0f).icon(BitmapDescriptorFactory.fromBitmap(bmp));

        } else {
            bmp = viewToBitmap(iconView);

            opt = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(bmp));
        }

        return opt;
    }

    /**
     * Gets image of marker
     *
     * @return Image of marker
     */
    public Bitmap getImage() {
        return image;
    }

    /**
     * Sets image of marker
     *
     * @param image the image
     */
    public void setImage(Bitmap image) {
        this.image = image;
    }


    /**
     * Gets marker style.
     *
     * @return the marker style
     */
    public MarkerStyle getMarkerStyle() {
        return markerStyle;
    }

    /**
     * Sets marker style.
     *
     * @param markerStyle the marker style
     */
    public void setMarkerStyle(MarkerStyle markerStyle) {
        this.markerStyle = markerStyle;
    }

    /**
     * Gets image mode.
     *
     * @return image mode
     */
    public ImageMode getImageMode() {
        return imageMode;
    }

    /**
     * Sets image mode.
     *
     * @param imageMode the image mode
     */
    public void setImageMode(ImageMode imageMode) {
        this.imageMode = imageMode;
    }

    /**
     * Gets border color.
     *
     * @return border color
     */
    public int getBorderColor() {
        return borderColor;
    }

    /**
     * Sets border color.
     *
     * @param borderColor the border color
     */
    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * Gets scrap area color.
     *
     * @return scrap area color
     */
    public int getScrapAreaColor() {
        return scrapAreaColor;
    }

    /**
     * Sets scrap area color.
     *
     * @param scrapAreaColor the scrap area color
     */
    public void setScrapAreaColor(int scrapAreaColor) {
        this.scrapAreaColor = scrapAreaColor;
    }

    /**
     * Gets image size. (in dp)
     *
     * @return image size
     */
    public float getImageSize() {
        return imageSize;
    }

    /**
     * Sets image size. (in dp)
     * <br>
     * <b>Warning: this method set cursor width and height to default</b>
     *
     * @param imageSize the image size
     */
    public void setImageSize(float imageSize) {
        this.imageSize = imageSize;

        cursorHeight = imageSize / 2;
        cursorWidth = imageSize / 2;
    }

    /**
     * Gets border width. (in dp)
     *
     * @return border width
     */
    public float getBorderWidth() {
        return borderWidth;
    }

    /**
     * Sets border width. (in dp)
     *
     * @param borderWidth the border width
     */
    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * Gets corner radius. (in dp)
     *
     * @return corner radius
     */
    public float getCornerRadius() {
        return cornerRadius;
    }

    /**
     * Sets corner radius. (in dp)
     *
     * @param cornerRadius the corner radius
     */
    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    /**
     * Gets cursor height. (in dp)
     * Default value is half of imageSize
     *
     * @return cursor height
     */
    public float getCursorHeight() {
        return cursorHeight;
    }

    /**
     * Sets cursor height. (in dp)
     *
     * @param cursorHeight the cursor height
     */
    public void setCursorHeight(float cursorHeight) {
        this.cursorHeight = cursorHeight;
    }

    /**
     * Gets cursor width. (in dp)
     * Default value is half of imageSize
     *
     * @return cursor width
     */
    public float getCursorWidth() {
        return cursorWidth;
    }

    /**
     * Sets cursor width. (in dp)
     *
     * @param cursorWidth the cursor width
     */
    public void setCursorWidth(float cursorWidth) {
        this.cursorWidth = cursorWidth;
    }

    private Bitmap createMarkerBitmap(float density) throws InvalidDimensionException, NullImageException,
            NullImageModeException, NullMarkerStyleException {

        // Check for requirements

        if (image == null)
            throw new NullImageException();

        if (markerStyle == null)
            throw new NullMarkerStyleException();

        if (imageMode == null)
            throw new NullImageModeException();

        if (cursorWidth > (imageSize + (2 * borderWidth)))
            throw new InvalidDimensionException("Cursor cannot be wider than image size");

        // Define variables
        Bitmap output;
        Canvas canvas;
        Paint paint;
        Point p1, p2, p3;
        Path p;
        CornerPathEffect cornerPathEffect;

        float imageSizePx, borderWidthPx, cursorHeightPx, cursorWidthPx, cornerRadiusPx;

        // Set variables
        imageSizePx = imageSize * density;
        borderWidthPx = borderWidth * density;
        cursorHeightPx = cursorHeight * density;
        cursorWidthPx = cursorWidth * density;
        cornerRadiusPx = cornerRadius * density;

        // Create objects
        if (markerStyle.equals(MarkerStyle.SQUARE)) {
            output = Bitmap.createBitmap((int) (imageSizePx + (2 * borderWidthPx)), (int) (imageSizePx + borderWidthPx + cursorHeightPx), Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap((int) (imageSizePx + (2 * borderWidthPx)),
                    (int) ((imageSizePx / 2) + borderWidthPx + cursorHeightPx + MathUtils.calculateSide(imageSizePx / 2, cursorWidthPx / 2)), Bitmap.Config.ARGB_8888);
        }
        canvas = new Canvas(output);
        paint = new Paint();

        // Set defaults
        paint.setAntiAlias(true);

        // Paint bitmap to transparent
        canvas.drawColor(Color.TRANSPARENT);

        // Draw border,cursor and scrap area
        if (markerStyle.equals(MarkerStyle.CIRCLE)) {
            // Draw border circle
            paint.setColor(borderColor);
            canvas.drawCircle((imageSizePx / 2) + borderWidthPx, (imageSizePx / 2) + borderWidthPx, (imageSizePx / 2) + borderWidthPx, paint);

            // Draw cursor
            p1 = new Point((int) ((imageSizePx / 2) + borderWidthPx - (cursorWidthPx / 2)),
                    (int) ((imageSizePx * 0.5) + borderWidthPx + MathUtils.calculateSide(imageSizePx / 2, cursorWidthPx / 2)));
            p2 = new Point(p1.x + (int) (cursorWidthPx / 2), p1.y + (int) cursorHeightPx);

            p3 = new Point(p1.x + (int) cursorWidthPx, p1.y);

            p = new Path();
            p.setFillType(Path.FillType.EVEN_ODD);

            p.moveTo(p1.x, p1.y);
            p.lineTo(p2.x, p2.y);
            p.lineTo(p3.x, p3.y);
            p.lineTo(p1.x, p1.y);
            p.close();

            cornerPathEffect = new CornerPathEffect(cornerRadiusPx);

            paint.setPathEffect(cornerPathEffect);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPath(p, paint);

            // Draw scrap circle

            paint.setColor(scrapAreaColor);
            canvas.drawCircle((imageSizePx / 2) + borderWidthPx, (imageSizePx / 2) + borderWidthPx, (imageSizePx / 2), paint);
        } else {
            RectF border, scrap;

            // Draw border rectangle
            border = new RectF(0, 0, imageSizePx + (2 * borderWidthPx), imageSizePx + (2 * borderWidthPx));
            paint.setColor(borderColor);
            canvas.drawRoundRect(border, cornerRadiusPx, cornerRadiusPx, paint);

            // Draw cursor
            p1 = new Point((int) ((imageSizePx / 2) + borderWidthPx - (cursorWidthPx / 2)), (int) (imageSizePx + borderWidthPx));
            p2 = new Point(p1.x + (int) (cursorWidthPx / 2), p1.y + (int) cursorHeightPx);
            p3 = new Point(p1.x + (int) cursorWidthPx, p1.y);

            p = new Path();
            p.setFillType(Path.FillType.EVEN_ODD);

            p.moveTo(p1.x, p1.y);
            p.lineTo(p2.x, p2.y);
            p.lineTo(p3.x, p3.y);
            p.lineTo(p1.x, p1.y);

            cornerPathEffect = new CornerPathEffect(cornerRadiusPx);

            paint.setPathEffect(cornerPathEffect);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPath(p, paint);

            // Draw scrap rectangle
            scrap = new RectF(borderWidthPx, borderWidthPx, imageSizePx + borderWidthPx, imageSizePx + borderWidthPx);
            paint.setColor(scrapAreaColor);
            canvas.drawRect(scrap, paint);
        }

        // Draw given image
        if (imageMode.equals(ImageMode.CENTER_ZOOM)) {
            if (imageSizePx > image.getWidth() && imageSizePx > image.getHeight()) {
                if (markerStyle.equals(MarkerStyle.SQUARE)) {
                    float bmpX, bmpY;

                    bmpX = borderWidthPx + ((imageSizePx - image.getWidth()) / 2);
                    bmpY = borderWidthPx + ((imageSizePx - image.getHeight()) / 2);

                    canvas.drawBitmap(image, bmpX, bmpY, null);
                } else {
                    canvas.drawBitmap(GraphicsUtil.circularCrop(image, imageSizePx / 2, false, scrapAreaColor), borderWidthPx, borderWidthPx, null);
                }
            } else {
                Bitmap croppedImage;
                int startX, startY;

                startX = (int) ((image.getWidth() - imageSizePx) / 2);

                if (startX <= 0)
                    startX = 0;

                startY = (int) ((image.getHeight() - imageSizePx) / 2);

                if (startY <= 0)
                    startY = 0;

                croppedImage = Bitmap.createBitmap(image, startX, startY, (int) imageSizePx, (int) imageSizePx);

                if (markerStyle.equals(MarkerStyle.CIRCLE))
                    croppedImage = GraphicsUtil.circularCrop(croppedImage, imageSizePx / 2, false);

                canvas.drawBitmap(croppedImage, borderWidthPx, borderWidthPx, null);
            }

        } else if (imageMode.equals(ImageMode.FIT)) {
            Bitmap resizedImage;

            if (markerStyle.equals(MarkerStyle.CIRCLE)) {
                canvas.drawBitmap(GraphicsUtil.circularCrop(image, imageSizePx / 2, true, scrapAreaColor), borderWidthPx, borderWidthPx, null);
            } else {
                if (image.getHeight() == image.getWidth()) {
                    resizedImage = Bitmap.createScaledBitmap(image, (int) imageSizePx, (int) imageSizePx, false);

                    canvas.drawBitmap(resizedImage, borderWidthPx, borderWidthPx, null);
                } else if (image.getHeight() < image.getWidth()) {
                    float ratio;
                    int newHeight;

                    ratio = imageSizePx / image.getWidth();
                    newHeight = (int) (image.getHeight() * ratio);

                    resizedImage = Bitmap.createScaledBitmap(image, (int) imageSizePx, newHeight, false);

                    canvas.drawBitmap(resizedImage, borderWidthPx, (int) (((imageSizePx - newHeight) / 2) + borderWidthPx), null);
                } else {
                    float ratio;
                    int newWidth;

                    ratio = imageSizePx / image.getHeight();
                    newWidth = (int) (image.getWidth() * ratio);

                    resizedImage = Bitmap.createScaledBitmap(image, newWidth, (int) imageSizePx, false);

                    canvas.drawBitmap(resizedImage, (int) (((imageSizePx - newWidth) / 2) + borderWidthPx), borderWidthPx, null);
                }
            }
        } else {
            Bitmap resizedImage;

            resizedImage = Bitmap.createScaledBitmap(image, (int) imageSizePx, (int) imageSizePx, false);

            if (markerStyle.equals(MarkerStyle.CIRCLE))
                resizedImage = GraphicsUtil.circularCrop(resizedImage, imageSizePx / 2, false);

            canvas.drawBitmap(resizedImage, borderWidthPx, borderWidthPx, null);
        }

        return output;
    }

    private Bitmap viewToBitmap(View view) {
        int spec;
        Bitmap b, cacheBmp, viewBmp;
        Canvas c;

        spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        b = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        c = new Canvas(b);
        c.translate(-view.getScrollX(), -view.getScrollY());
        view.draw(c);
        view.setDrawingCacheEnabled(true);
        cacheBmp = view.getDrawingCache();

        viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
        view.destroyDrawingCache();

        return viewBmp;
    }
}
