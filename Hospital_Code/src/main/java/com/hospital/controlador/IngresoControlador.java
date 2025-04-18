package com.hospital.controlador;



import com.hospital.modelo.entidad.Ingresos;
import com.hospital.modelo.servicio.IIngresosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingresos")
public class IngresoControlador {
    @Autowired
    private IIngresosServicio servicio;

    @GetMapping("/mostrar")
    public List<Ingresos> mostrar() {
        return servicio.listarTodos();
    }

    @PostMapping("/guardar")
    public void guardar(@RequestBody Ingresos ingreso) {
        servicio.guardar(ingreso);
    }
    @PostMapping("/actualizar")
    public void actualizar(@RequestBody Ingresos ingreso){
        servicio.guardar(ingreso);
    }

    @GetMapping("/buscarPaciente/{id}")
    public ResponseEntity<?> buscarPaciente(@PathVariable int id){
        Ingresos ing=servicio.buscarPorPaciente(id);
        if (ing!=null){
            return ResponseEntity.ok(ing);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingreso no encontrado");
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Ingresos in= servicio.buscarPorId(id);
        if (in!=null){
            return  ResponseEntity.ok(in);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingreso no encontrado");
        }
    }

    @PostMapping("/asignar/{idIngreso}/habitacion/{idHabitacion}")
    public void asignar(@PathVariable int idIngreso,@PathVariable int idHabitacion){
        servicio.asignar(idIngreso,idHabitacion);
    }


    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }
}