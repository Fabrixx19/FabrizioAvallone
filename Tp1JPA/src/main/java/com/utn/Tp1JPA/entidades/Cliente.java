package com.utn.Tp1JPA.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*
relacion:
Cliente--1---------N->Pedido
Cliente--1---------N->Domicilio
*/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "cliente")

public class Cliente extends BaseEntidad{

    @Column (name = "nombre")

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    //Relacion de uno a muchos Cliente-Domicilio
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")       // nombre de la FK que se encuentra en el lado de muchos
    @Builder.Default
    private List<Domicilio>domicilios = new ArrayList<>();


    //Relacion de uno a muchos Cliente-Pedido
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarDomicilio(Domicilio domi){
        domicilios.add(domi);
    }
    public void mostrarDomicilios(){
        System.out.println("Domicilios de "+ nombre +" "+apellido+ ";");
        for (Domicilio domicilio: domicilios){
            System.out.println("Calle:"+domicilio.getCalle()+", Numero"+domicilio.getNumero());
        }
    }
    public void agregarPedido(Pedido ped){
        pedidos.add(ped);
    }
    public void mostrarPedidos(){
        System.out.println("Pedido de "+ nombre +" "+apellido+ ";");
        for (Pedido pedido: pedidos){
            System.out.println("Estado:"+pedido.getEstadoPedido()+", Fecha:"+pedido.getFecha()+", Tipo de envio:"+pedido.getTipoEnvio()+", Total:"+pedido.getTotal());
        }
    }
}
