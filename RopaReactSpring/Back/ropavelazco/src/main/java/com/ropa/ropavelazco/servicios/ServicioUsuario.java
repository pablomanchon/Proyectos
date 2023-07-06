package com.ropa.ropavelazco.servicios;

import com.ropa.ropavelazco.entidades.Usuario;
import com.ropa.ropavelazco.enums.Rol;
import com.ropa.ropavelazco.excepciones.MiExcepcion;
import com.ropa.ropavelazco.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuario {
    @Autowired
    RepositorioUsuario repositorioUsuario;

    public Usuario registrarUsuario(Usuario usuario, String password2) throws MiExcepcion {
        validar(usuario, password2);
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        usuario.setRol(Rol.USER);
        return repositorioUsuario.save(usuario);
    }

    /*@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Optional<Usuario> resUsuario = repositorioUsuario.findByNombre(email);
            Usuario usuario = new Usuario();

            if (resUsuario.isPresent()) {
                usuario = resUsuario.get();
                List<GrantedAuthority> permisos = new ArrayList<>();
                GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
                permisos.add(p);
                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpSession session = attr.getRequest().getSession(true);
                session.setAttribute("usuariosession", usuario);
                return new User(usuario.getEmail(), usuario.getPassword(), permisos);
            } else {
                resUsuario = repositorioUsuario.findByEmail(email);
                if (resUsuario.isPresent()) {
                    usuario = resUsuario.get();
                    List<GrantedAuthority> permisos = new ArrayList<>();
                    GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
                    permisos.add(p);
                    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                    HttpSession session = attr.getRequest().getSession(true);
                    session.setAttribute("usuariosession", usuario);
                    return new User(usuario.getEmail(), usuario.getPassword(), permisos);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }*/

    public Usuario buscarPorId(String id) {
        return repositorioUsuario.findById(id).orElse(null);
    }

    public List<Usuario> listarUsuarios() throws Exception {
        return repositorioUsuario.findAll();
    }


    private void validar(Usuario usuario, String password2) throws MiExcepcion {
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new MiExcepcion("Debe poner nombre de usuario");
        }
        if (repositorioUsuario.findByNombre(usuario.getNombre()).isPresent()||repositorioUsuario.findByEmail(usuario.getEmail()).isPresent()){
            throw new MiExcepcion("El usuario ya existe");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new MiExcepcion("Debe poner email");
        }
        if (usuario.getPassword() == null || usuario.getPassword().length() <= 5) {
            throw new MiExcepcion("La contraseña no puede estar vacía y debe tener mas de 5 digitos");
        }
        if (!password2.equals(usuario.getPassword())) {
            throw new MiExcepcion("Las contraseñas no coinciden");
        }
    }
}
