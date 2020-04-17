package br.com.risterp.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.risterp.dao.Usuarios_EmpresasDAO;
import br.com.risterp.entidade.Usuarios_Empresas;

@Path("/usuarios_empresas")
public class Usuarios_EmpresasService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";
	private Usuarios_EmpresasDAO usuarios_empresaDAO;

	@PostConstruct
	private void init() {
		usuarios_empresaDAO = new Usuarios_EmpresasDAO();
	}

	@GET
	@Path("/empresas/{cusuario}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Usuarios_Empresas> buscar_empresas_usuario(@PathParam("cusuario") int cusuario) {
		List<Usuarios_Empresas> lista = new ArrayList<>();

		try {
			lista = usuarios_empresaDAO.listar_empresas_usuario(cusuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
