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
    public  TextView tvLong;
    public  TextView tvLat;

    public static void start(@NonNull Context context, @NonNull Note note) {
        Intent intent = new Intent(context, PlaceDetailsActivity.class);
        intent.putExtra(EXTRA_ADDRESS, note.getAddress());
        intent.putExtra(EXTRA_TITLE, note.getTitle()); // опция!
        intent.putExtra(EXTRA_LAT, note.getLatitude());
        intent.putExtra(EXTRA_LONG, note.getLongitude());
       // intent.putExtra(EXTRA_TIME, ) а надо ли?


        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String fullName = intent.getStringExtra(EXTRA_ADDRESS);
        //tvTime.setText(DateFormat.format("dd-MM-yyyy/HH:mm",(long) mNoteList.get(position).getTimestamp() * 1000).toString());
        holder.tvAddress.setText(mNoteList.get(position).getAddress());
        holder.tvLat.setText(String.valueOf(mNoteList.get(position).getLatitude()));
        holder.tvLong.setText(String.valueOf(mNoteList.get(position).getLongitude()));
        intent.getExtra(EXTRA_ADDRESS, note.getAddress());
        intent.Extra(EXTRA_TITLE, note.getTitle()); // опция!
        intent.putExtra(EXTRA_LAT, note.getLatitude());
        intent.putExtra(EXTRA_LONG, note.getLongitude());

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(fullName);
        }
        tvTime = findViewById(R.id.tv_time);
        tvAddress = findViewById(R.id.tv_address);
        tvLong = findViewById(R.id.tv_longitude);
        tvLat = findViewById(R.id.tv_latitude);

//        Glide.with(this).load(avatar).apply(new RequestOptions()
//                .placeholder(R.drawable.avatar_default_details)
//                .fallback(R.drawable.avatar_default_details)
//                .centerCrop()).into(photoView);
//        wikiInfoView.setText(wikiArticle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
