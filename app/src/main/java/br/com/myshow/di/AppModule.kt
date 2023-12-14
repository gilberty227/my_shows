package br.com.myshow.di

import android.app.Application
import androidx.room.Room
import br.com.myshow.data.db.AppDatabase
import br.com.myshow.data.api.ApiMyShow
import br.com.myshow.data.repository.OrderRepository
import br.com.myshow.data.repository.OrderRepositoryImp
import br.com.myshow.data.repository.ShowRepository
import br.com.myshow.data.repository.ShowRepositoryImp
import br.com.myshow.data.repository.TicketRepository
import br.com.myshow.data.repository.TicketRepositoryImp
import br.com.myshow.domain.repository.CartUseCase
import br.com.myshow.domain.repository.GetOrders
import br.com.myshow.domain.repository.GetShows
import br.com.myshow.domain.repository.ShowUseCase
import br.com.myshow.domain.repository.GetTicketCart
import br.com.myshow.domain.repository.OrderUseCase
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
    fun provideShowRepository(service: ApiMyShow, appDatabase: AppDatabase): ShowRepository {
        return ShowRepositoryImp(service, appDatabase)
    }

    @Provides
    @Singleton
    fun provideShowUseCase(showRepository: ShowRepository): ShowUseCase {
        return GetShows(showRepository)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideTicketRepository(appDatabase: AppDatabase): TicketRepository {
        return TicketRepositoryImp(appDatabase)
    }

    @Provides
    @Singleton
    fun provideGetCartUseCase(ticketRepository: TicketRepository): CartUseCase {
        return GetTicketCart(ticketRepository)
    }

    @Provides
    @Singleton
    fun provideOrderRepository(appDatabase: AppDatabase): OrderRepository {
        return OrderRepositoryImp(appDatabase)
    }

    @Provides
    @Singleton
    fun provideGetOrderUseCase(orderRepository: OrderRepository): OrderUseCase {
        return GetOrders(orderRepository)
    }



}
