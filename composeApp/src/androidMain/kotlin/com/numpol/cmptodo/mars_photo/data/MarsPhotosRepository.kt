package com.numpol.cmptodo.mars_photo.data

import com.numpol.cmptodo.mars_photo.model.MarsPhoto
import com.numpol.cmptodo.mars_photo.network.MarsApiService

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
): MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}