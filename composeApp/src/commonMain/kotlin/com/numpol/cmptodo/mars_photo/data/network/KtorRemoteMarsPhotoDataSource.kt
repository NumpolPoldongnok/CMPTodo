package com.numpol.cmptodo.mars_photo.data.network

import com.numpol.cmptodo.core.data.safeCall
import com.numpol.cmptodo.core.domain.DataError
import com.numpol.cmptodo.core.domain.Result
import com.numpol.cmptodo.mars_photo.data.dto.MarsPhotoDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

class KtorRemoteMarsPhotoDataSource(
    private val httpClient: HttpClient
): MarsApiService {

    override suspend fun getPhotos(): Result<List<MarsPhotoDto>, DataError.Remote> {
        return safeCall<List<MarsPhotoDto>> {
            httpClient.get(
                urlString = "${BASE_URL}/photos"
            )
        }
    }
}