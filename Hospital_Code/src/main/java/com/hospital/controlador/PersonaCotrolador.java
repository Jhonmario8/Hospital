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

    @GetMapping("servicios/mostrar")
    public List<Persona> mostrar() {
        return servicio.listarTodos();
    }

    @PostMapping("servicios/guardar")
    public void guardar(@ModelAttribute Persona persona) {
        servicio.guardar(persona);
    }

    @PostMapping("servicios/buscar/{id}")
    public Persona buscar(@PathVariable int id) {
        return servicio.buscarPorId(id);
    }

    @DeleteMapping("servicios/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }

}
