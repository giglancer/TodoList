package com.example.sampletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampletodo.databinding.ActivityMainBinding
import com.example.sampletodo.room.TaskEntity

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: TaskAdapter
    private lateinit var taskList: List<TaskEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val viewModel: MainActivityViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.taskList.observe(this, {
            Log.d("task",it.toString())
            taskList = it

            val recyclerView = binding.taskRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)

            mAdapter = TaskAdapter(taskList, viewModel)
            recyclerView.adapter = mAdapter
        })
    }
}