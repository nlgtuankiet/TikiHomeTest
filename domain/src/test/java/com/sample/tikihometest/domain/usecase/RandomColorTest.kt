package com.sample.tikihometest.domain.usecase

import com.sample.tikihometest.domain.usecase.RandomColor.HSL
import com.sample.tikihometest.domain.usecase.RandomColor.RGB
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class RandomColorTest {
    private val usecase = RandomColor()

    @Test
    fun `getArgb return correct white color`() {
        val expected = -1
        val actual = usecase.getArgb(255, 255, 255, 255)
        assertEquals(expected, actual)
    }

    @Test
    fun `getArgb return correct prime number color`() {
        val expected = 1502331387
        val actual = usecase.getArgb(89, 139, 193, 251)
        assertEquals(expected, actual)
    }

    @Test
    fun `getArgb throws IllegalArgumentException when input invalid alpha`() {
        val invalidInput = 1000
        val expected = IllegalArgumentException("Invalid alpha: $invalidInput")
        val actual = kotlin.runCatching {
            usecase.getArgb(invalidInput, 0, 0, 0)
        }.exceptionOrNull()

        assertTrue(actual is IllegalArgumentException)
        assertEquals(expected.message, actual?.message)
    }

    @Test
    fun `getArgb throws IllegalArgumentException when input negative alpha`() {
        val invalidInput = -1
        val expected = IllegalArgumentException("Invalid alpha: $invalidInput")
        val actual = kotlin.runCatching {
            usecase.getArgb(invalidInput, 0, 0, 0)
        }.exceptionOrNull()

        assertTrue(actual is IllegalArgumentException)
        assertEquals(expected.message, actual?.message)
    }

    @Test
    fun `getArgb throws IllegalArgumentException when input invalid red`() {
        val invalidInput = 1000
        val expected = IllegalArgumentException("Invalid red: $invalidInput")
        val actual = kotlin.runCatching {
            usecase.getArgb(0, invalidInput, 0, 0)
        }.exceptionOrNull()

        assertTrue(actual is IllegalArgumentException)
        assertEquals(expected.message, actual?.message)
    }

    @Test
    fun `getArgb throws IllegalArgumentException when input negative red`() {
        val invalidInput = -1
        val expected = IllegalArgumentException("Invalid red: $invalidInput")
        val actual = kotlin.runCatching {
            usecase.getArgb(0, invalidInput, 0, 0)
        }.exceptionOrNull()

        assertTrue(actual is IllegalArgumentException)
        assertEquals(expected.message, actual?.message)
    }

    @Test
    fun `getArgb throws IllegalArgumentException when input invalid green`() {
        val invalidInput = 1000
        val expected = IllegalArgumentException("Invalid green: $invalidInput")
        val actual = kotlin.runCatching {
            usecase.getArgb(0, 0, invalidInput, 0)
        }.exceptionOrNull()

        assertTrue(actual is IllegalArgumentException)
        assertEquals(expected.message, actual?.message)
    }

    @Test
    fun `getArgb throws IllegalArgumentException when input negative green`() {
        val invalidInput = -1
        val expected = IllegalArgumentException("Invalid green: $invalidInput")
        val actual = kotlin.runCatching {
            usecase.getArgb(0, 0, invalidInput, 0)
        }.exceptionOrNull()

        assertTrue(actual is IllegalArgumentException)
        assertEquals(expected.message, actual?.message)
    }

    @Test
    fun `getArgb throws IllegalArgumentException when input invalid blue`() {
        val invalidInput = 1000
        val expected = IllegalArgumentException("Invalid blue: $invalidInput")
        val actual = kotlin.runCatching {
            usecase.getArgb(0, 0, 0, invalidInput)
        }.exceptionOrNull()

        assertTrue(actual is IllegalArgumentException)
        assertEquals(expected.message, actual?.message)
    }

    @Test
    fun `getArgb throws IllegalArgumentException when input negative blue`() {
        val invalidInput = -1
        val expected = IllegalArgumentException("Invalid blue: $invalidInput")
        val actual = kotlin.runCatching {
            usecase.getArgb(0, 0, 0, invalidInput)
        }.exceptionOrNull()

        assertTrue(actual is IllegalArgumentException)
        assertEquals(expected.message, actual?.message)
    }

    @Test
    fun `hslToRgb throws IllegalArgumentException when input is invalid`() {
        val invalidInput = HSL(2f, 3f, 4f)
        val expected = IllegalArgumentException("HSL: $invalidInput is not valid")

        val actual = kotlin.runCatching { usecase.hslToRgb(invalidInput) }.exceptionOrNull()

        assertTrue(actual is IllegalArgumentException)
        assertTrue(expected.message == actual?.message)
    }

    @Test
    fun `hslToRgb random hsl`() {
        val h = 60 / 360f
        val s = 35 / 100f
        val l = 45 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(155, 155, 75)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }

    @Test
    fun `hslToRgb random hsl prime`() {
        val h = 139 / 360f
        val s = 83 / 100f
        val l = 53 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(36, 235, 99)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }

    @Test
    fun `hslToRgb Black`() {
        val h = 0 / 360f
        val s = 0 / 100f
        val l = 0 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(0, 0, 0)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }

    @Test
    fun `hslToRgb White`() {
        val h = 0 / 360f
        val s = 0 / 100f
        val l = 100 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(255, 255, 255)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }
    @Test
    fun `hslToRgb Red`() {
        val h = 0 / 360f
        val s = 100 / 100f
        val l = 50 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(255, 0, 0)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }
    @Test
    fun `hslToRgb Lime`() {
        val h = 120 / 360f
        val s = 100 / 100f
        val l = 50 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(0, 255, 0)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }
    @Test
    fun `hslToRgb Blue`() {
        val h = 240 / 360f
        val s = 100 / 100f
        val l = 50 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(0, 0, 255)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }
    @Test
    fun `hslToRgb Yellow`() {
        val h = 60 / 360f
        val s = 100 / 100f
        val l = 50 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(255, 255, 0)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }
    @Test
    fun `hslToRgb Cyan`() {
        val h = 180 / 360f
        val s = 100 / 100f
        val l = 50 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(0, 255, 255)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }
    @Test
    fun `hslToRgb Magenta`() {
        val h = 300 / 360f
        val s = 100 / 100f
        val l = 50 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(255, 0, 255)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }

    @Test
    fun `hslToRgb Gray`() {
        val h = 0 / 360f
        val s = 0 / 100f
        val l = 50 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(128, 128, 128)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }
    @Test
    fun `hslToRgb Maroon`() {
        val h = 0 / 360f
        val s = 100 / 100f
        val l = 25 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(128, 0, 0)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }
    @Test
    fun `hslToRgb Olive`() {
        val h = 60 / 360f
        val s = 100 / 100f
        val l = 25 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(128, 128, 0)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }
    @Test
    fun `hslToRgb Green`() {
        val h = 120 / 360f
        val s = 100 / 100f
        val l = 25 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(0, 128, 0)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }

    @Test
    fun `hslToRgb Navy`() {
        val h = 240 / 360f
        val s = 100 / 100f
        val l = 25 / 100f
        val hsl = HSL(h, s, l)
        val expected = RGB(0, 0, 128)

        val actual = usecase.hslToRgb(hsl)

        assertEquals(expected, actual)
    }
}