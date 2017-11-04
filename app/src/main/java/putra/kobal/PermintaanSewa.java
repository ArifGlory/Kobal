package putra.kobal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import putra.kobal.Adapter.RecycleAdapteraListPermintaan;

public class PermintaanSewa extends AppCompatActivity {

    public static TextView txt_infoPS;
    public static ProgressBar progressBar;
    RecyclerView recycler_permintaan;
    RecycleAdapteraListPermintaan adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permintaan_sewa);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txt_infoPS = (TextView) findViewById(R.id.txtInfoPS);
        recycler_permintaan = (RecyclerView) findViewById(R.id.recycler_permintaanSewa);

        adapter = new  RecycleAdapteraListPermintaan(this);
        recycler_permintaan.setAdapter(adapter);
        recycler_permintaan.setLayoutManager(new LinearLayoutManager(this));


    }
}
