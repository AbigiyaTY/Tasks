package com.example.studenttasks

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studenttasks.ui.theme.StudentTasksTheme

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
fun StudentTasksApp(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    var tasks by remember {
        mutableStateOf(
            listOf(
                Task(
                    1,
                    "Prepare Kotlin exercise",
                    priority = Priority.HIGH
                ),
                Task(
                    2,
                    "Read Android documentation",
                    priority = Priority.MEDIUM
                ),
                Task(
                    3,
                    "Run the app",
                    isCompleted = true,
                    priority = Priority.LOW
                )
            )
        )
    }

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {

            HomeScreen(
                tasks = tasks,

                onAddTask = {
                    navController.navigate("add")
                },

                onAbout = {
                    navController.navigate("about")
                },

                onCompletedChange = { changedTask, isCompleted ->

                    tasks = tasks.map { task ->

                        if (task.id == changedTask.id) {
                            task.copy(isCompleted = isCompleted)
                        } else {
                            task
                        }
                    }
                },

                onDelete = { taskToDelete ->

                    Log.d(
                        "StudentTasks",
                        "Deleting task ${taskToDelete.id}: ${taskToDelete.title}"
                    )

                    tasks = tasks.filter {
                        it.id != taskToDelete.id
                    }
                }
            )
        }

        composable("add") {

            AddTaskScreen(

                onSave = { title ->

                    Log.d(
                        "StudentTasks",
                        "Adding task: $title"
                    )

                    val task = Task(
                        id = (tasks.maxOfOrNull { it.id } ?: 0) + 1,
                        title = title
                    )

                    tasks = tasks + task

                    navController.popBackStack()
                },

                onCancel = {
                    navController.popBackStack()
                }
            )
        }

        composable("about") {
            AboutScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
fun HomeScreen(
    tasks: List<Task>,
    onAddTask: () -> Unit,
    onAbout: () -> Unit,
    onCompletedChange: (Task, Boolean) -> Unit,
    onDelete: (Task) -> Unit
) {

    val completedCount = tasks.count {
        it.isCompleted
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Student Tasks",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            "$completedCount of ${tasks.size} completed"
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = onAddTask
            ) {
                Text("Add Task")
            }

            OutlinedButton(
                onClick = onAbout
            ) {
                Text("About")
            }
        }

        if (tasks.isEmpty()) {

            Text("No tasks yet.")

        } else {

            TaskList(
                tasks = tasks,
                onCompletedChange = onCompletedChange,
                onDelete = onDelete,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun AddTaskScreen(
    onSave: (String) -> Unit,
    onCancel: () -> Unit
) {

    var title by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Add Task",
            style = MaterialTheme.typography.headlineLarge
        )

        OutlinedTextField(
            value = title,
            onValueChange = { newValue ->
                title = newValue
            },
            label = {
                Text("Task title")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = {
                    onSave(title.trim())
                },
                enabled = title.isNotBlank()
            ) {
                Text("Save")
            }

            OutlinedButton(
                onClick = onCancel
            ) {
                Text("Cancel")
            }
        }
    }
}

@Composable
fun AboutScreen(
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Student Tasks",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            "Introductory Kotlin/Android application"
        )

        Button(
            onClick = onBack
        ) {
            Text("Back")
        }
    }
}

@Composable
fun TaskList(
    tasks: List<Task>,
    onCompletedChange: (Task, Boolean) -> Unit,
    onDelete: (Task) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(
            items = tasks,
            key = { task -> task.id }
        ) { task ->

            TaskRow(
                task = task,

                onCompletedChange = { isCompleted ->
                    onCompletedChange(task, isCompleted)
                },

                onDelete = {
                    onDelete(task)
                }
            )
        }
    }
}

@Composable
fun TaskRow(
    task: Task,
    onCompletedChange: (Boolean) -> Unit,
    onDelete: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = onCompletedChange
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f)
        ) {

            Text(
                text = task.title,
                textDecoration =
                    if (task.isCompleted)
                        TextDecoration.LineThrough
                    else
                        TextDecoration.None
            )

            Text(
                text = task.priority.name,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedButton(
            onClick = onDelete
        ) {
            Text("Delete")
        }
    }
}