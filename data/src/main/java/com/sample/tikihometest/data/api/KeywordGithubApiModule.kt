package com.sample.tikihometest.data.api

import com.sample.tikihometest.data.di.DataScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object KeywordGithubApiModule {
    @Provides
    @JvmStatic
    @DataScope
    fun provideKeywordGithubApi(): KeywordGithubApi {
        return Retrofit.Builder()
            .baseUrl(KeywordGithubApi.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(KeywordGithubApi::class.java)
    }
}