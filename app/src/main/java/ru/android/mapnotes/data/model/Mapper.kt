package ru.android.mapnotes.data.model

import ru.android.mapnotes.models.entities.Tag

fun TagEntity.toTag(): Tag {
    return Tag(
        lat = lat,
        lng = lng,
        id = id
    )
}

fun Tag.toTagEntity(): TagEntity {
    return TagEntity(
        lat = lat,
        lng = lng,
        id = id
    )
}