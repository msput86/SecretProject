package com.secretproject.secretproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.plus.PlusOneButton;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.MapBoxTileSource;
import org.osmdroid.tileprovider.tilesource.MapQuestTileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link OsmFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OsmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OsmFragment extends Fragment {
    private OnItemSelectedListener listener;
    private MapView map = null;

    // Define the events that the fragment will use to communicate
    public interface OnItemSelectedListener {
        // This can be any number of events to be sent to the activity
        public void onRssItemSelected(String link);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // map = new MapView(inflater.getContext());
        //map = (MapView) l(R.id.map);


        return inflater.inflate(R.layout.fragment_osm, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);
        map = (MapView) getView().findViewById(R.id.map);
        final MapBoxTileSource tileSource = new MapBoxTileSource();
//option 1, load your settings from the manifest
        tileSource.retrieveAccessToken(view.getContext());
        tileSource.retrieveMapBoxMapId(view.getContext());
        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);




        // map.setMaxZoomLevel(30);
        IMapController mapController = map.getController();
        mapController.setZoom(14);
        GeoPoint startPoint = new GeoPoint(55.1913,30.2115);
        mapController.setCenter(startPoint);

    }

    // Store the listener (activity) that will have events fired once the fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    // Now we can fire the event when the user selects something in the fragment
    public void onSomeClick(View v) {
        listener.onRssItemSelected("some link");
    }

}
