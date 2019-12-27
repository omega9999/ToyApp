package com.example.android.toyapp.activity.todolist;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.toyapp.activity.todolist.database.AppDatabase;
import com.example.android.toyapp.activity.todolist.database.TaskEntry;

import java.util.List;

public class ToDoListViewModel extends AndroidViewModel {
    public ToDoListViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        this.mTasks = AppDatabase.getInstance(application).taskDao().loadAllTasks();
    }

    public LiveData<List<TaskEntry>> getTasks() {
        return this.mTasks;
    }

    private final LiveData<List<TaskEntry>> mTasks;
    private static final String TAG = ToDoListViewModel.class.getSimpleName();
}
