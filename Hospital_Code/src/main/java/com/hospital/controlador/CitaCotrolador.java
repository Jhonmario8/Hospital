package com.hospital.controlador;


import com.hospital.modelo.entidad.Cita;
import com.hospital.modelo.servicio.ICitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void guardar(@ModelAttribute Cita cita) {
        servicio.guardar(cita);
    }

    @PostMapping("citas/buscar/{id}")
    public Cita buscar(@PathVariable int id) {
        return servicio.buscarPorId(id);
    }

    @DeleteMapping("citas/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }
}
