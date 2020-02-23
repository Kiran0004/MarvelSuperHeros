package com.example.marvel.superheros.ui.activity.base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.example.marvel.superheros.mvp.viewModel.base.BaseViewModel

/**
 * @author kiran
 */


@SuppressLint("Registered")
open class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    protected var viewModel: T? = null
}