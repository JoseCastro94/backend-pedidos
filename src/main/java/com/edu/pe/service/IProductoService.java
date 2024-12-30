package com.edu.pe.service;

import com.edu.pe.model.Producto;
import reactor.core.publisher.Flux;

public interface IProductoService {
	public Flux<Producto> ListarTodos();
}
