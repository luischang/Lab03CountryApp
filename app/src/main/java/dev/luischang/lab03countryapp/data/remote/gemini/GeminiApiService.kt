package dev.luischang.lab03countryapp.data.remote.gemini

import dev.luischang.lab03countryapp.data.model.GeminiRequest
import dev.luischang.lab03countryapp.data.model.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


interface GeminiApiService {
    @POST("v1beta/models/gemini-2.0-flash:generateContent")
    suspend fun generateContent(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}