package br.com.myshow.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.myshow.data.db.AppDatabase

@Entity(tableName = AppDatabase.ORDER_NAME_TABLE)
data class Order(
    @PrimaryKey var id: Int?,
    var idShow: Int?,
    var countTicket: Int?
)
