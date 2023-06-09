package com.ropa.ropamountain.repositorios;

import com.ropa.ropamountain.entidades.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPrenda extends JpaRepository<Prenda,Long> {
}
