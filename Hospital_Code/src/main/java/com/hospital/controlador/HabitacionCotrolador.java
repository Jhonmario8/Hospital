package com.hospital.controlador;

import com.hospital.modelo.entidad.Habitacion;
import com.hospital.modelo.servicio.IHabitacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void guardar(@ModelAttribute Habitacion habitacion) {
        servicio.guardar(habitacion);
    }

    @PostMapping("habitaciones/buscar/{id}")
    public Habitacion buscar(@PathVariable int id) {
        return servicio.buscarPorId(id);
    }

    @DeleteMapping("habitaciones/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }
}
