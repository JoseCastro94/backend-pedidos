package com.edu.pe;

import com.edu.pe.dto.ResponseDTO;
import com.edu.pe.exception.ResourceNotFoundException;
import com.edu.pe.model.Cliente;
import com.edu.pe.model.DetallePedido;
import com.edu.pe.model.Pedido;
import com.edu.pe.model.Producto;
import com.edu.pe.repository.IPedidoRepository;
import com.edu.pe.repository.IClienteRepository;
import com.edu.pe.repository.IProductoRepository;
import com.edu.pe.service.impl.PedidoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class PedidoServiceImplTest {

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Mock
    private IPedidoRepository pedidoRepository;

    @Mock
    private IClienteRepository clienteRepository;

    @Mock
    private IProductoRepository productoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardar() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);  
        cliente.setNombres("Juan");
        
        Producto producto = new Producto();
        producto.setId(1L);  
        producto.setNombre("Producto 1");
        producto.setPrecio(100);

        Pedido pedido = new Pedido();
        pedido.setIdCliente(1L); 
        pedido.setDetalles(Arrays.asList(new DetallePedido(1L,"Producto 1", 10, 4)));  
   
        when(clienteRepository.findById(1L)).thenReturn(java.util.Optional.of(cliente));  
        when(productoRepository.findById(1L)).thenReturn(java.util.Optional.of(producto));  
        when(pedidoRepository.save(pedido)).thenReturn(Mono.just(pedido));

        Mono<Pedido> result = pedidoService.Guardar(pedido);

        Pedido savedPedido = result.block();
        assertNotNull(savedPedido);
        assertEquals("Juan", savedPedido.getNombreCliente());
        assertEquals(40, savedPedido.getTotal());
    }

    @Test
    public void testEliminar() {
        Pedido pedido = new Pedido();
        pedido.setId("pedido1");

        when(pedidoRepository.findById("pedido1")).thenReturn(Mono.just(pedido));
        when(pedidoRepository.delete(pedido)).thenReturn(Mono.empty());

        Mono<ResponseDTO> result = pedidoService.Eliminar("pedido1");

        ResponseDTO response = result.block();
        assertNotNull(response);
        assertEquals("Pedido eliminado con éxito", response.getMessage());
    }

    @Test
    public void testEliminarPedidoNoEncontrado() {
        when(pedidoRepository.findById("pedido1")).thenReturn(Mono.empty());

        Mono<ResponseDTO> result = pedidoService.Eliminar("pedido1");

        assertThrows(ResourceNotFoundException.class, result::block);
    }
}
