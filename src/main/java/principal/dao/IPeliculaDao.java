package principal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import principal.entities.Pelicula;

@Repository
public interface IPeliculaDao extends JpaRepository<Pelicula, Integer>{

	//@Query( value = "SELECT * FROM peliculas p WHERE p.fecha_estreno >= '2021-05-07' and p.fecha_estreno <= CURRENT_TIMESTAMP()", 
		//	nativeQuery = true)
	@Query( value = "SELECT * FROM peliculas p WHERE YEAR(p.fecha_estreno)=YEAR(NOW())",nativeQuery = true)
	List<Pelicula> showMoviesLastYear();
	
	@Query( value = "SELECT * FROM peliculas p ORDER BY p.fecha_estreno desc",nativeQuery = true)
	List<Pelicula> mostrarPeliculasPorFecha(); 
	
}
