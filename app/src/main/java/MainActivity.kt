package com.example.nutodo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoApp()
        }
    }
}

@Composable
fun ToDoApp(viewModel: NuToDoViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = viewModel.newTaskDescription,
                onValueChange = { viewModel.newTaskDescription = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = { Text("Enter a task") }
            )
            Button(
                modifier = Modifier
                    .padding(8.dp),
                onClick = { viewModel.addTask() }
            ) {
                Text("Add")
            }
        }

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(viewModel.tasks.value.size) { index ->
                val task = viewModel.tasks.value[index]
                TaskItem(task = task, onTaskClick = { viewModel.toggleTaskCompletion(index) })
                Divider(modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onTaskClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onTaskClick)
    ) {
        Text(
            text = task.description,
            style = TextStyle(
                color = if (task.isCompleted) Color.Gray else Color.Black,
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else TextDecoration.None
            ),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NUToDo() {
    ToDoApp(viewModel = NuToDoViewModel())
}
