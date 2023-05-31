package com.kinesoft.zero.model;

import com.kinesoft.zero.components.GridView;
import com.kinesoft.zero.components.NumerText;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DocumentoPagoDetalle {

    public Integer id;
    public DocumentoPago documento_pago;
    public Producto producto;
    public Integer cantidad;
    public BigDecimal precio;
    public BigDecimal total;
    public GridView<DocumentoPagoDetalle> grid;
    private NumerText numerText = new NumerText();



    public DocumentoPagoDetalle(Integer id, DocumentoPago documento_pago, Producto producto, Integer cantidad, BigDecimal precio, BigDecimal total) {
        super();
        this.id = id;
        this.documento_pago = documento_pago;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;


    }

    public DocumentoPagoDetalle(Integer id,Producto producto, Integer cantidad, BigDecimal precio, BigDecimal total) {

        this.id = id;

        this.producto = producto;
        this.cantidad = cantidad;
        this.numerText.setValue(Double.valueOf(cantidad));

        this.precio = precio;
        this.total = total;
/*
        numerText.addValueChangeListener(e->{
            this.cantidad=(int) Math.round(numerText.getValue());
            this.total   = precio.multiply(new BigDecimal(this.cantidad));
            System.out.println("El dinero al cambiar es "+this.total);

            this.numerText.setValue(Double.valueOf(this.cantidad));

           // this.grid.getDataProvider().refreshItem(this);


        });

*/
    }
    public DocumentoPagoDetalle() {


    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DocumentoPago getDocumento_pago() {
        return documento_pago;
    }

    public void setDocumento_pago(DocumentoPago documento_pago) {
        this.documento_pago = documento_pago;
    }

    public Producto getProducto() {
        return producto;
    }

    public String getProductotoSring() {
        return producto.getNombre();
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DocumentoPagoDetalle other = (DocumentoPagoDetalle) obj;
        return id == other.id;
    }

    public NumerText getNumerText() {
        return numerText;
    }
}
