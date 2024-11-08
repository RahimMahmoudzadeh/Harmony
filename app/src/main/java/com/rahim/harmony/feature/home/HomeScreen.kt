package com.rahim.harmony.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                contentDescription = "empty list home"
            )
            Text(stringResource(R.string.music), modifier = Modifier.padding(start = 6.dp))
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
        LazyColumn {
            item {
                LazyRow {
                    items(state.categoryName) {
                        MusicCategory(name = it, onClick = {})
                    }
                }
            }
            item {
                LazyRow {
                    items(state.offlineMusic) {

                    }
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_4_XL)
@Composable
fun HomeScreenPreview() {
    HarmonyTheme {
        HomeScreen(state = HomeContract.HomeState())
    }
}