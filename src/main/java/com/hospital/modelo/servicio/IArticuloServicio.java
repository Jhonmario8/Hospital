package com.hospital.modelo.servicio;

import com.hospital.modelo.dto.ArticuloDto;
import com.hospital.modelo.entidad.Articulo;
import java.util.List;

public interface IArticuloServicio {
    List<ArticuloDto> listarTodos();
    void guardar(Articulo articulo);

    boolean asignar(int idArt,int idHab,int cantArt);
    Articulo buscarPorId(Integer id);
    void eliminar(Integer id);
    void activar(Integer id);
    List<ArticuloDto> findDtoByNameContaining(String name);
}
