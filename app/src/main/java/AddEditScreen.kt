package ph.edu.comteq.dailywin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddEditScreen(navController:NavController,habitId:Int){
    val habit = habitList.find{ it.id == habitId}
    var name by remember { mutableStateOf(habit?.name ?: "") }

Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = if (habit == null) "Add Habit"
            else "Edit Habit",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Habit name") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp)
        )

        Button(
            onClick = { navController.popBackStack("home", false) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

    TextButton(onClick = { navController.popBackStack() }) {
            Text("Cancel")
        }
    }
}