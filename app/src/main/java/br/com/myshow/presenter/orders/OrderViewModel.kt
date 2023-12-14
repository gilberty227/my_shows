package br.com.myshow.presenter.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.myshow.domain.model.toOrderUi
import br.com.myshow.domain.repository.OrderUseCase
import br.com.myshow.presenter.model.OrderUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val orderUseCase: OrderUseCase) : ViewModel() {


    private val _orders = MutableLiveData<List<OrderUi>>()
    val orders = _orders as LiveData<List<OrderUi>>

    fun getOrders(){
        viewModelScope.launch {
            val result = orderUseCase.getOrders()?: listOf()
            _orders.value = result.map {
                it.toOrderUi()
            }
        }
    }
}