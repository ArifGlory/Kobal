package putra.kobal.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import putra.kobal.BerandaDriver;
import putra.kobal.LoginDriver;
import putra.kobal.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentHomeDriver.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentHomeDriver#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHomeDriver extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUEST_LOCATION = 1 ;
    public static ToggleButton toogle_status;
    TextView txtNotif;
    Firebase Kref;
    private String status;
    private String provider;
    public static ProgressBar progressBar;
    private LocationManager locationManager;
    Location lokasiterahir;
    private Double Klat, Klon;
    public static TextView txtPemilik,txtPlat,txtKodeAngkot,txtTrayek;
    Firebase K2ref;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentHomeDriver() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHomeDriver.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHomeDriver newInstance(String param1, String param2) {
        FragmentHomeDriver fragment = new FragmentHomeDriver();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_fragment_home_driver, container, false);
        View view = inflater.inflate(R.layout.fragment_fragment_home_driver, container, false);
        Firebase.setAndroidContext(getActivity());
        Kref = new Firebase("https://kobal-d8264.firebaseio.com/").child("driver").child(LoginDriver.keyDriver);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        toogle_status = view.findViewById(R.id.toggleButton1);
        txtNotif = view.findViewById(R.id.txtNotif);
        txtPemilik = view.findViewById(R.id.txtNamaPemilik);
        txtPlat = view.findViewById(R.id.txtPlatNomor);
        txtKodeAngkot = view.findViewById(R.id.txtKodeAngkot);
        txtTrayek = view.findViewById(R.id.txtTrayek);

        ambilDetailDriver();

        try {

            Kref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    progressBar.setVisibility(View.VISIBLE);
                    toogle_status.setEnabled(false);

                    status = dataSnapshot.child("status").getValue().toString();

                    if (status.equals("off0")) {
                        toogle_status.setChecked(false);
                        txtNotif.setText("Status : Tidak Aktif");
                        //Toast.makeText(getActivity(),"Statusnya : "+status,Toast.LENGTH_SHORT).show();
                    } else if (status.equals("on1")) {
                        toogle_status.setChecked(true);
                        txtNotif.setText("Status : Aktif");
                        //Toast.makeText(getActivity(),"Statusnya : "+status,Toast.LENGTH_SHORT).show();
                    }

                   // Toast.makeText(getActivity(), "Statusnya : " + status, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    toogle_status.setEnabled(true);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        } catch (Exception e) {
            Log.e("Eror ambil data status", "Erornya : " + e);
            Toast.makeText(getActivity(), "Gagal Mengambil data: " + e.toString(), Toast.LENGTH_LONG).show();
        }


        toogle_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (toogle_status.isChecked()) {
                    Kref.child("status").setValue("on1");
                    BerandaDriver.status_berkendara = "on1";
                    Toast.makeText(getActivity(), "Mode Berkendara diaktifkan, jangan lupa untuk menonaktifkan mode berkendara" +
                            "ketika selesai. ", Toast.LENGTH_SHORT).show();
                } else {
                    Kref.child("status").setValue("off0");
                    BerandaDriver.status_berkendara = "off0";
                    Toast.makeText(getActivity(), "Mode Berkendara dinonaktifkan", Toast.LENGTH_SHORT).show();
                }
            }
        });

       /* locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        if (ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION },
                    REQUEST_LOCATION);
        } else {
            Log.e("DB", "PERMISSION GRANTED");
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, (LocationListener) getActivity());
        Location location = locationManager.getLastKnownLocation(provider);*/




        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void ambilDetailDriver(){

        K2ref = Kref;

        progressBar.setVisibility(View.VISIBLE);
        toogle_status.setEnabled(false);

        try{

            K2ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String nama = dataSnapshot.child("nama").getValue().toString();
                    String platNomor = dataSnapshot.child("plat_nomr").getValue().toString();
                    String kodeAngkot = dataSnapshot.child("kode").getValue().toString();
                    String trayek = dataSnapshot.child("trayek").getValue().toString();


                    txtPemilik.setText(nama);
                    txtPlat.setText(platNomor);
                    txtKodeAngkot.setText("Kode Angkot : "+kodeAngkot);
                    txtTrayek.setText(trayek);

                    toogle_status.setEnabled(true);
                    progressBar.setVisibility(View.GONE);


                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }catch (Exception e){
            Toast.makeText(getActivity(), "Eror detail Driver : "+e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
