package com.technical.task.ui.errorwaitscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ErrorScreen(
    errorMessage: String,
    modifier: Modifier,
    paddingStart: Dp = 56.dp,
    paddingEnd: Dp = 56.dp,
    paddingTop: Dp = 32.dp,
    paddingBottom: Dp = 32.dp
) {
    Dialog(
        onDismissRequest = {
        }
    ) {
        Column(
            modifier = modifier
                .padding(start = paddingStart, end = paddingEnd, top = paddingTop),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = paddingBottom),
                text = errorMessage,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}