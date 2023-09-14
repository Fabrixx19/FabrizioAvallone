package com.utn.Tp1JPA;

import com.utn.Tp1JPA.entidades.*;
import com.utn.Tp1JPA.enumeraciones.EstadoPedido;
import com.utn.Tp1JPA.enumeraciones.FormaDePago;
import com.utn.Tp1JPA.enumeraciones.TipoEnvio;
import com.utn.Tp1JPA.enumeraciones.TipoProducto;
import com.utn.Tp1JPA.repositorios.ClienteRepository;
import com.utn.Tp1JPA.repositorios.DomicilioRepository;
import com.utn.Tp1JPA.repositorios.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Tp1JpaApplication {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	RubroRepository rubroRepository;


	public static void main(String[] args) {

		SpringApplication.run(Tp1JpaApplication.class, args);
		System.out.println("Estoy funcionando" );
	}

	@Bean
	CommandLineRunner init(RubroRepository rubroRepo ,ClienteRepository clienteRepo) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");
// Construccion de Objetos

			Rubro rubro = Rubro.builder()
					.denominacion("Pizza")
					.build();
			Producto producto1 = Producto.builder()
					.tipoProducto(TipoProducto.INSUMO)
					.tiempoEstimadoCocina(10)
					.denominacion("Pizza con rucula")
					.precioCompra(500)
					.precioVenta(600)
					.stockMinimo(200)
					.stockActual(300)
					.receta("Queso, masa, rucula")
					.unidadMedida("kg")
					.build();
			Producto producto2 = Producto.builder()
					.tipoProducto(TipoProducto.INSUMO)
					.tiempoEstimadoCocina(20)
					.denominacion("Piza napolitana")
					.precioCompra(1000)
					.precioVenta(1200)
					.stockMinimo(200)
					.stockActual(300)
					.receta("Queso, masa, tomate")
					.unidadMedida("kg")
					.build();

			rubro.agregarProducto(producto1);
			rubro.agregarProducto(producto2);

			Cliente cliente = Cliente.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.telefono("2617032312")
					.email("avallone296@gmail.com")
					.build();
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Chubut")
					.numero("2311")
					.localidad("Ciudad")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Paso de los andes")
					.numero("2511")
					.localidad("Ciudad")
					.build();

			cliente.agregarDomicilio(domicilio1);
			cliente.agregarDomicilio(domicilio2);

			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			String fechaString = "2023-09-09";
			Date fecha = formatoFecha.parse(fechaString);

			Pedido pedido1= Pedido.builder()
					.estadoPedido(EstadoPedido.ENTREGADO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(700.0)
					.fecha(fecha)
					.build();
			Pedido pedido2= Pedido.builder()
					.estadoPedido(EstadoPedido.ENTREGADO)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(700.0)
					.fecha(fecha)
					.build();

			cliente.agregarPedido(pedido1);
			cliente.agregarPedido(pedido2);


			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(4000.0)
					.build();
			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(2200.0)
					.build();
			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(6000.0)
					.build();


			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto1);
			pedido1.agregarDetallePedido(detallePedido1);
			pedido1.agregarDetallePedido(detallePedido2);
			pedido2.agregarDetallePedido(detallePedido3);

			Factura factura1 = Factura.builder()
					.total(2000)
					.numero(321)
					.formaPago(FormaDePago.EFECTIVO)
					.descuento(0.20)
					.fecha(fecha)
					.build();

			pedido1.setFactura(factura1);


			// Guardar el objeto Persona en la base de datos
			rubroRepository.save(rubro);
			clienteRepository.save(cliente);
			// Recuperar el objeto Persona desde la base de datos
			Cliente clienteRecuperado = clienteRepository.findById(cliente.getId()).orElse(null);
			if (clienteRecuperado != null) {
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos();
			}

			Rubro rubroRecuperado = rubroRepository.findById(cliente.getId()).orElse(null);
			if (clienteRecuperado != null) {
				System.out.println("Deniminacion: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();


			}


		};

	}
}
