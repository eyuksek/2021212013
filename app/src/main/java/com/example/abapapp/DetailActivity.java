package com.example.abapapp;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class DetailActivity extends AppCompatActivity {

    private YouTubePlayerView Video;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = findViewById(R.id.TextView);
        Video = findViewById(R.id.Video);
        getLifecycle().addObserver(Video);

        String topic = getIntent().getStringExtra("topic");
        String details = getIntent().getStringExtra("details");
        String videoId = getIntent().getStringExtra("videoId");

        if (details != null) {
            textView.setText(details);
        } else {
            textView.setText("Bilgi bulunamadı.");
        }

        if (videoId != null) {
            loadYouTubeVideo(videoId);
        }
    }

    private void loadYouTubeVideo(String videoId) {
        Video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

    }
}