package principal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import principal.service.IPeliculaService;

@Controller
public class HomeController {

	@Autowired
	private IPeliculaService peliculaService; 
	
	@GetMapping("/")
	public String home(Model model, Authentication auth) {
		model.addAttribute("peliculasCarousel", peliculaService.showMoviesLastYear());
		return "home";
	}
}
