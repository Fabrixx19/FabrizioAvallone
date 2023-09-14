package com.utn.Tp1JPA.entidades;

import com.utn.Tp1JPA.enumeraciones.EstadoPedido;
import com.utn.Tp1JPA.enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido extends BaseEntidad {

    @Temporal(TemporalType.DATE)
    private EstadoPedido estadoPedido;
    private Date fecha;
    private TipoEnvio tipoEnvio;
    private double total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    private Factura factura;


    public void agregarDetallePedido(DetallePedido detalleped){
        detallePedidos.add(detalleped);
    }
    public void mostrarDetallePedido(){
        System.out.println("Pedido "+ estadoPedido +" "+fecha+ tipoEnvio + total + ";");
        for (DetallePedido detallePedido: detallePedidos){
            System.out.println("Cantidad: "+detallePedido.getCantidad()+", Subtotal: "+ detallePedido.getSubtotal());
        }
    }
}
