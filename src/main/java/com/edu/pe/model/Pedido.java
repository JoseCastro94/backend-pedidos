package com.edu.pe.model;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "pedido")
public class Pedido {	
	@BsonId
	@Id
	private String id;
	
    private Long idCliente;
    private String nombreCliente;
    private LocalDateTime  fecha; 
    private List<DetallePedido> detalles;
    private double total;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public LocalDateTime  getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime  fecha) {
		this.fecha = fecha;
	}
	public List<DetallePedido> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	} 
    
    
}
