package com.example.studenttasks

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studenttasks.ui.theme.StudentTasksTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.material3.Checkbox
import android.R.style


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentTasksTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    StudentTasksApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TaskRow(
    title: String,
    isCompleted: Boolean,
    priority: Priority
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isCompleted,
            onCheckedChange = null
        )

        Text(
            text = title,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        )

        Text(priority.name)
    }
}
@Composable
fun StudentTasksApp(modifier: Modifier = Modifier) {

    val task = Task(
        id = 1,
        title = "Read Kotlin documentation"
    )
    val task_2 = Task(
        id = 2,
        title = "Prepare exercise",
        priority = Priority.HIGH
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = "Student Tasks",
            style = MaterialTheme.typography.headlineLarge
        )
        TaskRow(
            title = "Prepare Kotlin exercise",
            isCompleted = false,
            priority = Priority.HIGH
        )

        TaskRow(
            title = "Read Android documentation",
            isCompleted = false,
            priority = Priority.MEDIUM
        )

        TaskRow(
            title = "Run the app",
            isCompleted = true,
            priority = Priority.LOW
        )
    }
}
