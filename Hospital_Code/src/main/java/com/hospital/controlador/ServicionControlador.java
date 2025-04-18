package com.hospital.controlador;


import com.hospital.modelo.entidad.Servicio;
import com.hospital.modelo.servicio.IServicioServicio;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServicionControlador {
    @Autowired
    private IServicioServicio servicio;

    @GetMapping("/mostrar")
    public List<Servicio> mostrar() {
        return servicio.listarTodos();
    }

    @PostMapping("/guardar")
    public void guardar(@RequestBody Servicio ser) {
        servicio.guardar(ser);
    }

    @PostMapping("/actualizar")
    public void actualizar(@RequestBody Servicio ser){
        servicio.guardar(ser);
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Servicio ser= servicio.buscarPorId(id);
        if (ser!=null){
            return ResponseEntity.ok(ser);
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servicio no encontrado");
        }
    }
    @PostMapping("/adquirir/{idServicio}/paciente/{idPaciente}")
    public void adquirir(@PathVariable int idServicio,@PathVariable int idPaciente){
        servicio.adquirir(idServicio,idPaciente);
    }
    @GetMapping("/cuenta/{idPersona}")
    public double cuenta(@PathVariable int idPersona){
        return servicio.cuenta(idPersona);
    }
    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }
}
