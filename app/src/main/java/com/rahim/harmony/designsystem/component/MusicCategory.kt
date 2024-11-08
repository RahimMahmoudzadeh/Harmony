package com.rahim.harmony.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MusicCategory(modifier: Modifier = Modifier, name: String, onClick: () -> Unit) {
    Box(modifier = modifier
        .clickable { onClick() }
        .background(MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.Center) {
        Text(
            text = name,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp)
        )
    }
}