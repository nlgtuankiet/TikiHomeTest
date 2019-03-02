package com.sample.tikihometest.data.di

import org.junit.Assert
import org.junit.Test
import kotlin.reflect.KFunction0

/**
 * make sure what supposed to be singleton is a singleton
 * to determine 2 objects are the same instance, we use hashCode
 */
class DataComponentScopeTest {
    private val dataComponent: TestDataComponent = DaggerTestDataComponent.create()

    @Test
    fun `KeywordDataSource is singleton`() {
        testSingleton(dataComponent::keywordDataSource)
    }

    @Test
    fun `KeywordRepository is singleton`() {
        testSingleton(dataComponent::keywordRepository)
    }

    private inline fun <reified T> testSingleton(kFunction0: KFunction0<T>) {
        val objectA: T = kFunction0()
        val objectB: T = kFunction0()
        if (objectA == null) TODO()
        if (objectB == null) TODO()
        Assert.assertTrue(objectA == objectB)
    }
}