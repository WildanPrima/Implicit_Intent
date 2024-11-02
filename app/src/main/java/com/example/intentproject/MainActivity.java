package com.example.intentproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Adjust for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Mendapatkan EditText
        EditText editTextSearch = findViewById(R.id.editTextSearch);

        // Menangani klik tombol
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mendapatkan teks dari EditText
                String searchQuery = editTextSearch.getText().toString().trim();

                // Cek apakah EditText kosong
                if (searchQuery.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Masukkan kata kunci pencarian", Toast.LENGTH_SHORT).show();
                    return; // Hentikan eksekusi jika kosong
                }

                // Membuka aplikasi Shopee atau browser jika Shopee tidak terinstal
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://shopee.co.id/search?keyword=" + Uri.encode(searchQuery)));
                // Hanya set package jika Anda ingin mengarah ke aplikasi Shopee saja
                intent.setPackage("com.shopee.app"); // Coba buka aplikasi Shopee

                // Cek apakah aplikasi Shopee terinstal
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent); // Buka aplikasi Shopee jika ditemukan
                } else {
                    // Jika Shopee tidak terinstal, buka di browser
                    intent.setPackage(null); // Set package ke null untuk membuka di browser
                    startActivity(intent);
                }
            }
        });
    }
}
