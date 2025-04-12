package com.hospital.controlador;


import com.hospital.modelo.entidad.UsuarioSesion;
import com.hospital.modelo.servicio.IUsuarioSesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginControlador {
    @Autowired
    private IUsuarioSesionServicio servicio;

    @PostMapping("/usuario/guardar")
    public void guardar(@RequestBody UsuarioSesion usuario){
        servicio.guardar(usuario);
    }
    @GetMapping("/usuario/search/{id}")
    public ResponseEntity<?> search(@PathVariable String id){
        UsuarioSesion usu=servicio.buscarByUsuario(id);
        if (usu!=null){
            return ResponseEntity.ok(usu);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @PostMapping("/usuario/buscar")
    public ResponseEntity<?> buscar(@RequestBody UsuarioSesion usu) {
        UsuarioSesion usuario = servicio.buscarByUsuario(usu.getUsuario());

        if (usuario != null) {
            if (usuario.getContraseña().equals(usu.getContraseña())) {
                return ResponseEntity.ok(usuario);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario o la contraseña no coinciden");
    }
}
