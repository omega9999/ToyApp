package com.example.android.toyapp.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.android.toyapp.R;

import java.util.ArrayList;
import java.util.List;

public class CustomRadioButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_radio_button);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onResume() {
        super.onResume();

        final List<RadioButton> list = new ArrayList<>();
        list.add(findViewById(R.id.radio1));
        list.add(findViewById(R.id.radio2));
        list.add(findViewById(R.id.radio3));
        list.add(findViewById(R.id.radio4));

        View view = findViewById(R.id.textView2);
        view.setOnClickListener(v -> {
            // MotionEvent.ACTION_DOWN
            Toast.makeText(this, "click consumed", Toast.LENGTH_SHORT).show();
        });

        //list.get(2).setOnClickListener(v -> Toast.makeText(CustomRadioButtonActivity.this, "OnClickListener: Click on radio button disabled", Toast.LENGTH_SHORT).show());
        //disable(list.get(2));
        hidden(list.get(1));
    }


    //FIXME implement better: greyscale and message when clicked, but do not change state
    @SuppressLint("ClickableViewAccessibility")
    private void disable(@NonNull final RadioButton radioButton) {
        final ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);  //0 means grayscale
        final ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
        radioButton.setEnabled(false);
        //radioButton.getBackground().setColorFilter(cf);
        //radioButton.setOnClickListener(v -> Toast.makeText(CustomRadioButtonActivity.this, "OnClickListener: Click on radio button disabled", Toast.LENGTH_SHORT).show());


    }


    private void hidden(@NonNull final View radioButton) {
        radioButton.setVisibility(View.GONE);
    }
}
