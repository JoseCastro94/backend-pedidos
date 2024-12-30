package com.edu.pe.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.edu.pe.model.Pedido;

@Repository
public interface IPedidoRepository extends ReactiveMongoRepository<Pedido, String> {
    
}