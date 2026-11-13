package ph.edu.comteq.dailywin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HabitViewModel(application: Application) : AndroidViewModel(application) {


    private val actions = AppDatabase.getDatabase(application).habitActions()


    val habits = actions.getAllHabits()
    val completions = actions.getAllCompletions()


    fun today(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }

    fun addHabit(name: String) {
        viewModelScope.launch {
            actions.insertHabit(Habit(name = name))
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch {
            actions.updateHabit(habit)
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            actions.deleteHabit(habit)
        }
    }


    fun toggleDone(habitId: Int, isDone: Boolean) {
        viewModelScope.launch {
            if (isDone) {
                actions.insertCompletion(Completion(habitId = habitId, date = today()))
            } else {
                actions.deleteCompletion(habitId, today())
            }
        }
    }
}