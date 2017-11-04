package putra.kobal.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import putra.kobal.R;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleViewHolderListSewa extends RecyclerView.ViewHolder {

    public TextView txtNamaPemilik,txtPlatNomor;
    public ImageView img_iconlistAngkot;
    public LinearLayout lineItem;

    public RecycleViewHolderListSewa(View itemView) {
        super(itemView);

        txtNamaPemilik = (TextView) itemView.findViewById(R.id.txt_namaPemilik);
        txtPlatNomor = (TextView) itemView.findViewById(R.id.txt_platNomorPilih);
        img_iconlistAngkot = (ImageView) itemView.findViewById(R.id.img_iconlistMotor);
        lineItem = (LinearLayout) itemView.findViewById(R.id.lineItem);

    }
}
