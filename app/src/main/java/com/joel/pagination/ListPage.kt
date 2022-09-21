package com.joel.pagination

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.joel.pagination.model.ListViewModel
import com.joel.pagination.model.User
import kotlinx.coroutines.flow.Flow

@Composable
fun UserList(modifier: Modifier = Modifier, viewModel:ListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), ) {
    UserInfoList(modifier, userList = viewModel.user, )
}

@Composable
fun UserInfoList(modifier: Modifier, userList: Flow<PagingData<User>>) {
    val context = LocalContext.current
    val userListItems: LazyPagingItems<User> =            userList.collectAsLazyPagingItems()

    LazyColumn {
        items(userListItems) { item ->
            item?.let {
                EmployeeItem(empData = it, onClick = {
                    Toast.makeText(context, item.id.toString(),   Toast.LENGTH_SHORT).show()
                })
            }
        }
        userListItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    //You can add modifier to manage load state when first time response page is loading
                }
                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                }
                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                }
            }
        }
    }
}