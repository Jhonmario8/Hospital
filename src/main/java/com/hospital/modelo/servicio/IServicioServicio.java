package com.hospital.modelo.servicio;



import com.hospital.modelo.dto.ServicioDto;
import com.hospital.modelo.entidad.Servicio;

import java.util.List;

public interface IServicioServicio {
    List<ServicioDto> listarTodos();
    void guardar(Servicio servicio);
    Servicio buscarPorId(Integer id);

    void adquirir(int idServicio,int idPaciente);

    double cuenta(Integer idPaciente);
    void eliminar(Integer id);
}
