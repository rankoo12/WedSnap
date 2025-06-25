package com.example.wedsnap.firebase

import com.example.wedsnap.model.ImageItem
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class GalleryService {
    private val db = FirebaseFirestore.getInstance()

    suspend fun fetchImagesForEvent(eventId: String): List<ImageItem> {
        return try {
            val snapshot = db.collection("events")
                .document(eventId)
                .collection("images")
                .get()
                .await()

            snapshot.documents.mapNotNull { doc ->
                val url = doc.getString("imageUrl")
                val id = doc.id
                if (url != null) ImageItem(id = id, imageUrl = url) else null
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
