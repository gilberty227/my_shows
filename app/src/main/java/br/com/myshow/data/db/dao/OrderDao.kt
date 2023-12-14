package br.com.myshow.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.myshow.data.db.AppDatabase
import br.com.myshow.domain.model.Order

@Dao
interface OrderDao {

    @Query("SELECT * FROM `order`")
    suspend fun getOrders(): List<Order>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)
    @Delete
    suspend fun deleteOrder(order: Order)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateOrder(order: Order)
}

