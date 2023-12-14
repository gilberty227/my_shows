package br.com.myshow.domain.repository

import br.com.myshow.data.db.AppDatabase
import br.com.myshow.data.repository.OrderRepository
import br.com.myshow.data.repository.ShowRepository
import br.com.myshow.domain.model.Order
import br.com.myshow.domain.model.Show
import javax.inject.Inject


class GetOrders @Inject constructor(private val orderRepository: OrderRepository) : OrderUseCase {

    override suspend fun insertOrder(order: Order) {
        orderRepository.insertOrder(order)
    }
    override suspend fun deleteOrder(order: Order) {
        orderRepository.deleteOrder(order)
    }

    override suspend fun updateOrder(order: Order) {
        orderRepository.updateOrder(order)
    }

    override suspend fun getOrders(): List<Order> {
        return orderRepository.getOrders()
    }
}

interface  OrderUseCase {
    suspend fun insertOrder(order: Order)

    suspend fun deleteOrder(order: Order)

    suspend fun updateOrder(order: Order)

    suspend fun getOrders(): List<Order>
}