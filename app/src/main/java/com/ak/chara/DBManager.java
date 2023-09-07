package com.ak.chara;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "diarydb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "diary";
    private static final String ID = "id";
    private static final String ENTRYDATE = "entrydate";
    private static final String ENTRYMOOD = "entrymood";
    private static final String ENTRYTEXT = "entrytext";

    public DBManager(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,ENTRYDATE TEXT,ENTRYMOOD TEXT,ENTRYTEXT TEXT)";
        db.execSQL(query);
    }
    public void addNewEntry(String entryDate,String entryMood,String entryText)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ENTRYDATE,entryDate);
        values.put(ENTRYMOOD,entryMood);
        values.put(ENTRYTEXT,entryText);

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void updateEntry(String entryDate,String entryMood,String entryText)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ENTRYTEXT,entryText);
        values.put(ENTRYMOOD,entryMood);
        db.update(TABLE_NAME,values,"entrydate=?",new String[]{entryDate});
        db.close();
    }

    public void deleteEntry(String entryDate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"entrydate=?",new String[]{entryDate});
        db.close();
    }
    public void deleteDatabase()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME;
        db.execSQL(query);
    }
    public boolean CheckForEntry(String date)
    {
        String dateSelected = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ENTRYDATE=? ",new String[]{date});
        ArrayList<EntryModal> entryModalArrayList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                dateSelected = cursor.getString(1);
            }while(cursor.moveToNext());
        }
        cursor.close();
        if(dateSelected != null){
            return true;
        }
        else{
            return false;
        }
    }

    public String ReturnEntryText(String date)
    {
        String entryText = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ENTRYDATE=? ",new String[]{date});
        if(cursor.moveToFirst()){
            do{
                entryText = cursor.getString(3);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return entryText;
    }
    public String ReturnMood(String date)
    {
        String mood = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ENTRYDATE=? ",new String[]{date});
        if(cursor.moveToFirst()){
            do{
                mood = cursor.getString(2);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return mood;
    }
    public ArrayList<EntryModal> readEntriesTest()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);

        ArrayList<EntryModal> entryModalArrayList = new ArrayList<>();

        if(cursor.moveToFirst())
        {
            do
            {
                entryModalArrayList.add(new EntryModal(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return entryModalArrayList;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
