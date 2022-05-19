package principal.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import principal.dao.IComentarioDao;
import principal.entities.Comentario;
import principal.service.IComentarioService;

@Service
public class ComentarioService implements IComentarioService {

	@Autowired
	private IComentarioDao comentarioDao;
 	
	@Override
	@Transactional(readOnly=true)
	public List<Comentario> listarComentarios() {
		return comentarioDao.findAll();
	}

	@Override
	@Transactional
	public void guardarComentario(Comentario comentario) {
		comentarioDao.save(comentario);
	}

	@Override
	@Transactional(readOnly=true)
	public Comentario encontrarComentario(Comentario comentario) {
		return comentarioDao.findById(comentario.getIdComentario()).orElse(null);
	}

	@Override
	@Transactional
	public void eliminarComentario(Comentario comentario) {
		comentarioDao.delete(encontrarComentario(comentario));
	}

}
