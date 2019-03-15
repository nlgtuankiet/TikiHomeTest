package com.sample.tikihometest.ui.main.di;


import com.sample.tikihometest.ui.main.MainViewModel.Factory;
import com.sample.tikihometest.ui.main.MainViewModel_AssistedFactory;

import dagger.Binds;
import dagger.Module;

@Module
public interface MainFragmentBindingModule {
    @Binds
    public Factory bindFactory(MainViewModel_AssistedFactory factory);
}
