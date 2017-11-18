package com.anntony.panaderiahouston.Adaptador;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.anntony.panaderiahouston.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Antonio Facundo on 03/11/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    CircleImageView imageUrl;
    TextView nombre;
    TextView hora;
    Switch visto, preparando, listo;

    public ViewHolder(View itemView) {
        super(itemView);
       // cardView = itemView.findViewById(R.id.recyclerView);
        imageUrl =  itemView.findViewById(R.id.imgImageCard);
        nombre = itemView.findViewById(R.id.txtClienteCard);
        hora = itemView.findViewById(R.id.txtTiempoCard);
        visto = itemView.findViewById(R.id.swVistoCard);
        preparando = itemView.findViewById(R.id.swPreparandoCard);
        listo = itemView.findViewById(R.id.swListoCard);
    }
}
