package com.joel.pagination.model

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("users")
    suspend fun getUserList(@Query("page") page: Int) : UserResponse

}