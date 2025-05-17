package com.hospital.controlador;



import com.hospital.modelo.dto.IngresoDto;
import com.hospital.modelo.entidad.Ingresos;
import com.hospital.modelo.servicio.IIngresosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingresos")
public class IngresoControlador {

    private final IIngresosServicio servicio;

    public IngresoControlador(IIngresosServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/mostrar")
    public List<IngresoDto> mostrar() {
        return servicio.listarTodos();
    }

    @PostMapping("/guardar")
    public void guardar(@RequestBody IngresoDto ingreso) {
        servicio.guardar(ingreso);
    }
    @PostMapping("/actualizar")
    public void actualizar(@RequestBody IngresoDto ingreso){
        servicio.actualizar(ingreso);
    }

    @GetMapping("/buscarPaciente/{id}")
    public ResponseEntity<?> buscarPaciente(@PathVariable int id){
        IngresoDto ing=servicio.buscarPorPaciente(id);
        if (ing!=null){
            return ResponseEntity.ok(ing);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingreso no encontrado");
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Ingresos in= servicio.buscarPorId(id);
        if (in!=null){
            return  ResponseEntity.ok(in);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingreso no encontrado");
        }
    }

    @PostMapping("/asignar/{idIngreso}/habitacion/{idHabitacion}")
    public void asignar(@PathVariable int idIngreso,@PathVariable int idHabitacion){
        servicio.asignar(idIngreso,idHabitacion);
    }


    @DeleteMapping("/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }
}