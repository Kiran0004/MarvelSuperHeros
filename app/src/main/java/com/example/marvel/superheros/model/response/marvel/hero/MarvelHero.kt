package com.example.marvel.superheros.model.response.marvel.hero

import com.example.marvel.superheros.model.response.marvel.common.MarvelThumbnail
import com.google.gson.annotations.SerializedName


data class MarvelHero(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val name: String,
        @SerializedName("description") val description: String?,
        @SerializedName("thumbnail") val thumbnail: MarvelThumbnail
)