package com.example.proect.repository.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.proect.repository.database.dao.PerfumeDao;
import com.example.proect.repository.database.entity.Perfume;

@Database(entities = {Perfume.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PerfumeDao perfumeDao();
}