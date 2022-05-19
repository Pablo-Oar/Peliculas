package principal.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "peliculas")
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pelicula")
	private int idPelicula;

	private String titulo;
	
	private String sinapsis;

	private String portada;

	@Column(name = "fecha_estreno")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date fechaEstreno;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pelicula",cascade=CascadeType.ALL)
	private List<Actor> actores;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pelicula", cascade=CascadeType.ALL)
	private List<Comentario> comentarios;
	
	// Método para agregar actores a la pelicula
	public void agregarActor(Actor actor) {
		actores.add(actor);
	}
	
	// Método para agregar comentarios a la pelicula
	public void agregarComentario(Comentario comentario) {
		comentarios.add(comentario);
	}

	public Pelicula() {

	}

	public Pelicula(String titulo, String portada, Date fechaEstreno, List<Actor> actores,List<Comentario> comentarios) {
		super();
		this.titulo = titulo;
		this.portada = portada;
		this.fechaEstreno = fechaEstreno;
		this.actores = actores;
		this.comentarios = comentarios;
	}

	public Pelicula(int idPelicula, String titulo, String portada, Date fechaEstreno, List<Actor> actores,List<Comentario> comentarios) {
		super();
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.portada = portada;
		this.fechaEstreno = fechaEstreno;
		this.actores = actores;
		this.comentarios = comentarios;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getTitulo() {
		return titulo;
	}
	
	

	public String getSinapsis() {
		return sinapsis;
	}

	public void setSinapsis(String sinapsis) {
		this.sinapsis = sinapsis;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public Date getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public List<Actor> getActores() {
		return actores;
	}

	public void setActores(List<Actor> actores) {
		this.actores = actores;
	}
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentario) {
		this.comentarios = comentario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPelicula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		return idPelicula == other.idPelicula;
	}
}
