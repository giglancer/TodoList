package com.example.sampletodo.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface  TaskDAO {
    @Insert
    fun insertTask(taskEntity: TaskEntity)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Query("select * from TaskEntity")
    fun get(): LiveData<List<TaskEntity>>

    @Update
    fun updateTask(taskEntity: TaskEntity)
}