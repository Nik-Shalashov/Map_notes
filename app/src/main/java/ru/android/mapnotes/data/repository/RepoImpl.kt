package ru.android.mapnotes.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.android.mapnotes.data.datasource.TagDao
import ru.android.mapnotes.data.model.toTag
import ru.android.mapnotes.data.model.toTagEntity
import ru.android.mapnotes.models.entities.Tag
import ru.android.mapnotes.models.repository.TagRepo

class TagRepoImpl(
    private val dao: TagDao
): TagRepo {

    override suspend fun insertTag(tag: Tag) {
        dao.insertTag(tag.toTagEntity())
    }

    override suspend fun deleteTag(tag: Tag) {
        dao.deleteTag(tag.toTagEntity())
    }

    override fun getTags(): Flow<List<Tag>> {
        return dao.getTags().map { tags ->
            tags.map { it.toTag() }
        }
    }
}