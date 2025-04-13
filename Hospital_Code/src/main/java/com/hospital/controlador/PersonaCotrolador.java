package com.hospital.controlador;


import com.hospital.modelo.entidad.Persona;
import com.hospital.modelo.servicio.IPersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("personas/empleados")
    public List<Persona> empleados(){
        return servicio.listarEmpleados();
    }

    @GetMapping("persona/pacientes")
    public List<Persona> pacientes(){
        return servicio.listarPacientes();
    }

    @PostMapping("personas/guardar")
    public void guardar(@RequestBody Persona persona) {
        servicio.guardar(persona);
    }

    @PutMapping("personas/actualizar")
    public void actualizar(@RequestBody Persona persona) {
        servicio.guardar(persona);
    }

    @GetMapping("personas/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Persona per= servicio.buscarPorId(id);
        if (per!=null){
            return ResponseEntity.ok(per);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
    }

    @DeleteMapping("personas/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }

}
