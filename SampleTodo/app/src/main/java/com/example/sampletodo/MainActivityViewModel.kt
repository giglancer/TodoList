package com.example.sampletodo

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.*
import com.example.sampletodo.repository.TaskRepository
import com.example.sampletodo.room.TaskDatabase
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.sampletodo.room.TaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(application: Application): AndroidViewModel(application) {
  private val repository: TaskRepository

  var editText = MutableLiveData<String>()
  var enable = MediatorLiveData<Boolean>()

  var checkBoxIsChecked = MutableLiveData<Boolean>()

  init {
    val taskDB = TaskDatabase.getDatabase(application).taskDAO()
    repository = TaskRepository(taskDB)

    // 文字が空だったら追加ボタンを非活性
    enable.addSource(editText) {
      if (editText.value == "") {
        enable.postValue(false)
      } else {
        enable.postValue(true)
      }
    }
  }
  var taskList = repository.getTask()


  fun onClick() {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        var taskEntity = TaskEntity(
          id = 0,
          task = editText.value.toString(),
          isChecked = false
        )
        repository.insertTask(taskEntity)
      }
      withContext(Dispatchers.Main) {
        editText.value = ""
      }
    }
  }
  fun updateTask(id: Int, task: String, isChecked: Boolean) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        var taskEntity = TaskEntity(
          id = id,
          task = task,
          isChecked = isChecked
        )
        repository.updateTask(taskEntity)
        Log.d("checked", repository.getTask().toString())
      }
    }
  }
  fun deleteTask(id: Int, task: String, isChecked: Boolean) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        var taskEntity = TaskEntity(
          id = id,
          task = task,
          isChecked = isChecked
        )
        repository.deleteTask(taskEntity)
      }
    }
  }
}