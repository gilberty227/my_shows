package br.com.myshow.data.repository

import br.com.myshow.data.api.ApiMyShow
import br.com.myshow.data.model.toShow
import br.com.myshow.data.utils.Result
import br.com.myshow.data.utils.parseResponse
import br.com.myshow.domain.model.Show
import javax.inject.Inject

class ShowRepositoryImp @Inject constructor(private val service: ApiMyShow): ShowRepository {

    class GetShowException : Exception()

    override suspend fun getShows(): List<Show>? {
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
}

interface ShowRepository {
    suspend fun getShows(): List<Show>?
}