package principal.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import principal.entities.Actor;
import principal.entities.Comentario;
import principal.entities.Pelicula;
import principal.entities.Usuario;
import principal.service.IActorService;
import principal.service.IComentarioService;
import principal.service.IPeliculaService;
import principal.service.IUsuarioService;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

	@Autowired
	private IPeliculaService peliculasService;
	
	@Autowired
	private IComentarioService comentarioService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPeliculaService peliculaService;
	
	@Autowired
	private IActorService actorService;
	
	@GetMapping("/show-detaills/{idPelicula}")
	public String mostrarDetalles(Pelicula pelicula, Model model,Comentario comentario,
								  Authentication auth, HttpSession http, RedirectAttributes feedBack) {
		Usuario user = usuarioService.getUsuarioByUsername(auth.getName()).orElse(null);
		model.addAttribute("user", user);
		model.addAttribute("pelicula", peliculasService.econtrarPelicula(pelicula));
		model.addAttribute("comentario", comentario);
		return "show-detaills";
	}
	
	@GetMapping("/peli-form/{idPelicula}")
	public String peliform( Pelicula pelicula,Model model) {
		
		if( pelicula.getIdPelicula() != 0) {
			model.addAttribute("pelicula", peliculaService.econtrarPelicula(pelicula));
		}
		return "peliform";
	}
	
	@PostMapping("/save-peli")
	public String guardarPeli(@RequestParam(name="file", required = false) MultipartFile portada, Pelicula pelicula,
								RedirectAttributes feedBack) {
		if(!portada.isEmpty()) {
			String ruta = "D://Programacion/Java/Proyectos/peliculas/src/main/resources/static/imgs";
			String nombre= UUID.randomUUID()+""+ portada.getOriginalFilename();
			try {
				byte[] bytesImg= portada.getBytes();
				Path rutaAbs = Paths.get(ruta + "//" + nombre);
				Files.write(rutaAbs, bytesImg);
				pelicula.setPortada(nombre);
			}catch(Exception e) {
				e.getCause().getMessage();
			}
		}
		
		peliculaService.guardar(pelicula);
		feedBack.addFlashAttribute("peliGuardada", "Pelicula guardada con exito");
		feedBack.addFlashAttribute("peliParaActor",pelicula);
		return "redirect:/actors/actors-form";
	}
	
	@GetMapping("/editar-peli/{idPelicula}")
	public String editarPeli(Model model,Pelicula pelicula) {
		model.addAttribute("pelicula", peliculasService.econtrarPelicula(pelicula));
		return "redirect:/peli-form/{idPelicula}";
	}
	
	@GetMapping("/delete-peli/{idPelicula}")
	private String eliminarPeli(Pelicula pelicula) {
		peliculaService.eliminarPelicula(peliculaService.econtrarPelicula(pelicula));
		return "redirect:/index";
	}
	
	@PostMapping("/addComment/{idPelicula}")
	public String agregarComentariosPelicula(Model model,Comentario comentario,Pelicula pelicula,
											 Authentication auth, HttpSession http, RedirectAttributes feedBack	) {
		if(auth != null) {
			Usuario usuario = usuarioService.getUsuarioByUsername(auth.getName()).orElse(null);
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			comentario.setPelicula(pelicula);
			comentario.setUsuario(usuario);
			comentario.setFechaComentario(formatter.format(calendar.getTime()));
			comentarioService.guardarComentario(comentario);
		}
		return "redirect:/peliculas/show-detaills/{idPelicula}";
	}
	
	@GetMapping("/add-actor/{idPelicula}")
	public String addActorPeli(Model model, Pelicula pelicula,Actor actor) {
		model.addAttribute("pelicula",peliculasService.econtrarPelicula(pelicula));
		model.addAttribute("actor",actor);
		return "addActorPeli";
	}
	
	@PostMapping("/saveActorPeli")
	public String saveActorPeli(Actor actor, RedirectAttributes feedBack, Model model, Pelicula pelicula,
								@RequestParam(name="file") MultipartFile imagen) {
		actor.setPelicula(pelicula);
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
		feedBack.addFlashAttribute("actorGuardado", "Actor guardado con exito");
		return "redirect:/index";
	}
	
}
