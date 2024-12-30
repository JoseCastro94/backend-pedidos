package com.edu.pe.service;

import com.edu.pe.dto.ResponseDTO;
import com.edu.pe.model.Pedido;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPedidoService {
	
	 public Mono<Pedido> Guardar(Pedido pedido);
	 
	 public Flux<Pedido> ListarTodos();
	 
	 public Mono<Pedido> BuscarPorId(String id);
	 
	 public Mono<ResponseDTO> Eliminar(String id);
}
