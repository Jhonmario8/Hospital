
package com.hospital.modelo.servicio;

import com.hospital.modelo.entidad.UsuarioSesion;
import com.hospital.modelo.repositorio.UsuarioSesionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSesionServicio implements IUsuarioSesionServicio {
    @Autowired
    private UsuarioSesionRepositorio usuariorepositorio;
    
    @Override
    public void guardar(UsuarioSesion usuario){
        usuariorepositorio.save(usuario);
    }
    @Override
    public void eliminar(UsuarioSesion usuario){
        usuariorepositorio.delete(usuario);
    }
    @Override
    public UsuarioSesion buscarByUsuario(String usuario){
        return usuariorepositorio.findById(usuario).orElse(null);
    }
    
}
