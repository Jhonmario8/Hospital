package com.hospital.modelo.entidad;

        import jakarta.persistence.*;
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
    private String eps;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Cita> citas;

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

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
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

}