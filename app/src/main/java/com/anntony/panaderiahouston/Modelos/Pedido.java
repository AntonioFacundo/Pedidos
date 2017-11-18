package com.anntony.panaderiahouston.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Antonio Facundo on 03/11/2017.
 */

public class Pedido implements Parcelable {

    private Cliente cliente;
    private ArrayList<Producto> pedido;
    private String tiempo;
    private Boolean visto;
    private Boolean preparando;
    private Boolean listo;
    private String key;

    public Pedido() {
    }

    public Pedido(Cliente cliente, ArrayList<Producto> pedido, String tiempo, Boolean visto, Boolean preparando, Boolean listo) {
        this.cliente = cliente;
        this.pedido = pedido;
        this.tiempo = tiempo;
        this.visto = visto;
        this.preparando = preparando;
        this.listo = listo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Producto> getPedido() {
        return pedido;
    }

    public void setPedido(ArrayList<Producto> pedido) {
        this.pedido = pedido;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public Boolean getVisto() {
        return visto;
    }

    public void setVisto(Boolean visto) {
        this.visto = visto;
    }

    public Boolean getPreparando() {
        return preparando;
    }

    public void setPreparando(Boolean preparando) {
        this.preparando = preparando;
    }

    public Boolean getListo() {
        return listo;
    }

    public void setListo(Boolean listo) {
        this.listo = listo;
    }


    protected Pedido(Parcel in) {
        cliente = (Cliente) in.readValue(Cliente.class.getClassLoader());
        if (in.readByte() == 0x01) {
            pedido = new ArrayList<Producto>();
            in.readList(pedido, Producto.class.getClassLoader());
        } else {
            pedido = null;
        }
        tiempo = in.readString();
        byte vistoVal = in.readByte();
        visto = vistoVal == 0x02 ? null : vistoVal != 0x00;
        byte preparandoVal = in.readByte();
        preparando = preparandoVal == 0x02 ? null : preparandoVal != 0x00;
        byte listoVal = in.readByte();
        listo = listoVal == 0x02 ? null : listoVal != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(cliente);
        if (pedido == null) {
            parcel.writeByte((byte) (0x00));
        } else {
            parcel.writeByte((byte) (0x01));
            parcel.writeList(pedido);
        }
        parcel.writeString(tiempo);
        if (visto == null) {
            parcel.writeByte((byte) (0x02));
        } else {
            parcel.writeByte((byte) (visto ? 0x01 : 0x00));
        }
        if (preparando == null) {
            parcel.writeByte((byte) (0x02));
        } else {
            parcel.writeByte((byte) (preparando ? 0x01 : 0x00));
        }
        if (listo == null) {
            parcel.writeByte((byte) (0x02));
        } else {
            parcel.writeByte((byte) (listo ? 0x01 : 0x00));
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Pedido> CREATOR = new Parcelable.Creator<Pedido>() {
        @Override
        public Pedido createFromParcel(Parcel in) {
            return new Pedido(in);
        }

        @Override
        public Pedido[] newArray(int size) {
            return new Pedido[size];
        }
    };
}
