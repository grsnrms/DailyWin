package ph.edu.comteq.dailywin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddEditScreen(navController: NavController, habitId: Int, viewModel: HabitViewModel) {
    val habits by viewModel.habits.collectAsState(initial = emptyList())
    val habit = habits.find { it.id == habitId }

    var name by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }


    LaunchedEffect(habit?.id) {
        if (habit != null) name = habit.name
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = if (habit == null) "Add Habit" else "Edit Habit",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                showError = false
            },
            label = { Text("Habit name") },
            isError = showError,
            supportingText = { if (showError) Text("Please enter a habit name") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        )

        Button(
            onClick = {
                if (name.isBlank()) {
                    showError = true
                } else {
                    if (habit == null) {
                        viewModel.addHabit(name.trim())
                    } else {
                        viewModel.updateHabit(habit.copy(name = name.trim()))
                    }
                    navController.popBackStack("home", false)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        TextButton(onClick = { navController.popBackStack() }) {
            Text("Cancel")
        }
    }
}