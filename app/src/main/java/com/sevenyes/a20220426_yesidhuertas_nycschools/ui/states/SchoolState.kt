package com.sevenyes.a20220426_yesidhuertas_nycschools.ui.states

/**
 * [SchoolState] used to represent state between layers
 */
sealed class SchoolState {
    object LOADING: SchoolState()
    data class SUCCESS<out T>(val response: T) : SchoolState()
    data class ERROR(val throwable: Throwable) : SchoolState()
}