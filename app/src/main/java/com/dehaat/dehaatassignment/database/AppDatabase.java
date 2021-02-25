package com.dehaat.dehaatassignment.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.dehaat.dehaatassignment.converters.Converters;
import com.dehaat.dehaatassignment.dao.AuthorDao;
import com.dehaat.dehaatassignment.model.Author;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Author.class},version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "dehaat_database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
    public abstract AuthorDao authorDao();

}
