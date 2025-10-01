package com.hospital.modelo.entidad;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "ingresos")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idIngreso"
)
public class Ingresos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idIngreso;

    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "idPersona")
    private Persona persona;
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
