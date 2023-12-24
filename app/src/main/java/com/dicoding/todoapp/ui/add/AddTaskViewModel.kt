package com.dicoding.todoapp.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.data.TaskRepository
import kotlinx.coroutines.launch

class AddTaskViewModel(private val addRepository: TaskRepository): ViewModel() {

    fun newData(newData: Task) {
        viewModelScope.launch {
            addRepository.insertTask(newData)
        }
    }
}