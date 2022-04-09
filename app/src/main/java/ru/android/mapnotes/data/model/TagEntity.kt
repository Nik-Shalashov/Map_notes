package ru.android.mapnotes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TagEntity(
    val lat: Double,
    val lng: Double,
    @PrimaryKey val id: Int? = null
)
