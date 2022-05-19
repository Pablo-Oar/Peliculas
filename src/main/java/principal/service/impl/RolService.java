package principal.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import principal.dao.IRolDao;
import principal.entities.Rol;
import principal.enums.RolNombre;
import principal.service.IRolService;

@Service
public class RolService implements IRolService{

	@Autowired
	private IRolDao rolDao;
	
	@Override
	public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
		return rolDao.findByRolNombre(rolNombre);
	}

	@Override
	public void guardarRol(Rol rol) {
		rolDao.save(rol);
	}

	@Override
	public boolean existsByRolNombre(RolNombre rolNombre) {
		return rolDao.existsByRolNombre(rolNombre);
	}

}
