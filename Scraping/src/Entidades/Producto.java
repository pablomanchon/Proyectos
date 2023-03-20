package Entidades;

import javax.swing.*;

public class Producto {
    private String nombre;
    private String marca;
    private Integer precio;
    private String ID;
    private ImageIcon imagen;

    public Producto() {
    }

    public Producto(String nombre, String marca, Integer precio, String ID, ImageIcon imagen) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.ID = ID;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                ", ID='" + ID + '\'' +
                ", imagen=" + imagen +
                '}';
    }
}
