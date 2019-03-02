package com.sample.tikihometest.data.api

import retrofit2.http.GET

interface KeywordGithubApi {
    @GET(value = "/tikivn/android-home-test/v2/keywords.json")
    suspend fun getKeywords(): List<String>
    companion object {
        const val baseUrl: String = "https://raw.githubusercontent.com/"
    }
}