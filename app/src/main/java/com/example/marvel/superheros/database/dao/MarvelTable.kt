package com.example.marvel.superheros.database.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author kiran
 */


@Entity(tableName = "MarvelTable")
class MarvelTable(
        @PrimaryKey
        var id: Int
)