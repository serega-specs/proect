package com.example.proect.presentation.activities.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proect.AddPerfumeActivity;
import com.example.proect.PosledneeActivity;
import com.example.proect.databinding.ActivityMainBinding;
import com.example.proect.databinding.ItemParBinding;
import com.example.proect.repository.database.AppData;
import com.example.proect.repository.database.entity.Perfume;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    List<Perfume> perfumeList;
    PerfumeAdapter adapter;
    AppData data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new PerfumeAdapter();
        perfumeList = new ArrayList<>();
        binding.recyclerView.setAdapter(adapter);
        data=AppData.getGetInstance(getApplicationContext());
        data.db.perfumeDao().getAllService().observe(this, new Observer<List<Perfume>>() {
            @Override
            public void onChanged(List<Perfume> perfumes) {
                perfumeList=perfumes;
                adapter.notifyDataSetChanged();
            }
        });
//        add();
    }
    public void add()
    {
        Perfume perfumes=new Perfume();
        perfumes.title="Что-то";
        perfumes.description="Какое-то описание";
        perfumes.price=123;
        perfumes.URLPhoto="https://static9.depositphotos.com/1364311/1228/i/600/depositphotos_12283116-stock-photo-bottle-of-perfume.jpg";
        data.db.perfumeDao().insert(perfumes);
    }

    private class PerfumeAdapter extends RecyclerView.Adapter<PerfumeAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            return new ViewHolder(ItemParBinding.inflate(inflater,parent,false));

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Perfume perfume = perfumeList.get(position);
            holder.binding.textView3.setText(perfume.title);
            holder.binding.textView4.setText(perfume.description);
            holder.binding.textView5.setText(perfume.getPrice());
            data.getImage(perfume.URLPhoto,holder.binding.imageView3);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, PosledneeActivity.class);
                    intent.putExtra(data.ID,perfume.PerfumeID);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return perfumeList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private ItemParBinding binding;
            public ViewHolder(@NonNull ItemParBinding itemView) {
                super(itemView.getRoot());
                this.binding = itemView;
            }
        }
    }
    public void AddPerfume(View view) {
        Intent intent = new Intent(MainActivity.this, AddPerfumeActivity.class);
        startActivity(intent);
    }
}