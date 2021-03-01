package com.example.proect.repository.database;

import android.content.Context;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.proect.R;
import com.example.proect.repository.database.entity.Perfume;

public class AppData {
    public static final String ID="id";

    private static AppData getInstance;

    public static AppData getGetInstance(Context context) {
        if (getInstance==null)
            getInstance = new AppData(context);
        return getInstance;
    }
    RequestManager glide;
    public  AppDatabase db;

    public AppData(Context context) {
         db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
         glide= Glide.with(context);
    }
    public LiveData<Perfume> getPerfumeInId(int id)
    {
        return db.perfumeDao().findById(id);
    }

    public void getImage(String URL, ImageView imageView){
        glide.load(URL).placeholder(R.drawable.ic_launcher_background).into(imageView);
    }
}
