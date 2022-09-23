package com.joel.cars.model.network.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.joel.cars.model.network.repository.MovieRepository
import com.joel.cars.model.data.Result

class MovieSource(
    private val repository: MovieRepository
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return  state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,Result> {
       return try {
           val nextPage = params.key ?: 1
           val movieList = repository.getMovies(nextPage)
           LoadResult.Page(
               data = movieList.results,
               nextKey = movieList.page.plus(1),
               prevKey = if (nextPage==1) null else nextPage-1
           )
       }
       catch (e:Exception){
           LoadResult.Error(e)
       }
    }
}