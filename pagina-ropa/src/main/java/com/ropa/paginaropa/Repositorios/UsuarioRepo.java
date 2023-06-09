package com.ropa.paginaropa.Repositorios;

import com.ropa.paginaropa.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
}
