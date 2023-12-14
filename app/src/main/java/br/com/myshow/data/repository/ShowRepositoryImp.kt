package br.com.myshow.data.repository

import br.com.myshow.data.api.ApiMyShow
import br.com.myshow.data.db.AppDatabase
import br.com.myshow.data.model.toShow
import br.com.myshow.data.utils.Result
import br.com.myshow.data.utils.parseResponse
import br.com.myshow.domain.model.Show
import javax.inject.Inject

class ShowRepositoryImp @Inject constructor(private val service: ApiMyShow,
                                            private val database: AppDatabase): ShowRepository {

    class GetShowException : Exception()

    override suspend fun getShowsServer(): List<Show>? {
        val result = service.getShowList().parseResponse()


        return when(result){
            is Result.Success -> {
                val showResponseList = result.value?.shows

                showResponseList?.map {
                    it.toShow()
                }
            }
            is Result.Failure -> {
                throw GetShowException()
            }
        }
    }

    override suspend fun getShowsDatabase(): List<Show> {
        return database.showDao.getShowsDatabase()
    }

    override suspend fun getShowsDatabase(listId: MutableList<Int>): List<Show> {
        return database.showDao.getShowsDatabase(listId)
    }

    override suspend fun insertShow(show: Show) {
        database.showDao.insertShow(show)
    }

    override suspend fun deleteShow(show: Show) {
        database.showDao.deleteShow(show)
    }

    override suspend fun updateShow(show: Show) {
        database.showDao.updateShow(show)
    }
}

interface ShowRepository {
    suspend fun getShowsServer(): List<Show>?
    suspend fun getShowsDatabase(): List<Show>
    suspend fun getShowsDatabase(listId: MutableList<Int>): List<Show>
    suspend fun insertShow(show: Show)
    suspend fun deleteShow(show: Show)
    suspend fun updateShow(show: Show)
}