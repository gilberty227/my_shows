package br.com.myshow.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.myshow.data.db.AppDatabase

@Entity(tableName = AppDatabase.SHOW_NAME_TABLE)
data class Show(
    @PrimaryKey var id: Int?,
    var title: String?,
    var desc: String?,
    var imageUrl: String?,
    var price: Int?,
    var day: String?,
    var hour: String?,
    var location: String?,
    var locationQuick: String?
)
