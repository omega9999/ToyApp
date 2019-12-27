package com.example.android.toyapp.activity.todolist;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.toyapp.activity.todolist.database.TaskDao;
import com.example.android.toyapp.activity.todolist.database.TaskEntry;

import java.util.List;

public class ToDoListRepository {
    private static final Object LOCK = new Object();
    private volatile static ToDoListRepository sInstance;

    private ToDoListRepository(@NonNull final TaskDao dao, @NonNull final AppExecutors executors) {
        if (sInstance != null){
            throw new RuntimeException("Use getInstance() method");
        }
        this.mDao = dao;
        this.mAppExecutors = executors;
    }
    public synchronized static ToDoListRepository getInstance(@NonNull final TaskDao dao,  @NonNull final AppExecutors executors) {
        Log.d(TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new ToDoListRepository(dao, executors);
                Log.d(TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    public interface SingleTaskCallBack {
        void callBack(@Nullable TaskEntry task);
    }
    @SuppressWarnings("WeakerAccess")
    public void getTaskData(@NonNull final FragmentActivity activity, final int taskId, @Nullable final SingleTaskCallBack callBack){
        final AddTaskViewModel viewModel = ViewModelProviders.of(activity, new AddTaskViewModelFactory(this.mDao, taskId)).get(AddTaskViewModel.class);
        viewModel.getTask().observe(activity, new Observer<TaskEntry>() {
            @Override
            public void onChanged(@Nullable TaskEntry task) {
                viewModel.getTask().removeObserver(this);
                Log.d(TAG, "Receiving view model update from LiveData");
                if (callBack != null) {
                    callBack.callBack(task);
                }
            }
        });
    }

    public interface TasksCallBack {
        void callBack(@Nullable List<TaskEntry> tasks);
    }
    public void getTasksData(@NonNull final FragmentActivity activity, @NonNull final TasksCallBack callBack){
        final ToDoListViewModel model = ViewModelProviders.of(activity).get(ToDoListViewModel.class);
        model.getTasks().observe(activity, taskEntries -> {
            Log.d(TAG, "Receiving ViewModel update from LiveData");
            callBack.callBack(taskEntries);
        });
    }

    @SuppressWarnings("WeakerAccess")
    public void save(@NonNull final FragmentActivity activity, @NonNull final TaskEntry taskEntry, @Nullable final Runnable callBack){
        getTaskData(activity, taskEntry.getId(), (taskEntryFromDb)-> mAppExecutors.diskIO().execute(() -> {
            if (taskEntryFromDb == null) {
                mDao.insertTask(taskEntry);
            }
            else{
                mDao.updateTask(taskEntry);
            }
            if (callBack != null){
                mAppExecutors.mainThread().execute(callBack);
            }
        }));
    }

    public void delete(@NonNull final TaskEntry taskEntry, @Nullable final Runnable callBack){
        mAppExecutors.diskIO().execute(() -> {
            mDao.deleteTask(taskEntry);
            if (callBack != null){
                mAppExecutors.mainThread().execute(callBack);
            }
        });
    }

    private TaskDao mDao;
    private AppExecutors mAppExecutors;
    private static final String TAG = ToDoListRepository.class.getSimpleName();
}
