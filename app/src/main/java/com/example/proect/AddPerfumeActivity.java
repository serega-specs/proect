package com.example.proect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.proect.databinding.ActivityAddPerfumeBinding;
import com.example.proect.databinding.ActivityPosledneeBinding;
import com.example.proect.presentation.activities.main.MainActivity;
import com.example.proect.repository.database.AppData;
import com.example.proect.repository.database.entity.Perfume;

public class AddPerfumeActivity extends AppCompatActivity {

    AppData data;
    ActivityAddPerfumeBinding binding;
    Perfume perfumes;
    int id =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPerfumeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        data = AppData.getGetInstance(getApplicationContext());
        id=getIntent().getIntExtra(data.ID,-1);
        if(id != -1)
        {
            data.db.perfumeDao().findById(id).observe(this, new Observer<Perfume>() {
                @Override
                public void onChanged(Perfume perfume) {
                    perfumes = perfume;
                    binding.Name.setText(perfume.title);
                    binding.Price.setText(perfume.getPrice());
                    binding.Description.setText(perfume.description);
                    binding.URL.setText(perfume.URLPhoto);
                    data.getImage(perfume.URLPhoto,binding.imageView5);
                }
            });
        }
        else perfumes=new Perfume();
        binding.URL.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                data.getImage(binding.URL.getText().toString(),binding.imageView5);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void AddPerfume(View view) {
        perfumes.title= binding.Name.getText().toString();
        perfumes.price = Double.parseDouble(binding.Price.getText().toString());
        perfumes.URLPhoto=binding.URL.getText().toString();
        perfumes.description=binding.Description.getText().toString();
        data.db.perfumeDao().insert(perfumes);
        Intent intent = new Intent(AddPerfumeActivity.this, MainActivity.class);
        intent.putExtra(data.ID,id);
        startActivity(intent);
    }
}