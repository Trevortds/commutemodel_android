package com.etymachine.commutemodel;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.etymachine.commutemodel.db.AppDatabase;
import com.etymachine.commutemodel.db.DatabaseInitializer;
import com.etymachine.commutemodel.db.TimeEntry;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<TimeEntry> returnedResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void populateTestData(View view) {
        DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(this));
        final ProgressBar spinner;
        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        DatabaseInitializer.getAllAsync(AppDatabase.getAppDatabase(this),
                new DatabaseInitializer.GetAllAsync.AsyncResponse() {
                    @Override
                    public void processFinish(List<TimeEntry> result) {
                        spinner.setVisibility(View.GONE);
                        Log.v("DEBUG", result.toString());
                        returnedResult = result;
                    }
                });

    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
