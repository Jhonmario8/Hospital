package com.hospital.modelo.entidad;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String motivo;

    @ManyToMany(mappedBy = "citas" , cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Persona> personas=new HashSet<>();


    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int codCita) {
        this.idCita = codCita;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public LocalTime getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(LocalTime horaCita) {
        this.horaCita = horaCita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String horaCita) {
        this.motivo = horaCita;
    }


    public Set<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "idCita=" + idCita +
                ", fechaCita='" + fechaCita + '\'' +
                ", horaCita="+horaCita+
                ", motivo='" + motivo + '\'' +
                ", personas=" + personas+
                '}';
    }
}