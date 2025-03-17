package com.hospital.modelo.servicio;



import com.hospital.modelo.entidad.Cita;
import com.hospital.modelo.entidad.Empleado;

import java.util.List;
import java.util.Set;

public interface ICitaServicio {
    List<Cita> listarTodos();
    void guardar(Cita cita);
    Cita buscarPorId(Integer id);

    void eliminar(Integer id);
}
