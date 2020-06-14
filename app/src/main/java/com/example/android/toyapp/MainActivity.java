package com.example.android.toyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Class<? extends Activity>> mActivities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivities = ExerciseUtils.getIntents();

        final RecyclerView mNumbersList = findViewById(R.id.activities);
        mNumbersList.setLayoutManager(new LinearLayoutManager(this));
        mNumbersList.setHasFixedSize(true);
        mNumbersList.setAdapter(new MyAdapter());
    }

    public void onListItemClick(int index) {
        startActivity(new Intent(this, mActivities.get(index)));
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ActivityViewHolder> {

        public MyAdapter() {
        }

        @NonNull
        @Override
        public ActivityViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            Context context = viewGroup.getContext();
            int layoutIdForListItem = R.layout.activity_main_detail;
            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

            return new ActivityViewHolder(view);
        }


        @Override
        public void onBindViewHolder(ActivityViewHolder holder, int position) {
            holder.viewHolderIndex.setText(String.format("Activity: %1$s" ,mActivities.get(position).getSimpleName()));
            if (position % 2 == 0){
                holder.root.setBackgroundColor(Color.parseColor("#EBEBEB"));
            }
        }

        @Override
        public int getItemCount() {
            return mActivities.size();
        }

        public class ActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView viewHolderIndex;
            View root;

            ActivityViewHolder(View itemView) {
                super(itemView);
                root = itemView;

                viewHolderIndex = itemView.findViewById(R.id.activity_holder_instance);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int clickedPosition = getAdapterPosition();
                MainActivity.this.onListItemClick(clickedPosition);
                Log.logStacktrace(TAG,"ActivityViewHolder.onClick");
            }

        }
    }
}
