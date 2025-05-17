package com.hospital.controlador;


import com.hospital.modelo.dto.ArticuloDto;
import com.hospital.modelo.entidad.Articulo;
import com.hospital.modelo.servicio.IArticuloServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articulos")
public class ArticuloCotrolador {

    private final IArticuloServicio servicio;

    public ArticuloCotrolador(IArticuloServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/mostrar")
    public List<Articulo> mostrar(){
        return servicio.listarTodos();
    }
    @PostMapping("/guardar")
    public void guardar(@RequestBody ArticuloDto articulo){
        Articulo art=new Articulo();
        art.setNomArticulo(articulo.getNomArticulo());
        art.setCantidad(articulo.getCantidad());
        art.setDescripcion(articulo.getDescripcion());
        servicio.guardar(art);
    }
    @PostMapping("/actualizar")
    public void actualizar(@RequestBody ArticuloDto articulo){
        Articulo art=new Articulo();
        art.setIdArticulo(articulo.getIdArticulo());
        art.setNomArticulo(articulo.getNomArticulo());
        art.setCantidad(articulo.getCantidad());
        art.setDescripcion(articulo.getDescripcion());
        servicio.guardar(art);
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id){
        Articulo art=servicio.buscarPorId(id);
        if (art!=null){
            return ResponseEntity.ok(art);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Articulo no encontrado");
        }
    }
    @PostMapping("/asignar/{idArt}/habitacion/{idHab}/cantidad/{cantidad}")
    public boolean asignar(@PathVariable int idArt,@PathVariable int idHab,@PathVariable int cantidad){
       return servicio.asignar(idArt,idHab,cantidad);
    }
    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable int id){
        servicio.eliminar(id);
    }
}