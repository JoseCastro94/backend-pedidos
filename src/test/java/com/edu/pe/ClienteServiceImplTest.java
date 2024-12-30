package com.edu.pe;

import com.edu.pe.model.Cliente;
import com.edu.pe.repository.IClienteRepository;
import com.edu.pe.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private IClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTodos() {
        Cliente cliente1 = new Cliente();
        cliente1.setNombres("Juan");
        cliente1.setApellidos("Perez");

        Cliente cliente2 = new Cliente();
        cliente2.setNombres("Maria");
        cliente2.setApellidos("Gomez");

        when(clienteRepository.findAllByOrderByApellidosAsc()).thenReturn(Arrays.asList(cliente1, cliente2));

        Flux<Cliente> result = clienteService.ListarTodos();
        
        assertEquals(2, result.collectList().block().size());
        assertEquals("Juan", result.collectList().block().get(0).getNombres());
    }
}