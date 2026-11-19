package ph.edu.comteq.dailywin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HabitViewModel(application: Application) : AndroidViewModel(application) {


    private val actions = AppDatabase.getDatabase(application).habitActions()


    val habits = actions.getAllHabits()
    val completions = actions.getAllCompletions()

    private fun format(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
    }

    fun today(): String {
        return format(Date())
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


    fun getStreak(doneDates: List<String>): Int {
        val day = Calendar.getInstance()

        // if today isn't done yet the streak is still alive, so start from yesterday
        if (format(day.time) !in doneDates) {
            day.add(Calendar.DAY_OF_YEAR, -1)
        }

        var streak = 0
        while (format(day.time) in doneDates) {
            streak++
            day.add(Calendar.DAY_OF_YEAR, -1)
        }
        return streak
    }

    fun getHistory(doneDates: List<String>, createdAt: Long): List<Pair<String, Boolean>> {
        val created = format(Date(createdAt))
        val day = Calendar.getInstance()
        val result = mutableListOf<Pair<String, Boolean>>()

        for (i in 0 until 7) {
            val text = format(day.time)
            if (text >= created) {
                val label = when (i) {
                    0 -> "Today"
                    1 -> "Yesterday"
                    else -> text
                }
                result.add(Pair(label, text in doneDates))
            }
            day.add(Calendar.DAY_OF_YEAR, -1)
        }
        return result
    }
}