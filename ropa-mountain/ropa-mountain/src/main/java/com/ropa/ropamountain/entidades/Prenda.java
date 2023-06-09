package com.ropa.ropamountain.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Prenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String marca;
    private double precio;
    private List<String> imagenes;
    List<String> caracteristicas;

    public Prenda() {
    }

    public Prenda(String nombre, String marca, double precio, List<String> imagenes, List<String> caracteristicas) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.imagenes = imagenes;
        this.caracteristicas = caracteristicas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public List<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
