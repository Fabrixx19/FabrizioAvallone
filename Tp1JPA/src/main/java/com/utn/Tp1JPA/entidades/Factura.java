package com.utn.Tp1JPA.entidades;

import com.utn.Tp1JPA.enumeraciones.FormaDePago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Factura extends BaseEntidad {

    private int numero;
    private Date fecha;
    private FormaDePago formaPago;
    private double descuento;
    private int total;



}
