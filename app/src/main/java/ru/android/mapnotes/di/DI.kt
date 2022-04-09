package ru.android.mapnotes.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.android.mapnotes.data.datasource.TagDB
import ru.android.mapnotes.data.repository.TagRepoImpl
import ru.android.mapnotes.models.repository.TagRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI {
    @Singleton
    @Provides
    fun provideSpotDatabase(app: Application): TagDB =
        Room.databaseBuilder(
            app,
            TagDB::class.java,
            "maps_spots.db"
        ).build()

    @Singleton
    @Provides
    fun provideSpotRepository(db: TagDB): TagRepo = TagRepoImpl(db.dao)
}