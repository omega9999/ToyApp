package com.example.android.toyapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.toyapp.R;

public class AsynkTaskCursorActivity extends AppCompatActivity {
    // The current state of the app
    private int mCurrentState;

    private Cursor mData;
    private Button mButton;

    // This state is when the word definition is hidden and clicking the button will therefore
    // show the definition
    private final int STATE_HIDDEN = 0;

    // This state is when the word definition is shown and clicking the button will therefore
    // advance the app to the next word
    private final int STATE_SHOWN = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynk_task_cursor);

        // Get the views
        mButton = findViewById(R.id.button_next);

        new WordFetchTask(this).execute();
    }

    /**
     * This is called from the layout when the button is clicked and switches between the
     * two app states.
     *
     * @param view The view that was clicked
     */
    public void onButtonClick(View view) {

        // Either show the definition of the current word, or if the definition is currently
        // showing, move to the next word.
        switch (mCurrentState) {
            case STATE_HIDDEN:
                showDefinition();
                break;
            case STATE_SHOWN:
                nextWord();
                break;
        }
    }

    public void nextWord() {

        // Change button text
        mButton.setText(getString(R.string.show_definition));

        mCurrentState = STATE_HIDDEN;

    }

    public void showDefinition() {

        // Change button text
        mButton.setText(getString(R.string.next_word));

        mCurrentState = STATE_SHOWN;

    }

    private static class WordFetchTask extends AsyncTask<Void, Void, Cursor>{
        private AsynkTaskCursorActivity activity;

        private WordFetchTask(AsynkTaskCursorActivity activity) {
            this.activity = activity;
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            ContentResolver resolver = this.activity.getContentResolver();

            // Call the query method on the resolver with the correct Uri from the contract class
            Cursor cursor = resolver.query(DroidTermsExampleContract.CONTENT_URI,
                    null, null, null, null);
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            activity.mData = cursor;
        }
    }
    // TODO (2) In the doInBackground method, write the code to access the DroidTermsExample
    // provider and return the Cursor object
    // TODO (4) In the onPostExecute method, store the Cursor object in mData

}
