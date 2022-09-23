package com.joel.pagination.model.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.joel.pagination.model.library.UserSource
import kotlinx.coroutines.flow.Flow



class ListViewModel : ViewModel() {
    val user: Flow<PagingData<User>> = Pager(PagingConfig(pageSize = 6)) {
        UserSource()
    }.flow.cachedIn(viewModelScope)

}