package com.edu.pe.service;

import com.edu.pe.model.Cliente;

import reactor.core.publisher.Flux;

public interface IClienteService {

	public Flux<Cliente> ListarTodos();
}
