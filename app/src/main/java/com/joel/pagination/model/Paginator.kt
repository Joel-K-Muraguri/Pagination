package com.joel.pagination.model

interface Paginator <Key, Items>{

    suspend fun loadItems()
    fun reset()
}