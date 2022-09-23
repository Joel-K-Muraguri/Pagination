package com.joel.cars

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.joel.cars.model.network.paged.MovieSource
import com.joel.cars.model.network.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import com.joel.cars.model.data.Result

class MainViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    val movies : Flow<PagingData<Result>> = Pager(PagingConfig(pageSize = 20)){
        MovieSource(repository)
    }.flow

}