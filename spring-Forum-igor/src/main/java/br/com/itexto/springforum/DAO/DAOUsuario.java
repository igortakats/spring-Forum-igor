package br.com.itexto.springforum.DAO;

import java.io.Serializable;
import java.util.List;

import br.com.itexto.springforum.entidades.Usuario;

public class DAOUsuario implements Serializable{

	private static final long serialVersionUID = 3382091732037378555L;
	
	public Usuario getUsuario(String login, String senha) {

		Usuario usuario = new Usuario();
		
		return usuario;
		
	}
	
	public void persistir(Usuario usuario)  {
		
	}
	
	public List<?> list() {
		return null;
	}
	
}
