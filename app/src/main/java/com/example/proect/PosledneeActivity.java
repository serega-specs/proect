package com.example.proect;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.proect.databinding.ActivityPosledneeBinding;
import com.example.proect.repository.database.AppData;
import com.example.proect.repository.database.entity.Perfume;

public class PosledneeActivity extends AppCompatActivity {

    AppData data;
    ActivityPosledneeBinding binding;
    Perfume perfumes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPosledneeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int id = getIntent().getIntExtra(data.ID,0);
        data = AppData.getGetInstance(getApplicationContext());
        data.getPerfumeInId(id).observe(this, new Observer<Perfume>() {
            @Override
            public void onChanged(Perfume perfume) {
                if(perfume == null) finish();
                else {
                    perfumes = perfume;
                    binding.textView7.setText(perfumes.title);
                    binding.textView8.setText(perfumes.getPrice());
                    data.getImage(perfumes.URLPhoto, binding.imageView6);
                }
            }
        });
    }
    public void deletePerfume(View view) {
        data.db.perfumeDao().delete(perfumes);
    }
}