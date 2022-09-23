package com.joel.pagination.model.demo

import kotlinx.coroutines.delay

class Repository {

    private val remoteDataStore = (1..200).map {
        ListItem(
           item =  "Item $it",
            description = "Description $it"
        )
    }

    suspend fun getItems(page : Int, pageSize : Int) : Result<List<ListItem>>{
        delay(2000L)
        val startingIndex = page * pageSize
        return if (startingIndex * pageSize <= remoteDataStore.size){
            Result.success(
                remoteDataStore.slice(startingIndex until startingIndex + pageSize)
            )
        }
        else{
            return Result.success(emptyList())
        }
    }
}