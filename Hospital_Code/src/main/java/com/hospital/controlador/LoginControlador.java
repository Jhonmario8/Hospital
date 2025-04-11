package com.hospital.controlador;


import com.hospital.modelo.entidad.UsuarioSesion;
import com.hospital.modelo.servicio.IUsuarioSesionServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginControlador {
    private IUsuarioSesionServicio servicio;

    @PostMapping("/usuario/guardar")
    public void guardar(@RequestBody UsuarioSesion usuario){
        servicio.guardar(usuario);
    }

    @GetMapping("/usuario/buscar")
    public ResponseEntity<?> buscar(@PathVariable String id){
        UsuarioSesion usuario=servicio.buscarByUsuario(id);
        if (usuario!=null){
            return ResponseEntity.ok(usuario);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
}
