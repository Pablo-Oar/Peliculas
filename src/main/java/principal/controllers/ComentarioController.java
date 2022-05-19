package principal.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import principal.entities.Comentario;
import principal.entities.Pelicula;
import principal.entities.Usuario;
import principal.service.IComentarioService;
import principal.service.IPeliculaService;
import principal.service.IUsuarioService;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	private IComentarioService comentarioService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPeliculaService peliculaService;

	
	@GetMapping("/editar-comentario/{idComentario}/{idPelicula}")
	public String editarComentario(	Model model,Comentario comentario, Pelicula pelicula) {
		model.addAttribute("comentario", comentarioService.encontrarComentario(comentario));
		model.addAttribute("pelicula", peliculaService.econtrarPelicula(pelicula));
		return "editCommentForm";
	}
	
	@PostMapping("/edit/{idComentario}/{idPelicula}")
	public String editarComentario(	Model model,Comentario comentario, Pelicula pelicula,
			Authentication auth, HttpSession http) {
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
	
	@GetMapping("/eliminar-comentario/{idComentario}/{idPelicula}")
	public String eliminarComentario(Model model,Comentario comentario, Pelicula pelicula) {

			comentarioService.eliminarComentario(comentario) ;
		
		return "redirect:/peliculas/show-detaills/{idPelicula}";
	}
}
