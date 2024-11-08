package com.rahim.harmony.feature.musicDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rahim.harmony.R

@Composable
internal fun MusicDetailScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: MusicDetailViewModel = hiltViewModel()
) {
    MusicDetailScreen(modifier)
}

@Composable
private fun MusicDetailScreen(modifier: Modifier = Modifier) {

}