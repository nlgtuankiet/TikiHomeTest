package com.sample.tikihometest.data.equal

import org.junit.Assert
import org.junit.Test

class EqualImplementationTest {
    private val myComponent: MyComponent =
        DaggerMyComponent.create()

    @Test
    fun `Foo is not singleton`() {
        val fooA = myComponent.provideFoo()
        val fooB = myComponent.provideFoo()

        Assert.assertTrue(fooA != fooB)
    }

    @Test
    fun `Bar is singleton`() {
        val barA = myComponent.provideBar()
        val barB = myComponent.provideBar()

        Assert.assertTrue(barA == barB)
    }
}