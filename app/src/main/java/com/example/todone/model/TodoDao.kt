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

    // Get root lists (no parent)
    @Query("SELECT * FROM todo_lists WHERE parent_id IS NULL")
    fun getRootLists(): Flow<List<TodoListEntity>>

    // Get sub-lists for a parent list
    @Query("SELECT * FROM todo_lists WHERE parent_id = :parentId")
    fun getSubLists(parentId: Long): Flow<List<TodoListEntity>>

    @Delete
    suspend fun deleteList(list: TodoListEntity)
}

