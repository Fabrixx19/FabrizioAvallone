package com.utn.Tp1JPA.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Rubro extends BaseEntidad{

    private String denominacion;

    //Relacion de uno a muchos
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto prod){
        productos.add(prod);
    }
    public void mostrarProductos(){
        System.out.println("Rubro de Producto: "+ denominacion + ";");
        for (Producto producto: productos){
            System.out.println("Tipo:"+producto.getTipoProducto()+", Tiempo estimado cocina: "+ producto.getTiempoEstimadoCocina()+"min"
                    +", Denominacion: "+ producto.getDenominacion()+", Precio de Venta: "+ producto.getPrecioVenta()
                    +", Precio de Compra: "+ producto.getPrecioCompra()+", Stock Actual: "+ producto.getStockActual()
                    +", Stock Minimo: "+ producto.getStockMinimo()+", Unidad de Medida: "+ producto.getUnidadMedida()
                    +", Receta: "+ producto.getReceta());
        }
    }

}
