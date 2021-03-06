package com.kirilldrob.savelocation.presentation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;

import android.support.v4.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kirilldrob.h7fragments.R;
import com.kirilldrob.savelocation.db.AppDatabase;
import com.kirilldrob.savelocation.db.Note;
import com.kirilldrob.savelocation.services.Constants;
import com.kirilldrob.savelocation.services.FetchAddressIntentService;


import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class NowPlacePageFragment extends Fragment {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    int pageNumber;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    //------Google API  -- т.к. он разделяется многими приложениями!
    private Location mLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private AddressResultReceiver mResultReceiver;

    private View mRootView;


    static NowPlacePageFragment newInstance(int page) {
        NowPlacePageFragment pageFragment = new NowPlacePageFragment();
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_now_page, container, false);

        getLocationAndInflate(rootView);
        mRootView = rootView;
        return rootView;
    }

    private void getLocationAndInflate(final View rootView) {
        if (!checkPlayServices() || !checkLocationPermission()) return;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            ((TextView) rootView.findViewById(R.id.tv_longitude)).setText(String.valueOf(location.getLongitude()));
                            ((TextView) rootView.findViewById(R.id.tv_latitude)).setText(String.valueOf(location.getLatitude()));
                            ((TextView) rootView.findViewById(R.id.tv_altitude)).setText(String.valueOf(location.getAltitude()));

                            // Geocoding
                            if (!Geocoder.isPresent()) {
                                Toast.makeText(getContext(),
                                        R.string.no_geocoder_available,
                                        Toast.LENGTH_LONG).show();
                                return;
                            } else {
                                mLocation = location;
                                startIntentService(location);
                            }

                        }
                    }
                });
    }

    protected void startIntentService(Location location) {
        Intent intent = new Intent(getContext(), FetchAddressIntentService.class);
        if (mResultReceiver==null) mResultReceiver=new AddressResultReceiver(new Handler());
        intent.putExtra(Constants.RECEIVER, mResultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        getContext().startService(intent);
    }


    private boolean checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(getContext());
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(getActivity(), result,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }

        return true;
    }


    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission((AppCompatActivity) getActivity(), MainActivity.LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
            return false;
        } else {
            // Access to the location has been granted to the app.
            return true;
        }
    }




    @NonNull
    private Note createNote(String address, double longitude, double latitude, String title) {
        Note note = new Note();
        note.setTitle(title); // Опция!
        note.setAddress(address);
        note.setLatitude(latitude);
        note.setLongitude(longitude);
        note.setTimestamp((double) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        return note;
    }


    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            String addressOutput = "";
            if (resultData != null) {
                // Display the address string
                // or an error message sent from the intent service.
                addressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
                if (addressOutput == null) {
                    addressOutput = "";
                }
            }
            ((TextView) mRootView.findViewById(R.id.tv_address)).setText(addressOutput);
            //-- HISTORY
            Note note = createNote(addressOutput, mLocation.getLongitude(), mLocation.getLatitude(), ""); //tittle will be in Detail screen

            //TODO Опция удаления ДУБЛИКАТОВ  - ОТключена для наглядности RecyclerView, чтобы не бегать по району при тестах;)
            // if (AppDatabase.getInstance(getContext()).noteDao().getNoteByAddress(addressOutput)!=null) return;
                  AppDatabase.getInstance(getContext()).noteDao().insert(note);


        }
    }


}