package com.numpol.cmptodo.mars_photo.data.repository

import com.numpol.cmptodo.core.domain.DataError
import com.numpol.cmptodo.core.domain.Result
import com.numpol.cmptodo.core.domain.map
import com.numpol.cmptodo.mars_photo.data.mapper.toMarsPhoto
import com.numpol.cmptodo.mars_photo.data.network.MarsApiService
import com.numpol.cmptodo.mars_photo.domain.MarsPhoto
import com.numpol.cmptodo.mars_photo.domain.MarsPhotoRepository

class DefaultMarsPhotoRepository(
    private val remoteMarsPhotoDataSource: MarsApiService,
): MarsPhotoRepository {
    override suspend fun getMarPhoto(): Result<List<MarsPhoto>, DataError.Remote> {
        return remoteMarsPhotoDataSource
            .getPhotos()
            .map { dto ->
                dto.map { it.toMarsPhoto() }
            }
    }
}