
package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.Paciente;
import java.util.List;


public interface IPacienteServicio {
    List<Paciente> listarTodos();
    void guardar(Paciente paciente);
    Paciente buscarPorId(Integer id);
    void eliminar(Integer id);
}
