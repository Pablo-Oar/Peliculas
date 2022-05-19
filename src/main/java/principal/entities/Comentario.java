package principal.entities;


import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comentarios")
public class Comentario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comentario")
	private int idComentario;
	
	private String texto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pelicula pelicula;
	
	@Column(name = "fecha_comentario")
	private String  fechaComentario;

	public Comentario() {
		
	}

	public Comentario(String texto, Usuario usuario, Pelicula pelicula,String fechaComentario) {
		this.texto = texto;
		this.usuario = usuario;
		this.pelicula = pelicula;
		this.fechaComentario = fechaComentario;
	}

	public Comentario(int idComentario, String texto, Usuario usuario, Pelicula pelicula,String fechaComentario) {
		this.idComentario = idComentario;
		this.texto = texto;
		this.usuario = usuario;
		this.pelicula = pelicula;
		this.fechaComentario = fechaComentario;
	}

	public int getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getFechaComentario() {
		return fechaComentario;
	}

	public void setFechaComentario(String fechaComentario) {
		this.fechaComentario = fechaComentario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(idComentario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		return idComentario == other.idComentario;
	}

}
