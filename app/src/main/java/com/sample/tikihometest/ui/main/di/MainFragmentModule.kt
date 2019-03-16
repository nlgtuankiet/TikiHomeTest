package com.sample.tikihometest.ui.main.di

import com.sample.tikihometest.di.FragmentScope
import com.sample.tikihometest.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            MainFragmentBindingModule::class
        ]
    )
    abstract fun contrubuteMainFragment(): MainFragment
}