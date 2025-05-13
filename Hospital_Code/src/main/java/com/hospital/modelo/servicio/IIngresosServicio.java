package com.hospital.modelo.servicio;



import com.hospital.modelo.dto.IngresoDto;
import com.hospital.modelo.entidad.Ingresos;

import java.util.List;

public interface IIngresosServicio {
    List<IngresoDto> listarTodos();
    void guardar(IngresoDto ingresos);
    Ingresos buscarPorId(Integer id);

    IngresoDto buscarPorPaciente(Integer id);
    void actualizar(IngresoDto ingresoDto);

    void asignar(Integer idIngreso,Integer idHabitacion);
    void eliminar(Integer id);
}
