package putra.kobal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import putra.kobal.DetailAngkot;
import putra.kobal.DetailPermintaan;
import putra.kobal.ListSewaAngkot;
import putra.kobal.LoginDriver;
import putra.kobal.PermintaanSewa;
import putra.kobal.R;


/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleAdapteraListPermintaan extends RecyclerView.Adapter<RecycleViewHolderListPermintaan> {


    LayoutInflater inflater;
    Context context;
    Intent i;
    public static List<String> list_namaPenyewa = new ArrayList();
    public static List<String> list_email = new ArrayList();
    public static List<String> list_nope = new ArrayList();
    public static List<String> list_key = new ArrayList();
    Firebase Kref;

    public RecycleAdapteraListPermintaan(final Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        Firebase.setAndroidContext(this.context);
        Kref = new Firebase("https://kobal-d8264.firebaseio.com/").child("driver").child(LoginDriver.keyDriver).child("zlist");

        try {

            Kref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    PermintaanSewa.progressBar.setVisibility(View.VISIBLE);
                    list_namaPenyewa.clear();
                    list_email.clear();
                    list_key.clear();
                    list_nope.clear();


                    for (DataSnapshot child : dataSnapshot.getChildren()){


                        String namaPenyewa = (String) child.child("nama").getValue();
                        String email = (String) child.child("email").getValue();
                        String nope = (String) child.child("nope").getValue();
                        String key = child.getKey();

                        list_namaPenyewa.add(namaPenyewa);
                        list_nope.add(nope);
                        list_key.add(key);
                        list_email.add(email);
                    }

                    PermintaanSewa.progressBar.setVisibility(View.GONE);

                    if (list_namaPenyewa == null){
                        PermintaanSewa.txt_infoPS.setVisibility(View.VISIBLE);
                    }else {
                        PermintaanSewa.txt_infoPS.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }catch (Exception e){
            Log.e("Eror permintaan sewa","Erornya : "+e);
            Toast.makeText(context.getApplicationContext(),"Gagal Mengambil data: "+e.toString() ,Toast.LENGTH_LONG).show();
        }




    }


    @Override
    public RecycleViewHolderListPermintaan onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_permintaansewa, parent, false);
        //View v = inflater.inflate(R.layout.item_list,parent,false);
        RecycleViewHolderListPermintaan viewHolderListPermintaan = new RecycleViewHolderListPermintaan(view);
        return viewHolderListPermintaan;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolderListPermintaan holder, int position) {

        Resources res = context.getResources();

      // holder.txtNamaMotor.setText(nama[position].toString());
        //holder.txtPlatNomor.setText(plat[position].toString());
        //holder.contentWithBackground.setGravity(Gravity.LEFT);
       holder.txtNamaPenyewa.setText(list_namaPenyewa.get(position).toString());

        holder.txtNamaPenyewa.setOnClickListener(clicklistener);
        holder.img_iconlistPenyewa.setOnClickListener(clicklistener);
        holder.card_item.setOnClickListener(clicklistener);


        holder.txtNamaPenyewa.setTag(holder);
        holder.img_iconlistPenyewa.setTag(holder);


    }

    View.OnClickListener clicklistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            RecycleViewHolderListPermintaan vHolder = (RecycleViewHolderListPermintaan) v.getTag();
            int position = vHolder.getPosition();
            Toast.makeText(context.getApplicationContext(), "Item diklik Ke : "+position, Toast.LENGTH_SHORT).show();
            i = new Intent(context, DetailPermintaan.class);
            i.putExtra("nama",list_namaPenyewa.get(position).toString());
            i.putExtra("nope",list_nope.get(position).toString());
            i.putExtra("email",list_email.get(position).toString());
            i.putExtra("key",list_key.get(position).toString());
            context.startActivity(i);

        }
    };


    public int getItemCount() {

        return list_namaPenyewa == null ? 0 : list_namaPenyewa.size();
       //return nama.length;

    }



}
