
package com.hospital.modelo.entidad;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numHabitacion;
    private String tipoHabitacion;
    private String ocupacion;

    @ManyToMany(mappedBy = "habitaciones")
    private Set<Articulo> articulos=new HashSet<>();

    @OneToMany(mappedBy = "habitacion", cascade= CascadeType.ALL)
    private List<Ingresos> ingresos;

    public Habitacion() {
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String individual) {
        this.ocupacion = individual;
    }

    public Set<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(Set<Articulo> articulos) {
        this.articulos = articulos;
    }

    public List<Ingresos> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Ingresos> ingresos) {
        this.ingresos = ingresos;
    }

    @Override
    public String toString() {
        return "Numero:" + numHabitacion + ", Tipo:" + tipoHabitacion ;
    }
}
    
    

