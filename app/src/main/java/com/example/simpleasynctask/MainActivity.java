package com.example.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private static final String TEXT_STATE = "currentText";
    private static final String PROGRESS_BAR_STATE = "currentProgressBar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textview1);
        mProgressBar = findViewById(R.id.progressbar1);

        // Restore TextView if there is a savedInstanceState
        if(savedInstanceState!=null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            mProgressBar.setProgress(savedInstanceState.getInt(PROGRESS_BAR_STATE),true);
        }
    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);

        new SimpleAsyncTask(mTextView,mProgressBar).execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE,
                mTextView.getText().toString());
        outState.putInt(PROGRESS_BAR_STATE,
                mProgressBar.getProgress());
    }
}