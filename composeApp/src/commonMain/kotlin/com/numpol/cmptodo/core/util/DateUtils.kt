package com.numpol.cmptodo.core.util

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateUtils {
    fun getCurrentDate(): String {
        val currentMoment = Clock.System.now()
        val currentDate = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault()).date
        return currentDate.toString()
    }
}