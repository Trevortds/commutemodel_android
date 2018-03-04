package com.etymachine.commutemodel.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by trevor on 3/3/18.
 */

@Dao
public interface TimeEntryDao {
    @Query("SELECT * FROM TimeEntry")
    List<TimeEntry> getAll();

    @Query("SELECT * FROM TimeEntry WHERE entryId IN (:entryIds)")
    List<TimeEntry> loadAllByIds(int[] entryIds);

    @Query("SELECT * FROM TimeEntry WHERE day_of_week LIKE :dow "
            + "")
    TimeEntry findByDayOfWeek(String dow);

    @Insert
    void insertAll(TimeEntry... timeEntries);

    @Delete
    void delete(TimeEntry timeEntries);

}
