package com.hospital.controlador;


import com.hospital.modelo.entidad.Articulo;
import com.hospital.modelo.servicio.IArticuloServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void guardar(@RequestBody Articulo articulo){
        servicio.guardar(articulo);
    }
    @PostMapping("articulo/actualizar/")
    public void actualizar(@RequestBody Articulo articulo){
        servicio.guardar(articulo);
    }
    @PostMapping("articulos/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id){
        Articulo art=servicio.buscarPorId(id);
        if (art!=null){
            return ResponseEntity.ok(art);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Articulo no encontrado");
        }
    }
    @DeleteMapping("articulos/borrar/{id}")
    public void borrar(@PathVariable int id){
        servicio.eliminar(id);
    }
}