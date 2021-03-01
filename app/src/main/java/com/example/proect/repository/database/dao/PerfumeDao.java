package com.example.proect.repository.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.proect.repository.database.entity.Perfume;

import java.util.List;

@Dao
public interface PerfumeDao {
    @Query("Select * from Perfume")
    LiveData<List<Perfume>> getAllService();
    @Query("Select * from Perfume where PerfumeID=:id")
    LiveData<Perfume> findById(int id);

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    void insert(Perfume perfume);

    @Delete
    void delete(Perfume perfume);
}
