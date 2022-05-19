package principal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import principal.entities.Actor;

@Repository
public interface IActorDao extends JpaRepository<Actor, Integer>{

}
