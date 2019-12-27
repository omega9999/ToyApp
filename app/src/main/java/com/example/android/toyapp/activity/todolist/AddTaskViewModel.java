package com.example.android.toyapp.activity.todolist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.toyapp.activity.todolist.database.AppDatabase;
import com.example.android.toyapp.activity.todolist.database.TaskEntry;

public class AddTaskViewModel extends ViewModel {
    public AddTaskViewModel(AppDatabase database, int taskId) {
        Log.d(TAG, "Actively retrieving a specific task from the DataBase");
        this.mTask = database.taskDao().loadTaskById(taskId);
    }

    public LiveData<TaskEntry> getTask() {
        return this.mTask;
    }

    private final LiveData<TaskEntry> mTask;
    private static final String TAG = AddTaskViewModel.class.getSimpleName();
}