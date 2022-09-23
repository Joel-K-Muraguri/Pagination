package com.joel.cars.model.network

import com.joel.cars.model.data.Movie
import com.joel.cars.utils.Constants.CARS_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface CarApiService {

    @GET(CARS_ENDPOINT)
    suspend fun getCarList(
        @Query ("page") pageNumber : Int
    ) : Movie
}