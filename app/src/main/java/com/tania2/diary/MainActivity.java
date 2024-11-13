package com.tania2.diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Catatan> kumpulan;
    private KumpulanAdapter kumpulanAdapter;
    private RecyclerView rvKumpulan;
    private Button btnAdd;
    private ActivityResultLauncher<Intent> addNoteLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi array list
        this.kumpulan = new ArrayList<>();
        //inisialiasasi adapter
        this.kumpulanAdapter = new KumpulanAdapter(this,this.kumpulan);
        //inisialisasi row view
        this.rvKumpulan = this.findViewById(R.id.rvKumpulan);

        this.rvKumpulan.setLayoutManager(new LinearLayoutManager(this));
        this.rvKumpulan.setAdapter(this.kumpulanAdapter);

        // Inisialisasi ActivityResultLauncher
        addNoteLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Catatan newCatatan = (Catatan) data.getSerializableExtra("newCatatan");
                            kumpulan.add(newCatatan);
                            kumpulanAdapter.notifyDataSetChanged(); // Update RecyclerView
                        }
                    }
                });

        // Tombol untuk menambah catatan baru
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                addNoteLauncher.launch(intent); // Menggunakan ActivityResultLauncher untuk memulai WriteDiaryActivity
            }
        });
        }
}