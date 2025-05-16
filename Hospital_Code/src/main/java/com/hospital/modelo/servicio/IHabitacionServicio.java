package com.hospital.modelo.servicio;

import com.hospital.modelo.dto.HabitacionDto;
import com.hospital.modelo.entidad.Habitacion;
import java.util.List;

public interface IHabitacionServicio {
    List<HabitacionDto> listarTodos();
    List<HabitacionDto> findByIdContaining(String id);
    Habitacion guardar(Habitacion habitacion);
    Habitacion buscarPorId(Integer id);
    void eliminar(Integer id);
}
