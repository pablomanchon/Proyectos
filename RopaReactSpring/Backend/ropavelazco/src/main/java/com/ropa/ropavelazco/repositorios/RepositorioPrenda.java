package com.ropa.ropavelazco.repositorios;

import com.ropa.ropavelazco.entidades.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioPrenda extends JpaRepository<Prenda,Long> {
    @Query("SELECT p FROM Prenda p WHERE p.nombre LIKE %:term% OR p.marca LIKE %:term% OR EXISTS (SELECT c FROM p.caracteristicas c WHERE c LIKE %:term%)")
    List<Prenda> buscarPorTermino(@Param("term") String term);
    List<Prenda> findByNombreContaining(String term);
}
