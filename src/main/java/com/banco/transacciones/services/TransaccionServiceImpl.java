package com.banco.transacciones.services;

import com.banco.transacciones.dto.TransaccionRequestDto;
import com.banco.transacciones.models.Transaccion;
import com.banco.transacciones.repositories.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepo;

    public TransaccionServiceImpl(TransaccionRepository transaccionRepo) {
        this.transaccionRepo = transaccionRepo;
    }

    @Override
    public List<Transaccion> listarPorCuenta(UUID cuentaId) {
        return transaccionRepo.findByCuentaIdOrderByFechaDesc(cuentaId);
    }

    @Override
    public List<Transaccion> listarPorUsuario(UUID userId) {
        return transaccionRepo.findByUserIdOrderByFechaDesc(userId);
    }

    @Override
    public Transaccion registrarTransaccion(TransaccionRequestDto dto) {

        List<String> tiposValidos = Arrays.asList("debito", "credito");
        if (!tiposValidos.contains(dto.getTipo())) {
            throw new RuntimeException("Tipo invalido. Debe ser 'debito' o 'credito'");
        }

        if (dto.getMonto() == null || dto.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Monto debe ser mayor a 0");
        }

        if (dto.getDescripcion() == null || dto.getDescripcion().trim().isEmpty()) {
            throw new RuntimeException("Descripcion es requerida");
        }

        if (dto.getUserId() == null || dto.getCuentaId() == null) {
            throw new RuntimeException("userId y cuentaId son requeridos");
        }

        Transaccion t = new Transaccion();
        t.setUserId(dto.getUserId());
        t.setCuentaId(dto.getCuentaId());
        t.setTipo(dto.getTipo());
        t.setDescripcion(dto.getDescripcion());
        t.setMonto(dto.getMonto());

        return transaccionRepo.save(t);
    }

    @Override
    public Transaccion actualizarTransaccion(UUID id, TransaccionRequestDto dto) {

        Transaccion existente = transaccionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaccion no encontrada con id: " + id));

        if (dto.getTipo() != null) {
            List<String> tiposValidos = Arrays.asList("debito", "credito");
            if (!tiposValidos.contains(dto.getTipo())) {
                throw new RuntimeException("Tipo invalido. Debe ser 'debito' o 'credito'");
            }
            existente.setTipo(dto.getTipo());
        }

        if (dto.getDescripcion() != null && !dto.getDescripcion().trim().isEmpty()) {
            existente.setDescripcion(dto.getDescripcion());
        }

        if (dto.getMonto() != null) {
            if (dto.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("Monto debe ser mayor a 0");
            }
            existente.setMonto(dto.getMonto());
        }

        return transaccionRepo.save(existente);
    }

    @Override
    public void eliminarTransaccion(UUID id) {
        if (!transaccionRepo.existsById(id)) {
            throw new RuntimeException("Transaccion no encontrada con id: " + id);
        }
        transaccionRepo.deleteById(id);
    }

}
