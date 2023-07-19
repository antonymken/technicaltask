package com.technical.task.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.technical.task.core_android.activity.BaseActivity
import com.technical.task.domain.races.model.NextToGoRaceDomainModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.handleAction(HomeAction.OnScreenLoading)
    }

    @Composable
    override fun SetAppLayout(navHostController: NavHostController) {
        val uiState by viewModel.state.collectAsState()
        LoadScreen(uiState)
    }

    @Composable
    fun LoadScreen(
        homeState: HomeState
    ) {

        when (homeState) {
            is HomeState.Error -> ErrorScreen(homeState.message)
            is HomeState.Waiting -> WaitingScreen()

            is HomeState.AllCategoriesList -> NextToGoList(homeState.list)
            is HomeState.Default -> {
                //no-op
            }
        }
    }

    @Composable
    fun NextToGoList(
        list: List<NextToGoRaceDomainModel>,
        cornerRadius: Dp = 16.dp
    ) {
        Surface(
            tonalElevation = 4.dp,
            shape = RoundedCornerShape(cornerRadius),
            modifier = Modifier.fillMaxHeight()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(items = list) { _, item ->
                    NextToGoItem(item)
                }
            }
        }
    }

    @Composable
    fun ErrorScreen(
        errorMessage: String,
        cornerRadius: Dp = 16.dp,
        paddingStart: Dp = 56.dp,
        paddingEnd: Dp = 56.dp,
        paddingTop: Dp = 32.dp,
        paddingBottom: Dp = 32.dp
    ) {
        Dialog(
            onDismissRequest = {
            }
        ) {
            Surface(
                tonalElevation = 4.dp,
                shape = RoundedCornerShape(cornerRadius)
            ) {
                Column(
                    modifier = Modifier
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
    }

    @Composable
    fun WaitingScreen(
        cornerRadius: Dp = 16.dp,
        paddingStart: Dp = 56.dp,
        paddingEnd: Dp = 56.dp,
        paddingTop: Dp = 32.dp,
        paddingBottom: Dp = 32.dp,
        progressIndicatorColor: Color = MaterialTheme.colorScheme.onTertiary,
        progressIndicatorSize: Dp = 80.dp
    ) {
        Dialog(
            onDismissRequest = {
            }
        ) {
            Surface(
                tonalElevation = 4.dp,
                shape = RoundedCornerShape(cornerRadius)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = paddingStart, end = paddingEnd, top = paddingTop),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    ProgressIndicatorLoading(
                        progressIndicatorSize = progressIndicatorSize,
                        progressIndicatorColor = progressIndicatorColor
                    )

                    // Gap between progress indicator and text
                    Spacer(modifier = Modifier.height(32.dp))

                    // Please wait text
                    Text(
                        modifier = Modifier
                            .padding(bottom = paddingBottom),
                        text = "Please wait...",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    }

    @Composable
    fun ProgressIndicatorLoading(
        progressIndicatorSize: Dp,
        progressIndicatorColor: Color
    ) {

        val infiniteTransition = rememberInfiniteTransition()

        val angle by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 600
                }
            )
        )

        CircularProgressIndicator(
            progress = 1f,
            modifier = Modifier
                .size(progressIndicatorSize)
                .rotate(angle)
                .border(
                    12.dp,
                    brush = Brush.sweepGradient(
                        listOf(
                            MaterialTheme.colorScheme.onPrimary,
                            progressIndicatorColor.copy(alpha = 0.1f),
                            progressIndicatorColor
                        )
                    ),
                    shape = CircleShape
                ),
            strokeWidth = 1.dp,
            color = MaterialTheme.colorScheme.background
        )
    }

    @Composable
    fun NextToGoItem(nextToGo: NextToGoRaceDomainModel) {

        Card(
            modifier = Modifier
                .padding(4.dp, 4.dp)
                .fillMaxWidth()
        ) {
            androidx.compose.material.Surface {
                Row(
                    modifier = Modifier
                        .padding(4.dp, 4.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        Text(
                            text = nextToGo.meetingName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = nextToGo.raceNumber.toString(),
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .padding(4.dp)
                        )
                        Text(
                            text = nextToGo.advertisedStartTime.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(4.dp)
                        )
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun NextToGoListPreview(
        @PreviewParameter(HomeStatePreviewParameterProvider::class) list: List<NextToGoRaceDomainModel>,
        cornerRadius: Dp = 16.dp
    ) {
        Surface(
            tonalElevation = 4.dp,
            shape = RoundedCornerShape(cornerRadius),
            modifier = Modifier.fillMaxHeight()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(items = list) { _, item ->
                    NextToGoItem(item)
                }
            }
        }
    }

}
