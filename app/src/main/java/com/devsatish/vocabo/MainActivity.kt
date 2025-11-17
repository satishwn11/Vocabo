package com.devsatish.vocabo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.devsatish.vocabo.Repository.AppDatabase
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.devsatish.vocabo.View.Drawer
import com.devsatish.vocabo.View.EasyActivity
import com.devsatish.vocabo.View.HardScreen
import com.devsatish.vocabo.View.LevelSelect
import com.devsatish.vocabo.View.MediumScreen
import com.devsatish.vocabo.View.NoteScreen
import com.devsatish.vocabo.View.TimerScreen
import com.devsatish.vocabo.View.WriteScreen
import com.devsatish.vocabo.ViewModel.TimerViewModel
import com.devsatish.vocabo.ViewModel.TimerViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "session_db"
        ).build()

        val dao = db.sessionDao()

        setContent {
            val viewModel: TimerViewModel = viewModel(factory = TimerViewModelFactory(dao))
            val elapsedTime by viewModel.elapsedTime.collectAsState()
            val sessions by viewModel.allSessions.collectAsState(initial = emptyList())
            val grouped by viewModel.groupedSessions.collectAsState(initial = emptyMap())

            // lifecycle observer for start/pause
            DisposableEffect(Unit) {
                val observer = object : DefaultLifecycleObserver {
                    override fun onStart(owner: LifecycleOwner) {
                        viewModel.startTimer()
                    }

                    override fun onStop(owner: LifecycleOwner) {
                        viewModel.stopAndSave()
                    }
                }

                lifecycle.addObserver(observer)
                onDispose {
                    lifecycle.removeObserver(observer)
                }
            }

            val navController = rememberNavController()


            NavHost(
                navController = navController,
                startDestination = "Main",
            ) {
                composable("Main") { Drawer(navController)}
                composable("EasyActivity") { EasyActivity() }
                composable("LevelSelect") { LevelSelect(navController) }
                composable("MediumScreen") { MediumScreen() }
                composable("HardScreen") { HardScreen() }
                composable("TimerScreen") { TimerScreen(elapsedTime, sessions, grouped) }
                composable("NoteScreen") { NoteScreen(navController) }
                composable("WriteScreen") { WriteScreen(navController) }
            }
        }
    }
}

