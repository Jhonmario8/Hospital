package com.hospital.controlador;

import com.hospital.modelo.entidad.Habitacion;
import com.hospital.modelo.servicio.IHabitacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HabitacionCotrolador {
    @Autowired
    private IHabitacionServicio servicio;

    @GetMapping("habitaciones/mostrar")
    public List<Habitacion> mostrar() {
        return servicio.listarTodos();
    }

    @PostMapping("habitaciones/guardar")
    public Habitacion guardar(@RequestBody Habitacion habitacion) {
       return servicio.guardar(habitacion);
    }

    @PutMapping("habitaciones/actualizar")
    public void actualizar(@RequestBody Habitacion habitacion){
        servicio.guardar(habitacion);
    }
    @GetMapping("habitaciones/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Habitacion hab= servicio.buscarPorId(id);
        if (hab!=null){
            return ResponseEntity.ok(hab);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Habitacion no encontrada");
        }
    }

    @DeleteMapping("habitaciones/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }
}
