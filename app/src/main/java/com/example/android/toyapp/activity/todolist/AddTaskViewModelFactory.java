package com.example.android.toyapp.activity.todolist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.toyapp.activity.todolist.database.TaskDao;

public class AddTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    public AddTaskViewModelFactory(TaskDao dao, int taskId) {
        this.mDao = dao;
        this.mTaskId = taskId;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTaskViewModel(mDao, mTaskId);
    }

    private TaskDao mDao;
    private final int mTaskId;
}