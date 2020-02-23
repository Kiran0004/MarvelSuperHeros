package com.example.marvel.superheros.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author kiran
 */


@Parcelize
data class MarvelHeroesModel(val id: Int = 0,
                             val name: String = "",
                             val description: String = "",
                             val thumbnail: String = "") : Parcelable

@Parcelize
data class MarvelListModel(val marvelHeroes: ArrayList<MarvelHeroesModel> = arrayListOf()) : Parcelable