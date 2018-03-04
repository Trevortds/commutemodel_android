package com.etymachine.commutemodel.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by trevor on 3/3/18.
 */

@Entity
public class TimeEntry {

    @PrimaryKey(autoGenerate = true)
    private int entryId;

    @ColumnInfo(name = "day_of_week")
    private String dayOfWeek;

    @ColumnInfo(name = "departure_time")
    private Double departureTime;

    @ColumnInfo(name = "duration")
    private int duration;


    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Double getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Double departureTime) {
        this.departureTime = departureTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
