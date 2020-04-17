package br.com.risterp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.risterp.config.BDConfig;

public class UsuarioDAO {
	public String validarSenha(String login, String senha) throws Exception {
		Connection con = BDConfig.getConnection();

		String sql = " select usuario.cusuario, usuario.login, usuario.senha from usuario where usuario.login = '" + login
				+ "' and usuario.senha = '" + senha + "' ";

		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			return "OK|" + rs.getInt("cusuario");
		}

		return "N";
	}
}
