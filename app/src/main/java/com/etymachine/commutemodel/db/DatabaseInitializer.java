package com.etymachine.commutemodel.db;

/**
 * Created by trevor on 3/3/18.
 */


import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;


import java.sql.Time;
import java.util.List;

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void populateAsync(@NonNull final AppDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    public static void getAllAsync(@NonNull final AppDatabase db, GetAllAsync.AsyncResponse delegate) {
        GetAllAsync task = new GetAllAsync(db, delegate);
        task.execute();
    }

    private static TimeEntry addTimeEntry(final AppDatabase db, TimeEntry timeEntry) {
        db.timeEntryDao().insertAll(timeEntry);
        return timeEntry;
    }

    private static void populateWithTestData(AppDatabase db) {
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setDayOfWeek("Monday");
        timeEntry.setDepartureTime(9.5);
        timeEntry.setDuration(25);
        addTimeEntry(db, timeEntry);

        List<TimeEntry> entryList = db.timeEntryDao().getAll();
        Log.d(DatabaseInitializer.TAG, "Rows Count: " + entryList.size());
    }

    private static List<TimeEntry> getall(AppDatabase db) {
        return db.timeEntryDao().getAll();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }

    public static class GetAllAsync extends AsyncTask<Void, Void, List<TimeEntry>> {

        public interface AsyncResponse {
            void processFinish(List<TimeEntry> result);
        }

        private final AppDatabase mDb;
        public AsyncResponse delegate = null;

        GetAllAsync(AppDatabase db, AsyncResponse delegate) {
            mDb = db;
            this.delegate = delegate;
        }

        @Override
        protected List<TimeEntry> doInBackground(final Void... params) {
            return getall(mDb);

        }

        @Override
        protected  void onPostExecute(List<TimeEntry> result){
            this.delegate.processFinish(result);
        }



    }
}