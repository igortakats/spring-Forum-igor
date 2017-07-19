package br.com.itexto.springforum.controladores;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.com.itexto.springforum.entidades.Usuario;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {

	@RequestMapping("/usuario/autenticado")
	public ModelAndView infoAutenticado(@ModelAttribute("usuario") Usuario usuario) {
		
		ModelAndView mav = new ModelAndView("usuario/show");
		
		mav.getModel().put("usuario", usuario);
		
		return mav;
		
	}
	
	@RequestMapping("/resgistro")
	public String registro(Map<String, Object> model) {
		
		if(model.get("usuario") == null) {
			model.put("usuario", new Usuario());
		}
		
		return "registro";
	}
}
