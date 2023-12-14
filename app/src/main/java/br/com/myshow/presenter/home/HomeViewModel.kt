package br.com.myshow.presenter.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import br.com.myshow.domain.model.Show
import br.com.myshow.domain.repository.ShowUseCase
import br.com.myshow.presenter.model.ShowUi
import br.com.myshow.presenter.model.toShowDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val showsUseCase: ShowUseCase) : ViewModel() {

    private val _shows = MutableLiveData<List<Show>>()
    val shows = _shows as LiveData<List<Show>>

    val showsDtoList: LiveData<List<ShowUi>> = shows.map {
        it.map {  show ->
            show.toShowDto()
        }
    }

    fun getShows(){
        viewModelScope.launch {
            val showList = showsUseCase.getShowsServer()
            _shows.value = showList?: listOf()
        }
    }
}