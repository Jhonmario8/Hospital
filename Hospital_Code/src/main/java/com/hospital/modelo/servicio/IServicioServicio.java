package com.hospital.modelo.servicio;



import com.hospital.modelo.entidad.Servicio;

import java.util.List;

public interface IServicioServicio {
    List<Servicio> listarTodos();
    void guardar(Servicio servicio);
    Servicio buscarPorId(Integer id);

    void adquirir(int idServicio,int idPaciente);
    void eliminar(Integer id);
}
