package com.sample.tikihometest.ui.main.di

import androidx.lifecycle.ViewModel
import com.sample.tikihometest.di.ViewModelKey
import com.sample.tikihometest.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityBindingModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}