package com.anntony.panaderiahouston.Modelos;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by Antonio Facundo on 03/11/2017.
 */

public class Producto implements Parcelable {

    private String nombre;
    private String image;
    private int cantidad;
    private double precio;
    private String categoria;

    public Producto() {
    }

    public Producto(String nombre, String image, int cantidad, double precio) {
        this.nombre = nombre;
        this.image = image;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    protected Producto(Parcel in) {
        nombre = in.readString();
        image = in.readString();
        cantidad = in.readInt();
        precio = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(image);
        parcel.writeInt(cantidad);
        parcel.writeDouble(precio);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Producto> CREATOR = new Parcelable.Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };
}