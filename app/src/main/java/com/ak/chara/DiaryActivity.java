package com.ak.chara;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DiaryActivity extends AppCompatActivity {
    private TextView currentDateTV;
    private EditText entryText;
    Toolbar currentDayNotesToolbar;
    ImageButton backButton,editMoodBtn;
    ImageView superHappyMoodBtn, happyMoodBtn,okMoodBtn, sadMoodBtn, superSadMoodBtn;
    private DBManager dbManager;
    private boolean entryIsNotEmpty;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private String selectedMood = null;


    /*long date = System.currentTimeMillis();
    SimpleDateFormat dateFormat = new SimpleDateFormat("d/MMM/yyyy");
    String dateString = dateFormat.format(date);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        dbManager = new DBManager(DiaryActivity.this);

        currentDayNotesToolbar = findViewById(R.id.currentDatNotesToolbar);
        setSupportActionBar(currentDayNotesToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        currentDateTV = (TextView) findViewById(R.id.CurrentDayDateTextView);
        Intent intent = getIntent();
        String currentDate = intent.getStringExtra("date");
        String currentText = intent.getStringExtra("text");
        Boolean entryIsNew = intent.getBooleanExtra("isNew",false);
        currentDateTV.setText(currentDate);

        if(entryIsNew)
        {
            CreateNewFieldDialog();
        }
        entryText = (EditText) findViewById(R.id.entryText);

        backButton = (android.widget.ImageButton) findViewById(R.id.backButton);

        editMoodBtn = (android.widget.ImageButton) findViewById(R.id.editMoodButton);
            editMoodBtn.setVisibility(View.VISIBLE);
            entryText.setText(currentText);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entryIsNew)
                {
                    dbManager.addNewEntry(currentDate,selectedMood,entryText.getText().toString());
                }
                else
                {
                    dbManager.updateEntry(currentDate,selectedMood,entryText.getText().toString());
                }
                Intent intent = new Intent(DiaryActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        editMoodBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CreateNewFieldDialog();
            }
        });

    }
    private void CreateNewFieldDialog()
    {
        dialogBuilder = new AlertDialog.Builder(this);
        final View fieldPopUpView = getLayoutInflater().inflate(R.layout.popup_layout,null);
        superHappyMoodBtn = (ImageButton) fieldPopUpView.findViewById(R.id.super_happy_mood);
        happyMoodBtn = (ImageButton) fieldPopUpView.findViewById(R.id.happy_mood);
        okMoodBtn = (ImageButton) fieldPopUpView.findViewById(R.id.ok_mood);
        sadMoodBtn = (ImageButton) fieldPopUpView.findViewById(R.id.sad_mood);
        superSadMoodBtn = (ImageButton) fieldPopUpView.findViewById(R.id.super_sad_mood);

        dialogBuilder.setView(fieldPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();
        dialog.setCancelable(false);

        superHappyMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedMood = "5";
                dialog.dismiss();
            }
        });

        happyMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedMood = "4";
                dialog.dismiss();
            }
        });
        okMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedMood = "3";
                dialog.dismiss();}});
        sadMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedMood = "2";
                dialog.dismiss();
            }
        });
        superSadMoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedMood = "1";
                dialog.dismiss();
            }
        });

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener(){
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event){
                if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP)
                {
                    Log.d("onKey","Now pressed");
                    finish();
                    dialog.dismiss();
                    startActivity(getIntent());
                }
                return true;
            }
        });
    }
}
