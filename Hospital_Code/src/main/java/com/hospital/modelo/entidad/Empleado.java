package com.hospital.modelo.entidad;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    private int idEmpleado;
    private String nomEmpleado;
    private String apeEmpleado;
    private int edadEmpleado;
    private String telefonoEmpleado;
    private String fechaEmpleado;

    @ManyToMany
    @JoinTable(
            name = "empleado_cita",
            joinColumns = @JoinColumn(name = "cod_empleado"),
            inverseJoinColumns = @JoinColumn(name = "cod_cita")
    )
    private Set<Cita> citas;


    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNomEmpleado() {
        return nomEmpleado;
    }

    public void setNomEmpleado(String nomEmpleado) {
        this.nomEmpleado = nomEmpleado;
    }

    public String getApeEmpleado() {
        return apeEmpleado;
    }

    public void setApeEmpleado(String apeEmpleado) {
        this.apeEmpleado = apeEmpleado;
    }

    public int getEdadEmpleado() {
        return edadEmpleado;
    }

    public void setEdadEmpleado(int edadEmpleado) {
        this.edadEmpleado = edadEmpleado;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    public String getFechaEmpleado() {
        return fechaEmpleado;
    }

    public void setFechaEmpleado(String fechaEmpleado) {
        this.fechaEmpleado = fechaEmpleado;
    }

    public Set<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "Empleado{ " +
                "ID: " + idEmpleado +
                ", Nombre: " + nomEmpleado + ' '+ apeEmpleado +
                '}';
    }
}
