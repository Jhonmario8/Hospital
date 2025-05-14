package com.hospital.controlador;


import com.hospital.modelo.dto.CitaDto;
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
    public List<CitaDto> mostrar() {
        return servicio.listarTodos();
    }

    @PostMapping("/guardar/{idPersona}")
    public void guardar(@RequestBody CitaDto citaDto, @PathVariable int idPersona) {
        Cita cita=new Cita();
        if (citaDto.getIdCita()!=0){
            cita.setIdCita(citaDto.getIdCita());
        }
        cita.setFechaCita(citaDto.getFechaCita());
        cita.setHoraCita(citaDto.getHoraCita());
        cita.setMotivo(citaDto.getMotivo());
        servicio.guardar(cita, idPersona);
    }

    @PostMapping("/actualizar")
    public void actualizar(@RequestBody CitaDto citaDto){
        Cita cita=servicio.buscarPorId(citaDto.getIdCita());
        cita.setIdCita(citaDto.getIdCita());
        cita.setFechaCita(citaDto.getFechaCita());
        cita.setHoraCita(citaDto.getHoraCita());
        cita.setMotivo(citaDto.getMotivo());
        servicio.actualizar(cita);
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

