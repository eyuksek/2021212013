package com.example.abapapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "eyuksekabapapp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Topics (name TEXT, details TEXT, videoId TEXT)");
        db.execSQL("INSERT INTO Topics VALUES ('Temel Giriş ve Veri Türleri', 'Bu bölümde ABAP Programlama dilinde temel veri türleri anlatılacaktır.', " +
                "'rSulANKAINU')");
        db.execSQL("INSERT INTO Topics VALUES ('Matematiksel İşlemler, Koşul ve Döngüler', 'Matematik işlemleri, koşullar(If,Else vb.) ve döngüler(Do,While vb.) detaylı açıklanacaktır.', " +
                "'rUGdDY53W08')");
        db.execSQL("INSERT INTO Topics VALUES ('Table, Data Element ve Domain', 'Table, data element ve domain yapıları, nasıl oluşturulacakları ve mantıkları açıklanmaktadır.', " +
                "'ctNj0wZ2yMk')");
        db.execSQL("INSERT INTO Topics VALUES ('OPEN SQL Komutları', 'ABAP Programlama dilinde OPEN SQL komutları detaylı şekilde incelenecektir.', " +
                "'WUM8AvTgL3g')");
        db.execSQL("INSERT INTO Topics VALUES ('Kullanıcı Veri Girişleri', 'Kullanıcıdan veri girişi nasıl alınır ve form yapıları nelerdir anlatılacaktır.', " +
                "'fHqwXbz7n8Y')");
    }
    //videolar burak kocaaslan youtube kanalından alınmıştır
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public ArrayList<String> getTopics() {
        ArrayList<String> topics = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM Topics", null);
        while (cursor.moveToNext()) {
            topics.add(cursor.getString(0));
        }
        cursor.close();
        return topics;
    }

    public String getTopicDetails(String topic) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT details FROM Topics WHERE name = ?", new String[]{topic});
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        cursor.close();
        return "Bilgi bulunamadı.";
    }

    public String getTopicVideoId(String topic) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT videoId FROM Topics WHERE name = ?", new String[]{topic});
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        cursor.close();
        return "dQw4w9WgXcQ"; // Varsayılan video ID
    }
}
