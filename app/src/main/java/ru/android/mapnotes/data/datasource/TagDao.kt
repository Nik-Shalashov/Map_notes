package ru.android.mapnotes.data.datasource

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.android.mapnotes.data.model.TagEntity

@Dao
interface TagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(spot: TagEntity)

    @Delete
    suspend fun deleteTag(spot: TagEntity)

    @Query("SELECT * FROM tagentity")
    fun getTags(): Flow<List<TagEntity>>
}