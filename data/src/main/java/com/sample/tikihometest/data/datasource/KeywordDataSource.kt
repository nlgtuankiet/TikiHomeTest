package com.sample.tikihometest.data.datasource

interface KeywordDataSource {
    suspend fun getKeywords(): List<String>
}