package com.isu.serveruimovieapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.isu.serveruimovieapp.data.model.Movie
import com.isu.serveruimovieapp.data.model.UI
import com.isu.serveruimovieapp.data.model.getMovies
import com.isu.serveruimovieapp.navagation.MovieScreens
import com.isu.serveruimovieapp.widgets.MovieRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val uiScreenState = homeViewModel.uiScreenState.collectAsState().value

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
            Text(text = "Movies")
        }
    }) {
        Surface(modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxSize()
            .padding(2.dp)
            .background(Color.DarkGray)) {
            if (uiScreenState.loading == true) {
                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(10.dp), color = Color.Blue
                    )
                }
            }
            uiScreenState.uiScreenResponse?.ui.let { UI ->
                if (UI != null) {
                    MainContent(navController = navController, Ui = UI)
                }
            }
            uiScreenState.error?.let {
                BBPSTextField(text = it,fontSize = 14.dp)
            }
        }

    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies(),
    Ui: UI,
) {

    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(start = Ui.mainScreen.paddingStart.dp,
            end = Ui.mainScreen.paddingEnd.dp,
            top = Ui.mainScreen.paddingTop.dp,
            bottom = Ui.mainScreen.paddingBottom.dp), elevation = Ui.mainScreen.elevation.dp, shape = RoundedCornerShape(
        corner = CornerSize(size = Ui.mainScreen.cornerSize.dp))) {
        Column(modifier = Modifier.padding(0.dp)) {
            LazyColumn {
                items(items = movieList) {
                    MovieRow(movie = it,Ui = Ui) { id ->
                        navController.navigate(route = MovieScreens.DetailsScreen.name + "/$id")
                    }
                }
            }
        }

    }
}
@Composable
fun BBPSTextField(
    text: String,
    color: Color = Color.Blue,
    fontStyle: Int =com.isu.serveruimovieapp.R.font.roboto_medium,
    fontSize: Dp,
) {
    Text(text = text,
        style = TextStyle(color = color,
            fontFamily = FontFamily(Font(fontStyle)),
            fontSize = dpToSp(fontSize)))
}
@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }
