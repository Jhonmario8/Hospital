package com.hospital.controlador;


import com.hospital.modelo.entidad.Ingresos;
import com.hospital.modelo.servicio.IIngresosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngresoControlador {
    @Autowired
    private IIngresosServicio servicio;

    @GetMapping("ingresos/mostrar")
    public List<Ingresos> mostrar() {
        return servicio.listarTodos();
    }

    @PostMapping("ingresos/guardar")
    public void guardar(@ModelAttribute Ingresos ingreso) {
        servicio.guardar(ingreso);
    }

    @PostMapping("ingresos/buscar/{id}")
    public Ingresos buscar(@PathVariable int id) {
        return servicio.buscarPorId(id);
    }

    @DeleteMapping("ingresos/borrar/{id}")
    public void borrar(@PathVariable int id) {
        servicio.eliminar(id);
    }
}