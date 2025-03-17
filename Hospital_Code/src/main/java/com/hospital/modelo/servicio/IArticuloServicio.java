package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.Articulo;
import java.util.List;

public interface IArticuloServicio {
    List<Articulo> listarTodos();
    void guardar(Articulo articulo);
    Articulo buscarPorId(Integer id);
    void eliminar(Integer id);
}
