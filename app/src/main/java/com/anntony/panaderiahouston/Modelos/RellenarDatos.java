package com.anntony.panaderiahouston.Modelos;

import java.util.ArrayList;

/**
 * Created by Antonio Facundo on 03/11/2017.
 */

public class RellenarDatos {

    static ArrayList<Pedido> lista;
    static ArrayList<Producto> listaProducto;

    public static Pedido darPedido(){

        Cliente cliente = new Cliente("FirebaseCliente","");
        Producto firebaseProducto_1 = new Producto("firebaseProducto1","",2,3.3);
        Producto firebaseProducto_2 = new Producto("firebaseProducto2","",3,4.3);
        ArrayList<Producto> listaProducto = new ArrayList<>();
        listaProducto.add(firebaseProducto_1);
        listaProducto.add(firebaseProducto_2);


        Pedido pedido = new Pedido(cliente, listaProducto,"00:00",false,false,false);
        return pedido;
    }

    public static ArrayList<Pedido> darDatos(){
        lista = new ArrayList<>();
        listaProducto = new ArrayList<>();

        Cliente cliente_1 = new Cliente("Juan", "");
        Cliente cliente_2 = new Cliente("Sandra", "");
        Producto cocaCola = new Producto("Coca cola","", 2, 3);
        Producto fritos = new Producto("Fritos", "", 3, 1);
        Producto pastel = new Producto("Pastel", "", 1, 10);

        listaProducto.add(cocaCola);
        listaProducto.add(fritos);
        listaProducto.add(pastel);

        Pedido pedido = new Pedido(cliente_1,listaProducto, "00:00", false,false,false);

        Pedido pedido_2 = new Pedido(cliente_1,listaProducto, "00:00", false,false,false);
        Pedido pedido_3 = new Pedido(cliente_1,listaProducto, "00:00", false,false,false);
        Pedido pedido_4 = new Pedido(cliente_1,listaProducto, "00:00", false,false,false);
        Pedido pedido_5 = new Pedido(cliente_1,listaProducto, "00:00", false,false,false);
        Pedido pedido_6 = new Pedido(cliente_1,listaProducto, "00:00", false,false,false);
        Pedido pedido_7 = new Pedido(cliente_1,listaProducto, "00:00", false,false,false);
        Pedido pedido_8 = new Pedido(cliente_1,listaProducto, "00:00", false,false,false);
        Pedido pedido_9 = new Pedido(cliente_1,listaProducto, "00:00", false,false,false);

        Pedido pedido_10 = new Pedido(cliente_2, listaProducto, "00:00", false, false, false);


        lista.add(pedido);
        lista.add(pedido_2);
        lista.add(pedido_3);
        lista.add(pedido_4);
        lista.add(pedido_5);
        lista.add(pedido_6);
        lista.add(pedido_7);
        lista.add(pedido_8);
        lista.add(pedido_9);
        lista.add(pedido_10);



        return lista;
    }

}
