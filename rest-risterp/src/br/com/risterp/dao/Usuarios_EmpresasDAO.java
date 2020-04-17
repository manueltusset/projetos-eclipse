package br.com.risterp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.risterp.config.BDConfig;
import br.com.risterp.entidade.Usuarios_Empresas;

public class Usuarios_EmpresasDAO {
	public List<Usuarios_Empresas> listar_empresas_usuario(int cusuario) throws Exception {
		List<Usuarios_Empresas> lista = new ArrayList<>();

		Connection con = BDConfig.getConnection();

		String sql = " select usuarios_empresas.cempresa, empresa.razao_social,  CONCAT(cidade.cidade, '/', cidade.uf) as cidade " + 
				" from usuarios_empresas " + 
				" inner join empresa on (empresa.cempresa = usuarios_empresas.cempresa) " + 
				" inner join cidade on (cidade.ccidade = empresa.ccidade) " + 
				" where usuarios_empresas.cusuario = ?";

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, cusuario);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Usuarios_Empresas usuarios_empresas = new Usuarios_Empresas();
			usuarios_empresas.setCempresa(rs.getInt("cempresa"));
			usuarios_empresas.setRazao_social(rs.getString("razao_social"));
			usuarios_empresas.setCidade(rs.getString("cidade"));
			lista.add(usuarios_empresas);
		}
		return lista;
	}
}
