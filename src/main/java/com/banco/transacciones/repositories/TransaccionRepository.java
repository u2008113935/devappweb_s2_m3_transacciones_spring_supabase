package com.banco.transacciones.repositories;

import com.banco.transacciones.models.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, UUID> {

    List<Transaccion> findByCuentaIdOrderByFechaDesc(UUID cuentaId);

    List<Transaccion> findByUserIdOrderByFechaDesc(UUID userId);
}
