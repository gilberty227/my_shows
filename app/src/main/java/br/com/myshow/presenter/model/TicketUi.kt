package br.com.myshow.presenter.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import br.com.myshow.domain.model.Show
import br.com.myshow.domain.model.Ticket
import kotlinx.parcelize.Parcelize

data class TicketUi(
    var idShow: Int?,
    var countTicket: Int?,
    var show: Show?
)

fun TicketUi.toTicket(): Ticket{
    return Ticket(
        idShow = this.idShow,
        countTicket = this.countTicket
    )
}

