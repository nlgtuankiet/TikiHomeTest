package com.sample.tikihometest.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryBindingModule {
    @Binds
    fun bindFactory(factory: TikiViewModelFactory): ViewModelProvider.Factory
}