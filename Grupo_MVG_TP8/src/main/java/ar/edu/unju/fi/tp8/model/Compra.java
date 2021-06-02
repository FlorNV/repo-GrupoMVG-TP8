package ar.edu.unju.fi.tp8.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "COMPRAS")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Min(value = 1, message = "Debe ingresar un codigo igual o mayor a uno")
	@Column(name = "com_codigo", nullable = false)
	private int codigo;
	
	@Min(value = 1, message = "El número de unidades debe ser igual o mayor a uno")
	@Column(name = "com_cantidad")
	private int cantidad;
	
	@Column(name = "com_total")
	private double total;
	
	@OneToMany(mappedBy = "compra")
	private List<Producto> productos = new ArrayList<Producto>();
	
	//@Valid
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prod_id")
	private Producto producto;
	
	public Compra() {
		super();
	}

	public Compra(Long id, int codigo, Producto producto, int cantidad, double total) {
		super();
		this.codigo = codigo;
		this.producto = producto;
		this.cantidad = cantidad;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getTotal() {
		double total = this.cantidad*this.producto.getPrecio();
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", codigo=" + codigo + ", cantidad=" + cantidad + ", total=" + total + ", producto="
				+ producto + "]";
	}
	
}
