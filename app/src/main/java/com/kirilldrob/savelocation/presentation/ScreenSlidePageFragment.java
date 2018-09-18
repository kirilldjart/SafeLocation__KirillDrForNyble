package com.kirilldrob.savelocation.presentation;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirilldrob.h7fragments.R;
import com.kirilldrob.savelocation.network.CollectionsRepository;
import com.kirilldrob.savelocation.network.CuratedCollection;
import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;

public class ScreenSlidePageFragment extends Fragment {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    int pageNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        if (CollectionsRepository.getInstance().collectionList != null) {
            //  Log.d("MyFragment",  String.valueOf(CollectionsRepository.getInstance().collectionList.get(pageNumber)));
            CuratedCollection collection = CollectionsRepository.getInstance().collectionList.get(pageNumber);
                ((TextView) rootView.findViewById(R.id.tv_description)).setText(collection.description);
                ((TextView) rootView.findViewById(R.id.tv_tittle)).setText(collection.title);
                Picasso.get().load(collection.coverPhoto.urls.small).into((ImageView) rootView.findViewById(R.id.imageView));
        }
        return rootView;
    }

    static ScreenSlidePageFragment newInstance(int page) {
        ScreenSlidePageFragment pageFragment = new ScreenSlidePageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }


}