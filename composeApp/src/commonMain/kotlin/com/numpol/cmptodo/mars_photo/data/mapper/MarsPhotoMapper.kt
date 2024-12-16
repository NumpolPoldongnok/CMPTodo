package com.numpol.cmptodo.mars_photo.data.mapper

import com.numpol.cmptodo.mars_photo.data.dto.MarsPhotoDto
import com.numpol.cmptodo.mars_photo.domain.MarsPhoto

fun MarsPhotoDto.toMarsPhoto(): MarsPhoto {
    return MarsPhoto(
        id = id,
        imgSrc = imageSrc
    )
}