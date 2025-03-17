package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.Articulo;

import com.hospital.modelo.repositorio.ArticuloRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticuloServicio implements IArticuloServicio{
    @Autowired
    private ArticuloRepositorio articuloRepositorio;
    @Override
    public List<Articulo> listarTodos(){
        return (List<Articulo>)articuloRepositorio.findAll();
    }
    @Override

    public void guardar(Articulo articulo){
        articuloRepositorio.save(articulo);
    }
    @Override
    public Articulo buscarPorId(Integer id){
     return articuloRepositorio.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id){
        articuloRepositorio.deleteById(id);
    }
}
