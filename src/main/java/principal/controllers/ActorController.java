package principal.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import principal.entities.Actor;
import principal.entities.Pelicula;
import principal.service.IActorService;
import principal.service.IPeliculaService;

@Controller
@RequestMapping("/actors")
@SessionAttributes("peliParaActor")
public class ActorController {

	@Autowired
	private IActorService actorService;
	
	@Autowired
	private IPeliculaService peliculaService;
	
	@GetMapping("/actors-form")
	public String actorsForm(Actor actor, Model model, @ModelAttribute("peliParaActor") Pelicula pelicula) {
		model.addAttribute("film", pelicula);
		model.addAttribute("actor",actor);
		return "actorsForm";
	}
	
	@PostMapping("/save")
	public String guardarActor(Actor actor, RedirectAttributes feedBack, Model model,
								@ModelAttribute("peliParaActor") Pelicula pelicula,
								@RequestParam(name="file")MultipartFile imagen) {
		
		if(!imagen.isEmpty()) {
			String ruta = "D://Programacion/Java/Proyectos/peliculas/src/main/resources/static/imgs";
			String nombre= UUID.randomUUID()+""+ imagen.getOriginalFilename();
			try {
				byte[] bytesImg= imagen.getBytes();
				Path rutaAbs = Paths.get(ruta + "//" + nombre);
				Files.write(rutaAbs, bytesImg);
				actor.setImagen(nombre);
			}catch(Exception e) {
				e.getCause().getMessage();
			}
		}
		actorService.guardar(actor);
		feedBack.addFlashAttribute("actorGuardado","Actor Guardado con exito");
		return "redirect:/actors/actors-form";
	}
	
	@GetMapping("/delete-actor/{idActor}/{idPelicula}")
	public String deleteActor(Actor actor, Pelicula pelicula) {
		actorService.eliminarActor(actorService.econtrarActor(actor));
		return "redirect:/peliculas/show-detaills/{idPelicula}";
	}
	
	@GetMapping("/edit-actor/{idActor}/{idPelicula}")
	public String editActor(Actor actor, Pelicula pelicula,Model model) {
		model.addAttribute("actor", actorService.econtrarActor(actor));
		model.addAttribute("pelicula", peliculaService.econtrarPelicula(pelicula));
		return "editActor";
	}
	
	@PostMapping("/editActor/{idActor}/{idPelicula}")
	public String editarActor(Actor actor, Pelicula pelicula, @RequestParam(name="file", required = false)MultipartFile imagen) {
		actor.setPelicula(peliculaService.econtrarPelicula(pelicula));
		if(!imagen.isEmpty()) {
			String ruta = "D://Programacion/Java/Proyectos/peliculas/src/main/resources/static/imgs";
			String nombre= UUID.randomUUID()+""+ imagen.getOriginalFilename();
			try {
				byte[] bytesImg= imagen.getBytes();
				Path rutaAbs = Paths.get(ruta + "//" + nombre);
				Files.write(rutaAbs, bytesImg);
				actor.setImagen(nombre);
			}catch(Exception e) {
				e.getCause().getMessage();
			}
		}
		actorService.guardar(actor);
		return "redirect:/peliculas/show-detaills/{idPelicula}";
	}
}
