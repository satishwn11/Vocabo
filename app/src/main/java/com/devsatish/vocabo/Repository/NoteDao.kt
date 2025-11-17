package com.devsatish.vocabo.Repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    suspend fun addnotes(text: Notes)

    @Query("Select * From notes ORDER BY timestamp DESC")
    fun getAllnotes(): Flow<List<Notes>>
}