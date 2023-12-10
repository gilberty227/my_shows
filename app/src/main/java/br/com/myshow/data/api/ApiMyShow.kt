package br.com.myshow.data.api

import br.com.myshow.data.model.BaseResponse
import br.com.myshow.data.model.ShowResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiMyShow {
    @GET("shows")
    suspend fun getShowList(): Response<BaseResponse<ShowResponse>>
}