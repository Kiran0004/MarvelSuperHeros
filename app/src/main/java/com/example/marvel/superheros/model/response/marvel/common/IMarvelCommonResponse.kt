package com.example.marvel.superheros.model.response.marvel.common

/**
 * @author kiran
 */


interface IMarvelCommonResponse {
    fun isSuccess(): Boolean

    fun getErrorMessage(): String
}