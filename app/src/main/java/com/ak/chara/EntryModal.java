package com.ak.chara;

public class EntryModal {

    private String entryDate;
    private String entryMood;
    private String entryText;
    private int id;

    public EntryModal(String entryDate, String entryMood, String entryText) {
        this.entryDate = entryDate;
        this.entryMood = entryMood;
        this.entryText = entryText;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryMood() {
        return entryMood;
    }

    public void setEntryMood(String entryMood) {
        this.entryMood = entryMood;
    }

    public String getEntryText() {
        return entryText;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
