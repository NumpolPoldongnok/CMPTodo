package com.numpol.cmptodo.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object TodoGraph: Route

    @Serializable
    data object TodoList: Route

    @Serializable
    data class TodoDetail(val id: String): Route
}