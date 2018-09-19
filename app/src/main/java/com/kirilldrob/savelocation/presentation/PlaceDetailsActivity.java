package com.kirilldrob.savelocation.presentation;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirilldrob.h7fragments.R;
import com.kirilldrob.savelocation.db.Note;


public class PlaceDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_ADDRESS = "addres";
    private static final String EXTRA_TITLE = "tittle";
    private static final String EXTRA_LONG ="long";
    private static final String EXTRA_LAT ="lat";
    private static final String EXTRA_TIME ="time";
    public  TextView tvTime;
    public  TextView tvAddress;
    public  TextView tvTittle;
    public  TextView tvLong;
    public  TextView tvLat;

    public static void start(@NonNull Context context, @NonNull Note note) {
        Intent intent = new Intent(context, PlaceDetailsActivity.class);
        intent.putExtra(EXTRA_ADDRESS, note.getAddress());
        intent.putExtra(EXTRA_TITLE, note.getTitle()); // опция!
        intent.putExtra(EXTRA_LAT, note.getLatitude());
        intent.putExtra(EXTRA_LONG, note.getLongitude());
        intent.putExtra(EXTRA_TIME, note.getTimestamp());  /// а надо ли?


        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //--- Update Screen
        tvTime = findViewById(R.id.tvD_time);
        tvAddress = findViewById(R.id.tvD_address);
        tvTittle = findViewById(R.id.tvD_tittle);
        tvLong = findViewById(R.id.tvD_longitude);
        tvLat = findViewById(R.id.tvD_latitude);

        Intent intent = getIntent();

        tvAddress.setText(intent.getStringExtra(EXTRA_ADDRESS));
        tvTime.setText(intent.getStringExtra(EXTRA_TIME)); // TODO: проверить отображение в нужной Локали.
        tvTime.setText(DateFormat.format("dd-MM-yyyy/HH:mm",(long) intent.getLongExtra(EXTRA_TIME,0)).toString());
        tvLat.setText(String.valueOf( intent.getDoubleExtra(EXTRA_LAT,0)));
        tvLong.setText(String.valueOf( intent.getDoubleExtra(EXTRA_LONG,0)));
            //1. Опция: свое название(описание) места( Пример: "здесь очень вкусная пицца")
        String tittle=intent.getStringExtra(EXTRA_TITLE);
        if (tittle!=null) tvTittle.setText(tittle);
        //--- end Update Screen  ...

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle("Place from the History");
        }



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
