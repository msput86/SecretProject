package com.secretproject.secretproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MapActivity extends AppCompatActivity implements OsmFragment.OnItemSelectedListener {
   // private TextView mBeerTextView;
   // private TextView mBeerTextView2;
   BroadcastReceiver br;
    public final static String BROADCAST_ACTION = "com.secretproject.secretproject.broadcast";
    public final static String LONGITUDE = "LONGITUDE";
    public final static String LATITUDE = "LATITUDE";
    private MapView map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                       // | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_map);
        Intent intent = new Intent(this, LocationListnerService.class);
        startService(intent);
       // FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        //ft.add(R.id.container, new OsmFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
       // ft.commit();
        org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);




       // map.setMaxZoomLevel(30);
        IMapController mapController = map.getController();
        mapController.setZoom(14);
        GeoPoint startPoint = new GeoPoint(55.1913,30.2115);
        mapController.setCenter(startPoint);

        br = new BroadcastReceiver() {
            // действия при получении сообщений
            public void onReceive(Context context, Intent intent) {
                double longitude = intent.getDoubleExtra(LONGITUDE, 0);
                double latitude = intent.getDoubleExtra(LATITUDE, 0);
                Log.d("MESSAGE FROM SERVICE", "onReceive: longitude = " + longitude + ", status = " + latitude);
                IMapController mapController = map.getController();
                GeoPoint startPoint = new GeoPoint(latitude,longitude);
                mapController.setCenter(startPoint);





            }
        };
        // создаем фильтр для BroadcastReceiver
        IntentFilter intFilt = new IntentFilter(BROADCAST_ACTION);
        // регистрируем (включаем) BroadcastReceiver
        registerReceiver(br, intFilt);



    }


    @Override
    protected void onStart() {
        super.onStart();

       // Log.d("ZOOM ", (new Integer (map.getZoomLevel())).toString());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }

    /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }*/
   // @Override
   /* public boolean onOptionsItemSelected(MenuItem item) {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.add(R.id.container, new OsmFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();
        return true;

    }*/

    @Override
    public void onRssItemSelected(String link) {
        Log.d("FRAGMENT","FRAGMENT");
    }



    /*public void onClick(View view) {
        mBeerTextView.setText("I");
        mBeerTextView2.setText("BEER");
    }*/
}
