package com.hospital.modelo.servicio;



import com.hospital.modelo.entidad.Ingresos;

import java.util.List;

public interface IIngresosServicio {
    List<Ingresos> listarTodos();
    void guardar(Ingresos ingresos);
    Ingresos buscarPorId(Integer id);

    Ingresos buscarPorPaciente(Integer id);

    void asignar(Integer idIngreso,Integer idHabitacion);
    void eliminar(Integer id);
}
