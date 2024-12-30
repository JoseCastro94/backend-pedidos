package com.edu.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.pe.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    public List<Cliente> findAllByOrderByApellidosAsc();
}