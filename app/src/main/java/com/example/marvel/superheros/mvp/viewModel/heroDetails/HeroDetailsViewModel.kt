package com.example.marvel.superheros.mvp.viewModel.heroDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marvel.superheros.model.data.MarvelHeroesModel
import com.example.marvel.superheros.mvp.repository.heroDetails.HeroDetailsRepository
import com.example.marvel.superheros.mvp.viewModel.base.BaseViewModel

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * @author kiran
 */


class HeroDetailsViewModel(private val dashboardRepository: HeroDetailsRepository?, var hero: MarvelHeroesModel?) : BaseViewModel() {

    private lateinit var comics: MutableLiveData<List<MarvelHeroesModel>>

    fun getComics(): LiveData<List<MarvelHeroesModel>> {
        if (!::comics.isInitialized) {
            loadComics()
        }
        return comics
    }

    private fun loadComics() {
        comics = MutableLiveData()
        uiScope.launch {
            try {
                showLoading.value = true
                val response = withContext(bgDispatcher) {
                    dashboardRepository?.fetchComics(hero?.id ?: 0)
                }
                response?.let {
                    showError.value = false
                    comics.value = it.marvelHeroes.toList()
                } ?: run {
                    showError.value = true
                }
            } catch (e: Exception) {
                Timber.e(e.toString())
                showError.value = true
            } finally {
                showLoading.value = false
            }
        }
    }
}