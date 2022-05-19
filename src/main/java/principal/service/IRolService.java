package principal.service;

//import java.util.List;
import java.util.Optional;
import principal.entities.Rol;
import principal.enums.RolNombre;

public interface IRolService {
	//List<Rol> listadoRoles();
	//Optional<Rol> getRolById(Rol rol);
	Optional<Rol> getByRolNombre(RolNombre rolNombre);
	void guardarRol(Rol rol);
	//boolean existsById(Rol usuario);
	boolean existsByRolNombre(RolNombre rolNombre);
}
