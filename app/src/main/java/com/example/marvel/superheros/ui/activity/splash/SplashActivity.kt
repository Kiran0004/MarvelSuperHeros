package com.example.marvel.superheros.ui.activity.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.marvel.superheros.R
import com.example.marvel.superheros.ui.activity.comics.ComicsListActivity

/**
 * @author kiran
 */


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initLayout()
    }

    private fun initLayout() {
        Handler().postDelayed({
            startActivity(Intent(this, ComicsListActivity::class.java))
            finish()
        }, 2000)
    }


}
