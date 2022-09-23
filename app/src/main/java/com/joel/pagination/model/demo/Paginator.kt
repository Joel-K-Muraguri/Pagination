package com.joel.pagination.model.demo

interface Paginator <Key, Items>{

    suspend fun loadItems()
    fun reset()
}