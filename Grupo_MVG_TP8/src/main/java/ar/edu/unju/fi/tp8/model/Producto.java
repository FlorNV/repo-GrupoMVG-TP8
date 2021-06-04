package ar.edu.unju.fi.tp8.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="PRODUCTOS")
public class Producto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_id")
	private Long id;
	
	@NotEmpty(message = "El nombre no puede estar vacio")
	@Size(message = "El nombre debe tener como mínimo 3 caracteres")
	@Column(name="prod_nombre")
	private String nombre;
	
	@Min(value = 1, message = "Debe ingresar un precio igual o mayor a uno")
	@Column(name="prod_precio")
	private double precio;
	
	@NotEmpty(message = "La marca no puede estar vacio")
	@Column(name="prod_marca")
	private String marca;
	
	@Min(value = 0, message = "Debe ingresar un stock igual o mayor a cero")
	@Column(name="prod_stock")
	private int stock;
	
	@Valid
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "com_id")
	private Compra compra;
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public Producto(Long id,
			@NotEmpty(message = "El nombre no puede estar vacio") @Size(message = "El nombre debe tener como mínimo 3 caracteres") String nombre,
			@Min(value = 1, message = "Debe ingresar un precio igual o mayor a uno") double precio,
			@NotEmpty(message = "La marca no puede estar vacio") String marca,
			@Min(value = 0, message = "Debe ingresar un stock igual o mayor a cero") int stock, @Valid Compra compra) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.marca = marca;
		this.stock = stock;
		this.compra = compra;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", marca=" + marca + ", stock="
				+ stock + ", compra=" + compra + "]";
	}

	
	
}
