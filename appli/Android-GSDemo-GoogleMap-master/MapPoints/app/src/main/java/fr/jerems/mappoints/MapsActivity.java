package fr.jerems.mappoints;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<String> toto = new ArrayList<>();

    private ArrayList<Point> points = new ArrayList<>();
    private Point p1 = new Point(1,2,3,43.6,3.8833);
    private Point p2 = new Point(10,20,30,43.76,3.8933);
    private Point p3 = new Point(0,3,5,43.86,3.9036);
    private Point p4 = new Point(40,60,100,43.96,3.98833);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
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

        mMap = googleMap;
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        for (int i = 0; i < points.size(); i++) {

            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(points.get(i).getLat(),points.get(i).getLng()))
                    .title("hello world"));
        }
    }
    protected void createMarker(double latitude, double longitude) {

              mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f));
    }
}
