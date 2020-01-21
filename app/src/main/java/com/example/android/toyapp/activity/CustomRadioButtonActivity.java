package com.example.android.toyapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.android.toyapp.R;

import java.util.ArrayList;
import java.util.List;

public class CustomRadioButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_radio_button);

    }

    @Override
    protected void onResume() {
        super.onResume();

        final List<View> list = new ArrayList<>();
        list.add(findViewById(R.id.radio1));
        list.add(findViewById(R.id.radio2));
        list.add(findViewById(R.id.radio3));
        list.add(findViewById(R.id.radio4));

        list.get(2).setOnClickListener(v -> Toast.makeText(CustomRadioButtonActivity.this, "OnClickListener: Click on radio button disabled", Toast.LENGTH_SHORT).show());
        //disable(list.get(2));
        hidden(list.get(1));
    }

    /*
    //TODO implement better: greyscale and message when clicked, but do not change state
    private void disable(@NonNull final RadioButton radioButton) {
        final ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);  //0 means grayscale
        final ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
        radioButton.setEnabled(false);
        radioButton.getBackground().setColorFilter(cf);
        //radioButton.setOnClickListener(v -> Toast.makeText(CustomRadioButtonActivity.this, "OnClickListener: Click on radio button disabled", Toast.LENGTH_SHORT).show());

        radioButton.setOnTouchListener((v,t) -> {Toast.makeText(CustomRadioButtonActivity.this, "OnTouchListener: Click on radio button disabled", Toast.LENGTH_SHORT).show();radioButton.performClick();return true;});
    }

     */

    private void hidden(@NonNull final View radioButton) {
        radioButton.setVisibility(View.GONE);
    }
}
