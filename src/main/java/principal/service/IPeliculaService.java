package principal.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import principal.entities.Pelicula;

public interface IPeliculaService {
	List<Pelicula> mostrarPeliculas(); 
	void guardar(Pelicula pelicula);
	Pelicula econtrarPelicula(Pelicula pelicula);
	void eliminarPelicula(Pelicula pelicula);
	List<Pelicula> showMoviesLastYear();
	Page<Pelicula> getPagination(Pageable pageable);
}
