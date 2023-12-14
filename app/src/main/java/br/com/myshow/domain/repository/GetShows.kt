package br.com.myshow.domain.repository

import br.com.myshow.data.repository.ShowRepository
import br.com.myshow.domain.model.Show
import javax.inject.Inject


class GetShows @Inject constructor(private val showRepository: ShowRepository) : ShowUseCase {

    override suspend fun getShowsServer(): List<Show>? = try {
        showRepository.getShowsServer()
    } catch (ex: Exception){
        listOf()
    }

    override suspend fun getShowsDatabase(): List<Show> {
        return showRepository.getShowsDatabase()
    }

    override suspend fun getShowsDatabase(listId: MutableList<Int>): List<Show> {
        return showRepository.getShowsDatabase(listId)
    }

    override suspend fun insertShow(show: Show) {
        showRepository.insertShow(show)
    }

    override suspend fun deleteShow(show: Show) {
        showRepository.deleteShow(show)
    }

    override suspend fun updateShow(show: Show) {
        showRepository.updateShow(show)
    }
}

interface  ShowUseCase {
    suspend fun getShowsServer(): List<Show>?
    suspend fun getShowsDatabase(): List<Show>
    suspend fun getShowsDatabase(listId: MutableList<Int>): List<Show>
    suspend fun insertShow(show: Show)
    suspend fun deleteShow(show: Show)
    suspend fun updateShow(show: Show)
}