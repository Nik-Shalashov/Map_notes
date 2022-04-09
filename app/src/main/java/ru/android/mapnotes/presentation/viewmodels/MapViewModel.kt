package ru.android.mapnotes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.android.mapnotes.models.entities.Tag
import ru.android.mapnotes.models.repository.TagRepo
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: TagRepo
) : ViewModel() {

    val tags = repository.getTags()

    fun insertTag(latLng: LatLng) = viewModelScope.launch {
        repository.insertTag(Tag(lat = latLng.latitude, lng = latLng.longitude))
    }

    fun deleteTag(tag: Tag) = viewModelScope.launch {
        repository.deleteTag(tag)
    }
}