package br.com.itexto.springforum.controladores;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.itexto.springforum.DAO.DAOUsuario;
import br.com.itexto.springforum.entidades.Usuario;

@Controller
public class AutenticacaoController implements Serializable {

	private static final long serialVersionUID = 6874419628608497463L;
	
	/*
	 * Em metodo, eh possivel o envio de dois parametros para informar ao controller
	 * que dois tipos ou mais de metodos sao aceitaveis. Contudo caso apenas um seja
	 * processado, nao eh necessario informar o valor entre chaves.
	 */
	@RequestMapping(value="/login", method={RequestMethod.POST, RequestMethod.PUT})
	public String login(@RequestParam("login") String login, 
						@RequestParam("senha") String senha,
						HttpSession session) {
		
		DAOUsuario daoUsuario = new DAOUsuario();
		
		Usuario usuario = daoUsuario.getUsuario(login, senha);
		
		if (usuario == null) {
			return "loginFalho";
		} else {
			usuario.setUltimoLogin(new Date());
			daoUsuario.persistir(usuario);
			session.setAttribute("usuario", usuario);
			return "redirect:/";
		}
		
	}
	

}
