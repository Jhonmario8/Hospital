
package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.Persona;

import java.util.List;


public interface IPersonaServicio {
    List<Persona> listarTodos();
    void guardar(Persona persona);
    Persona buscarPorId(Integer id);
    void eliminar(Integer id);
}
