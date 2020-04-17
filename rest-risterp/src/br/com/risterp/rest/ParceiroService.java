package br.com.risterp.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.risterp.dao.ParceiroDAO;
import br.com.risterp.entidade.Parceiro;



@Path("/parceiros")
public class ParceiroService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";
	private ParceiroDAO parceiroDAO;

	@PostConstruct
	private void init() {
		parceiroDAO = new ParceiroDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Parceiro> listarParceiros() {
		List<Parceiro> lista = null;

		try {
			lista = parceiroDAO.listarParceiros();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return lista;
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Parceiro> parceiroId(@PathParam("id") int idParceiro) {
		List<Parceiro> lista = null;

		try {
			lista = parceiroDAO.listarParceiro(idParceiro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarPessoa(Parceiro parceiro, @PathParam("id") int idParceiro) {
		String msg = "";

		try {
			parceiroDAO.editarParceiro(parceiro, idParceiro);
			msg = "Parceiro alterado com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao editar o parceiro!";
			e.printStackTrace();
		}
		return msg;
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletarPessoa(@PathParam("id") int idParceiro) {
		String msg = "";

		try {
			parceiroDAO.removerParceiro(idParceiro);
			msg = "Parceiro excluido com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao deletar o parceiro!\n" + e.getMessage();
			e.printStackTrace();
		}
		return msg;
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserirParceiro(Parceiro parceiro) {
		String msg = "";

		try {
			parceiroDAO.inserirParceiro(parceiro);
			msg = "Parceiro cadastrado com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao incluir a pessoa!";
			e.printStackTrace();
		}
		return msg;
	}

	@POST
	@Path("/test")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		String msg = "";

		try {
			msg = "Serviço em execucao!";
		} catch (Exception e) {
			msg = "Erro no no servico!";
			e.printStackTrace();
		}
		return msg;
	}
	
	@GET
	@Path("/numeroExtenso/{vlr}")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public String valorExtenso(@PathParam("vlr") double vlr) {

		try {
			return parceiroDAO.numeroExtenso(vlr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "zero";
	}
	
}
