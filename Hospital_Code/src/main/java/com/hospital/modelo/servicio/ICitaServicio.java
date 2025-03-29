package com.hospital.modelo.servicio;



import com.hospital.modelo.entidad.Cita;


import java.util.List;


public interface ICitaServicio {
    List<Cita> listarTodos();
    void guardar(Cita cita);
    Cita buscarPorId(Integer id);

    void eliminar(Integer id);
}
