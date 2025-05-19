package com.hospital.controlador;


import com.hospital.modelo.dto.PersonaDto;
import com.hospital.modelo.dto.ServicioDto;
import com.hospital.modelo.entidad.Persona;
import com.hospital.modelo.servicio.IPersonaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaCotrolador {

    private final IPersonaServicio servicio;

    public PersonaCotrolador(IPersonaServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("personas/mostrar")
    public List<PersonaDto> mostrar() {
        return servicio.listarTodos();
    }
    @GetMapping("personas/empleados")
    public List<PersonaDto> empleados(){
        return servicio.listarEmpleados();
    }

    @GetMapping("persona/pacientes")
    public List<PersonaDto> pacientes(){
        return servicio.listarPacientes();
    }

    @GetMapping("personas/containing/{id}")
    public ResponseEntity<?> findByIdContaining(@PathVariable String id){
        List<PersonaDto> personaDtos= servicio.findByIdContaining(id);
        if (personaDtos.size()>0){
            return ResponseEntity.ok(personaDtos);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro ningun paciente");
        }
    }
    @PostMapping("personas/guardar")
    public void guardar(@RequestBody PersonaDto persona) {
        Persona per=new Persona();
        per.setIdPersona(persona.getIdPersona());
        per.setTelefonoPersona(persona.getTelefonoPersona());
        per.setNomPersona(persona.getNomPersona());
        per.setTipoPersona(persona.isTipoPersona());
        per.setDireccion(persona.getDireccion());
        per.setEdadPersona(persona.getEdadPersona());
        servicio.guardar(per);
    }

    @PutMapping("personas/actualizar")
    public void actualizar(@RequestBody PersonaDto persona) {
        Persona per=new Persona();
        per.setIdPersona(persona.getIdPersona());
        per.setTelefonoPersona(persona.getTelefonoPersona());
        per.setNomPersona(persona.getNomPersona());
        per.setTipoPersona(persona.isTipoPersona());
        per.setDireccion(persona.getDireccion());
        per.setEdadPersona(persona.getEdadPersona());
        servicio.guardar(per);
    }

    @GetMapping("personas/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        PersonaDto per= servicio.buscarPorId(id);
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

    @GetMapping("personas/inactivo/{id}")
    public ResponseEntity<?> inactivos(@PathVariable int id){
        return servicio.buscarInactivo(id);
    }

    @PostMapping("personas/activar/{id}")
    public void activos(@PathVariable int id){
        servicio.activar(id);
    }

    @GetMapping("personas/tieneCita/{id}")
    public boolean tieneCita(@PathVariable int id){
        return servicio.tieneCita(id);
    }

    @GetMapping("personaas/getServicios/{id}")
    public List<ServicioDto> getServicios(@PathVariable int id){
        return servicio.serviciosPersona(id);
    }

}