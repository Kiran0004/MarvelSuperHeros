package com.example.marvel.superheros.network.api


import com.example.marvel.superheros.commons.Constants
import com.example.marvel.superheros.commons.Constants.PARAM_CHARACTER_ID
import com.example.marvel.superheros.model.response.marvel.comics.MarvelComicsResponse
import com.example.marvel.superheros.model.response.marvel.hero.MarvelHeroResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author kiran
 */

interface MarvelApi {

    @GET("comics")
    fun getMarvelHeroesAsync(@Query(Constants.PARAM_LIMIT) limit: Int, @Query(Constants.PARAM_OFFSET) offset: Int): Deferred<MarvelHeroResponse>

    @GET("comics/{$PARAM_CHARACTER_ID}/characters")
    fun getMarvelComicsAsync(@Path(PARAM_CHARACTER_ID) heroId: Int): Deferred<MarvelComicsResponse>

}