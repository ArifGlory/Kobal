package putra.kobal.Fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import putra.kobal.Modules.DirectionFinderListener;
import putra.kobal.Modules.Route;
import putra.kobal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMaps extends Fragment implements OnMapReadyCallback,DirectionFinderListener {


    public FragmentMaps() {
        // Required empty public constructor
    }


    private GoogleMap mMap;
    public Marker marker_ghost;
    Intent i;
    Firebase Kref;
    public static List<String> list_keyDriver = new ArrayList();
    int iconMarker = R.drawable.car_icon;
    Button btnCari;
    private ProgressBar progressBar;
    private TextView tvDistance,tvDuration;

    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();

    private double latitud, longitud;
    private LocationManager locationManager;
    private String provider;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_maps, container, false);
        Firebase.setAndroidContext(this.getActivity());
        Kref = new Firebase("https://kobal-d8264.firebaseio.com/").child("driver");
        btnCari = view.findViewById(R.id.btnCari);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        tvDistance = view.findViewById(R.id.tvDistance);
        tvDuration = view.findViewById(R.id.tvDuration);


        final FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        final SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.mapView, fragment);
        transaction.commit();

        fragment.getMapAsync(this);




        return view;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.clear();
        LatLng lampung = new LatLng(-5.382351, 105.257791);
        //mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CircleOptions mOptions = new CircleOptions()
                .center(lampung).radius(100)
                .strokeColor(0x110000FF).strokeWidth(8).fillColor(0x110000FF);
        mMap.addCircle(mOptions);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


        //  mMap.addMarker(new MarkerOptions().position(lampung).title("lokasi"));

       // mMap.moveCamera(CameraUpdateFactory.newLatLng(lampung));

        Toast.makeText(getActivity().getApplication(),"Mengambil lokasi..." , Toast.LENGTH_LONG).show();
        ambilDataDriver();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lampung, 14));


       mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
           @Override
           public void onInfoWindowClick(final Marker marker) {


           }
       });


    }

    public void ambilDataDriver(){
        try{
            Kref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    list_keyDriver.clear();
                    for (DataSnapshot child : dataSnapshot.getChildren()){
                        String status = (String) child.child("status").getValue();

                        if (status.equals("on1")) {

                            String nama = (String) child.child("nama").getValue();
                            String email = (String) child.child("email").getValue();

                            String kunci = child.getKey();
                            Double lat = (Double) child.child("lat").getValue();
                            Double lon = (Double) child.child("lon").getValue();
                            list_keyDriver.add(kunci);
                            LatLng posisiDriver = new LatLng(lat, lon);

                            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(iconMarker))
                                    .position(posisiDriver)
                                    .title(nama))
                                    .setSnippet(email);
                            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posisiDriver,17));
                        }
                    }
                    Toast.makeText(getActivity().getApplication(),"Berhasil : " ,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }catch (Exception e){
            Log.e("Eror Maps Ambildata","Erornya : "+e);
            Toast.makeText(getActivity().getApplication(),"Gagal mengambil lokasi : "+e.toString() ,Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onDirectionFinderStart() {
        progressBar.setVisibility(View.VISIBLE);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressBar.setVisibility(View.GONE);
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();



        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            tvDuration.setText(route.duration.text);
            tvDistance.setText(route.distance.text);

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .title("Lokasi Saya")
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(iconMarker))
                    .title("Angkot Terdekat")
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            polylineOptions.add(route.startLocation);
            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));
            polylineOptions.add(route.endLocation);

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }

    }


}
