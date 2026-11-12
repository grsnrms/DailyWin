package ph.edu.comteq.dailywin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController) {
    var darkMode by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(15.dp)) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Dark mode")
            Switch(checked = darkMode, onCheckedChange = { darkMode = it })
        }

        // Optional for reminder time
        Text("Reminder time: 8:00 PM")

        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}