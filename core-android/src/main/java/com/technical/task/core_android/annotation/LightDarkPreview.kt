package com.technical.task.core_android.annotation

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
annotation class LightDarkPreview