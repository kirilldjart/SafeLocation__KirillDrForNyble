package com.kirilldrob.savelocation.presentation;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kirilldrob.h7fragments.R;
import com.kirilldrob.savelocation.db.Note;


import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> implements View.OnClickListener {

    private List<Note> mNoteList;
    private final NotesAdapterInteraction mListener;

    public NotesAdapter(NotesAdapterInteraction notesAdapterInteraction, List<Note> noteList) {
        mListener = notesAdapterInteraction;
        mNoteList = noteList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTime;
        public final TextView tvAddress;
        public final TextView tvLong;
        public final TextView tvLat;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTime = view.findViewById(R.id.tv_time);
            tvAddress = view.findViewById(R.id.tv_address);
            tvLong = view.findViewById(R.id.tv_longitude);
            tvLat = view.findViewById(R.id.tv_latitude);
        }
    }

    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, int position) {
      // String callData = new SimpleDateFormat("EEE(MMM dd)HH:mm:ss").format(new Date());
        holder.tvTime.setText(DateFormat.format("dd-MM-yyyy/HH:mm",(long) mNoteList.get(position).getTimestamp() * 1000).toString());
        holder.tvAddress.setText(mNoteList.get(position).getAddress());
        holder.tvLat.setText(String.valueOf(mNoteList.get(position).getLatitude()));
        holder.tvLong.setText(String.valueOf(mNoteList.get(position).getLongitude()));

        holder.mView.setOnClickListener(this);
        holder.mView.setTag(position);
    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        Note note = mNoteList.get(position);
        mListener.onClickItem(note);
        //Опции
       // notifyItemRemoved(position);
       // notifyItemRangeChanged(position, mNoteList.size());
    }

    public void updateList(List<Note> updatedList) {
        mNoteList = updatedList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public interface NotesAdapterInteraction {
        void onClickItem(Note note);
    }
}
