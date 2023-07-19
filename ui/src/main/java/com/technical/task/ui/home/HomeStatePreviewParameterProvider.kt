package com.technical.task.ui.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.technical.task.domain.races.model.NextToGoRaceDomainModel

class HomeStatePreviewParameterProvider :
    PreviewParameterProvider<List<NextToGoRaceDomainModel>> {
    override val values = sequenceOf(
        listOf(
            NextToGoRaceDomainModel(
                "Traralgon",
                9,
                "4a2788f8-e825-4d36-9894-efd4baf1cfae",
                1689582600
            ),
            NextToGoRaceDomainModel(
                "Morioka",
                11,
                "9daef0d7-bf3c-4f50-921d-8e818c60fe61",
                1689582960
            ),
            NextToGoRaceDomainModel(
                "Richmond",
                11,
                "9daef0d7-bf3c-4f50-921d-8e818c60fe61",
                1689582420
            ),
            NextToGoRaceDomainModel(
                "Northam",
                10,
                "9daef0d7-bf3c-4f50-921d-8e818c60fe61",
                1689583140
            )
        )
    )
}