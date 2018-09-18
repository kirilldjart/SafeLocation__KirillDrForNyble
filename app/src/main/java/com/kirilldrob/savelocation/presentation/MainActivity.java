package com.kirilldrob.savelocation.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.kirilldrob.h7fragments.R;
import com.kirilldrob.savelocation.network.CollectionsRepository;


public class MainActivity extends AppCompatActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updatePagerUI();


/*
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);*/
        // Instantiate a ViewPager and a PagerAdapter.
//       if ( CollectionsRepository.getInstance().collectionList!=null)
//           updatePagerUI();
//       else NetworkManager.getInstance().getData(this);


    }


    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 NowPlacePageFragment objects, in
     * sequence.
     */

//// ----- Network interaface
//    @Override
//    public void onDataReady() {
//        updatePagerUI();
//    }
//
//    @Override
//    public void onError(String msg) {
//        showErrorMessage(this);
//
//
//    }
    private void showErrorMessage(Context context) {
        //if (context == null) return;
        Toast.makeText(context, "Something went wrong :(\n Check internet connection", Toast.LENGTH_SHORT).show();
    }
//----------------------------------


    private void updatePagerUI() {

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
//        mPager.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               Log.d("Main", CollectionsRepository.getInstance().collectionList.get(mPager.getCurrentItem()).toString()); //.currentUserCollections.get(5)
//            }
//        });

                               /* mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                                    @Override
                                    public void onPageSelected(int position) {
                                        Log.d("MainActivity", "onPageSelected, position = " + position);
                                    }
                                });*/

                        /*saveData(filmList);

                        findViewById(R.id.pb_am_loading).setVisibility(View.GONE);

                        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setHasFixedSize(true);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        recyclerView.setLayoutManager(layoutManager);

                        MyAdapter adapter = new MyAdapter(filmList, MainActivity.this);
                        recyclerView.setAdapter(adapter);*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }
        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            //!!! recreate and Enable the  location layer if the permission has been granted.
            updatePagerUI();

        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
                    //... more beauty way
            //    Fragment page = getSupportFragmentManager().findFragmentByTag("page1");
            //  page.onRequestPermissionsResult(); --- and there  getLocationAndInflate(mRootView);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    //------------------------------------------------------------------
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return NowPlacePageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return CollectionsRepository.getInstance().collectionList.size();
        }
    }


}
