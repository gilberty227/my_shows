package br.com.myshow.di

import br.com.myshow.data.api.ApiMyShow
import br.com.myshow.data.repository.ShowRepository
import br.com.myshow.data.repository.ShowRepositoryImp
import br.com.myshow.domain.repository.GetShows
import br.com.myshow.domain.repository.GetShowsUseCase
import br.com.myshow.domain.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrl: String): ApiMyShow {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }
            ).build())
            .build()
            .create(ApiMyShow::class.java)
    }

    @Provides
    @Singleton
    fun provideShowRepository(service: ApiMyShow): ShowRepository {
        return ShowRepositoryImp(service)
    }

    @Provides
    @Singleton
    fun provideGetShowUseCase(showRepository: ShowRepository): GetShowsUseCase {
        return GetShows(showRepository)
    }

    /*@Provides
    @Singleton
    fun provideCheckInternet(application: Application): ConnectivityInterceptor {
        return ConnectivityInterceptor(application)
    }*/

}
