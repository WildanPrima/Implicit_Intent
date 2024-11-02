package com.example.intentproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        // Menangani klik tombol
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuka aplikasi Shopee atau browser jika Shopee tidak terinstal
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://id.shp.ee/CT3J72x"));
                intent.setPackage("com.shopee.app"); // Coba buka aplikasi Shopee
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent); // Buka aplikasi Shopee jika ditemukan
                } else {
                    // Jika Shopee tidak terinstal, buka di browser
                    intent.setPackage(null);
                    startActivity(intent);
                }
            }
        });
    }
}
