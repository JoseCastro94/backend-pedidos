package com.edu.pe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.edu.pe.model.Producto;
import com.edu.pe.repository.IProductoRepository;
import com.edu.pe.service.impl.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import static org.mockito.Mockito.*;

import java.util.Arrays;

class ProductoServiceImplTest {

	@InjectMocks
    private ProductoServiceImpl productoService;

    @Mock
    private IProductoRepository productoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTodos() {
        Producto producto1 = new Producto();
        producto1.setNombre("Producto 1");

        Producto producto2 = new Producto();
        producto2.setNombre("Producto 2");

        when(productoRepository.findAllByOrderByNombreAsc()).thenReturn(Arrays.asList(producto1, producto2));

        Flux<Producto> result = productoService.ListarTodos();

        assertEquals(2, result.collectList().block().size());
        assertEquals("Producto 1", result.collectList().block().get(0).getNombre());
    }

}
