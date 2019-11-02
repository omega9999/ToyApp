package com.example.android.toyapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.toyapp.R;

public class ExplicitIntentActivity extends AppCompatActivity {

    /* Fields that will store our EditText and Button */
    private EditText mNameEntry;
    private Button mDoSomethingCoolButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        /*
         * Using findViewById, we get a reference to our Button from xml. This allows us to
         * do things like set the onClickListener which determines what happens when the button
         * is clicked.
         */
        mDoSomethingCoolButton = findViewById(R.id.b_do_something_cool);
        mNameEntry = findViewById(R.id.et_text_entry);

        /* Setting an OnClickListener allows us to do something when this button is clicked. */
        mDoSomethingCoolButton.setOnClickListener(new View.OnClickListener() {

            /**
             * The onClick method is triggered when this button (mDoSomethingCoolButton) is clicked.
             *
             * @param v The view that is clicked. In this case, it's mDoSomethingCoolButton.
             */
            @Override
            public void onClick(View v) {
                final String str = mNameEntry.getText().toString();

                /*
                 * Storing the Context in a variable in this case is redundant since we could have
                 * just used "this" or "MainActivity.this" in the method call below. However, we
                 * wanted to demonstrate what parameter we were using "MainActivity.this" for as
                 * clear as possible.
                 */
                final Context context = ExplicitIntentActivity.this;

                /* This is the class that we want to start (and open) when the button is clicked. */
                final Class destinationActivity = ChildActivity.class;

                /*
                 * Here, we create the Intent that will start the Activity we specified above in
                 * the destinationActivity variable. The constructor for an Intent also requires a
                 * context, which we stored in the variable named "context".
                 */
                final Intent startChildActivityIntent = new Intent(context, destinationActivity);

                startChildActivityIntent.putExtra(ChildActivity.EXTRA_TEXT, str);

                /*
                 * Once the Intent has been created, we can use Activity's method, "startActivity"
                 * to start the ChildActivity.
                 */

                startActivity(startChildActivityIntent);
            }
        });
    }
}

