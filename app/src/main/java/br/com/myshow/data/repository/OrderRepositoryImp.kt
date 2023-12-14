package br.com.myshow.data.repository

import br.com.myshow.data.db.AppDatabase
import br.com.myshow.domain.model.Order
import javax.inject.Inject

class OrderRepositoryImp @Inject constructor(private val database: AppDatabase): OrderRepository {
    override suspend fun insertOrder(order: Order) {
        database.orderDao.insertOrder(order)
    }
    override suspend fun deleteOrder(order: Order) {
        database.orderDao.deleteOrder(order)
    }

    override suspend fun updateOrder(order: Order) {
        database.orderDao.updateOrder(order)
    }

    override suspend fun getOrders(): List<Order> {
        return database.orderDao.getOrders()
    }
}

interface OrderRepository {

    suspend fun insertOrder(order: Order)

    suspend fun deleteOrder(order: Order)

    suspend fun updateOrder(order: Order)

    suspend fun getOrders(): List<Order>
}