package com.hospital.modelo.entidad;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;
    private LocalDateTime fechaCita;
    private String motivo;

    @ManyToMany(mappedBy = "citas" , cascade = CascadeType.ALL)
    private Set<Persona> personas=new HashSet<>();


    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int codCita) {
        this.idCita = codCita;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
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
                ", horaCita='" + motivo + '\'' +
                '}';
    }
}