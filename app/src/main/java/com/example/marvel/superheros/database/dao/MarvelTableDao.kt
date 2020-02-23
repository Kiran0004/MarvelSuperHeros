package com.example.marvel.superheros.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author kiran
 */


@Dao
interface MarvelTableDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHero(marvelTable:  MarvelTable)

    @Query("DELETE FROM MarvelTable")
    fun deleteAll()

    @Query("select * from MarvelTable")
    fun loadHeroes(): List<MarvelTable>
}