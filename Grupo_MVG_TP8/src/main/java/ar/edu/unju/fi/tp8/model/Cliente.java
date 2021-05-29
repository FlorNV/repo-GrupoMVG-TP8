package ar.edu.unju.fi.tp8.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="CLIENTES")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_id")
	private Long id;
	
	@Column(name = "cli_tipoDocumento")
	private String tipoDocumento;
	
	@Column(name = "cli_nroDocumento", nullable = false)
	private int nroDocumento;
	
	@Column(name = "cli_nombreApellido", length = 300)
	private String nombreApellido;
	
	@Column(name = "cli_email")
	private String email;
	
	@Column(name = "cli_password")
	private String password;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "cli_fechaNacimiento")
	private LocalDate fechaNacimiento;
	
	@Column(name = "cli_codigoAreaTelefono")
	private int codigoAreaTelefono;
	
	@Column(name = "cli_nroTelefono")
	private int nroTelefono;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "cli_fechaUltimaCompra")
	private LocalDate fechaUltimaCompra;
	
	@Autowired
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cuen_id")
	private Cuenta cuenta;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(Long id, String tipoDocumento, int nroDocumento, String nombreApellido, String email,
			String password, LocalDate fechaNacimiento, int codigoAreaTelefono, int nroTelefono,
			LocalDate fechaUltimaCompra, Cuenta cuenta) {
		super();
		this.id = id;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.nombreApellido = nombreApellido;
		this.email = email;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.codigoAreaTelefono = codigoAreaTelefono;
		this.nroTelefono = nroTelefono;
		this.fechaUltimaCompra = fechaUltimaCompra;
		this.cuenta = cuenta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido.toUpperCase();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}

	public void setCodigoAreaTelefono(int codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}

	public int getNroTelefono() {
		return nroTelefono;
	}

	public void setNroTelefono(int nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}
	
	public int getEdad() {
		Period periodo = Period.between(fechaNacimiento, LocalDate.now());
		int edad = periodo.getYears();
		return edad;
	}
	
	public String getTiempoDesdeUltimaCompra() {
		Period periodo = Period.between(fechaUltimaCompra, LocalDate.now());
		String tiempo = "Años: " + periodo.getYears() + " - Meses: " + periodo.getMonths() + " - Dias: " + periodo.getDays();
		return tiempo;
	}
	
	public long getTiempoDesdeFechaNacimiento() {
		Calendar hoy = Calendar.getInstance();
		Calendar fNacimiento = Calendar.getInstance();
		fNacimiento.set(fechaNacimiento.getYear(), fechaNacimiento.getMonthValue()-1, fechaNacimiento.getDayOfMonth());
		long tiempo = hoy.getTimeInMillis() - fNacimiento.getTimeInMillis();
		long dias = tiempo/1000/60/60/24;
		return dias;
	}
	
	public String getTiempoHastaCumpleanios() {
		LocalDate fechaProximoCumpleanios;
		LocalDate cumple = fechaNacimiento.withYear(LocalDate.now().getYear());
		if(cumple.isBefore(LocalDate.now())) {
			fechaProximoCumpleanios = fechaNacimiento.withYear(LocalDate.now().getYear()+1);
		}
		else
			fechaProximoCumpleanios = fechaNacimiento.withYear(LocalDate.now().getYear());
		
		Period periodo = Period.between(LocalDate.now(), fechaProximoCumpleanios);
		
		LocalDateTime fechaProximoCumpleaniosConHora = LocalDateTime.of(fechaProximoCumpleanios.getYear(), fechaProximoCumpleanios.getMonthValue(), fechaProximoCumpleanios.getDayOfMonth(), 0, 0, 0);
		Duration duracion = Duration.between(LocalDateTime.now(), fechaProximoCumpleaniosConHora);
		String tiempo = periodo.getDays() + " Dias - " + periodo.getMonths() + " Meses - " + periodo.getYears() + " Año - " + duracion.toHoursPart() + " Horas - " + duracion.toMinutesPart() + " Min - " + duracion.toSecondsPart() + " Seg";
		return tiempo;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", tipoDocumento=" + tipoDocumento + ", nroDocumento=" + nroDocumento
				+ ", nombreApellido=" + nombreApellido + ", email=" + email + ", password=" + password
				+ ", fechaNacimiento=" + fechaNacimiento + ", codigoAreaTelefono=" + codigoAreaTelefono
				+ ", nroTelefono=" + nroTelefono + ", fechaUltimaCompra=" + fechaUltimaCompra + ", cuenta=" + cuenta
				+ ", getEdad()=" + getEdad() + ", getTiempoDesdeUltimaCompra()=" + getTiempoDesdeUltimaCompra()
				+ ", getTiempoDesdeFechaNacimiento()=" + getTiempoDesdeFechaNacimiento()
				+ ", getTiempoHastaCumpleanios()=" + getTiempoHastaCumpleanios() + "]";
	}
	
}
