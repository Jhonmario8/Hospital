package com.hospital.modelo.entidad;



import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cita")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idCita"
)
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String motivo;

    @JsonManagedReference
    @ManyToMany(mappedBy = "citas", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Persona> personas = new ArrayList<>();


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


    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
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