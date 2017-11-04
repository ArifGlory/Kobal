package putra.kobal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import putra.kobal.Adapter.RecycleAdapteraListSewa;

public class ListSewaAngkot extends AppCompatActivity {

    RecyclerView recycler_listSewa;
    RecycleAdapteraListSewa adapter;
    public static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sewa_angkot);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        recycler_listSewa = (RecyclerView) findViewById(R.id.recycler_listsewa);
        adapter = new RecycleAdapteraListSewa(this);
        recycler_listSewa.setAdapter(adapter);
        recycler_listSewa.setLayoutManager(new LinearLayoutManager(this));

    }
}
