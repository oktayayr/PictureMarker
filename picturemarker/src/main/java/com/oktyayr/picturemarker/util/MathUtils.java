package com.oktyayr.picturemarker.util;

/**
 * MathUtils contains methods for mathematical calculations
 *
 * @author  Oktay AYAR
 * @version 1.0.0
 * @since 1.0.0
 *
 * Date: 12.01.2016
 */
public class MathUtils {
    /**
     * Calculates one side of triangle whose hypotenuse and other side is given.
     *
     * @param hypotenuse the hypotenuse
     * @param side       the other side
     * @return the side
     */
    public static float calculateSide(float hypotenuse, float side) {
        return (float) Math.sqrt(Math.pow(hypotenuse, 2) - Math.pow(side, 2));
    }
}
