package com.joel.cars.model.network.repository

import com.joel.cars.model.network.CarApiService

class MovieRepository(
    private val apiService: CarApiService
) {

    suspend fun getMovies(pageNumber : Int) = apiService.getCarList(pageNumber)
}