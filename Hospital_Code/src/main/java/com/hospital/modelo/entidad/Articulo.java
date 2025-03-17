package com.hospital.modelo.entidad;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="articulo")
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArticulo;
    private String nomArticulo;
    private int cantidad;
    private String descripcion;
    @ManyToMany
    @JoinTable(
            name = "articulo_habitacion",
            joinColumns = @JoinColumn(name = "cod_articulo"),
            inverseJoinColumns = @JoinColumn(name = "cod_habitacion")
    )
    private Set<Habitacion>habitaciones=new HashSet<>();

    public Articulo() {
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNomArticulo() {
        return nomArticulo;
    }

    public void setNomArticulo(String nomArticulo) {
        this.nomArticulo = nomArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitacion(Set<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "idArticulo=" + idArticulo +
                ", nomArticulo='" + nomArticulo + '\'' +
                ", cantidad=" + cantidad +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
