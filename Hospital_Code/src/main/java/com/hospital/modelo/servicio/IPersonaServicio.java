
package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.Persona;

import java.util.List;


public interface IPersonaServicio {
    List<Persona> listarTodos();
    void guardar(Persona persona);
    Persona buscarPorId(Integer id);
    List<Persona> listarEmpleados();
    List<Persona> listarPacientes();
    void eliminar(Integer id);
    Persona buscarInactivo(Integer id);
    void activar(Integer id);
    void actualizar(Persona persona);
}