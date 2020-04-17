package br.com.risterp.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.risterp.dao.UsuarioDAO;
import br.com.risterp.util.Util;

@Path("/usuarios")
public class UsuarioService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";
	private UsuarioDAO usuarioDAO;

	@PostConstruct
	private void init() {
		usuarioDAO = new UsuarioDAO();
	}

	@GET
	@Path("/validar/{user}/{senha}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public String parceiroId(@PathParam("user") String user, @PathParam("senha") String senha) {
		String retorno;
		try {
			retorno = usuarioDAO.validarSenha(user, senha);
		} catch (Exception e) {
			e.printStackTrace();
			retorno = e.getMessage();
		}
		return retorno;
	}
}
