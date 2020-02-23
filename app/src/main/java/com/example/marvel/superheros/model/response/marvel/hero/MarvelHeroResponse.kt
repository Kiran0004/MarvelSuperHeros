package com.example.marvel.superheros.model.response.marvel.hero

import com.example.marvel.superheros.model.response.marvel.common.MarvelCommonResponse
import com.google.gson.annotations.SerializedName


data class MarvelHeroResponse(@SerializedName("data") val heroData: MarvelHeroData): MarvelCommonResponse()