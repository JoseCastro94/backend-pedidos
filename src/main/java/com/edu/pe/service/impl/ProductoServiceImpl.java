package com.edu.pe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.pe.model.Producto;
import com.edu.pe.repository.IProductoRepository;
import com.edu.pe.service.IProductoService;

import reactor.core.publisher.Flux;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoRepository productoRepository;
	
	@Override
	public Flux<Producto> ListarTodos() {
		return Flux.fromIterable(productoRepository.findAllByOrderByNombreAsc());
	}

}
