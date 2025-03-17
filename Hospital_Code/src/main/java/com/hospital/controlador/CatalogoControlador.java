package com.hospital.controlador;

import com.hospital.modelo.entidad.Habitacion;
import com.hospital.modelo.entidad.Ingresos;
import com.hospital.modelo.entidad.Servicio;
import com.hospital.modelo.servicio.IHabitacionServicio;
import com.hospital.modelo.servicio.IServicioServicio;
import com.hospital.modelo.servicio.IngresosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CatalogoControlador {

    @Autowired
    private IServicioServicio servicioServicio;
    @Autowired
    private IHabitacionServicio habitacionServicio;
    @Autowired
    private IngresosServicio ingresosServicio;
    private String[] imagenes={"https://img.freepik.com/fotos-premium/habitacion-hospital-moderna-lujo-vacia_1205263-152235.jpg",
    "https://img.freepik.com/fotos-premium/habitacion-hospital-moderna-lujo-vacia_1205263-153420.jpg",
    "https://img.freepik.com/foto-gratis/escena-atencion-medica-hospital-dibujos-animados-3d_23-2151644110.jpg",
    "https://img.freepik.com/premium-photo/3d-cartoon-hospital-room-with-bed-machines-equipment-diagnosis-treatment-concept-hospital-room-design-3d-cartoon-medical-equipment-diagnosis-treatment-bed-machines_918839-288478.jpg",
    "https://img.freepik.com/fotos-premium/interior-habitacion-hospital-moderna_693425-2480.jpg",
    "https://img.freepik.com/fotos-premium/habitacion-hospital-moderna-gran-ventana_604472-38151.jpg"};


    @GetMapping("/catalogo")
    public String catalogo(){
        return "Catalogo/catalogo";
    }

    @GetMapping("/servicios")
    public String servicios(Model modelo){
       List<Servicio>servicios=servicioServicio.listarTodos();
        modelo.addAttribute("servicios",servicios);
        return "Catalogo/servicios";
    }
    @PostMapping("/servicios")
    public String servicios(@ModelAttribute Servicio servicio,Model modelo){
        modelo.addAttribute("codigo",servicio.getCodServicio());
        return "GestionServicios/adquirirservicio";
    }
    @GetMapping("/habitaciones/{id}")
    public String habitaciones(@PathVariable("id")int idPaciente, Model modelo) {
        int x=0;
        Ingresos ingreso=ingresosServicio.buscarPorId(idPaciente);
        modelo.addAttribute("idPaciente",idPaciente);
        List<Habitacion> habitaciones = habitacionServicio.listarTodos();
        Map<Habitacion, String> habitacionImg = new HashMap<>();
        for (Habitacion habitacion : habitaciones) {
            String imagen = imagenes[x];
            habitacionImg.put(habitacion, imagen);
            x++;
        }
        modelo.addAttribute("habitacionimg", habitacionImg);
        return "Catalogo/habitaciones";
    }

    @PostMapping("/seleccionar")
    public String seleccionar(@RequestParam(name = "numHabitacion")int idHabitacion,
                              @RequestParam(name = "idPaciente")int idPaciente,Model modelo){
        Ingresos ingresos=ingresosServicio.buscarPorId(idPaciente);
        Habitacion habitacion=habitacionServicio.buscarPorId(idHabitacion);
        if (habitacion.getOcupacion().equalsIgnoreCase("individual")&&habitacion.getIngresos().size()>=1){
            modelo.addAttribute("error","La habitacion esta ocupada");
            return "Catalogo/habitaciones";
        }
        ingresos.setHabitacion(habitacion);
        ingresos.setHospitalizado(true);
        ingresosServicio.guardar(ingresos);
        modelo.addAttribute("Mensaje","Paciente Hospitalizado");

        return "Catalogo/habitaciones";
    }

}
