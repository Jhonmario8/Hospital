package com.hospital.modelo.entidad;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
public class Persona {
    @Id
    private int idPersona;
    private String nomPersona;
    private int edadPersona;
    private String direccion;
    private String telefonoPersona;
    private boolean tipoPersona;
    private boolean activo=true;


    @ManyToMany
    @JoinTable(
            name = "persona_cita",
            joinColumns = @JoinColumn(name = "cod_persona"),
            inverseJoinColumns = @JoinColumn(name = "cod_cita")
    )
    private List<Cita> citas = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "persona_servicios",
            joinColumns = @JoinColumn(name = "cod_persona"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio")
    )

    private List<Servicio> servicios;

    public Persona() {
    }

    // Getters and Setters
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNomPersona() {
        return nomPersona;
    }

    public void setNomPersona(String nomPersona) {
        this.nomPersona = nomPersona;
    }

    public int getEdadPersona() {
        return edadPersona;
    }

    public void setEdadPersona(int edadPersona) {
        this.edadPersona = edadPersona;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoPersona() {
        return telefonoPersona;
    }

    public void setTelefonoPersona(String telefonoPersona) {
        this.telefonoPersona = telefonoPersona;
    }

    public boolean isTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(boolean tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nomPersona='" + nomPersona + '\'' +
                ", edadPersona=" + edadPersona +
                ", direccion='" + direccion + '\'' +
                ", telefonoPersona='" + telefonoPersona + '\'' +
                ", tipoPersona=" + tipoPersona +
                ", activo=" + activo +
                '}';
    }
}