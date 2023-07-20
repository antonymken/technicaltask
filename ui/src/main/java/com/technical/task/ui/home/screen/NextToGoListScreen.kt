package com.technical.task.ui.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.technical.task.domain.races.model.NextToGoRaceDomainModel
import com.technical.task.ui.home.HomeStatePreviewParameterProvider

const val NEXT_TO_GO_LIST = "NEXT_TO_GO_LIST"

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
            modifier = Modifier.testTag(NEXT_TO_GO_LIST).fillMaxWidth(),
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