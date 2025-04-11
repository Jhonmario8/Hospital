package com.hospital.controlador;


import com.hospital.modelo.entidad.Cita;
import com.hospital.modelo.entidad.Ingresos;
import com.hospital.modelo.servicio.IIngresosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngresoControlador {
    @Autowired
    private IIngresosServicio servicio;

    @GetMapping("ingresos/mostrar")
    public List<Ingresos> mostrar() {
        return servicio.listarTodos();
    }

    @PostMapping("ingresos/guardar")
    public void guardar(@RequestBody Ingresos ingreso) {
        servicio.guardar(ingreso);
    }
    @PostMapping("ingresos/actualizar")
    public void actualizar(@RequestBody Ingresos ingreso){
        servicio.guardar(ingreso);
    }

    @PostMapping("ingresos/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Ingresos in= servicio.buscarPorId(id);
        if (in!=null){
            return  ResponseEntity.ok(in);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingreso no encontrado");
        }
    }

    @DeleteMapping("ingresos/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }
}