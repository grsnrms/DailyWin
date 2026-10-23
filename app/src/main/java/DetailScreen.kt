package ph.edu.comteq.dailywin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController, habitId: Int) {
    val habit = habitList.find { it.id == habitId }

Column(modifier = Modifier.fillMaxSize().padding(15.dp)) {
        if (habit == null) {
            Text("Habit not found")
        } else {
            Text(habit.name, style = MaterialTheme.typography.headlineMedium)
            Text(
                text = "Current streak: ${habit.streak} days",
                modifier = Modifier.padding(vertical = 10.dp)
            )

            Text("History", style = MaterialTheme.typography.titleMedium)
            // For sample data only
            Text("Today - Done")
            Text("Yesterday - Done")
            Text("2 days ago - Missed")
            Text("3 days ago - Done")
        }

        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}