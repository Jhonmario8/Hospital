package com.hospital.controlador;


import com.hospital.modelo.entidad.Servicio;
import com.hospital.modelo.servicio.IServicioServicio;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServicionControlador {
    @Autowired
    private IServicioServicio servicio;

    @GetMapping("servicios/mostrar")
    public List<Servicio> mostrar() {
        return servicio.listarTodos();
    }

    @PostMapping("servicios/guardar")
    public void guardar(@RequestBody Servicio ser) {
        servicio.guardar(ser);
    }

    @PostMapping("servicios/actualizar")
    public void actualizar(@RequestBody Servicio ser){
        servicio.guardar(ser);
    }
    @PostMapping("servicios/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Servicio ser= servicio.buscarPorId(id);
        if (ser!=null){
            return ResponseEntity.ok(ser);
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servicio no encontrado");
        }
    }

    @DeleteMapping("servicios/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }
}
