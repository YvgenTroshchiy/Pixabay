package com.troshchii.pixabay.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize data class Hit(
    val id: Int,
    val previewURL: String,
    val userImageURL: String,
    val user: String,
    val likes: Int,
) : Parcelable
