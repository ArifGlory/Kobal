package putra.kobal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import putra.kobal.Fragment.FragmentHomeDriver;

public class LoginDriver extends AppCompatActivity {

    Intent i;
    Button btn_login;
    EditText etEmail,etPass;
    private ProgressBar progressBar;
    private String idDriver;
    public static String keyDriver;



    DialogInterface.OnClickListener listener;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fStateListener;
    Firebase Kref,K2ref;

    public static List<String> list_keyDriver = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_driver);
        Firebase.setAndroidContext(this);
        FirebaseApp.initializeApp(LoginDriver.this);
        Kref = new Firebase("https://kobal-d8264.firebaseio.com/").child("driver");
        btn_login = (Button) findViewById(R.id.btnLoginDriver);
        etEmail = (EditText) findViewById(R.id.etEmailDriver);
        etPass = (EditText) findViewById(R.id.etPassDriver);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ambilDataDriver();


        //check jika sudah login
        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser()!= null){

            idDriver = fAuth.getCurrentUser().getUid();
            if (list_keyDriver.contains(idDriver)){

                String name = fAuth.getCurrentUser().getDisplayName();
                BerandaActivity.nama = name;
                keyDriver = fAuth.getCurrentUser().getUid().toString();
                startActivity(new Intent(LoginDriver.this,BerandaDriver.class));
                finish();
            }else{
                ambilDataDriver();
            }

        }else {
            ambilDataDriver();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                final String pass = etPass.getText().toString();


                if (formcek()){
                    progressBar.setVisibility(View.VISIBLE);
                    etEmail.setEnabled(false);
                    etPass.setEnabled(false);
                    btn_login.setEnabled(false);

                    fAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(LoginDriver.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                etEmail.setEnabled(true);
                                etPass.setEnabled(true);
                                btn_login.setEnabled(true);
                                Toast.makeText(getApplicationContext(), "Login Gagal, periksa kembali email dan password anda", Toast.LENGTH_LONG).show();
                            }else{

                                idDriver = fAuth.getCurrentUser().getUid();
                                int jml_driver = list_keyDriver.size();

                                for (int c=0;c<jml_driver;c++){

                                    if (list_keyDriver.contains(idDriver)){
                                        String name = fAuth.getCurrentUser().getDisplayName();
                                        BerandaDriver.namaDriver = name;
                                        keyDriver = fAuth.getCurrentUser().getUid().toString();

                                        i = new Intent(getApplicationContext(),BerandaDriver.class);
                                        startActivity(i);
                                    }else {
                                        progressBar.setVisibility(View.GONE);
                                        etEmail.setEnabled(true);
                                        etPass.setEnabled(true);
                                        btn_login.setEnabled(true);
                                        Toast.makeText(getApplicationContext(), "Login Gagal, periksa kembali email dan password anda", Toast.LENGTH_LONG).show();
                                    }

                                }

                            }


                        }
                    });

                }
            }
        });


    }

    private boolean validateEmail() {
        if (etEmail.getText().toString().trim().isEmpty()) {
            etEmail.setError("Tidak boleh kosong!");
            etEmail.requestFocus();
            return false;
        } else {
        }
        return true;
    }

    private boolean validatePass() {
        if (etPass.getText().toString().trim().isEmpty()) {
            etPass.setError("Tidak boleh kosong!");
            etPass.requestFocus();
            return false;
        } else {
        }
        return true;
    }

    private boolean formcek() {

        if (!validatePass()) {
            return false;
        }

        if (!validateEmail()) {
            return false;
        }

        return true;

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakan anda tetap ingin menutup aplikasi?");
        builder.setCancelable(false);

        listener = new DialogInterface.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == DialogInterface.BUTTON_POSITIVE){
                    finishAffinity();
                    System.exit(0);
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

    public void keDaftarDriver(View view) {
        i = new Intent(LoginDriver.this,RegisterDriver.class);
        startActivity(i);
    }

    public void keLoginUser(View view) {
        i = new Intent(LoginDriver.this,MainActivity.class);
        startActivity(i);
    }

    private void ambilDataDriver(){
        progressBar.setVisibility(View.VISIBLE);
        etEmail.setEnabled(false);
        etPass.setEnabled(false);
        btn_login.setEnabled(false);

        Kref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list_keyDriver.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    //kalo cocok bisa login
                    String kunci = child.getKey();
                    list_keyDriver.add(kunci);
                }
                progressBar.setVisibility(View.GONE);
                etPass.setEnabled(true);
                etEmail.setEnabled(true);
                btn_login.setEnabled(true);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


}
