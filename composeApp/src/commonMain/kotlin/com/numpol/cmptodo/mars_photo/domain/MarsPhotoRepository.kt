package com.numpol.cmptodo.mars_photo.domain

import com.numpol.cmptodo.core.domain.DataError
import com.numpol.cmptodo.core.domain.Result

interface MarsPhotoRepository {
    suspend fun getMarPhoto(): Result<List<MarsPhoto>, DataError.Remote>
}