package com.sample.tikihometest.di

import com.sample.tikihometest.TikiApplication
import com.sample.tikihometest.data.di.DataComponent
import com.sample.tikihometest.domain.di.DomainComponent
import com.sample.tikihometest.ui.main.di.MainActivityModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    MainActivityModule::class
],
    dependencies = [
        DataComponent::class,
        DomainComponent::class
    ])
interface ApplicationComponent : AndroidInjector<TikiApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TikiApplication>() {
        abstract fun dataComponent(dataComponent: DataComponent): Builder
        abstract fun domainComponent(domainComponent: DomainComponent): Builder
    }

    companion object {
        fun builder(): Builder = DaggerApplicationComponent.builder()
    }
}