package com.ropa.ropamountain.servicios;

import com.ropa.ropamountain.entidades.Usuario;
import com.ropa.ropamountain.excepciones.MiExcepcion;
import com.ropa.ropamountain.repositorios.RepositorioUsuario;
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
public class ServicioUsuario implements UserDetailsService {
    @Autowired
    RepositorioUsuario repositorioUsuario;

    public void registrarUsuario(String nombre, String email, String password, String password2) throws MiExcepcion {
        validar(nombre, email, password, password2);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        repositorioUsuario.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> resUsuario = repositorioUsuario.findByNombre(username);
        Usuario usuario;
        try {
            if (resUsuario.isPresent()) {
                usuario = resUsuario.get();
                return permisos(usuario);
            } else {
                resUsuario = repositorioUsuario.findByEmail(username);
                if (resUsuario.isPresent()) {
                    usuario = resUsuario.get();
                    return permisos(usuario);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Usuario buscarPorId(String id) {
        return repositorioUsuario.findById(id).orElse(null);
    }

    public List<Usuario> listarUsuarios() throws Exception {
        return repositorioUsuario.findAll();
    }

    public User permisos(Usuario usuario) throws UsernameNotFoundException {
        List<GrantedAuthority> permisos = new ArrayList<>();
        GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
        permisos.add(p);
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("usuariosession", usuario);
        return new User(usuario.getEmail(), usuario.getPassword(), permisos);
    }

    private void validar(String user, String email, String password, String password2) throws MiExcepcion {
        if (user == null || user.isEmpty()) {
            throw new MiExcepcion("Debe poner user de usuario");
        }
        if (email == null || email.isEmpty()) {
            throw new MiExcepcion("Debe poner email");
        }
        if (password == null || password.length() <= 5) {
            throw new MiExcepcion("La contraseña no puede estar vacía y debe tener mas de 5 digitos");
        }
        if (!password2.equals(password)) {
            throw new MiExcepcion("Las contraseñas no coinciden");
        }
    }
}
