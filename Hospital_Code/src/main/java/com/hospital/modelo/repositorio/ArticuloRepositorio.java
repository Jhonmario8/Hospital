package com.hospital.modelo.repositorio;

import com.hospital.modelo.dto.ArticuloDto;
import com.hospital.modelo.entidad.Articulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticuloRepositorio extends CrudRepository<Articulo,Integer> {

    @Query("select new com.hospital.modelo.dto.ArticuloDto(a.idArticulo,a.nomArticulo,a.cantidad,a.descripcion,a.activo) from Articulo a where a.activo=true and a.idArticulo=:id")
    Optional<ArticuloDto> findDtoById(@Param("id") int id);

    @Query("select new com.hospital.modelo.dto.ArticuloDto(a.idArticulo,a.nomArticulo,a.cantidad,a.descripcion,a.activo) from Articulo a where a.activo=false and lower(a.nomArticulo) like lower(concat('%', :name, '%'))")
    List<ArticuloDto> findAllByNomArticuloContainingIgnoreCase(@Param("name") String name);

    @Query("select new com.hospital.modelo.dto.ArticuloDto(a.idArticulo,a.nomArticulo,a.cantidad,a.descripcion,a.activo) from Articulo a where a.activo=true")
    List<ArticuloDto> findAllDtoById();

}
