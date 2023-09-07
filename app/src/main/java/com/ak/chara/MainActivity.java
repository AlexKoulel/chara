package com.ak.chara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private DBManager dbManager;
    private Button checkDBButton,deleteDatabaseButton,deleteEntryButton,newEntryButton,editEntryButton,showEntryButton;
    private TextView showDate,currentMoodTV,emojiTV;
    private CalendarView calendar;

    private String date = "";
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = (CalendarView) findViewById(R.id.calendarView);
        deleteDatabaseButton = (Button) findViewById(R.id.button2);
        checkDBButton = (Button) findViewById(R.id.button3);

        newEntryButton = (Button) findViewById(R.id.NewEntryBtn);
        editEntryButton = (Button) findViewById(R.id.EditEntryBtn);
        showEntryButton = (Button) findViewById(R.id.ShowEntryBtn);
        deleteEntryButton = (Button) findViewById(R.id.DeleteEntryBtn);

        newEntryButton.setVisibility(View.VISIBLE);
        editEntryButton.setVisibility(View.VISIBLE);
        showEntryButton.setVisibility(View.VISIBLE);
        deleteEntryButton.setVisibility(View.VISIBLE);

        newEntryButton.setEnabled(false);
        editEntryButton.setEnabled(false);
        showEntryButton.setEnabled(false);
        deleteEntryButton.setEnabled(false);

        newEntryButton.setBackgroundResource(R.drawable.custom_button_disabled);
        editEntryButton.setBackgroundResource(R.drawable.custom_button_disabled);
        showEntryButton.setBackgroundResource(R.drawable.custom_button_disabled);
        deleteEntryButton.setBackgroundResource(R.drawable.custom_button_disabled);

        showDate = (TextView) findViewById(R.id.textView1);
        emojiTV =(TextView) findViewById(R.id.emojiTV);
        emojiTV.setText(R.string.null_face);

        dbManager = new DBManager(MainActivity.this);
        deleteDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.deleteDatabase();
            }
        });

        checkDBButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewEntries.class);
                startActivity(intent);
            }
        });

        newEntryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                intent.putExtra("date",date);
                intent.putExtra("mood",dbManager.ReturnMood(date));
                intent.putExtra("text",dbManager.ReturnEntryText(date));
                intent.putExtra("isNew",true);
                startActivity(intent);
            }
        });

        editEntryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                intent.putExtra("date",date);
                intent.putExtra("mood",dbManager.ReturnMood(date));
                intent.putExtra("text",dbManager.ReturnEntryText(date));
                intent.putExtra("isNew",false);
                startActivity(intent);
            }
        });

        showEntryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiaryActivityReadOnly.class);
                intent.putExtra("text",dbManager.ReturnEntryText(date));
                startActivity(intent);
            }
        });

        deleteEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Date is:",date);
                try
                {
                    dbManager.deleteEntry(date);
                    emojiTV.setText(R.string.null_face);
                    newEntryButton.setEnabled(true);
                    newEntryButton.setBackgroundResource(R.drawable.custom_button_enabled);
                    editEntryButton.setEnabled(false);
                    editEntryButton.setBackgroundResource(R.drawable.custom_button_disabled);
                    deleteEntryButton.setEnabled(false);
                    deleteEntryButton.setBackgroundResource(R.drawable.custom_button_disabled);
                    showEntryButton.setEnabled(false);
                    showEntryButton.setBackgroundResource(R.drawable.custom_button_disabled);
                    Toast.makeText(MainActivity.this, "Entry deleted.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("Date Error","No entry on that date.");
                }
            }
        });

        calendar.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(
                            @NonNull CalendarView view,
                            int year,
                            int month,
                            int dayOfMonth)
                    {
                        date
                                = dayOfMonth + "/"
                                + (month + 1) + "/" + year;
                        showDate.setText(date);
                        if(dbManager.CheckForEntry(date))
                        {
                            try {
                                ChangeMoodEmoji(date);
                            }
                            catch (Exception e)
                            {
                                Log.e("Change Emoji : " ,"S==null");
                                e.printStackTrace();
                            }

                            newEntryButton.setEnabled(false);
                            newEntryButton.setBackgroundResource(R.drawable.custom_button_disabled);
                            editEntryButton.setEnabled(true);
                            editEntryButton.setBackgroundResource(R.drawable.custom_button_enabled);
                            deleteEntryButton.setEnabled(true);
                            deleteEntryButton.setBackgroundResource(R.drawable.custom_button_enabled);
                            showEntryButton.setEnabled(true);
                            showEntryButton.setBackgroundResource(R.drawable.custom_button_enabled);
                        }
                        else
                        {
                            emojiTV.setText(R.string.null_face);
                            newEntryButton.setEnabled(true);
                            newEntryButton.setBackgroundResource(R.drawable.custom_button_enabled);
                            editEntryButton.setEnabled(false);
                            editEntryButton.setBackgroundResource(R.drawable.custom_button_disabled);
                            deleteEntryButton.setEnabled(false);
                            deleteEntryButton.setBackgroundResource(R.drawable.custom_button_disabled);
                            showEntryButton.setEnabled(false);
                            showEntryButton.setBackgroundResource(R.drawable.custom_button_disabled);
                        }
                    }
                });
    }

    private void ChangeMoodEmoji(String date)
    {
        int integerReturned = Integer.parseInt(dbManager.ReturnMood(date));
        switch (integerReturned)
        {
            case 1:
                emojiTV.setText(R.string.super_sad);
                break;
            case 2:
                emojiTV.setText(R.string.sad);
                break;
            case 3:
                emojiTV.setText(R.string.ok);
                break;
            case 4:
                emojiTV.setText(R.string.happy);
                break;
            case 5:
                emojiTV.setText(R.string.super_happy);
                break;
            default:
                emojiTV.setText(R.string.clown);
        }
    }

}