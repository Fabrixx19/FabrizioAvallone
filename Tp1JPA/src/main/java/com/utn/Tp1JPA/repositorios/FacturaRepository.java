package com.utn.Tp1JPA.repositorios;

import com.utn.Tp1JPA.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
