package principal.service;

import java.util.List;
import java.util.Optional;

import principal.entities.Usuario;


public interface IUsuarioService {
	List<Usuario> listadoUsuarios();
	Optional<Usuario> getUsuarioById(Usuario usuario);
	Optional<Usuario> getUsuarioByUsername(String username);
	void guardarUsuario(Usuario usuario);
	boolean existsById(Usuario usuario);
	boolean existsByUsername(Usuario usuario);
}
