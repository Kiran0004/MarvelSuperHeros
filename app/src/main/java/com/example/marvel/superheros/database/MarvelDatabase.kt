package com.example.marvel.superheros.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marvel.superheros.database.dao.MarvelTable
import com.example.marvel.superheros.database.dao.MarvelTableDao

/**
 * @author kiran
 */


@Database(entities = [MarvelTable::class], version = 1, exportSchema = false)
abstract class MarvelDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "MarvelDb"

        fun get(context: Context): MarvelDatabase {
            return Room.databaseBuilder(context.applicationContext, MarvelDatabase::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }

    abstract fun marvelDao(): MarvelTableDao
}