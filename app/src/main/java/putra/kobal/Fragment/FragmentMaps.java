package putra.kobal.Fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import com.google.android.gms.location.LocationRequest;
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import putra.kobal.Modules.DirectionFinder;
import putra.kobal.Modules.DirectionFinderListener;
import putra.kobal.Modules.DirectionKobal;
import putra.kobal.Modules.DirectionKobalListener;
import putra.kobal.Modules.Route;
import putra.kobal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMaps extends Fragment implements OnMapReadyCallback,DirectionFinderListener,LocationListener,DirectionKobalListener {


    public FragmentMaps() {
        // Required empty public constructor
    }


    private GoogleMap mMap;
    public Marker marker_ghost;
    Intent i;
    Firebase Kref;
    public static List<String> list_keyDriver = new ArrayList();
    int iconMarker = R.drawable.car_icon;
    private Button btnCari;
    private Double min;
    private int indexTerkecil;
    private FloatingActionButton fab1;

    private ProgressBar progressBar;
    private TextView tvDistance,tvDuration;

    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private List<String> LatLngDriver = new ArrayList<>();
    private List<Double> list_jarak = new ArrayList<>();
    private List<LatLng> list_LatLng = new ArrayList<>();

    private double latitud, longitud;
    private LocationManager locationManager;
    private String provider;
    private LocationRequest mlocationrequest;



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
        fab1 = view.findViewById(R.id.fab1);


        final FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        final SupportMapFragment fragment = new SupportMapFragment();
        transaction.add(R.id.mapView, fragment);
        transaction.commit();

        mlocationrequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)//10 detik ddalam 1milisecond
                .setFastestInterval(1 * 1000);//1detik dalam milisecond

        fragment.getMapAsync(this);

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kirimRequest();
                fab1.setVisibility(View.GONE);
            }
        });





        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            System.out.println("Location not avilable");
        }




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
                    LatLngDriver.clear();
                    list_LatLng.clear();
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
                            LatLngDriver.add(lat.toString()+","+lon.toString());
                            list_LatLng.add(posisiDriver);

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

    private void sendRequest(){

        list_jarak.clear();
        for (int c=0;c<LatLngDriver.size();c++){
            String origin = String.valueOf(latitud) + "," + String.valueOf(longitud);
            int jml = LatLngDriver.size();
            String destination = LatLngDriver.get(c).toString();

            if (origin.isEmpty()) {
                Toast.makeText(getActivity(), "Tolong masukkan origin address!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (destination.isEmpty()) {
                Toast.makeText(getActivity(), "Tolong masukkan destination address!", Toast.LENGTH_SHORT).show();
                return;
            }

//            Toast.makeText(getActivity(),"origin : "+origin+"& destination : "+destination,Toast.LENGTH_SHORT).show();

            try {
                new DirectionFinder(this, origin, destination).execute();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(),"Eror Direction : "+e.toString(),Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void kirimRequest(){
        String origin = String.valueOf(latitud) + "," + String.valueOf(longitud);
        int jml = LatLngDriver.size();
        String destination = LatLngDriver.get(indexTerkecil).toString();

        if (origin.isEmpty()) {
            Toast.makeText(getActivity(), "Tolong masukkan origin address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(getActivity(), "Tolong masukkan destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

//            Toast.makeText(getActivity(),"origin : "+origin+"& destination : "+destination,Toast.LENGTH_SHORT).show();

        try {
            new DirectionKobal(this, origin, destination).execute();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(),"Eror Direction kobal : "+e.toString(),Toast.LENGTH_SHORT).show();
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


        int no = 0;
        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            tvDuration.setText(route.duration.text);
            tvDistance.setText(route.distance.text);

            String jarakS = route.distance.text;
            String jarakhasil = jarakS.substring(0,jarakS.lastIndexOf(" "));
            Double distance = Double.valueOf((String.valueOf(jarakhasil)));
            list_jarak.add(distance);
           Log.d("jarak driver ke : "+no+" = ",jarakhasil);
            Log.d("isi list jarak : ", String.valueOf(list_jarak.size()));
            Log.d("isi LAtlng driver: ", String.valueOf(LatLngDriver.size()));
            Log.d("isi key driver: ", String.valueOf(list_keyDriver.size()));

            /*originMarkers.add(mMap.addMarker(new MarkerOptions()
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

            polylinePaths.add(mMap.addPolyline(polylineOptions));*/
            no++;
        }


        min = Double.valueOf(list_jarak.get(0));
        indexTerkecil = 0;
        for (int d=0; d < list_jarak.size();d++){
            if (list_jarak.get(d) < min){
                min = list_jarak.get(d);
                indexTerkecil = d;
            }
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(list_LatLng.get(indexTerkecil), 16));
        tvDistance.setText(min+" km");
        fab1.setVisibility(View.VISIBLE);

    }


    @Override
    public void onLocationChanged(Location location) {
        double lat = (double) (location.getLatitude());
        double lng = (double) (location.getLongitude());

     //   Toast.makeText(getActivity().getApplication(),"lat : "+lat+"& Lon :"+lng , Toast.LENGTH_SHORT).show();

        latitud = lat;
        longitud = lng;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, (LocationListener) this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    public void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates((LocationListener) this);
    }


    public static String right(String value, int length) {
        // To get right characters from a string, change the begin index.
        return value.substring(value.length() - length);
    }


    @Override
    public void onDirectionKobalStart() {
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
    public void onDirectionKobalSuccess(List<Route> routes) {
        progressBar.setVisibility(View.GONE);
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();


        int no = 0;
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
            no++;
        }
    }
}
