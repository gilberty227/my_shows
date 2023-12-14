package br.com.myshow.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.myshow.data.db.AppDatabase

@Entity(tableName = AppDatabase.TICKET_NAME_TABLE)
data class Ticket(
     @PrimaryKey var idShow: Int?,
    var countTicket: Int?
)
