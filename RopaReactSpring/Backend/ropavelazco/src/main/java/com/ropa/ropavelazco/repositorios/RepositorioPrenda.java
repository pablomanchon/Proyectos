package com.ropa.ropavelazco.repositorios;

import com.ropa.ropavelazco.entidades.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPrenda extends JpaRepository<Prenda,Long> {
}
