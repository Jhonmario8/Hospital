package com.hospital.controlador;


import com.hospital.modelo.entidad.Articulo;
import com.hospital.modelo.servicio.IArticuloServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticuloCotrolador {
    @Autowired
    private IArticuloServicio servicio;

    @GetMapping("articulos/mostrar")
    public List<Articulo> mostrar(){
        return servicio.listarTodos();
    }
    @PostMapping("articulos/guardar")
    public void guardar(@ModelAttribute Articulo articulo){
        servicio.guardar(articulo);
    }
    @PostMapping("articulos/buscar/{id}")
    public Articulo buscar(@PathVariable int id){
        return servicio.buscarPorId(id);
    }
    @DeleteMapping("articulos/borrar/{id}")
    public void borrar(@PathVariable int id){
        servicio.eliminar(id);
    }
}