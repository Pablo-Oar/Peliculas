package principal.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import principal.entities.Pelicula;
import principal.service.IPeliculaService;

@Controller
public class IndexController {
	
	@Autowired
	private IPeliculaService peliculaService;

	@GetMapping("/index")
	private String index(Model model,@RequestParam Map<String, Object> params) {
		int page = params.get("page") != null ? Integer.valueOf(params.get("page").toString()) -1 : 0;//Si el parametro es nulo sigfnifica que estamos en la primer pagina.
		PageRequest pageRequest = PageRequest.of(page, 6); //La pagina a buscar y cuantos registros trae por pagina.
		Page<Pelicula> pagePelicula = peliculaService.getPagination(pageRequest); //Le paso el objeto para consultar a partir de la pagina u los registros.
		int totalPage = pagePelicula.getTotalPages();  //Obtengo el total de paginas (numeros de paginacion).
		if(totalPage > 0) { //Si el numero de paginacionmes es mayor a 0, entonces. Tomo el total de paginas y las convierto a una lista de numeros.
			List<Integer> pages =  IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList()); //Que van del 1 a el total de paginas.
			model.addAttribute("pages", pages); //Envio las paginas al modelo.
		}
		model.addAttribute("current",page+1); //Identifico la pagina actual.
		model.addAttribute("next",page+2);
		model.addAttribute("prev",page);
		model.addAttribute("last",totalPage); //Para saber que estoy en la ultima pagina.
		model.addAttribute("peliculas", pagePelicula.getContent()); //Envio la lista de peliculas a mostrar.
		model.addAttribute("peliculasCarousel", peliculaService.showMoviesLastYear());
		return "index";
	}
}
