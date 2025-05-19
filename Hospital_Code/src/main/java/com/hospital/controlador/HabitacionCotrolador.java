package com.hospital.controlador;

import com.hospital.modelo.dto.HabitacionDto;
import com.hospital.modelo.entidad.Habitacion;
import com.hospital.modelo.servicio.IHabitacionServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HabitacionCotrolador {

    private final IHabitacionServicio servicio;

    public HabitacionCotrolador(IHabitacionServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("habitaciones/mostrar")
    public List<HabitacionDto> mostrar() {
        return servicio.listarTodos();
    }

    @GetMapping("/habitaciones/containing/{id}")
    public ResponseEntity<?> findByIdContainig(@PathVariable String id){
        List<HabitacionDto> habitacionDtos= servicio.findByIdContaining(id);
        if (habitacionDtos.size()>0){
            return ResponseEntity.ok(habitacionDtos);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ninguna habitacion");
        }
    }

    @PostMapping("habitaciones/guardar")
    public Habitacion guardar(@RequestBody HabitacionDto habitacion) {
        Habitacion hab=new Habitacion();
        hab.setTipoHabitacion(habitacion.getTipoHabitacion());
        hab.setCapacidad(habitacion.getCapacidad());
       return servicio.guardar(hab);
    }

    @PutMapping("habitaciones/actualizar")
    public void actualizar(@RequestBody HabitacionDto habitacion) {
        Habitacion hab=new Habitacion();
        hab.setNumHabitacion(habitacion.getNumHabitacion());
        hab.setTipoHabitacion(habitacion.getTipoHabitacion());
        hab.setCapacidad(habitacion.getCapacidad());
        servicio.guardar(hab);
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
