package com.example.todone.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todone.model.TodoDao
import com.example.todone.model.TodoListEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoDao: TodoDao
) : ViewModel() {
    val rootLists = todoDao.getRootLists().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    private var selectedNode: TodoListEntity? = null
    private val _shouldShowBottomBar = MutableLiveData(false)
    var shouldShowBottomBar : LiveData<Boolean> = _shouldShowBottomBar

    fun onSelectedNodeChanged(node: TodoListEntity?) {
        selectedNode = node
        _shouldShowBottomBar.value = node != null
    }

    fun getSubLists(parentId: Long): Flow<List<TodoListEntity>> =
        todoDao.getSubLists(parentId)

    fun addList(title: String, parentId: Long? = null) {
        viewModelScope.launch {
            todoDao.insertList(TodoListEntity(title = title, parentId = parentId))
        }
    }

    fun deleteList() {
        viewModelScope.launch {
            selectedNode?.let {
                todoDao.deleteList(it)
            }
            onSelectedNodeChanged(null)
        }
    }

    fun editList(title: String) {
        viewModelScope.launch {
            selectedNode?.let {
                todoDao.insertList(it.copy(title = title))
            }
        }

        onSelectedNodeChanged(null)
    }

    fun getSelectedTitle(): String{
        return selectedNode?.title ?: ""
    }
}
