package com.example.marvel.superheros.model.response.marvel.comics

import com.example.marvel.superheros.model.response.marvel.common.MarvelThumbnail
import com.google.gson.annotations.SerializedName

/**
 * @author kiran
 */

data class MarvelComics(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String ?,
    @SerializedName("description") val description: String ?,
    @SerializedName("thumbnail") val thumbnail: MarvelThumbnail
)