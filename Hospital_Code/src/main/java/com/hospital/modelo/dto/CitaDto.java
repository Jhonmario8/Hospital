package com.hospital.modelo.dto;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CitaDto {
    private  int idCita;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String motivo;
    private List<PersonaDto> personas;


    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
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

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public List<PersonaDto> getPersonas() {
        return personas;
    }

    public void setPersonas(List<PersonaDto> personas) {
        this.personas = personas;
    }
}

