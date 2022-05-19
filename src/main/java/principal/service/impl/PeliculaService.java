package principal.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import principal.dao.IPeliculaDao;
import principal.entities.Pelicula;
import principal.service.IPeliculaService;

@Service
public class PeliculaService implements IPeliculaService{

	@Autowired
	private IPeliculaDao peliculaDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Pelicula> mostrarPeliculas() {
		return peliculaDao.mostrarPeliculasPorFecha();
	}

	@Override
	@Transactional
	public void guardar(Pelicula pelicula) {
		peliculaDao.save(pelicula);
	}

	@Override
	@Transactional(readOnly=true)
	public Pelicula econtrarPelicula(Pelicula pelicula) {
		return peliculaDao.findById(pelicula.getIdPelicula()).orElse(null);
	}

	@Override
	@Transactional
	public void eliminarPelicula(Pelicula pelicula) {
			peliculaDao.delete(pelicula);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Pelicula> showMoviesLastYear() {
		return peliculaDao.showMoviesLastYear();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Page<Pelicula> getPagination(Pageable pageable){
		return peliculaDao.findAll(pageable);
	}

}
