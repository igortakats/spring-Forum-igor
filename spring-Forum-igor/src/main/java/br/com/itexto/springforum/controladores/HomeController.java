package br.com.itexto.springforum.controladores;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.itexto.springforum.DAO.DAOAssunto;
import br.com.itexto.springforum.DAO.DAOUsuario;
import br.com.itexto.springforum.entidades.Usuario;

@Controller
public class HomeController implements Serializable{

	private static final long serialVersionUID = -6462824479388659814L;
	
	@Autowired
	private DAOUsuario daoUsuario;
	
	@Autowired
	private DAOAssunto daoAssunto;
	
	@RequestMapping("/")
	public String index(Map<String, Object> model) {
		
		model.put("assuntos", this.getDaoAssunto().list());
		model.put("usuarios", this.getDaoUsuario().list());
		
		return "index";
	}
	
	@RequestMapping("/registro")
	public String registro(Map<String, Object> model) {
		if (model.get("usuario") == null) {
			Usuario usr = new Usuario();
			model.put("usuario", usr);
		}
		return "registro";
	}

	@RequestMapping(value="/executaRegistro",method=RequestMethod.POST)
	public String executarRegistro(@Valid Usuario usuario, 
								   HttpSession sessao, 
								   BindingResult result,
								   @RequestParam(value="avatar", required=false) MultipartFile avatar) throws IOException	 {
		
		if (result.hasErrors()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("usuario", usuario);
			return registro(model);
			
		}
		
		this.getDaoUsuario().persistir(usuario);
		
		if (!avatar.isEmpty()) {
			this.processarAvatar(usuario, avatar);
		}
		
		
		sessao.setAttribute("usuario", usuario);
		return "redirect:/";
	}
	
	private void processarAvatar(Usuario usuario, MultipartFile file) throws IOException {
		byte[] conteudo = file.getBytes();
		this.persistirConteudo(usuario, conteudo);
	}
	
	private void persistirConteudo(Usuario usuario, byte[] conteudo) {
		
	}
	
	public DAOUsuario getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(DAOUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

	public DAOAssunto getDaoAssunto() {
		return daoAssunto;
	}

	public void setDaoAssunto(DAOAssunto daoAssunto) {
		this.daoAssunto = daoAssunto;
	}
	
	public List<?> list() {
	
		return null;
	}

}
