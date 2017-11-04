package putra.kobal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
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
import putra.kobal.ListSewaAngkot;
import putra.kobal.R;


/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleAdapteraListSewa extends RecyclerView.Adapter<RecycleViewHolderListSewa> {


    LayoutInflater inflater;
    Context context;
    Intent i;
    public static List<String> list_namaPemilik = new ArrayList();
    public static List<String> list_plat= new ArrayList();
    public static List<String> list_kode = new ArrayList();
    public static List<String> list_key = new ArrayList();
    Firebase Kref;

    public RecycleAdapteraListSewa(final Context context) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        Firebase.setAndroidContext(this.context);
        Kref = new Firebase("https://kobal-d8264.firebaseio.com/").child("driver");

       try {
           Kref.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {

                   ListSewaAngkot.progressBar.setVisibility(View.VISIBLE);
                   list_namaPemilik.clear();
                   list_plat.clear();
                   list_kode.clear();
                   list_key.clear();

                   for (DataSnapshot child : dataSnapshot.getChildren()){

                       String namaPemilik = (String) child.child("nama").getValue();
                       String plat = (String) child.child("plat_nomr").getValue();
                       String kode = (String) child.child("kode").getValue();
                       String key = child.getKey();
                       list_namaPemilik.add(namaPemilik);
                       list_plat.add(plat);
                       list_kode.add(kode);
                       list_key.add(key);
                   }
                   ListSewaAngkot.progressBar.setVisibility(View.GONE);

               }

               @Override
               public void onCancelled(FirebaseError firebaseError) {

               }
           });

       }catch (Exception e){
           Log.e("Eror  list sewa ","Erornya : "+e);
           Toast.makeText(context.getApplicationContext(),"Gagal Mengambil data: "+e.toString() ,Toast.LENGTH_LONG).show();
       }




    }


    @Override
    public RecycleViewHolderListSewa onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_pilihsewa, parent, false);
        //View v = inflater.inflate(R.layout.item_list,parent,false);
        RecycleViewHolderListSewa viewHolderSewa = new RecycleViewHolderListSewa(view);
        return viewHolderSewa;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolderListSewa holder, int position) {

        Resources res = context.getResources();

      // holder.txtNamaMotor.setText(nama[position].toString());
        //holder.txtPlatNomor.setText(plat[position].toString());
        //holder.contentWithBackground.setGravity(Gravity.LEFT);
       holder.txtNamaPemilik.setText(list_namaPemilik.get(position).toString());
       holder.txtPlatNomor.setText(list_plat.get(position).toString());

        holder.txtNamaPemilik.setOnClickListener(clicklistener);
        holder.txtPlatNomor.setOnClickListener(clicklistener);
        holder.img_iconlistAngkot.setOnClickListener(clicklistener);


        holder.txtNamaPemilik.setTag(holder);
        holder.txtPlatNomor.setTag(holder);
        holder.img_iconlistAngkot.setTag(holder);


    }

    View.OnClickListener clicklistener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            RecycleViewHolderListSewa vHolder = (RecycleViewHolderListSewa) v.getTag();
            int position = vHolder.getPosition();
            Toast.makeText(context.getApplicationContext(), "Item diklik Ke : "+position, Toast.LENGTH_SHORT).show();
            i = new Intent(context, DetailAngkot.class);
            i.putExtra("namaKirim",list_namaPemilik.get(position).toString());
            i.putExtra("platKirim",list_plat.get(position).toString());
            i.putExtra("kodeKirim",list_kode.get(position).toString());
            i.putExtra("keyKirim",list_key.get(position).toString());
            context.startActivity(i);

        }
    };


    public int getItemCount() {

        return list_namaPemilik == null ? 0 : list_namaPemilik.size();
       //return nama.length;

    }



}
