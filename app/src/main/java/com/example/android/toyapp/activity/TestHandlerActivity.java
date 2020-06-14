package com.example.android.toyapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android.toyapp.R;
import com.example.android.toyapp.TestHandlers;

public class TestHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);
        new TestHandlers();
    }
}