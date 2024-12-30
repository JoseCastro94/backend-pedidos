package com.edu.pe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.edu.pe.model.Cliente;
import com.edu.pe.service.IClienteService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/listar")
    public Flux<Cliente> ListarTodos() {
        return clienteService.ListarTodos();
    }

}
