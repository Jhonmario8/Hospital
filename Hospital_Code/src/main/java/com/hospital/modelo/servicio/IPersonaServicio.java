
package com.hospital.modelo.servicio;

import com.hospital.modelo.dto.PersonaDto;
import com.hospital.modelo.dto.ServicioDto;
import com.hospital.modelo.entidad.Persona;
import com.hospital.modelo.entidad.Servicio;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IPersonaServicio {
    List<PersonaDto> listarTodos();
    void guardar(Persona persona);
    PersonaDto buscarPorId(Integer id);
    List<PersonaDto> listarEmpleados();
    List<PersonaDto> listarPacientes();
    void eliminar(Integer id);
    ResponseEntity buscarInactivo(Integer id);
    void activar(Integer id);
    void actualizar(Persona persona);
    boolean tieneCita(Integer id);

    List<ServicioDto> serviciosPersona(Integer id);
}