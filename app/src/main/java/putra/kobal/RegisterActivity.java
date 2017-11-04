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

import putra.kobal.Kelas.UserModel;

public class RegisterActivity extends AppCompatActivity {

    Intent i;
    Spinner sp_Trayek;
    Button btnRegis;
    EditText etNama,etEmail,etPass,etNope;

    Firebase Kref;
    private ProgressBar progressBar;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fStateListener;
    UserModel userModel;

    Double lat = -5.381902;
    Double lon = 105.257662;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firebase.setAndroidContext(this);
        FirebaseApp.initializeApp(RegisterActivity.this);
        Kref = new Firebase("https://kobal-d8264.firebaseio.com/").child("user");
        fAuth = FirebaseAuth.getInstance();

        btnRegis = (Button) findViewById(R.id.btnRegisAkun);
        etNama = (EditText) findViewById(R.id.etRegisNama);
        etEmail = (EditText) findViewById(R.id.etRegisEmail);
        etPass = (EditText) findViewById(R.id.etRegisPass);
        etNope = (EditText) findViewById(R.id.etRegisNope);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

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

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (formcek()){
                    progressBar.setVisibility(View.VISIBLE);
                    etEmail.setEnabled(false);
                    etPass.setEnabled(false);
                    etNama.setEnabled(false);
                    etNope.setEnabled(false);

                    signUp(etEmail.getText().toString(),etPass.getText().toString());
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

    private boolean validateNope() {
        if (etNope.getText().toString().trim().isEmpty()) {
            etNope.setError("Tidak boleh kosong!");
            etNope.requestFocus();
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

        if (!validateNope()) {
            return false;
        }

        if (!validatePassLength()) {
            return false;
        }

        return true;

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

    private void signUp(final String email, String password){

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
                    Log.e("Eror gagal daftar ",task.getException().toString());
                    Toast.makeText(RegisterActivity.this,"Proses Pendaftaran gagal"+task.getException().toString(),Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    etEmail.setEnabled(true);
                    etPass.setEnabled(true);
                    etNama.setEnabled(true);
                    etNope.setEnabled(true);
                }else {
                    FirebaseUser user = fAuth.getCurrentUser();
                    String userID =  fAuth.getCurrentUser().getUid();
                    //ganti nama
                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                            .setDisplayName(etNama.getText().toString()).build();
                    user.updateProfile(profileChangeRequest);

                    userModel = new UserModel(etNama.getText().toString(),
                            etEmail.getText().toString(),
                            etPass.getText().toString(),
                            lat,
                            lon,
                            etNope.getText().toString());
                    Kref.child(userID).setValue(userModel);

                    Toast.makeText(RegisterActivity.this,"Proses Pendaftaran berhasil \n" +
                            "Email : "+email,Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    etEmail.setText("");
                    etPass.setText("");
                    etNama.setText("");
                    etNope.setText("");

                }
            }
        });
    }


}
