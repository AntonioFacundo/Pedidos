package com.anntony.panaderiahouston.Detalles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.anntony.panaderiahouston.Modelos.Pedido;
import com.anntony.panaderiahouston.Modelos.Producto;
import com.anntony.panaderiahouston.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalleActivity extends AppCompatActivity {

    @BindView(R.id.txtClientePedido)
    TextView cliente;
    @BindView(R.id.txtHoraPedido)
    TextView hora;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.listPedido)
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        Pedido pedido = extras.getParcelable("pedido");


        String[] objetos = new String[pedido.getPedido().size()];

        int i = 0;
        int precioTotal = 0;
        for(Producto p: pedido.getPedido()){
            objetos[i] = p.getNombre() +" "+  p.getCantidad();
            i++;

            precioTotal += p.getPrecio();
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, objetos);
        lista.setAdapter(adaptador);


        cliente.setText(pedido.getCliente().getNombre());
        hora.setText(pedido.getTiempo());

        total.setText("Total: $" + precioTotal);
    }
}
