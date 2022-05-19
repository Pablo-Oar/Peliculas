package principal.controllers;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import principal.entities.Rol;
import principal.entities.Usuario;
import principal.enums.RolNombre;
import principal.service.IRolService;
import principal.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder encriptarPassword;
	
	@Autowired
	private IRolService rolService;
	
	@GetMapping("/registro")
	public String registrar(RedirectAttributes feedBack, Model model) {
		return "registro";
	}
	
	@PostMapping("/save")
	public String saveUser(String username,String password,Model model, Usuario user, RedirectAttributes feedBack) {
		
		if(usuarioService.existsByUsername(user)) {
			model.addAttribute("usuarioRepetido", "El usuario ya existe");
			return "registro";
		}
		
		user.setUsername(username); 
		user.setPassword(encriptarPassword.encode(password));
		
		//Para setearle el rol lo busco en la enumeracion.
		Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
		//Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
		Set<Rol> roles = new HashSet<Rol>();
		roles.add(rolUser);	//roles.add(rolAdmin);
		user.setRoles(roles);
		
		//Guardo el usuario nuevo con su rol USER --> Todos van a ser ROLE_USER por defecto.
		usuarioService.guardarUsuario(user);
		
		//Envio a la vista un feedback para informar que se registro.
		model.addAttribute("usuarioRegistrado", "El usuario se registro de manera correcta, inicie sesion");
		//feedBack.addFlashAttribute("usuarioRegistrado", "El usuario se registro de manera correcta, inicie sesion");
		return "redirect:/login";
	}
}
