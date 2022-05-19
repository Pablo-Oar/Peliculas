package principal.service;

import java.util.List;

import principal.entities.Comentario;

public interface IComentarioService {

	List<Comentario> listarComentarios();
	void guardarComentario(Comentario comentario);
	Comentario encontrarComentario(Comentario comentario);
	void eliminarComentario(Comentario comentario);
}
