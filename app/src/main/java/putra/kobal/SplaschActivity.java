package putra.kobal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.firebase.client.Firebase;

import java.util.Timer;
import java.util.TimerTask;

public class SplaschActivity extends AppCompatActivity {

    Intent i;
    int delay =  3000;
    Firebase Kref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splasch);


        Timer waktu = new Timer();
        TimerTask show = new TimerTask() {
            @Override
            public void run() {
                finish();
                i = new Intent(SplaschActivity.this,MainActivity.class);
                startActivity(i);
            }
        };

        waktu.schedule(show,delay);
    }
}
