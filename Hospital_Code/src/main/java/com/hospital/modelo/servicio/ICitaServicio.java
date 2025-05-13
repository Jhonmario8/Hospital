package com.hospital.modelo.servicio;



import com.hospital.modelo.dto.CitaDto;
import com.hospital.modelo.entidad.Cita;


import java.util.List;


public interface ICitaServicio {
    List<CitaDto> listarTodos();
    void guardar(Cita cita,int idPersona);
    Cita buscarPorId(Integer id);

    void actualizar(Cita cita);

    void eliminar(Integer id);
}
