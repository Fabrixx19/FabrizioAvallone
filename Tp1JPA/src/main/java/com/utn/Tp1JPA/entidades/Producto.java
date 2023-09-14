package com.utn.Tp1JPA.entidades;

import com.utn.Tp1JPA.enumeraciones.TipoProducto;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto extends BaseEntidad{

    private TipoProducto tipoProducto;
    private String denominacion;
    private int tiempoEstimadoCocina;
    private double precioVenta;
    private double precioCompra;
    private int stockActual;
    private int stockMinimo;
    private String unidadMedida;
    private String receta;

}
