package com.hospital.controlador;


import com.hospital.modelo.entidad.Cita;
import com.hospital.modelo.servicio.ICitaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaCotrolador {
    @Autowired
    private ICitaServicio servicio;

    @GetMapping("/mostrar")
    public List<Cita> mostrar() {
        return servicio.listarTodos();
    }

    @PostMapping("/guardar/{idPersona}")
    public void guardar(@RequestBody Cita cita, @PathVariable int idPersona) {
        servicio.guardar(cita, idPersona);
    }

    @PostMapping("actualizar/{id}/persona/{idPersona}")
    public void actualizar(@RequestBody Cita cita,@PathVariable int id, @PathVariable int idPersona){
       Cita cit= servicio.buscarPorId(id);
       cit.setFechaCita(cita.getFechaCita());
       cit.setHoraCita(cita.getHoraCita());
       cit.setMotivo(cita.getMotivo());
       servicio.guardar(cit,idPersona);
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Cita cita= servicio.buscarPorId(id);
        if (cita!=null){
            return ResponseEntity.ok(cita);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada");
        }
    }

    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }
}

