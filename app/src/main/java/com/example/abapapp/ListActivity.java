package com.example.abapapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> topicList;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listView = findViewById(R.id.Abaplistview);
        dbHelper = new DBHelper(this);

        topicList = dbHelper.getTopics();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, topicList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String topic = topicList.get(position);
            String details = dbHelper.getTopicDetails(topic);
            String videoId = dbHelper.getTopicVideoId(topic);

            Log.d("ListActivity", "Tıklanan Konu: " + topic);

            Intent intent = new Intent(ListActivity.this, DetailActivity.class);
            intent.putExtra("topic", topic);
            intent.putExtra("details", details);
            intent.putExtra("videoId", videoId);
            startActivity(intent);
        });
    }
}