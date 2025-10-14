package com.hospital.controlador;

import com.hospital.modelo.dto.AnalisisDatosDto;
import com.hospital.modelo.servicio.IAnalisisDatosServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("analisis")
public class AnalisisControlador {

    private final IAnalisisDatosServicio servicio;

    public AnalisisControlador(IAnalisisDatosServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/resumen")
    public AnalisisDatosDto obtenerResumen() {
        return servicio.obtenerResumen();
    }
}
