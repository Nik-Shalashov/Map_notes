package ru.android.mapnotes.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.android.mapnotes.data.model.TagEntity

@Database(
    entities = [TagEntity::class],
    version = 1
)
abstract class TagDB: RoomDatabase() {

    abstract val dao: TagDao
}