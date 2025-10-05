package com.dxn.wallpaperx.data.remote.pixabay

import com.dxn.wallpaperx.data.remote.pixabay.models.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class PixabayKtorApi(
    val client: HttpClient
) : PixabayApi {
    override suspend fun searchImage(
        apiKey: String,
        query: String?,
        page: Int,
        lang: String,
        id: String?,
        imageType: String?,
        orientation: String?,
        category: String?
    ): SearchResponse {
        val response =
            client.get(
                "https://pixabay.com/api/?key=23976357-da1ce0f12602543da453c0902&q=wallpaper&page=1&lang=en&orientation=vertical",
            )
        println(response.bodyAsText())
        return response.body()
    }

    companion object {
        const val BASE_URL = "https://pixabay.com/api/"
    }
}
