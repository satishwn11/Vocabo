package com.devsatish.vocabo.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devsatish.vocabo.Repository.SessionDao
import com.devsatish.vocabo.Repository.SessionEntity
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TimerViewModel(private val dao: SessionDao) : ViewModel() {

    private var startTime = 0L
    private var totalTime = 0L
    private var timerJob: Job? = null

    private val _elapsedTime = MutableStateFlow(0L)
    val elapsedTime: StateFlow<Long> = _elapsedTime

    val allSessions: Flow<List<SessionEntity>> = dao.getAllSessions()


    val groupedSessions: Flow<Map<String, Long>> = dao.getAllSessions().map { sessions ->
        sessions
            .groupBy { session ->
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                dateFormat.format(Date(session.timestamp))
            }
            .mapValues { (_, sessionsOnSameDay) ->
                sessionsOnSameDay.sumOf { it.duration }
            }
    }


    fun startTimer() {
        startTime = System.currentTimeMillis()
        timerJob = viewModelScope.launch {
            while (true) {
                _elapsedTime.value = (System.currentTimeMillis() - startTime)
                delay(1000)
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
        totalTime += System.currentTimeMillis() - startTime
    }

    fun stopAndSave() {
        timerJob?.cancel()
        totalTime += System.currentTimeMillis() - startTime
        viewModelScope.launch {
            dao.insertSession(SessionEntity(duration = (System.currentTimeMillis() - startTime)))
        }
        totalTime = 0L
        _elapsedTime.value = 0L
    }

}

@Suppress("UNCHECKED_CAST")
class TimerViewModelFactory(private val dao: SessionDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TimerViewModel(dao) as T
    }
}