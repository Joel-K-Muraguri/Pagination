package com.joel.pagination

import android.graphics.Color
import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.joel.pagination.model.User

@Composable
fun EmployeeItem(empData: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(bottom = 5.dp, top = 5.dp,
                start = 5.dp, end = 5.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(15.dp),
        elevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
        ) {
            Surface(
                modifier = Modifier.size(130.dp),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colors.surface.copy(
                    alpha = 0.2f)
            ) {
                SubcomposeAsyncImage(
                    model = empData.avatar,
                    contentDescription = empData.first_name
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error  ){
                        CircularProgressIndicator()
                    }
                    else{
                        SubcomposeAsyncImageContent()
                    }

                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = empData.first_name,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 22.sp),
                    color = Black
                )
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.medium
                ) {
                    Text(
                        text = empData.email,
                        style = typography.body2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 25.dp)
                    )
                }
            }
        }
    }
}