package putra.kobal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import putra.kobal.Kelas.DriverModel;

public class RegisterDriver extends AppCompatActivity {

    Intent i;
    Firebase Kref;
    private ProgressBar progressBar;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fStateListener;
    DriverModel driverModel;

    Button btn_regisDriver;
    EditText etNama,etPass,etEmail,etKodeAngkot,etPlat;
    Spinner sp_trayek;

    Double lat = -5.381902;
    Double lon = 105.257662;
    String status = "off";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);
        Firebase.setAndroidContext(this);
        FirebaseApp.initializeApp(RegisterDriver.this);
        Kref = new Firebase("https://kobal-d8264.firebaseio.com/").child("driver");
        fAuth = FirebaseAuth.getInstance();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn_regisDriver = (Button) findViewById(R.id.btnRegisDriver);
        etNama = (EditText) findViewById(R.id.etRegisNama);
        etEmail = (EditText) findViewById(R.id.etRegisEmail);
        etKodeAngkot = (EditText) findViewById(R.id.etRegisKode);
        etPlat = (EditText) findViewById(R.id.etRegisPlat);
        etPass = (EditText) findViewById(R.id.etRegisPass);
        sp_trayek = (Spinner) findViewById(R.id.sp_trayek);


        fStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = fAuth.getCurrentUser();
                if (user != null ){
                    //user sedang login
                    Log.d("Fauth : ","onAuthStateChanged:signed_in:" + user.getUid());
                }
                //user sedang logout
                Log.d("Fauth : ","onAuthStateChanged:signed_out");
            }
        };

        btn_regisDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (formcek()){
                    progressBar.setVisibility(View.VISIBLE);
                    etEmail.setEnabled(false);
                    etPass.setEnabled(false);
                    etKodeAngkot.setEnabled(false);
                    etNama.setEnabled(false);
                    etPlat.setEnabled(false);
                    sp_trayek.setEnabled(false);
                    //ke method daftar
                    signUp(etEmail.getText().toString(),etPass.getText().toString());
                }

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(fStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (fStateListener != null) {
            fAuth.removeAuthStateListener(fStateListener);
        }
    }

    private void signUp(final String email,String password){

        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Log.d("Fauth :","createUserWithEmail:onComplete: " + task.isSuccessful());
                /**
                 * Jika sign in gagal, tampilkan pesan ke user. Jika sign in sukses
                 * maka auth state listener akan dipanggil dan logic untuk menghandle
                 * signed in user bisa dihandle di listener.
                 */

                if (!task.isSuccessful()){
                    task.getException().printStackTrace();
                    Log.e("Eror gagal daftar ",task.getException().toString());
                    Toast.makeText(RegisterDriver.this,"Proses Pendaftaran gagal"+task.getException().toString(),Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    etEmail.setEnabled(true);
                    etPass.setEnabled(true);
                    etKodeAngkot.setEnabled(true);
                    etNama.setEnabled(true);
                    etPlat.setEnabled(true);
                    sp_trayek.setEnabled(true);
                }else {

                    FirebaseUser user = fAuth.getCurrentUser();
                    String userID =  fAuth.getCurrentUser().getUid();
                    //ganti nama
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                            .setDisplayName(etNama.getText().toString()).build();
                    user.updateProfile(profileChangeRequest);
                    //menambahkan ke DB
                    String trayek = sp_trayek.getSelectedItem().toString();
                    driverModel = new DriverModel(etNama.getText().toString(),
                            etEmail.getText().toString(),
                            etKodeAngkot.getText().toString(),
                            etPass.getText().toString(),
                            trayek,
                            etPlat.getText().toString(),
                            lat,
                            lon,
                            status);
                    Kref.child(userID).setValue(driverModel);

                    Toast.makeText(RegisterDriver.this,"Proses Pendaftaran berhasil \n" +
                            "Email : "+email,Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    etEmail.setText("");
                    etPass.setText("");
                    etKodeAngkot.setText("");
                    etNama.setText("");
                    etPlat.setText("");
                    sp_trayek.setEnabled(true);
                    /*
                    i = new Intent(RegisterDriver.this,LoginDriver.class);
                    startActivity(i);*/

                }
            }
        });

    }

    private boolean validateName() {
        if (etNama.getText().toString().trim().isEmpty()) {
            etNama.setError("Tidak boleh kosong!");
            etNama.requestFocus();
            return false;
        } else {
        }
        return true;
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

    private boolean validatePassLength() {
        if (etPass.getText().toString().trim().length() <= 6) {
            etPass.setError("Password harus lebih dari 6 karakter");
            etPass.requestFocus();
            return false;
        } else {
        }
        return true;
    }



    private boolean validateKode() {
        if (etKodeAngkot.getText().toString().trim().isEmpty()) {
            etKodeAngkot.setError("Tidak boleh kosong!");
            etKodeAngkot.requestFocus();
            return false;
        } else {
        }
        return true;
    }

    private boolean validatePlat() {
        if (etPlat.getText().toString().trim().isEmpty()) {
            etPlat.setError("Tidak boleh kosong!");
            etPlat.requestFocus();
            return false;
        } else {
        }
        return true;
    }

    private boolean formcek() {

        if (!validateName()) {
            return false;
        }
        if (!validatePass()) {
            return false;
        }

        if (!validateEmail()) {
            return false;
        }
        if (!validatePlat()) {
            return false;
        }
        if (!validateKode()) {
            return false;
        }
        if (!validatePassLength()) {
            return false;
        }

        return true;

    }

   
}
