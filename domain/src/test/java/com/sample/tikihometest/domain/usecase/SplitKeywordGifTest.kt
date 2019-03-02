package com.sample.tikihometest.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Test

class SplitKeywordGifTest {
    val usecase: SplitKeyword = SplitKeyword()

    @Test
    fun `anh chính là thanh xuân của em`() {
        val input = "anh chính là thanh xuân của em"
        val expected = """
            anh chính là
            thanh xuân của em
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `sạc dự phòng`() {
        val input = "sạc dự phòng"
        val expected = """
            sạc dự
            phòng
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `laneige`() {
        val input = "laneige"
        val expected = """
            laneige
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }
    @Test
    fun `đắc nhân tâm`() {
        val input = "đắc nhân tâm"
        val expected = """
            đắc nhân
            tâm
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }
    @Test
    fun `túi đeo chéo`() {
        val input = "túi đeo chéo"
        val expected = """
            túi đeo
            chéo
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `harry potter`() {
        val input = "harry potter"
        val expected = """
            harry
            potter
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `bình giữ nhiệt`() {
        val input = "bình giữ nhiệt"
        val expected = """
            bình giữ
            nhiệt
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `tai nghe bluetooth`() {
        val input = "tai nghe bluetooth"
        val expected = """
            tai nghe
            bluetooth
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `son`() {
        val input = "son"
        val expected = """
            son
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `banh trung thu`() {
        val input = "banh trung thu"
        val expected = """
            banh
            trung thu
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `bts`() {
        val input = "bts"
        val expected = """
            bts
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `bitis`() {
        val input = "bitis"
        val expected = """
            bitis
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `bánh trung thu kinh đô`() {
        val input = "bánh trung thu kinh đô"
        val expected = """
            bánh trung
            thu kinh đô
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `anker`() {
        val input = "anker"
        val expected = """
            anker
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `tai nghe`() {
        val input = "tai nghe"
        val expected = """
            tai
            nghe
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `bitis hunter x`() {
        val input = "bitis hunter x"
        val expected = """
            bitis
            hunter x
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `balo`() {
        val input = "balo"
        val expected = """
            balo
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `innisfree`() {
        val input = "innisfree"
        val expected = """
            innisfree
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }

    @Test
    fun `xiaomi`() {
        val input = "xiaomi"
        val expected = """
            xiaomi
        """.trimIndent()
        val actual = usecase(input)
        assertEquals(expected, actual)
    }
}