package com.ropa.ropavelazco.repositorios;

import com.ropa.ropavelazco.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioUsuario extends JpaRepository <Usuario, String> {
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Optional<Usuario> findByEmail(@Param("email")String email);
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    public Optional<Usuario> findByNombre(@Param("nombre")String nombre);
}
