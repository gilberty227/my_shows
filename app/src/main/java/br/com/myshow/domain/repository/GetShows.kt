package br.com.myshow.domain.repository

import br.com.myshow.data.repository.ShowRepository
import br.com.myshow.domain.model.Show
import javax.inject.Inject

class GetShows @Inject constructor(private val showRepository: ShowRepository) : GetShowsUseCase {

    override suspend fun invoke(): List<Show>? = try {
        showRepository.getShows()
    } catch (ex: Exception){
        listOf()
    }
}

interface  GetShowsUseCase {
    suspend operator fun invoke(): List<Show>?
}