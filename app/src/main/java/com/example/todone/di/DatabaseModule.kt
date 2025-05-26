package com.example.todone.di

import android.content.Context
import com.example.todone.model.TodoDao
import com.example.todone.model.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase =
        TodoDatabase.getDatabase(context)

    @Provides
    fun provideTodoDao(database: TodoDatabase): TodoDao = database.todoDao()
}

