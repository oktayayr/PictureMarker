package com.oktyayr.picturemarkerdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oktyayr.picturemapmarkerdemo.R;
import com.oktyayr.picturemarker.ImageMode;
import com.oktyayr.picturemarker.MarkerStyle;
import com.oktyayr.picturemarker.PictureMarker;

public class DemoActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        PictureMarker mAnkara,mIstanbul;
        Bitmap ankara,istanbul;

        mMap = googleMap;

        istanbul = BitmapFactory.decodeResource(getResources(), R.drawable.istanbul);
        ankara = BitmapFactory.decodeResource(getResources(), R.drawable.ankara);

        mAnkara=new PictureMarker(LayoutInflater.from(this).inflate(R.layout.icon_layout,null));
        mAnkara.setImageMode(ImageMode.STRECT);
        mAnkara.setImageSize(50);

        mIstanbul=new PictureMarker(this,istanbul);
        mIstanbul.setImageSize(50);
        mIstanbul.setMarkerStyle(MarkerStyle.CIRCLE);

        // Add a marker in Sydney and move the camera
        LatLng lIstanbul = new LatLng(41, 28.97);
        LatLng lAnkara=new LatLng(39.93,32.85);

        try {
            mMap.addMarker(mAnkara.build().position(lAnkara).snippet("This is Ankara"));
            mMap.addMarker(mIstanbul.build().position(lIstanbul).snippet("This is Istanbul"));

            mMap.moveCamera(CameraUpdateFactory.newLatLng(lIstanbul));
        } catch (Exception e) {
            Log.e("Demo activity",e.getLocalizedMessage());
        }
    }
}
