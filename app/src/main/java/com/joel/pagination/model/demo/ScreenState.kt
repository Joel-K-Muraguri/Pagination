package com.joel.pagination.model.demo

import com.joel.pagination.model.demo.ListItem

data class ScreenState(
    val error : String? = null,
    val isLoading : Boolean = false,
    val page : Int = 0,
    val endReached : Boolean = false,
    val items : List<ListItem> = emptyList()
)
