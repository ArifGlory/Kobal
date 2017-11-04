package putra.kobal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import putra.kobal.Kelas.SewaModel;

public class DetailAngkot extends AppCompatActivity {

    Intent i;
    TextView txtNama,txtPlat,txtKode;
    Button btn_sewa;
    Firebase Kref,Uref;
    ProgressBar progressBar;
    private String nope,userID;
    SewaModel sewaModel;

    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_angkot);
        Firebase.setAndroidContext(this);
        fAuth = FirebaseAuth.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        i = getIntent();
        String nama = i.getStringExtra("namaKirim");
        String plat = i.getStringExtra("platKirim");
        String kode = i.getStringExtra("kodeKirim");
        String key = i.getStringExtra("keyKirim");

        Kref = new Firebase("https://kobal-d8264.firebaseio.com/").child("driver").child(key).child("zlist");
        Uref = new Firebase("https://kobal-d8264.firebaseio.com/").child("user").child(userID);


        txtNama = (TextView) findViewById(R.id.txtNamaPemilik);
        txtKode = (TextView) findViewById(R.id.txtKode);
        txtPlat = (TextView) findViewById(R.id.txtPlat);
        btn_sewa = (Button) findViewById(R.id.btnSewa);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        txtNama.setText(nama);
        txtKode.setText("Kode Angkot : "+kode);
        txtPlat.setText(plat);

        try {

            Uref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    progressBar.setVisibility(View.VISIBLE);
                    btn_sewa.setEnabled(false);
                    nope = (String) dataSnapshot.child("nope").getValue();

                    progressBar.setVisibility(View.GONE);
                    btn_sewa.setEnabled(true);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }catch (Exception e){
            Log.e("Eror Ambil data user","Erornya : "+e);
            Toast.makeText(getApplicationContext(),"Gagal Ambil data User: "+e.toString() ,Toast.LENGTH_LONG).show();
        }

        btn_sewa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sewaModel = new SewaModel(fAuth.getCurrentUser().getDisplayName(),
                        nope,
                        fAuth.getCurrentUser().getEmail());

                Kref.push().setValue(sewaModel);
                Toast.makeText(getApplicationContext(),"Permintaan Sewa Dikirim Ke Driver",Toast.LENGTH_LONG).show();
                i = new Intent(getApplicationContext(),ListSewaAngkot.class);
                startActivity(i);

            }
        });
    }
}
