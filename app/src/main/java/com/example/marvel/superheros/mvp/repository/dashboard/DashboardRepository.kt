package com.example.marvel.superheros.mvp.repository.dashboard

import com.example.marvel.superheros.database.MarvelDatabase
import com.example.marvel.superheros.database.dao.MarvelTable
import com.example.marvel.superheros.model.data.MarvelHeroesModel
import com.example.marvel.superheros.model.data.MarvelListModel
import com.example.marvel.superheros.mvp.repository.base.BaseRepository

import com.example.marvel.superheros.network.client.MarvelClient
import timber.log.Timber

class DashboardRepository(private val marvelClient: MarvelClient?, private val marvelDatabase: MarvelDatabase) : BaseRepository() {

    suspend fun fetchHeroes(offset: Int, limit: Int): MarvelListModel? {
        val response = try {
            marvelClient?.getMarvelHeroesAsync(limit, offset)?.await()
        } catch (e: Exception) {
            return null
        }

        val marvelModelList = ArrayList(response?.heroData?.results?.map { marvelHero ->
            marvelDatabase.marvelDao().insertHero(MarvelTable(marvelHero.id))

            return@map MarvelHeroesModel(
                marvelHero.id,
                marvelHero.name,
                marvelHero.description?:"",
                marvelHero.thumbnail.path + "." + marvelHero.thumbnail.extension
            )
        })
        return MarvelListModel(marvelModelList)
    }
}