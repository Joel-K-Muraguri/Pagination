package com.joel.pagination

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joel.pagination.model.MainViewModel

@Composable
fun Home(){

    val viewModel = viewModel<MainViewModel>()
    val state = viewModel.state

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(state.items.size){ i ->
            val item = state.items[i]
            if (i >= state.items.size -1 && ! state.endReached && !state.isLoading){
                viewModel. loadNextItems()

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = item.item,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.description,
                    fontSize = 16.sp
                )
            }
        }
        item {
            if (state.isLoading){
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}