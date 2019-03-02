package com.sample.tikihometest.domain.repository

interface KeywordRepository {
    suspend fun getKeyWorks(): List<String>
}