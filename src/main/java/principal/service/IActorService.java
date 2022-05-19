package principal.service;

import java.util.List;
import principal.entities.Actor;

public interface IActorService {
	List<Actor> mostrarActores(); 
	void guardar(Actor actor);
	Actor econtrarActor(Actor actor);
	void eliminarActor(Actor actor);
}
