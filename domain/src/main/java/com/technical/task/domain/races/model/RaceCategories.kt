package com.technical.task.domain.races.model

sealed class RaceCategories(val id: String) {
    object Greyhound : RaceCategories("9daef0d7-bf3c-4f50-921d-8e818c60fe61")
    object Harness : RaceCategories("161d9be2-e909-4326-8c2c-35ed71fb460b")
    object Horse : RaceCategories("4a2788f8-e825-4d36-9894-efd4baf1cfae")
    object AllRaceCategories : RaceCategories(ID)
}

const val ID = "arc"