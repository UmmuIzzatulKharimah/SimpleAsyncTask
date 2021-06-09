package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;
    private int s;

    SimpleAsyncTask(TextView tv, ProgressBar pb){
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pb);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        s = n * 500;
        mProgressBar.get().setMax(s);
        mProgressBar.get().setProgress(0,true);
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mTextView.get().setText("Sleep for "+ values[0] +" milliseconds");
        mProgressBar.get().incrementProgressBy(500);
    }

    @Override
    protected String doInBackground(Void... voids) {
        int n = s/500;

        try {
            for (int i = 1; i<=n;i++){
                Thread.sleep(500);
                publishProgress(i*500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

}
