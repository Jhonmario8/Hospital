
package com.hospital.modelo.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario_sesion")
public class UsuarioSesion {
    @Id
    private String usuario;
    private String contraseña;

    public UsuarioSesion() {
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "UsuarioSesion{" + "usuario=" + usuario + ", contrase\u00f1a=" + contraseña + '}';
    }

    
    
    
}
