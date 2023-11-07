package com.kinesoft.zero.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class DocumentoPago {

    public Integer id;
    public Serie serie;
    public Integer numero;
    public LocalDate fecha;
    public String condicion_pago;
    public Cliente cliente;
    public BigDecimal valor_venta;
    public BigDecimal igv;
    public BigDecimal monto_igv;
    public BigDecimal neto_pagar;
    public String estado;
    public Boolean cancelado;
    public Usuario usuario;
    public BigDecimal monto_pagado;
    public String firma_electronica;

    public DocumentoPago(Integer id, Serie serie, Integer numero, LocalDate fecha, String condicion_pago, Cliente cliente, BigDecimal valor_venta, BigDecimal igv, BigDecimal monto_igv, BigDecimal neto_pagar, String estado, Boolean cancelado, Usuario usuario, BigDecimal monto_pagado, String firma_electronica) {
        this.id = id;
        this.serie = serie;
        this.numero = numero;
        this.fecha = fecha;
        this.condicion_pago = condicion_pago;
        this.cliente = cliente;
        this.valor_venta = valor_venta;
        this.igv = igv;
        this.monto_igv = monto_igv;
        this.neto_pagar = neto_pagar;
        this.estado = estado;
        this.cancelado = cancelado;
        this.usuario = usuario;
        this.monto_pagado = monto_pagado;
        this.firma_electronica = firma_electronica;
    }

    public DocumentoPago(Serie serie, Integer numero, LocalDate fecha, String condicion_pago, Cliente cliente, BigDecimal valor_venta, BigDecimal igv, BigDecimal monto_igv, BigDecimal neto_pagar, String estado, Boolean cancelado, Usuario usuario, BigDecimal monto_pagado, String firma_electronica) {
        this.serie = serie;
        this.numero = numero;
        this.fecha = fecha;
        this.condicion_pago = condicion_pago;
        this.cliente = cliente;
        this.valor_venta = valor_venta;
        this.igv = igv;
        this.monto_igv = monto_igv;
        this.neto_pagar = neto_pagar;
        this.estado = estado;
        this.cancelado = cancelado;
        this.usuario = usuario;
        this.monto_pagado = monto_pagado;
        this.firma_electronica = firma_electronica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCondicion_pago() {
        return condicion_pago;
    }

    public void setCondicion_pago(String condicion_pago) {
        this.condicion_pago = condicion_pago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValor_venta() {
        return valor_venta;
    }

    public void setValor_venta(BigDecimal valor_venta) {
        this.valor_venta = valor_venta;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getMonto_igv() {
        return monto_igv;
    }

    public void setMonto_igv(BigDecimal monto_igv) {
        this.monto_igv = monto_igv;
    }

    public BigDecimal getNeto_pagar() {
        return neto_pagar;
    }

    public void setNeto_pagar(BigDecimal neto_pagar) {
        this.neto_pagar = neto_pagar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getMonto_pagado() {
        return monto_pagado;
    }

    public void setMonto_pagado(BigDecimal monto_pagado) {
        this.monto_pagado = monto_pagado;
    }

    public String getFirma_electronica() {
        return firma_electronica;
    }

    public void setFirma_electronica(String firma_electronica) {
        this.firma_electronica = firma_electronica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentoPago that = (DocumentoPago) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
