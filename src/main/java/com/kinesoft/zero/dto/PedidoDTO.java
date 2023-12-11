package com.kinesoft.zero.dto;

import com.kinesoft.zero.model.Cliente;
import com.kinesoft.zero.model.Mesa;
import com.vaadin.flow.component.button.Button;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class PedidoDTO {


    public Integer id;
    public Integer idCliente;
    public String cliente;
    public Integer idMesa;
    public String mesa;
    public BigDecimal total;
    public String estado;
    public LocalDateTime fecha_hora;



    public PedidoDTO(Integer id, Integer idCliente, String cliente, Integer idMesa, String mesa, BigDecimal total, String estado, LocalDateTime fecha_hora) {
        this.id = id;
        this.idCliente = idCliente;
        this.cliente = cliente;
        this.idMesa = idMesa;
        this.mesa = mesa;
        this.total = total;
        this.estado = estado;
        this.fecha_hora = fecha_hora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoDTO pedidoDTO)) return false;

        return Objects.equals(id, pedidoDTO.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
