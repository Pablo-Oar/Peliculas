package principal.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import principal.entities.Usuario;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Integer>{
	Optional<Usuario> findByUsername(String username);
	boolean existsByUsername(String username);
}
