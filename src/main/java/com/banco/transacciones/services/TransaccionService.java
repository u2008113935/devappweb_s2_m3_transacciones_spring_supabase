package com.banco.transacciones.services;

import com.banco.transacciones.dto.TransaccionRequestDto;
import com.banco.transacciones.models.Transaccion;

import java.util.List;
import java.util.UUID;

public interface TransaccionService {

    List<Transaccion> listarPorCuenta(UUID cuentaId);

    List<Transaccion> listarPorUsuario(UUID userId);

    Transaccion registrarTransaccion(TransaccionRequestDto dto);

    Transaccion actualizarTransaccion(UUID id, TransaccionRequestDto dto);

    void eliminarTransaccion(UUID id);
}
