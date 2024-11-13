package com.tania2.diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WriteActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etKonten;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        // Menghubungkan EditText dengan ID dari layout
        etTitle = findViewById(R.id.etTitle);
        etKonten = findViewById(R.id.etKonten);
        btnSimpan = findViewById(R.id.btnSimpan);

        // Ketika tombol Save diklik
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String content = etKonten.getText().toString();

                // Membuat objek Catatan baru
                Catatan newCatatan = new Catatan(title, content);

                // Mengirim kembali data catatan ke MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("newCatatan", (newCatatan));
                setResult(RESULT_OK, resultIntent);
                finish();  // Kembali ke MainActivity
            }
        });
    }
}