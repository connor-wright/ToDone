package com.example.todone.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    // TodoList operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: TodoListEntity): Long

    @Query("SELECT * FROM todo_lists")
    fun getAllLists(): Flow<List<TodoListEntity>>

    @Delete
    suspend fun deleteList(list: TodoListEntity)

    // TodoItem operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: TodoItemEntity): Long

    @Query("SELECT * FROM todo_items WHERE list_id = :listId")
    fun getItemsForList(listId: Long): Flow<List<TodoItemEntity>>

    @Delete
    suspend fun deleteItem(item: TodoItemEntity)
}

