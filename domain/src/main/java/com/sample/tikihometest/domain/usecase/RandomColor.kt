package com.sample.tikihometest.domain.usecase

import com.sample.tikihometest.domain.di.DomainScope
import com.sample.tikihometest.domain.util.Mockable
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * This use case generate selective random color
 * see [invoke] for more information
 *
 * @see invoke
 */
@DomainScope
@Mockable
class RandomColor @Inject constructor() {

    /**
     * Generate random color
     *
     * @param inputSaturation the saturation, default to 1.0f if not set
     * @param inputLightness the lightness, default to 0.25f if not set
     *
     * @return rgb color as [Int]
     */
    operator fun invoke(inputSaturation: Float? = null, inputLightness: Float? = null): Int {
        // random hue
        val hue = Random.nextInt(0, 1000) / 1000f
        // fully saturated
        val saturation = inputSaturation ?: 1.0f
        // dark color
        val lightness = inputLightness ?: 0.25f

        // convert hsl to rgb
        val (red, green, blue) = hslToRgb(HSL(hue, saturation, lightness))

        // convert rgb to int, no opacity
        return getArgb(255, red, green, blue)
    }

    /**
     * Since argb value is in [0, 255] range it can be represented in 8 bit (1 byte)
     * an [Int] is 4 bytes long so we can compact 4 a, r, g, b into an [Int]
     * Int: x-x-x-x
     *      ^ ^ ^ ^
     *      a r g b
     * @return argb color compacted as an [Int]
     *
     * @throws IllegalArgumentException if [alpha] or [red] or [green] or [blue] is not in [0, 255] range
     */
    internal fun getArgb(alpha: Int, red: Int, green: Int, blue: Int): Int {
        if (alpha < 0 || alpha > 255) throw IllegalArgumentException("Invalid alpha: $alpha")
        if (red < 0 || red > 255) throw IllegalArgumentException("Invalid red: $red")
        if (green < 0 || green > 255) throw IllegalArgumentException("Invalid green: $green")
        if (blue < 0 || blue > 255) throw IllegalArgumentException("Invalid blue: $blue")
        return (alpha shl 24) or (red shl 16) or (green shl 8) or blue
    }

    /**
     * Converts an HSL color value to RGB. Conversion formula
     * adapted from http://en.wikipedia.org/wiki/HSL_color_space.
     *
     * @param [hsl] hsl data
     *
     * @return The RGB representation
     *
     * @throws IllegalArgumentException if [hsl] is not valid
     * @throws RuntimeException if input [hsl] produce a invalid rgb result
     *
     * @see [HSL]
     * @see [RGB]
     * @author: https://gist.github.com/mjackson/5311256
     */
    internal fun hslToRgb(hsl: HSL): RGB {
        if (!hsl.isValid())
            throw IllegalArgumentException("HSL: $hsl is not valid")
        val (hue, saturation, lightness) = hsl
        val red: Float
        val green: Float
        val blue: Float
        if (saturation == 0f) {
            red = lightness
            green = lightness
            blue = lightness
        } else {
            val q = if (lightness < 0.5f)
                lightness * (1 + saturation)
            else
                lightness + saturation - lightness * saturation
            val p = 2f * lightness - q
            red = hue2rgb(p, q, hue + 1f / 3f)
            green = hue2rgb(p, q, hue)
            blue = hue2rgb(p, q, hue - 1f / 3f)
        }
        val result = RGB(
            red = (red * 255f).roundToInt(),
            green = (green * 255f).roundToInt(),
            blue = (blue * 255f).roundToInt()
        )
        if (!result.isValid())
            throw RuntimeException("Unexpected error, hsl: $hsl produce an invalid rgb resukt: $result")
        return result
    }

    /**
     * @see [hslToRgb]
     */
    internal fun hue2rgb(p0: Float, q0: Float, t0: Float): Float {
        var t = t0
        if (t < 0) t += 1
        if (t > 1) t -= 1
        if (t < 1 / 6f) return p0 + (q0 - p0) * 6f * t
        if (t < 1 / 2f) return q0
        if (t < 2 / 3f) return p0 + (q0 - p0) * (2f / 3f - t) * 6
        return p0
    }

    /**
     * Representations of the RGB color model
     * [red], [green], [blue] value range from 0 to 255
     */
    @Mockable
    internal data class RGB(val red: Int, val green: Int, val blue: Int) {
        /**
         * @return true if all value is in range, false otherwise
         */
        internal fun isValid(): Boolean {
            if (red < 0 || red > 255) return false
            if (green < 0 || green > 255) return false
            if (blue < 0 || blue > 255) return false
            return true
        }
    }

    /**
     * Representations of the HSL color model
     * [hue], [saturation], [lightness] value range from 0.0f to 1.0f
     */
    internal data class HSL(val hue: Float, val saturation: Float, val lightness: Float) {
        /**
         * @return true if all value is in range, false otherwise
         */
        internal fun isValid(): Boolean {
            if (hue < 0.0f || hue > 1.0f) return false
            if (saturation < 0.0f || saturation > 1.0f) return false
            if (lightness < 0.0f || lightness > 1.0f) return false
            return true
        }
    }
}