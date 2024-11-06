package com.example.nutodo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow

data class Task(val description: String, var isCompleted: Boolean = false)

class NuToDoViewModel : ViewModel() {

    var tasks = mutableStateOf(listOf<Task>())
        private set

    var newTaskDescription by mutableStateOf("")

    // Function to add a new task
    fun addTask() {
        if (newTaskDescription.isNotBlank()) {
            tasks.value = tasks.value + Task(newTaskDescription)
            newTaskDescription = ""
        }
    }

    // Function to toggle the completion status of a task
    fun toggleTaskCompletion(index: Int) {
        tasks.value = tasks.value.mapIndexed { i, task ->
            if (i == index) task.copy(isCompleted = !task.isCompleted) else task
        }
    }
}
