package com.ropa.ropamountain.repositorios;

import com.ropa.ropamountain.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioUsuario extends JpaRepository <Usuario, String> {
    public Optional<Usuario> findByEmail(String email);
    public Optional<Usuario> findByNombre(String nombre);
}
