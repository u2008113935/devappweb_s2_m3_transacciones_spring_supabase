package com.banco.transacciones.controllers;

import com.banco.transacciones.dto.TransaccionRequestDto;
import com.banco.transacciones.models.Transaccion;
import com.banco.transacciones.services.TransaccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/transacciones")
@CrossOrigin(origins = "*")
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    // GET /api/transacciones?cuentaId=UUID
    @GetMapping
    public ResponseEntity<?> listarPorCuenta(@RequestParam UUID cuentaId) {
        List<Transaccion> transacciones = transaccionService.listarPorCuenta(cuentaId);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("success", true);
        respuesta.put("data", transacciones);
        return ResponseEntity.ok(respuesta);
    }

    // GET /api/transacciones/usuario/{userId}
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable UUID userId) {
        List<Transaccion> transacciones = transaccionService.listarPorUsuario(userId);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("success", true);
        respuesta.put("data", transacciones);
        return ResponseEntity.ok(respuesta);
    }

    // POST /api/transacciones
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody TransaccionRequestDto dto) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            Transaccion creada = transaccionService.registrarTransaccion(dto);
            respuesta.put("success", true);
            respuesta.put("data", creada);
            return ResponseEntity.status(201).body(respuesta);
        } catch (RuntimeException e) {
            respuesta.put("success", false);
            respuesta.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(respuesta);
        }
    }

    // PUT /api/transacciones/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable UUID id, @RequestBody TransaccionRequestDto dto) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            Transaccion actualizada = transaccionService.actualizarTransaccion(id, dto);
            respuesta.put("success", true);
            respuesta.put("data", actualizada);
            return ResponseEntity.ok(respuesta);
        } catch (RuntimeException e) {
            respuesta.put("success", false);
            respuesta.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(respuesta);
        }
    }

    // DELETE /api/transacciones/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable UUID id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            transaccionService.eliminarTransaccion(id);
            respuesta.put("success", true);
            respuesta.put("message", "Transaccion eliminada correctamente");
            return ResponseEntity.ok(respuesta);
        } catch (RuntimeException e) {
            respuesta.put("success", false);
            respuesta.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(respuesta);
        }
    }

}
