package com.example.sampletodo

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletodo.room.TaskEntity
import android.text.TextPaint
import android.widget.ImageButton
import android.widget.ImageView


class TaskAdapter(private val taskList: List<TaskEntity>, private val viewModel: MainActivityViewModel): RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val task = view.findViewById<CheckBox>(R.id.checkTaskBox)
        val deleteBtn = view.findViewById<ImageButton>(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskList = taskList[position]
        holder.task.text = taskList.task

        // タスク取り消し線
        val paint: TextPaint = holder.task.paint
        paint.flags = holder.task.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        if (taskList.isChecked) {
            holder.task.setTextColor(Color.LTGRAY)
            paint.isAntiAlias = true
            holder.task.isChecked = true
        } else {
            holder.task.setTextColor(Color.BLACK)
            paint.flags = 0
            holder.task.isChecked = false
        }
        
        holder.task.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                viewModel.updateTask(taskList.id, taskList.task, isChecked)
            } else {
                viewModel.updateTask(taskList.id, taskList.task, isChecked)
            }
        }
        //削除
        holder.deleteBtn.setOnClickListener {
            viewModel.deleteTask(taskList.id, taskList.task, taskList.isChecked)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}