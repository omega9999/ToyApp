package com.example.android.toyapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.toyapp.R;
import com.example.android.toyapp.activity.todolist.AddTaskActivity;
import com.example.android.toyapp.activity.todolist.AppExecutors;
import com.example.android.toyapp.activity.todolist.TaskAdapter;
import com.example.android.toyapp.activity.todolist.ToDoListRepository;
import com.example.android.toyapp.activity.todolist.database.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ToDoListActivity extends AppCompatActivity implements TaskAdapter.ItemClickListener {


    // Constant for logging
    private static final String TAG = ToDoListActivity.class.getSimpleName();
    // Member variables for the adapter and RecyclerView
    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;

    private ToDoListRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        mRepository = ToDoListRepository.getInstance(AppDatabase.getInstance(getApplicationContext()).taskDao(), AppExecutors.getInstance());

        // Set the RecyclerView to its corresponding view
        mRecyclerView = findViewById(R.id.recyclerViewTasks);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new TaskAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        /*
         Add a touch helper to the RecyclerView to recognize when a user swipes to delete an item.
         An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
         and uses callbacks to signal when a user is performing these actions.
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Here is where you'll implement swipe to delete
                mRepository.delete(mAdapter.getTasks().get(viewHolder.getAdapterPosition()), () -> Log.v(TAG, "Delete callback"));
            }
        }).attachToRecyclerView(mRecyclerView);

        /*
         Set the Floating Action Button (FAB) to its corresponding View.
         Attach an OnClickListener to it, so that when it's clicked, a new intent will be created
         to launch the AddTaskActivity.
         */
        FloatingActionButton fabButton = findViewById(R.id.fab);

        fabButton.setOnClickListener(view -> {
            // Create a new intent to start an AddTaskActivity
            Intent addTaskIntent = new Intent(ToDoListActivity.this, AddTaskActivity.class);
            startActivity(addTaskIntent);
        });
        setupViewModel();
    }

    /**
     * This method is called after this activity has been paused or restarted.
     * Often, this is after new data has been inserted through an AddTaskActivity,
     * so this re-queries the database data for any changes.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupViewModel() {
        mRepository.getTasksData(this, tasks -> mAdapter.setTasks(tasks));
    }

    @Override
    public void onItemClickListener(int itemId) {
        Intent addTaskIntent = new Intent(this, AddTaskActivity.class);
        addTaskIntent.putExtra(AddTaskActivity.EXTRA_TASK_ID, itemId);
        startActivity(addTaskIntent);
    }
}
