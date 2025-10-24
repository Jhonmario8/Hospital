package com.hospital.modelo.servicio;

import com.hospital.modelo.dto.ArticuloDto;
import com.hospital.modelo.entidad.Articulo;

import com.hospital.modelo.entidad.Habitacion;
import com.hospital.modelo.repositorio.ArticuloRepositorio;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.hospital.modelo.repositorio.HabitacionRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticuloServicio implements IArticuloServicio{

    private final ArticuloRepositorio articuloRepositorio;


    private final HabitacionRepositorio habitacionRepositorio;

    public ArticuloServicio(ArticuloRepositorio articuloRepositorio, HabitacionRepositorio habitacionRepositorio) {
        this.articuloRepositorio = articuloRepositorio;
        this.habitacionRepositorio = habitacionRepositorio;
    }

    @Override
    public List<ArticuloDto> listarTodos(){
       return articuloRepositorio.findAllDtoById();
    }
    @Override
    public void guardar(Articulo articulo){
        articuloRepositorio.save(articulo);
    }

    @Override
    public boolean asignar(int idArt,int idHab,int cantArt){
        Habitacion hab=habitacionRepositorio.findById(idHab).orElse(null);
        Articulo art=articuloRepositorio.findById(idArt).filter(Articulo::isActivo).orElse(null);
        if (art.getCantidad()>=cantArt) {
            if (hab != null && art != null) {
                hab.getArticulos().add(art);
                art.getHabitaciones().add(hab);
                art.setCantidad(art.getCantidad() - cantArt);
                articuloRepositorio.save(art);
            }
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public Articulo buscarPorId(Integer id){
     return articuloRepositorio.findById(id).orElse(null);
    }
    @Override
    public void eliminar(Integer id){
        Optional<Articulo> articuloOptional=articuloRepositorio.findById(id).filter(Articulo::isActivo);
        if (articuloOptional.isPresent()){
            Articulo art= articuloOptional.get();
            art.setActivo(false);
            articuloRepositorio.save(art);
        }
    }
    @Override
    public void activar(Integer id){
        Optional<Articulo> articuloOptional=articuloRepositorio.findById(id);
        if (articuloOptional.isPresent()){
            Articulo art= articuloOptional.get();
            art.setActivo(true);
            articuloRepositorio.save(art);
        }
    }
    @Override
    public List<ArticuloDto> findDtoByNameContaining(String name){
        return articuloRepositorio.findAllByNomArticuloContainingIgnoreCase(name);
    }
}
