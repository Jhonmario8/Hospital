package com.hospital.modelo.entidad;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;
    private String fechaCita;
    private String horaCita;

    @ManyToOne
    @JoinColumn(name = "cod_paciente", nullable = false)
    private  Paciente paciente;

    @ManyToMany(mappedBy = "citas")
    private Set<Empleado> empleados=new HashSet<>();


    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int codCita) {
        this.idCita = codCita;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "idCita=" + idCita +
                ", fechaCita='" + fechaCita + '\'' +
                ", horaCita='" + horaCita + '\'' +
                ", paciente=" + paciente +
                '}';
    }
}