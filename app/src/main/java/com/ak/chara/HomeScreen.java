package com.ak.chara;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.util.Calendar;

public class HomeScreen extends AppCompatActivity
{

    private Button journalBtn,notificationsBtn,infoBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        journalBtn = (Button) findViewById(R.id.JournalButton);
        notificationsBtn = (Button) findViewById(R.id.NotificationButton);
        infoBtn = (Button) findViewById(R.id.InfoButton);

        journalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        notificationsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(HomeScreen.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Toast.makeText(HomeScreen.this, hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
                    }
                },hour,minute,false);
                timePickerDialog.show();
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this, InfoScreen.class);
                startActivity(intent);
            }
        });
    }
}
