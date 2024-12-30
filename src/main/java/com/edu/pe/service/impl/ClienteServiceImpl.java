package com.edu.pe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edu.pe.model.Cliente;
import com.edu.pe.repository.IClienteRepository;
import com.edu.pe.service.IClienteService;
import reactor.core.publisher.Flux;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Override
	public Flux<Cliente> ListarTodos() {
		 return Flux.fromIterable(clienteRepository.findAllByOrderByApellidosAsc());
    }

}
