package com.example.marvel.superheros.model.response.marvel.comics

import com.example.marvel.superheros.model.response.marvel.common.MarvelCommonResponse
import com.google.gson.annotations.SerializedName

/**
 * @author kiran
 */


data class MarvelComicsResponse(
    @SerializedName("data") val heroData: MarvelComicsData
): MarvelCommonResponse()