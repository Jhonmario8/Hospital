
package com.hospital.modelo.entidad;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="servicios")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codServicio;
    private String nomServicio;
    private double precioServicio;
    private String detallesServicio;
    @ManyToMany(mappedBy = "servicios", cascade = CascadeType.ALL)
    private List<Paciente> pacientes;

    public Servicio() {
    }

    public int getCodServicio() {
        return codServicio;
    }

    public void setCodServicio(int codServicio) {
        this.codServicio = codServicio;
    }

    public String getNomServicio() {
        return nomServicio;
    }

    public void setNomServicio(String nomServicio) {
        this.nomServicio = nomServicio;
    }

    public double getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(double precioServicio) {
        this.precioServicio = precioServicio;
    }

    public String getDetallesServicio() {
        return detallesServicio;
    }

    public void setDetallesServicio(String detallesServicio) {
        this.detallesServicio = detallesServicio;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "codServicio=" + codServicio +
                ", nomServicio='" + nomServicio + '\'' +
                ", precioServicio=" + precioServicio +
                ", detallesServicio='" + detallesServicio + '\'' +
                ", pacientes=" + pacientes +
                '}';
    }
}
