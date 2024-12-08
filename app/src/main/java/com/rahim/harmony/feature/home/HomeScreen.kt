@file:OptIn(ExperimentalMaterial3Api::class)

package com.rahim.harmony.feature.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.CarouselDefaults
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rahim.harmony.R
import com.rahim.harmony.core.base.use
import com.rahim.harmony.designsystem.component.MusicCategory
import com.rahim.harmony.designsystem.theme.HarmonyTheme

@Composable
internal fun HomeScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val (state, event) = use(viewModel)
    HomeScreen(modifier, state)
}
//---------------------------------------------------------------------
@Composable
private fun HomeScreen(modifier: Modifier = Modifier, state: HomeContract.HomeState) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 19.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp)
                .padding(top = 19.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "empty list home",
            )
            Text(
                stringResource(R.string.music),
                color = Color.Black,
                style = TextStyle(fontSize = 18.sp , fontWeight = FontWeight.Bold , fontFamily = FontFamily.Serif),
                modifier = Modifier.padding(start = 6.dp))
            Spacer(modifier = Modifier.weight(3f))
            Image(
                modifier = Modifier.padding(end = 6.dp),
                painter = painterResource(id = R.drawable.link_tv),
                contentDescription = "empty list home",
                colorFilter = ColorFilter.tint(Color.Black)
            )
            Image(
                modifier = Modifier.padding(end = 6.dp),
                painter = painterResource(id = R.drawable.search),
                contentDescription = "empty list home",
                colorFilter = ColorFilter.tint(Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "empty list home"
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(720.dp)
        ) {
            item {
                LazyRow(
                    modifier = Modifier.padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.categoryName) {
                        MusicCategory(name = it, onClick = {})
                    }
                }
            }
//            item {
//                LazyHorizontalGrid(
//                    rows = GridCells.Fixed(2),
//                    modifier = Modifier
//                        .padding(top = 12.dp)
//                        .height(64.dp)
//                ) {
//                    items(state.offlineMusic) {
//                        MusicItems(name = it.name, imageUrl = it.image.toString(), onClick = {})
//                    }
//                }
//            }
            item { HeadlineText() }
            item { AnimatedCarouselTrack() }
            item { AnimatedCarouselTrack() }
            item { HeadlineMixedText() }
            item { AnimatedCarouselMixedTrack() }
            item { HeadlineText() }
            item { AnimatedCarouselTrack() }
            item { AnimatedCarouselTrack() }
        }
    }
}

//---------------------------------------------------------------------

@Composable
fun AnimatedCarouselTrack(
    modifier: Modifier = Modifier
) {
    val items = listOf(
        R.drawable.image_1,
        R.drawable.image_2,
        R.drawable.image_3,
        R.drawable.image_4,
        R.drawable.image_5,
        R.drawable.image_6
    )
    val animatedScale = remember { Animatable(1f) }

    Column {
        LaunchedEffect(Unit) {
            while (true) {
                animatedScale.animateTo(
                    targetValue = 1.2f,
                    animationSpec = tween(durationMillis = 4000, easing = FastOutSlowInEasing)
                )
                animatedScale.animateTo(
                    targetValue = 1.1f,
                    animationSpec = tween(durationMillis = 4000, easing = FastOutSlowInEasing)
                )
            }
        }

        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { items.size },
            modifier = modifier,
            preferredItemWidth = 150.dp,
            itemSpacing = 8.dp,
        ) { index ->

            Column {
                Image(
                    painter = painterResource(id = items[index]),
                    modifier = Modifier
                        .height(150.dp)
                        .maskClip(MaterialTheme.shapes.extraLarge)
                        .graphicsLayer(
                            scaleX = animatedScale.value,
                            scaleY = animatedScale.value
                        )
                        .clickable { },
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

               TrackText()
            }
        }
    }
}

@Composable
fun HeadlineText(
    modifier: Modifier = Modifier
){
    Text(
        modifier = modifier.padding( top = 8.dp , bottom = 8.dp),
        text = "Listen again",
        style = TextStyle(fontSize = 24.sp , fontWeight = FontWeight.Bold),
        color = Color.Black
    )

}

@Composable
fun TrackText(
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier.padding(12.dp),
    ) {
        Text(
            text = "Test Music",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            ),
            color = Color.Black
        )

        Text(
            text = "3:25",
            style = TextStyle(color = Color.Gray, fontSize = 12.sp),
        )
    }

}

//---------------------------------------------------------------------

@Composable
fun AnimatedCarouselMixedTrack(
    modifier: Modifier = Modifier
) {
    val items = listOf(
        R.drawable.image_1,
        R.drawable.image_2,
        R.drawable.image_3,
        R.drawable.image_4,
        R.drawable.image_5,
        R.drawable.image_6
    )
    Column {
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { items.size },
            itemWidth = 250.dp,
            modifier = modifier,
            itemSpacing = 8.dp,
            flingBehavior = CarouselDefaults.noSnapFlingBehavior(),
            contentPadding = PaddingValues(8.dp)

        ) {index ->
            Column {
                Image(
                    painter = painterResource(id = items[index]),
                    modifier = Modifier
                        .height(150.dp)
                        .maskClip(MaterialTheme.shapes.extraLarge)
                        .clickable { },
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

                TrackText()
            }
        }
    }
}

@Composable
fun HeadlineMixedText(modifier: Modifier = Modifier){
    Text(
        modifier = modifier.padding( top = 8.dp , bottom = 8.dp),
        text = "Mixed for you",
        style = TextStyle(fontSize = 24.sp , fontWeight = FontWeight.Bold),
        color = Color.Black
    )
}
//---------------------------------------------------------------------
@Preview(device = Devices.PIXEL_4_XL)
@Composable
fun HomeScreenPreview() {
         HarmonyTheme { HomeScreen(state = HomeContract.HomeState())
    }
}