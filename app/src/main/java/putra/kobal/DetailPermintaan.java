package putra.kobal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class DetailPermintaan extends AppCompatActivity {

    Firebase Kref;
    TextView txtNama, txtEmail , txtNope;
    Button btnHapus;
    Intent i;
    DialogInterface.OnClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_permintaan);
        Firebase.setAndroidContext(this);
        Kref = new Firebase("https://kobal-d8264.firebaseio.com/").child("driver").child(LoginDriver.keyDriver).child("zlist");

        i = getIntent();
        String nama = i.getStringExtra("nama");
        String email  = i.getStringExtra("email");
        String nope = i.getStringExtra("nope");
        final String key = i.getStringExtra("key");

        txtNama = (TextView) findViewById(R.id.txtNamaPenyewa);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtNope = (TextView) findViewById(R.id.txtNope);
        btnHapus = (Button) findViewById(R.id.btnHapus);

        txtNama.setText(nama);
        txtEmail.setText(email);
        txtNope.setText(nope);

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DetailPermintaan.this);
                builder.setMessage("Apakan anda yakin ?");
                builder.setCancelable(false);

                listener = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == DialogInterface.BUTTON_POSITIVE){
                            Kref.child(key).setValue(null);
                            Toast.makeText(getApplicationContext(), "Terhapus", Toast.LENGTH_SHORT).show();
                            i = new Intent(getApplicationContext(),PermintaanSewa.class);
                            startActivity(i);
                        }

                        if(which == DialogInterface.BUTTON_NEGATIVE){
                            dialog.cancel();
                        }
                    }
                };
                builder.setPositiveButton("Ya",listener);
                builder.setNegativeButton("Tidak", listener);
                builder.show();


            }
        });



    }
}
