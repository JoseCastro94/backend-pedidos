package com.edu.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.pe.model.Producto;
import com.edu.pe.service.IProductoService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
    public Flux<Producto> ListarTodos() {
        return productoService.ListarTodos();
    }
}
