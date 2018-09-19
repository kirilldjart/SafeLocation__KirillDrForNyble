package com.kirilldrob.savelocation.presentation;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;

import android.os.Bundle;


import com.kirilldrob.h7fragments.R;


public class MainActivity extends AppCompatActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updatePagerUI();
        // Design: Таб полоска в Асtion баре!
// 2 variant: Create a tab listener that is called when the user changes tabs.
//        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
//            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//                // When the tab is selected, switch to the
//                // corresponding page in the ViewPager.
//                mViewPager.setCurrentItem(tab.getPosition());
//            }}
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

    private void updatePagerUI() {

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

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
            Fragment f = null;
            if (position == 0) f = NowPlacePageFragment.newInstance(position);
            if (position == 1)
                f = HistoryPageFragment.newInstance(null); //null- Опция: Подсветка текущего места в истории мест.

            return f;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String name = null;
            if (position == 0) name = " You are here: ";
            if (position == 1) name = " Your previous places: ";
            return name;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


}
