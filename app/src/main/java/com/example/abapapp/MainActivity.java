package com.example.abapapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("ABAP Programlama Diline Giriş");

        ImageView dondurImageView = findViewById(R.id.imageView4);
        TextView loadingText = findViewById(R.id.Text1);

        // Döndürme animasyonu
        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setDuration(2000);
        rotate.setRepeatCount(Animation.INFINITE);
        dondurImageView.startAnimation(rotate);


        // 3 saniye sonra internet kontrolü
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isInternetAvailable()) {

                    // MenuActivity'ye geçiş yap
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(intent);
                    finish(); // MainActivity'yi kapat
                } else {
                    Toast.makeText(MainActivity.this,
                            "İnternet bağlantısı yok!",
                            Toast.LENGTH_LONG).show();
                }

                // Animasyonu ve loading yazısını durdur
                dondurImageView.clearAnimation();
                loadingText.setVisibility(TextView.INVISIBLE);
            }
        }, 3000); // 5 saniye
    }

    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
}