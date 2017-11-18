package com.anntony.panaderiahouston.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.anntony.panaderiahouston.ContainerFragmentsActivity;
import com.anntony.panaderiahouston.Detalles.DetalleActivity;
import com.anntony.panaderiahouston.Modelos.Cliente;
import com.anntony.panaderiahouston.Modelos.Pedido;
import com.anntony.panaderiahouston.Modelos.RellenarDatos;
import com.anntony.panaderiahouston.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio Facundo on 03/11/2017.
 */

public class Adaptador extends RecyclerView.Adapter<ViewHolder> {


    //Lista que mostrará el RecyclerView
    List<Pedido> listaPedidos;

    //Contexto de la aplicación
    Context context;

    //Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //Flag
    private int flag = -1;

    public Adaptador(Context context) {
        listaPedidos = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("pedi");

        holder.setIsRecyclable(false);
        Glide.with(context)
                .load(R.drawable.ic_menu_gallery)// listaPedidos.get(position).getCliente().getImageUrl()
                .into(holder.imageUrl);
        holder.nombre.setText(context.getString(R.string.clienteHolder)
                .concat(listaPedidos.get(position).getCliente().getNombre()));

        holder.hora.setText(listaPedidos.get(position).getTiempo());
        holder.visto.setChecked(listaPedidos.get(position).getVisto());
        holder.preparando.setChecked(listaPedidos.get(position).getPreparando());
        holder.listo.setChecked(listaPedidos.get(position).getListo());



        if(!listaPedidos.get(position).getVisto())
        holder.visto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listaPedidos.get(position).setVisto(true);
                compoundButton.setClickable(false);
                databaseReference.child(listaPedidos.get(position).getKey()).child("visto").setValue(true);
            }
        });
        else holder.visto.setClickable(false);

        if(!listaPedidos.get(position).getVisto())
            holder.preparando.setClickable(false);
        if(!listaPedidos.get(position).getPreparando())
        holder.preparando.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listaPedidos.get(position).setPreparando(true);
                databaseReference.child(listaPedidos.get(position).getKey()).child("preparando").setValue(true);
            }
        });
        else holder.preparando.setClickable(false);


        if(!listaPedidos.get(position).getVisto() || !listaPedidos.get(position).getPreparando())
            holder.listo.setClickable(false);
        if(!listaPedidos.get(position).getListo())
        holder.listo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listaPedidos.get(position).setListo(true);
                databaseReference.child(listaPedidos.get(position).getKey()).child("listo").setValue(true);
            }
        });
        else holder.listo.setClickable(false);

        holder.imageUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), DetalleActivity.class);
                intent.putExtra("pedido",listaPedidos.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public void adicionarDatos(ArrayList<Pedido> adicionarPedidos) {
        listaPedidos.addAll(adicionarPedidos);
        notifyDataSetChanged();
    }

    public void agregarPedido (Pedido p){
        listaPedidos.add(p);
        notifyItemInserted(listaPedidos.size());
    }

    public void updatePedido(Pedido p){

        for(int i = 0; i < listaPedidos.size(); i++){
            if(listaPedidos.get(i).getKey() == p.getKey()){
                listaPedidos.get(i).setKey(p.getKey());
            }
        }
        notifyDataSetChanged();
    }

}
