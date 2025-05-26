package com.example.todone.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todone.model.TodoDao
import com.example.todone.model.TodoListEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoDao: TodoDao
) : ViewModel() {
    val lists = todoDao.getAllLists().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addList(title: String) {
        viewModelScope.launch {
            todoDao.insertList(TodoListEntity(title = title))
        }
    }
}
