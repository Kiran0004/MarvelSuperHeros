package com.example.marvel.superheros.mvp.repository.heroDetails

import com.example.marvel.superheros.model.data.MarvelHeroesModel
import com.example.marvel.superheros.model.data.MarvelListModel
import com.example.marvel.superheros.mvp.repository.base.BaseRepository
import com.example.marvel.superheros.network.client.MarvelClient


class HeroDetailsRepository(private val marvelClient: MarvelClient?) : BaseRepository() {

    suspend fun fetchComics(heroId: Int): MarvelListModel? {
        val response = try {
            marvelClient?.getMarvelComicsAsync(heroId)?.await()
        } catch (e: Exception) {
            return null
        }

        val marvelModelList = ArrayList(response?.heroData?.results?.map { marvelHero ->
            return@map MarvelHeroesModel(
                    marvelHero.id,
                    marvelHero.title ?: "",
                    marvelHero.description ?: "",
                    marvelHero.thumbnail.path + "." + marvelHero.thumbnail.extension)
        })
        return MarvelListModel(marvelModelList)
    }
}