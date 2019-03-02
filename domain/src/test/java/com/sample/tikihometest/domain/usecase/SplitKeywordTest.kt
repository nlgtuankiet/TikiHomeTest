package com.sample.tikihometest.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SplitKeywordTest {
    val usecase: SplitKeyword = SplitKeyword()

    // region cleanWhiteSpace
    @Test
    fun `cleanWhiteSpace() do nothing case`() {
        val input = "bánh trung thu"
        val expected = "bánh trung thu"
        val actual = usecase.cleanWhiteSpace(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `cleanWhiteSpace() with whitespace between`() {
        val input = "bánh   trung  thu"
        val expected = "bánh trung thu"
        val actual = usecase.cleanWhiteSpace(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `cleanWhiteSpace() with whitespace begin`() {
        val input = "  bánh trung thu"
        val expected = "bánh trung thu"
        val actual = usecase.cleanWhiteSpace(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `cleanWhiteSpace() with whitespace end`() {
        val input = "bánh trung thu   "
        val expected = "bánh trung thu"
        val actual = usecase.cleanWhiteSpace(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `cleanWhiteSpace() with edges whitespace`() {
        val input = "  bánh trung thu    "
        val expected = "bánh trung thu"
        val actual = usecase.cleanWhiteSpace(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `cleanWhiteSpace() with whitespace everywhere`() {
        val input = "  bánh   trung  thu    "
        val expected = "bánh trung thu"
        val actual = usecase.cleanWhiteSpace(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `cleanWhiteSpace() with empty string`() {
        val input = ""
        val expected = ""
        val actual = usecase.cleanWhiteSpace(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `cleanWhiteSpace() with 3 space string`() {
        val input = "   "
        val expected = ""
        val actual = usecase.cleanWhiteSpace(input)
        assertEquals(expected, actual)
    }
    // endregion cleanWhiteSpace

    @Test
    fun `getWords best case`() {
        val input = "bánh trung thu"
        val expected = listOf("bánh", "trung", "thu")
        val actual = usecase.getWords(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `last word take half sentence length`() {
        val input = "123 1234 123456789"
        val expected = """
            123 1234
            123456789
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `last word take half sentence length part 2`() {
        val input = "123 12345 123456789"
        val expected = """
            123 12345
            123456789
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `throw exception when input empty keyword`() {
        val input = ""
        val actual = kotlin.runCatching { usecase(input) }.exceptionOrNull()
        assertTrue(actual is IllegalArgumentException)
        assertTrue(actual?.message == """Keyword: "$input" is empty""")
    }

    @Test
    fun `throw exception when input empty keyword part 2`() {
        val input = "   "
        val actual = kotlin.runCatching { usecase(input) }.exceptionOrNull()
        assertTrue(actual is IllegalArgumentException)
        assertTrue(actual?.message == """Keyword: "$input" is empty""")
    }
}