package principal.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import principal.dao.IActorDao;
import principal.entities.Actor;
import principal.service.IActorService;

@Service
public class ActorService implements IActorService{

	@Autowired
	private IActorDao actorDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Actor> mostrarActores() {
		return actorDao.findAll();
	}


	@Override
	@Transactional(readOnly=true)
	public Actor econtrarActor(Actor actor) {
		return actorDao.findById(actor.getIdActor()).orElse(null);
	}

	@Override
	@Transactional
	public void eliminarActor(Actor actor) {
		try {
			actorDao.delete(econtrarActor(actor));
		}catch(Exception e) {
			e.getCause().getMessage();
		}
		
	}

	@Override
	@Transactional
	public void guardar(Actor actor) {
		actorDao.save(actor);
	}

}
