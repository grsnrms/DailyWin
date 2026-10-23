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
fun HomeScreen(navController: NavController) {
    val checkedIds = remember { mutableStateListOf<Int>() }

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

        // Habits
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(habitList) { habit ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = habit.id in checkedIds,
                            onCheckedChange = {
                                if (it) checkedIds.add(habit.id) else checkedIds.remove(habit.id)
                            }
                        )
                        Text(
                            text = habit.name,
                            modifier = Modifier
                                .clickable { navController.navigate("detail/${habit.id}") }
                                .padding(10.dp)
                        )
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