package putra.kobal.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import putra.kobal.R;

/**
 * Created by Glory on 03/10/2016.
 */
public class RecycleViewHolderListPermintaan extends RecyclerView.ViewHolder {

    public TextView txtNamaPenyewa;
    public ImageView img_iconlistPenyewa;
    public LinearLayout lineItem;
    public CardView card_item;

    public RecycleViewHolderListPermintaan(View itemView) {
        super(itemView);

        txtNamaPenyewa = (TextView) itemView.findViewById(R.id.txt_namaPenyewa);
        img_iconlistPenyewa = (ImageView) itemView.findViewById(R.id.img_iconlistPenyewa);
        lineItem = (LinearLayout) itemView.findViewById(R.id.lineItem);
        card_item = itemView.findViewById(R.id.cardlist_item);

    }
}
