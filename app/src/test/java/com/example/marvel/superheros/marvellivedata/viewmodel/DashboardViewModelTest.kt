package com.example.marvel.superheros.marvellivedata.viewmodel

import androidx.lifecycle.LiveData
import com.example.marvel.superheros.model.data.MarvelListModel
import com.example.marvel.superheros.mvp.repository.dashboard.DashboardRepository
import com.example.marvel.superheros.mvp.viewModel.dashboard.DashboardViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class DashboardViewModelTest {
    private lateinit var viewModel: DashboardViewModel

    private lateinit var isLoadingLiveData: LiveData<Boolean>

    private lateinit var isErrorLiveData: LiveData<Boolean>


    @Mock
    private var marveList: MarvelListModel? = null

    @Mock
    private lateinit var repo: DashboardRepository

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setUp() {
        viewModel = DashboardViewModel(repo)

        isLoadingLiveData = viewModel.getIsLoading()
        isErrorLiveData = viewModel.shouldShowError()
    }

    @Test
    fun loadTeamsShouldShowAndHideLoading() = runBlocking {
        `when`(repo.fetchHeroes(0, 20)).thenReturn(marveList)

        val isLoading = isLoadingLiveData.value
        isLoading?.let { assertTrue(it) }
        viewModel.getHeroes()
        verify(repo).fetchHeroes(0, 20)
        isLoading?.let { assertFalse(it) }
        return@runBlocking
    }
}