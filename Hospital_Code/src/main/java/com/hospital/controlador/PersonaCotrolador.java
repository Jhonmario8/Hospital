package com.hospital.controlador;


import com.hospital.modelo.entidad.Persona;
import com.hospital.modelo.servicio.IPersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaCotrolador {
    @Autowired
    private IPersonaServicio servicio;

    @GetMapping("personas/mostrar")
    public List<Persona> mostrar() {
        return servicio.listarTodos();
    }

    @PostMapping("personas/guardar")
    public void guardar(@RequestBody Persona persona) {
        servicio.guardar(persona);
    }

    @PostMapping("personas/buscar/{id}")
    public Persona buscar(@PathVariable int id) {
        return servicio.buscarPorId(id);
    }

    @DeleteMapping("personas/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }

}
