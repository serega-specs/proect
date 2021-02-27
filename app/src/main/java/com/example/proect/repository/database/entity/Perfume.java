package com.example.proect.repository.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Perfume {
    @PrimaryKey(autoGenerate = true)
    public int PerfumeID;

    public String URLPhoto;
    public String description;
    public String title;
    public double price;




}
