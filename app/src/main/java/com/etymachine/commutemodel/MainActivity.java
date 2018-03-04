package com.etymachine.commutemodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.etymachine.commutemodel.db.AppDatabase;
import com.etymachine.commutemodel.db.DatabaseInitializer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void populateTestData(View view) {
        DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(this));
        private ProgressBar spinner;
        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        Log.v("DEBUG", AppDatabase.getAppDatabase(this).timeEntryDao().getAll().toString());
        spinner.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
