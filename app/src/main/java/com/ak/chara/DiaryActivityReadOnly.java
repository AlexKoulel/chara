package com.ak.chara;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DiaryActivityReadOnly extends AppCompatActivity
{
    private TextView entryTextReadOnlyTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_read_only);

        entryTextReadOnlyTV = (TextView) findViewById(R.id.entryReadOnly);
        Intent intent = getIntent();
        String currentText = intent.getStringExtra("text");
        entryTextReadOnlyTV.setText(currentText);
    }
}
