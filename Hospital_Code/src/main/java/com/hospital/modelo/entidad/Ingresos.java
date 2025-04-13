package com.hospital.modelo.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "ingresos")
public class Ingresos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIngreso;

    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "idPersona")
    private Persona persona;
    private String ciudad;
    private String motivo;
    private Boolean acompañante;
    private Boolean hospitalizado=false;
    @ManyToOne
    @JoinColumn(name = "cod_habitacion", nullable = true)
    private Habitacion habitacion;

    public Ingresos() {
    }

    public int getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Boolean getAcompañante() {
        return acompañante;
    }

    public void setAcompañante(Boolean acompañante) {
        this.acompañante = acompañante;
    }

    public Boolean getHospitalizado() {
        return hospitalizado;
    }

    public void setHospitalizado(Boolean hospitalizado) {
        this.hospitalizado = hospitalizado;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
}
