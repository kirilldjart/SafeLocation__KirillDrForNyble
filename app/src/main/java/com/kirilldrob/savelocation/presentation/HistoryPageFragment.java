package com.kirilldrob.savelocation.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.kirilldrob.h7fragments.R;
import com.kirilldrob.savelocation.db.AppDatabase;
import com.kirilldrob.savelocation.db.Note;


import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HistoryPageFragment extends Fragment
        implements NotesAdapter.NotesAdapterInteraction {

    private NotesAdapter mNotesAdapter;

    static final String ARGUMENT_ID = "arg_id";
    int pageNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        pageNumber = getArguments().getInt(ARGUMENT_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_history_page, container, false);

        setRecyclerView(rootView);
        return rootView;
    }

    static HistoryPageFragment newInstance(Note currentPlace) {
        HistoryPageFragment pageFragment = new HistoryPageFragment();
        if (currentPlace == null) return pageFragment;
        Bundle arguments = new Bundle(); //Опция для подсветки выбранного
        arguments.putInt(ARGUMENT_ID, currentPlace.getId());
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mNotesAdapter.updateList(getNoteList());
    }

    // NotesAdapter.NotesAdapterInteraction interface methods
    @Override
    public void onClickItem(Note note) {
        PlaceDetailsActivity.start(getContext(), note);
    }



    @Override
    public void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void setRecyclerView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        mNotesAdapter = new NotesAdapter(this, getNoteList());
        Log.d("saveLocationSize", String.valueOf(getNoteList().size()));
        recyclerView.setAdapter(mNotesAdapter);
    }

    private List<Note> getNoteList() {
        return AppDatabase.getInstance(getContext()).noteDao().getAll();

    }
//  Опция для будущего!
    // NotesAdapter.NotesAdapterInteraction interface methods
//    @Override
//    public void onDeleteNote(Note note) {
//        AppDatabase.getInstance(this).noteDao().delete(note);
//    }
}
