package ph.edu.comteq.dailywin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, viewModel: HabitViewModel) {
    val habits by viewModel.habits.collectAsState(initial = emptyList())
    val completions by viewModel.completions.collectAsState(initial = emptyList())
    val today = viewModel.today()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Today", style = MaterialTheme.typography.headlineMedium)
            Row {
                TextButton(onClick = { navController.navigate("manage") }) {
                    Text("Manage")
                }
                TextButton(onClick = { navController.navigate("settings") }) {
                    Text("Settings")
                }
            }
        }


        if (habits.isEmpty()) {
            Text(
                text = "No habits yet. Tap + Add Habit to start.",
                modifier = Modifier.weight(1f).padding(vertical = 16.dp)
            )
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(habits) { habit ->
                    val isDone = completions.any {
                        it.habitId == habit.id && it.date == today
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isDone,
                                onCheckedChange = { viewModel.toggleDone(habit.id, it) }
                            )
                            // tap the name to open the detail screen
                            Text(
                                text = habit.name,
                                modifier = Modifier
                                    .clickable { navController.navigate("detail/${habit.id}") }
                                    .padding(12.dp)
                            )
                        }
                    }
                }
            }
        }


        Button(
            onClick = { navController.navigate("addedit/-1") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("+ Add Habit")
        }
    }
}