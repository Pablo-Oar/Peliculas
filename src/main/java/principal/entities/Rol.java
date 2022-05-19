package principal.entities;

import java.util.Objects;

import javax.persistence.*;

import org.springframework.lang.NonNull;

import principal.enums.RolNombre;

@Entity
@Table(name="roles")
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_rol")
	private int idRol;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private RolNombre rolNombre;

	public Rol() {
		
	}

	public Rol(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	public Rol(int idRol, RolNombre rolNombre) {
		this.idRol = idRol;
		this.rolNombre = rolNombre;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public RolNombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		return idRol == other.idRol;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", rolNombre=" + rolNombre + "]";
	}
}
