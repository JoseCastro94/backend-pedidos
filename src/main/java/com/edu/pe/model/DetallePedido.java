package com.edu.pe.model;

public class DetallePedido {
	private Long idProducto;
	private String nombreProducto;
	private double precio;
	private int cantidad;
	
	public DetallePedido() {
	}

	public DetallePedido(Long idProducto,String nombreProducto, double precio, int cantidad) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.precio = precio;
		this.cantidad = cantidad;
	}



	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
