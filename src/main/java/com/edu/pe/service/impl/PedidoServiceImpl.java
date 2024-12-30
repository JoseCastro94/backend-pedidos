package com.edu.pe.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.pe.dto.ResponseDTO;
import com.edu.pe.exception.ResourceNotFoundException;
import com.edu.pe.model.Cliente;
import com.edu.pe.model.Pedido;
import com.edu.pe.model.Producto;
import com.edu.pe.repository.IClienteRepository;
import com.edu.pe.repository.IPedidoRepository;
import com.edu.pe.repository.IProductoRepository;
import com.edu.pe.service.IPedidoService;
import jakarta.transaction.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PedidoServiceImpl implements IPedidoService{
	
    @Autowired
    private IPedidoRepository pedidoRepository;
    
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private IProductoRepository productoRepository;
	
	@Override
    @Transactional
    public Mono<Pedido> Guardar(Pedido pedido) {
        
        Mono<Cliente> clienteExistente = Mono.justOrEmpty(clienteRepository.findById(pedido.getIdCliente()))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Cliente no encontrado con ID: " + pedido.getIdCliente())));

        Flux<Mono<Producto>> productosValidos = Flux.fromIterable(pedido.getDetalles())
                .map(detalle -> Mono.justOrEmpty(productoRepository.findById(detalle.getIdProducto()))
                        .switchIfEmpty(Mono.error(new ResourceNotFoundException("Producto no encontrado con ID: " + detalle.getIdProducto()))));

        return clienteExistente.flatMap(cliente -> 
            Flux.concat(productosValidos)
                .collectList()
                .flatMap(productos -> {

                    pedido.setNombreCliente(cliente.getNombres());
                    pedido.setFecha(LocalDateTime.now());
                    
                    double total = pedido.getDetalles().stream()
                            .mapToDouble(detalle -> detalle.getPrecio() * detalle.getCantidad())
                            .sum();
                    pedido.setTotal(total);
                    
                    return pedidoRepository.save(pedido);
                })
        );
    }

	@Override
    public Flux<Pedido> ListarTodos() {
        return pedidoRepository.findAll();
    }
	
    @Override
    @Transactional
	public Mono<ResponseDTO> Eliminar(String id) {
		Mono<Pedido> pedidoExistente = pedidoRepository.findById(id)
			.switchIfEmpty(Mono.error(new ResourceNotFoundException("Pedido no encontrado con ID: " + id)));
		
		return pedidoExistente.flatMap(pedido -> {
			return pedidoRepository.delete(pedido)
				.then(Mono.just(new ResponseDTO("Pedido eliminado con éxito", "success")));
		});
	}

	@Override
	public Mono<Pedido> BuscarPorId(String id) {
		return pedidoRepository.findById(id);
	}

}
