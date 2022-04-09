package ru.android.mapnotes.models.repository

import kotlinx.coroutines.flow.Flow
import ru.android.mapnotes.models.entities.Tag

interface TagRepo {

    suspend fun insertTag(tag: Tag)

    suspend fun deleteTag(tag: Tag)

    fun getTags(): Flow<List<Tag>>
}