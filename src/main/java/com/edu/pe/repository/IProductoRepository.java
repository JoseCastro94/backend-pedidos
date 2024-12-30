package com.edu.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edu.pe.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
	
	public List<Producto> findAllByOrderByNombreAsc();
}