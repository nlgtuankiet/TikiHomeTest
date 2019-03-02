package com.sample.tikihometest.ui.main.di

import com.sample.tikihometest.di.ActivityScope
import com.sample.tikihometest.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainActivityBindingModule::class
        ]
    )
    abstract fun contrubuteMainActivity(): MainActivity
}