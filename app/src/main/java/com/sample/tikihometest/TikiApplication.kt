package com.sample.tikihometest

import com.sample.tikihometest.data.di.DataComponent
import com.sample.tikihometest.data.di.NetworkDataComponent
import com.sample.tikihometest.di.ApplicationComponent
import com.sample.tikihometest.domain.di.DomainComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TikiApplication : DaggerApplication() {
    val dataComponent: DataComponent by lazy {
        NetworkDataComponent.builder().build()
    }
    val domainComponent: DomainComponent by lazy {
        DomainComponent.builder()
            .keywordRepository(dataComponent.provideKeywordRepository())
            .build()
    }
    val applicationComponent: ApplicationComponent by lazy {
        ApplicationComponent.builder()
            .domainComponent(domainComponent)
            .dataComponent(dataComponent)
            .create(this) as? ApplicationComponent ?: TODO()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return applicationComponent
    }
}