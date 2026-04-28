package com.banco.transacciones.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class TransaccionRequestDto {

    private UUID userId;
    private UUID cuentaId;
    private String tipo;
    private String descripcion;
    private BigDecimal monto;

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public UUID getCuentaId() { return cuentaId; }
    public void setCuentaId(UUID cuentaId) { this.cuentaId = cuentaId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
}
