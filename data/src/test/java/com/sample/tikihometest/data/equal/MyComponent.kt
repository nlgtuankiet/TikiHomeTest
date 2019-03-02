package com.sample.tikihometest.data.equal

import dagger.Component

@Component
@MyScope
abstract class MyComponent {
    abstract fun provideFoo(): Foo
    abstract fun provideBar(): Bar
}