package com.isu.serveruimovieapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.isu.serveruimovieapp.data.model.Movie
import com.isu.serveruimovieapp.data.model.UI
import com.isu.serveruimovieapp.data.model.getMovies

@Composable
fun MovieRow(movie: Movie = getMovies()[0], Ui: UI = UI(), onItemClick: (String) -> Unit = {}) {
    Card(modifier = Modifier
        .padding(
            start = Ui.rowItem.paddingStart.dp,
            end = Ui.rowItem.paddingEnd.dp,
            top = Ui.rowItem.paddingTop.dp,
            bottom = Ui.rowItem.paddingBottom.dp,
        )
        .fillMaxWidth()
        .clickable {
            onItemClick(movie.id)
        }, shape = RoundedCornerShape(
        corner = CornerSize(size = Ui.rowItem.cornerSize.dp)),
        elevation = Ui.rowItem.elevation.dp) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(start = Ui.image.paddingStart.dp,
                    end = Ui.image.paddingEnd.dp,
                    top = Ui.image.paddingTop.dp,
                    bottom = Ui.image.paddingBottom.dp)
                .size(height = Ui.image.height.dp, width = Ui.image.width.dp),
                shape = RoundedCornerShape(
                    corner = CornerSize(size = Ui.image.cornerSize.dp)),
                elevation = Ui.image.elevation.dp) {
                Image(painter = rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current)
                    .data(data = movie.images[0]).apply(block = fun ImageRequest.Builder.() {
                        crossfade(enable = true)
                    }).build()),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Movie Poster")
            }
            Column(modifier = Modifier.padding(
                start = Ui.detailsColumn.paddingStart.dp,
                end = Ui.detailsColumn.paddingEnd.dp,
                top = Ui.detailsColumn.paddingTop.dp,
                bottom = Ui.detailsColumn.paddingBottom.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                Text(text = "Released : ${movie.year}", style = MaterialTheme.typography.caption)
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView(movieList: List<Movie>) {
    LazyRow {
        items(items = movieList[0].images) { image ->
            Card(modifier = Modifier
                .padding(12.dp)
                .size(240.dp), elevation = 5.dp) {
                Image(painter = rememberAsyncImagePainter(model = image),
                    contentDescription = "Movie Image")
            }
        }
    }
}