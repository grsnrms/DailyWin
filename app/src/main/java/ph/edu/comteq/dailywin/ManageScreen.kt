package ph.edu.comteq.dailywin

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
fun ManageScreen(navController: NavController, viewModel: HabitViewModel) {
    val habits by viewModel.habits.collectAsState(initial = emptyList())

    var habitToDelete by remember { mutableStateOf<Habit?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Manage Habits", style = MaterialTheme.typography.headlineMedium)


        if (habits.isEmpty()) {
            Text(
                text = "No habits yet.",
                modifier = Modifier.weight(1f).padding(vertical = 16.dp)
            )
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(habits) { habit ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(habit.name, modifier = Modifier.weight(1f))
                            TextButton(onClick = { navController.navigate("addedit/${habit.id}") }) {
                                Text("Edit")
                            }
                            TextButton(onClick = { habitToDelete = habit }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }

        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }


    if (habitToDelete != null) {
        AlertDialog(
            onDismissRequest = { habitToDelete = null },
            title = { Text("Delete this habit?") },
            text = { Text(habitToDelete!!.name) },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteHabit(habitToDelete!!)
                    habitToDelete = null
                }) { Text("Yes") }
            },
            dismissButton = {
                TextButton(onClick = { habitToDelete = null }) { Text("No") }
            }
        )
    }
}