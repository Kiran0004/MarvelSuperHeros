package com.example.marvel.superheros.ui.activity.comics

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.superheros.R
import com.example.marvel.superheros.commons.BUNDLE
import com.example.marvel.superheros.commons.Constants
import com.example.marvel.superheros.commons.application.MarvelApplication
import com.example.marvel.superheros.commons.extentions.closeSoftKeyboard
import com.example.marvel.superheros.database.MarvelDatabase
import com.example.marvel.superheros.mvp.repository.base.BaseViewModelFactory
import com.example.marvel.superheros.mvp.repository.dashboard.DashboardRepository
import com.example.marvel.superheros.mvp.viewModel.dashboard.DashboardViewModel
import com.example.marvel.superheros.ui.activity.base.BaseActivity
import com.example.marvel.superheros.ui.activity.comicsDetails.ComicsDetailsActivity
import com.example.marvel.superheros.ui.adapters.comics.ComicsRecyclerViewAdapter
import com.example.marvel.superheros.ui.custom.pagination.PaginationScrollListener

import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.layout_pagination_recyclerview.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * @author kiran
 */

class ComicsListActivity : BaseActivity<DashboardViewModel>() {

    private var comicsRecyclerViewAdapter: ComicsRecyclerViewAdapter? = null
    private var paginationScrollListener: PaginationScrollListener? = null
    private lateinit var searchView: SearchView
    private lateinit var activity:Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        activity = this
        initLayout()
        initViewModel()
    }

    private fun initViewModel() {
        val dashboardViewModelFactory = BaseViewModelFactory {
            DashboardViewModel(DashboardRepository(MarvelApplication.get()?.marvelClient, MarvelDatabase.get(this)))
        }
        /**
         * ViewModelProviders , keeping the ViewModel alive and paired with the scope
         */
        viewModel = ViewModelProviders.of(this, dashboardViewModelFactory).get(DashboardViewModel::class.java)
        viewModel?.getHeroes()?.observe(this, Observer { heroes ->
            // update UI
            moreProgressView?.visibility = View.GONE
            heroes?.let {
                comicsRecyclerViewAdapter?.setHeroesList(it.toMutableList())
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

    private fun initLayout() {
        setSupportActionBar(toolbar)
        // toolbar fancy stuff
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        toolbarTitleTextView.text = getString(R.string.dashboard_toolbar_title)

        backButtonImageView.visibility = View.INVISIBLE
        val linearLayoutManager = LinearLayoutManager(this)
        dashboardRecyclerView.layoutManager = linearLayoutManager
        comicsRecyclerViewAdapter = ComicsRecyclerViewAdapter(
                onHeroClicked = { hero ->
                    val intent = Intent(this, ComicsDetailsActivity::class.java)
                    intent.putExtra(BUNDLE.HERO_DETAILS, hero)
                    startActivity(intent)
                })



        paginationScrollListener = PaginationScrollListener(
                linearLayoutManager,
                {
                    if(searchView!=null && searchView.isIconified) {
                        moreProgressView?.visibility = View.VISIBLE
                        viewModel?.loadHeroes()
                    }
                },
                Constants.PAGINATION_SIZE
        )
        paginationScrollListener?.let {
            dashboardRecyclerView.addOnScrollListener(it)
        }
        dashboardRecyclerView.adapter = comicsRecyclerViewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        // Associate searchable configuration with the SearchView
        var searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE
        // listening to search query text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                comicsRecyclerViewAdapter!!.getFilter().filter(query)
                closeSoftKeyboard(activity)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                comicsRecyclerViewAdapter!!.getFilter().filter(query)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)

    }
    override fun onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified) {
            searchView.isIconified = true
            return
        }
        super.onBackPressed()
    }
}