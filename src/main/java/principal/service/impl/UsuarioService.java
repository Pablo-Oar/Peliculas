package principal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import principal.dao.IUsuarioDao;
import principal.entities.Usuario;
import principal.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> listadoUsuarios() {
		return usuarioDao.findAll();
	}

	@Override
	public Optional<Usuario> getUsuarioById(Usuario usuario) {
		return usuarioDao.findById(usuario.getIdUsuario());
	}

	@Override
	public Optional<Usuario> getUsuarioByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	public boolean existsById(Usuario usuario) {
		return usuarioDao.existsById(usuario.getIdUsuario());
	}

	@Override
	public boolean existsByUsername(Usuario usuario) {
		return usuarioDao.existsByUsername(usuario.getUsername());
	}

}
