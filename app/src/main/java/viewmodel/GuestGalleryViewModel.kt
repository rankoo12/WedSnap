package com.example.wedsnap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wedsnap.firebase.GalleryService
import com.example.wedsnap.model.ImageItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GuestGalleryViewModel(
    private val galleryService: GalleryService = GalleryService()
) : ViewModel() {

    private val _images = MutableStateFlow<List<ImageItem>>(emptyList())
    val images: StateFlow<List<ImageItem>> = _images

    fun loadGallery(eventId: String) {
        viewModelScope.launch {
            _images.value = galleryService.fetchImagesForEvent(eventId)
        }
    }
}
