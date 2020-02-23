package com.example.marvel.superheros.ui.activity.comicsDetails

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.superheros.R
import com.example.marvel.superheros.commons.BUNDLE
import com.example.marvel.superheros.commons.application.MarvelApplication
import com.example.marvel.superheros.commons.extentions.getScreenWidth
import com.example.marvel.superheros.commons.utils.pagersnap.PagerSnapCallbackHelper
import com.example.marvel.superheros.model.data.MarvelHeroesModel
import com.example.marvel.superheros.mvp.repository.base.BaseViewModelFactory
import com.example.marvel.superheros.mvp.repository.heroDetails.HeroDetailsRepository
import com.example.marvel.superheros.mvp.viewModel.heroDetails.HeroDetailsViewModel
import com.example.marvel.superheros.ui.activity.base.BaseActivity
import com.example.marvel.superheros.ui.adapters.comicsDetails.ComicsDetailsRecyclerViewAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_hero_details.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * @author kiran
 */


class ComicsDetailsActivity : BaseActivity<HeroDetailsViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_details)
        initViewModel()
        initLayout()
    }

    private fun initLayout() {
        backButtonImageView.setOnClickListener { onBackPressed() }
        toolbarTitleTextView.text = getString(R.string.dashboard_toolbar_title)
        Picasso.get().load(viewModel?.hero?.thumbnail).into(heroImageView)
        heroTitleTextView.text = viewModel?.hero?.name
        heroDescriptionTextView.text = if (viewModel?.hero?.description.isNullOrEmpty()) getString(R.string.dummy_description) else viewModel?.hero?.description
    }

    private fun initViewModel() {
        val heroDetailsViewModelFactory = BaseViewModelFactory {
            HeroDetailsViewModel(HeroDetailsRepository(MarvelApplication.get()?.marvelClient), intent?.extras?.getParcelable<MarvelHeroesModel>(
                BUNDLE.HERO_DETAILS))
        }
        viewModel = ViewModelProviders.of(this, heroDetailsViewModelFactory).get(HeroDetailsViewModel::class.java)

        viewModel?.getComics()?.observe(this, Observer { heroes ->
            heroes?.let {
                heroDetailsRecyclerView.setHasFixedSize(true)
                heroDetailsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                heroDetailsRecyclerView.adapter = ComicsDetailsRecyclerViewAdapter(it.toMutableList(), (getScreenWidth(this) * 0.85).toInt())

                val pagerSnapCallbackHelper = PagerSnapCallbackHelper()
                pagerSnapCallbackHelper.attachToRecyclerView(heroDetailsRecyclerView)
            } ?: run { emptyView.visibility = View.VISIBLE }
        })

        viewModel?.getIsLoading()?.observe(this, Observer { value ->
            value?.let { show ->
                loadingView.visibility = if (show) View.VISIBLE else View.GONE
            }
        })

        viewModel?.shouldShowError()?.observe(this, Observer { value ->
            value?.let { show ->
                emptyView.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }
}
