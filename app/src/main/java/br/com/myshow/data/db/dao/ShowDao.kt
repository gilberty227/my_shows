package br.com.myshow.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.myshow.data.db.AppDatabase
import br.com.myshow.domain.model.Show
import br.com.myshow.domain.model.Ticket

@Dao
interface ShowDao {
    @Query("SELECT * FROM ${AppDatabase.SHOW_NAME_TABLE}")
    suspend fun getShowsDatabase(): List<Show>

    @Query("SELECT * FROM ${AppDatabase.SHOW_NAME_TABLE} WHERE id in (:listId)")
    suspend fun getShowsDatabase(listId: MutableList<Int>): List<Show>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(show: Show)
    @Delete
    suspend fun deleteShow(show: Show)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateShow(show: Show)
}