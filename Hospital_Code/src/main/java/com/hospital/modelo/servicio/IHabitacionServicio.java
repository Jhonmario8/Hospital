package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.Habitacion;
import java.util.List;

public interface IHabitacionServicio {
    List<Habitacion> listarTodos();
    Habitacion guardar(Habitacion habitacion);
    Habitacion buscarPorId(Integer id);
    void eliminar(Integer id);
}
