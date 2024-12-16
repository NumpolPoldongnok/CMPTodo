package com.numpol.cmptodo.mars_photo.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhotoDto(
    @SerialName("key") val id: String,
    @SerialName("img_src") val imageSrc: String,
)
