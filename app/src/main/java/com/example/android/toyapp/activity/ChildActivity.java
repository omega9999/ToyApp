package com.example.android.toyapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.toyapp.R;

public class ChildActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = Intent.EXTRA_TEXT;

    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        this.mTextView = findViewById(R.id.tv_display);

        final Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_TEXT)){
            final String str = intent.getStringExtra(EXTRA_TEXT);
            mTextView.setText(str);
        }
    }
}
