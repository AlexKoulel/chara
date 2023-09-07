package com.ak.chara;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EntryRvAdapter extends RecyclerView.Adapter<EntryRvAdapter.ViewHolder>
{
    // variable for our array list and context
    private ArrayList<EntryModal> courseModalArrayList;
    private Context context;

    // constructor
    public EntryRvAdapter(ArrayList<EntryModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        EntryModal modal = courseModalArrayList.get(position);
        holder.entryDateTV.setText(modal.getEntryDate());
        holder.entryMoodTV.setText(modal.getEntryMood());
        holder.entryTextTV.setText(modal.getEntryText());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView entryDateTV, entryMoodTV, entryTextTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            entryDateTV = itemView.findViewById(R.id.idTVDate);
            entryMoodTV = itemView.findViewById(R.id.idTVMood);
            entryTextTV = itemView.findViewById(R.id.idTVText);
        }
    }
}
