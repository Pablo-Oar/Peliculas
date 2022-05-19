package principal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import principal.entities.Comentario;

@Repository
public interface IComentarioDao extends JpaRepository<Comentario, Integer>{

}
