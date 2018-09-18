package com.kirilldrob.savelocation.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.kirilldrob.h7fragments.R;
import com.kirilldrob.savelocation.db.AppDatabase;
import com.kirilldrob.savelocation.db.Note;


import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import android.support.v4.app.Fragment;

public class HistoryPageFragment extends Fragment
        implements NotesAdapter.NotesAdapterInteraction{


    static final String ARGUMENT_ID = "arg_id";
    int pageNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_history_page, container, false);
//        if (CollectionsRepository.getInstance().collectionList != null) {
//            //  Log.d("MyFragment",  String.valueOf(CollectionsRepository.getInstance().collectionList.get(pageNumber)));
//            CuratedCollection collection = CollectionsRepository.getInstance().collectionList.get(pageNumber);
//                ((TextView) rootView.findViewById(R.id.tv_description)).setText(collection.description);
//                ((TextView) rootView.findViewById(R.id.tv_tittle)).setText(collection.title);
//              //  Picasso.get().load(collection.coverPhoto.urls.small).into((ImageView) rootView.findViewById(R.id.imageView));
//        }
       // setViews(rootView);
        setRecyclerView( rootView);


        return rootView;
    }

    static HistoryPageFragment newInstance(Note currentPlace) {
        HistoryPageFragment pageFragment = new HistoryPageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_ID, currentPlace.getId());
        pageFragment.setArguments(arguments);
        return pageFragment;
    }




    private NotesAdapter mNotesAdapter;
    private Button mBtnAddNote;
    private EditText mEtTitle;
    private EditText mEtBody;



//    private void setViews(View rootView) {
//        mBtnAddNote = rootView.findViewById(R.id.btnAddNote);
//        mEtTitle = rootView.findViewById(R.id.etTitle);
//        mEtBody = rootView.findViewById(R.id.etBody);


//    }

    private void addNote() {
       // String title = mEtTitle.getText().toString();
//        String body = mEtBody.getText().toString();
//        Note note = createNote(title, body);
//
//        AppDatabase.getInstance(getContext()).noteDao().insert(note);
//
//        mEtTitle.getText().clear();
//        mEtTitle.requestFocus();
//        mEtBody.getText().clear();

//        List<Note> updatedList = getNoteList();
//        mNotesAdapter.updateList(updatedList);
    }

    @NonNull
    private Note createNote(String address,double longitude, double latitude, String title) {
        Note note = new Note();
        note.setTitle(title); // Опция!
        note.setAddress(address);
        note.setLatitude(latitude);
        note.setLongitude(longitude);
        note.setTimestamp((double) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        return note;
    }




    @Override
    public void onClickItem(Note note) {
        PlaceDetailsActivity.start(getContext(),note);
//startActivity(intent);
    }



    @Override
    public void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void setRecyclerView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        mNotesAdapter = new NotesAdapter(this, getNoteList());
        recyclerView.setAdapter(mNotesAdapter);
    }

    private List<Note> getNoteList() {
        return AppDatabase.getInstance(getContext()).noteDao().getAll();
    }

    // NotesAdapter.NotesAdapterInteraction interface methods
//    @Override
//    public void onDeleteNote(Note note) {
//        AppDatabase.getInstance(this).noteDao().delete(note);
//    }
}






}