package com.example.sampletodo.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val task: String,
    var isChecked: Boolean
        )