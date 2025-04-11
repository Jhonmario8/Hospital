package com.hospital.controlador;


import com.hospital.modelo.entidad.Cita;
import com.hospital.modelo.servicio.ICitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CitaCotrolador {
    @Autowired
    private ICitaServicio servicio;

    @GetMapping("citas/mostrar")
    public List<Cita> mostrar() {
        return servicio.listarTodos();
    }

    @PostMapping("citas/guardar")
    public void guardar(@RequestBody Cita cita) {
        servicio.guardar(cita);
    }

    @PostMapping("citas/actualizar")
    public void actualizar(@RequestBody Cita cita){
        servicio.guardar(cita);
    }


    @PostMapping("citas/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Cita cita= servicio.buscarPorId(id);
        if (cita!=null){
            return ResponseEntity.ok(cita);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada");
        }
    }

    @DeleteMapping("citas/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }
}
