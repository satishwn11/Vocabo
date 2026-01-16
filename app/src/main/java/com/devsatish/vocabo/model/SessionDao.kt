package com.devsatish.vocabo.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {
    @Insert
    suspend fun insertSession(session: SessionEntity)

    @Query("SELECT * FROM sessions ORDER BY id DESC")
    fun getAllSessions(): Flow<List<SessionEntity>>

    @Query("SELECT * FROM sessions WHERE date(timestamp/1000, 'unixepoch') = date(:time/1000, 'unixepoch') LIMIT 1")
    suspend fun getTodaySession(time: Long): SessionEntity?

    @Update
    suspend fun updateSession(session: SessionEntity)
}