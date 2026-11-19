package ph.edu.comteq.dailywin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController, habitId: Int, viewModel: HabitViewModel) {
    val habits by viewModel.habits.collectAsState(initial = emptyList())
    val completions by viewModel.completions.collectAsState(initial = emptyList())

    val habit = habits.find { it.id == habitId }
    val doneDates = completions.filter { it.habitId == habitId }.map { it.date }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (habit == null) {
            Text("Habit not found")
        } else {
            val streak = viewModel.getStreak(doneDates)
            val history = viewModel.getHistory(doneDates, habit.dateCreated)
            val doneCount = history.count { it.second }

            Text(habit.name, style = MaterialTheme.typography.headlineMedium)

            Text(
                text = "Current streak: $streak " + if (streak == 1) "day" else "days",
                modifier = Modifier.padding(top = 12.dp)
            )
            Text("Done in the last 7 days: $doneCount of ${history.size} days")
            Text("Total days done: ${doneDates.size}")

            Spacer(modifier = Modifier.height(16.dp))
            Text("History", style = MaterialTheme.typography.titleMedium)

            history.forEach { (label, done) ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(label)
                    Text(if (done) "Done" else "Missed")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}