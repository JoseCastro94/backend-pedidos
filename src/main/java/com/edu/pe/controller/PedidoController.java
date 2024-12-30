package com.edu.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.edu.pe.dto.ResponseDTO;
import com.edu.pe.exception.ResourceNotFoundException;
import com.edu.pe.model.Pedido;
import com.edu.pe.service.IPedidoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/pedido")
@CrossOrigin
public class PedidoController {
	@Autowired
	private IPedidoService pedidoService;

	@PostMapping("/guardar")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Pedido> CrearPedido(@RequestBody Pedido pedido) {
		return pedidoService.Guardar(pedido);
	}

	@GetMapping("/listar")
	public Flux<Pedido> ListarTodos() {
		return pedidoService.ListarTodos();
	}

	@GetMapping("/buscarPorId/{id}")
	public Mono<Pedido> BuscarPorId(@PathVariable String id) {
		return pedidoService.BuscarPorId(id)
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Pedido no encontrado con ID: " + id)));
	}

	@DeleteMapping("/eliminar/{id}")
	public Mono<ResponseDTO> EliminarPedido(@PathVariable String id) {
		return pedidoService.Eliminar(id);
	}
}
