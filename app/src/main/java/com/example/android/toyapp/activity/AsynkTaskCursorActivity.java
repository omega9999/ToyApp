package com.example.android.toyapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.toyapp.R;

public class AsynkTaskCursorActivity extends AppCompatActivity {
    // The current state of the app
    private int mCurrentState;

    private Cursor mData;
    private Button mButton;
    private int mDefCol, mWordCol;
    private TextView mWordTextView, mDefinitionTextView;

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

        mWordTextView = findViewById(R.id.text_view_word);
        mDefinitionTextView = findViewById(R.id.text_view_definition);
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

        if (mData != null) {
            // Move to the next position in the cursor, if there isn't one, move to the first
            if (!mData.moveToNext()) {
                mData.moveToFirst();
            }
            // Hide the definition TextView
            mDefinitionTextView.setVisibility(View.INVISIBLE);

            // Change button text
            mButton.setText(getString(R.string.show_definition));

            // Get the next word
            mWordTextView.setText(mData.getString(mWordCol));
            mDefinitionTextView.setText(mData.getString(mDefCol));

            mCurrentState = STATE_HIDDEN;
        }

    }

    public void showDefinition() {
        if (mData != null) {
            // Show the definition TextView
            mDefinitionTextView.setVisibility(View.VISIBLE);
            // Change button text
            mButton.setText(getString(R.string.next_word));

            mCurrentState = STATE_SHOWN;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mData != null){
            mData.close();
        }
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
            activity.mDefCol = cursor.getColumnIndex(DroidTermsExampleContract.COLUMN_DEFINITION);
            activity.mWordCol = cursor.getColumnIndex(DroidTermsExampleContract.COLUMN_WORD);

            // Set the initial state
            activity.nextWord();
        }
    }
}
