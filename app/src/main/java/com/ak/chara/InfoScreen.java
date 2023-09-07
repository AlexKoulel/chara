package com.ak.chara;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoScreen extends AppCompatActivity
{
    private ImageView githubImage, kofiImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        githubImage = (ImageView) findViewById(R.id.githubImage);
        kofiImage = (ImageView) findViewById(R.id.kofiImage);


        githubImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/AlexKoulel"));
                startActivity(browserIntent);
            }
        });
        kofiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ko-fi.com/alexkoulel"));
                startActivity(browserIntent);
            }
        });
    }
}
