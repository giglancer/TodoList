package com.example.sampletodo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sampletodo.room.TaskDAO
import com.example.sampletodo.room.TaskDatabase
import com.example.sampletodo.room.TaskEntity

class TaskRepository(private val taskDAO: TaskDAO) {

    fun insertTask(taskEntity: TaskEntity) =
        taskDAO.insertTask(taskEntity)

    fun deleteTask(taskEntity: TaskEntity) =
        taskDAO.deleteTask(taskEntity)

    fun getTask(): LiveData<List<TaskEntity>> =
        taskDAO.get()

    fun updateTask(taskEntity: TaskEntity) =
        taskDAO.updateTask(taskEntity)
}