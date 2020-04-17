package br.com.risterp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.risterp.config.BDConfig;
import br.com.risterp.entidade.Parceiro;
import br.com.risterp.util.Util;

public class ParceiroDAO {

	public List<Parceiro> listarParceiros() throws Exception {
		List<Parceiro> lista = new ArrayList<>();

		Connection con = BDConfig.getConnection();

		String sql = " select cparceiro, razao_social, cnpj, valor_contratado, vencimento, dia_inicio, email, telefone, ultimo_pagamento, TIMESTAMPDIFF(DAY, parceiro.ultimo_pagamento, CURDATE()) as dias_ultimo_pagamento from parceiro ";

		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Parceiro parceiro = new Parceiro();
			parceiro.setCparceiro(rs.getInt("cparceiro"));
			parceiro.setRazao_social(rs.getString("razao_social"));
			parceiro.setCnpj(rs.getString("cnpj"));
			parceiro.setValor_contratado(rs.getDouble("valor_contratado"));
			parceiro.setVencimento(rs.getDate("vencimento"));
			parceiro.setDia_inicio(rs.getDate("dia_inicio"));
			parceiro.setEmail(rs.getString("email"));
			parceiro.setTelefone(rs.getString("telefone"));
			parceiro.setUltimo_pagamento(rs.getDate("ultimo_pagamento"));
			parceiro.setDias_ultimo_pagamento(rs.getInt("dias_ultimo_pagamento"));
			lista.add(parceiro);
		}
		return lista;
	}

	public List<Parceiro> listarParceiro(int cParceiro) throws Exception {
		List<Parceiro> lista = new ArrayList<>();

		Connection con = BDConfig.getConnection();

		String sql = "select cparceiro, razao_social, cnpj, valor_contratado, vencimento, dia_inicio, email, telefone, ultimo_pagamento, TIMESTAMPDIFF(DAY, parceiro.ultimo_pagamento, CURDATE()) as dias_ultimo_pagamento from parceiro where parceiro.cparceiro = ?";

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, cParceiro);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Parceiro parceiro = new Parceiro();
			parceiro.setCparceiro(rs.getInt("cparceiro"));
			parceiro.setRazao_social(rs.getString("razao_social"));
			parceiro.setCnpj(rs.getString("cnpj"));
			parceiro.setValor_contratado(rs.getDouble("valor_contratado"));
			parceiro.setVencimento(rs.getDate("vencimento"));
			parceiro.setDia_inicio(rs.getDate("dia_inicio"));
			parceiro.setEmail(rs.getString("email"));
			parceiro.setTelefone(rs.getString("telefone"));
			parceiro.setUltimo_pagamento(rs.getDate("ultimo_pagamento"));
			parceiro.setDias_ultimo_pagamento(rs.getInt("dias_ultimo_pagamento"));
			lista.add(parceiro);
		}
		return lista;
	}

	public void inserirParceiro(Parceiro parceiro) throws Exception {
		Connection con = BDConfig.getConnection();

		String sql = "insert into parceiro(cparceiro, razao_social, cnpj, valor_contratado, vencimento, dia_inicio, email, telefone, ultimo_pagamento) "
				+ " values ((select coalesce(max(parceiro.cparceiro),0) + 1 as num from parceiro), ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, parceiro.getRazao_social());
		statement.setString(2, parceiro.getCnpj().replace(".", "").replace("/", "").replace("-", ""));
		statement.setDouble(3, parceiro.getValor_contratado());
		if (parceiro.getVencimento() == null) {
			statement.setDate(4, null);
		} else {
			statement.setDate(4, new java.sql.Date(parceiro.getVencimento().getTime()));
		}
		if (parceiro.getDia_inicio() == null) {
			statement.setDate(5, null);
		} else {
			statement.setDate(5, new java.sql.Date(parceiro.getDia_inicio().getTime()));
		}
		statement.setString(6, parceiro.getEmail());
		statement.setString(7, parceiro.getTelefone());
		if (parceiro.getUltimo_pagamento() == null) {
			statement.setDate(8, null);
		} else {
			statement.setDate(8, new java.sql.Date(parceiro.getUltimo_pagamento().getTime()));
		}
		statement.execute();
	}

	public void editarParceiro(Parceiro parceiro, int cParceiro) throws Exception {
		Connection con = BDConfig.getConnection();

		String sql = "update parceiro set razao_social = ?, cnpj = ?, valor_contratado = ?, vencimento = ?, dia_inicio = ?, email = ?, telefone = ?, ultimo_pagamento = ? where parceiro.cparceiro = ?";

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, parceiro.getRazao_social());
		statement.setString(2, parceiro.getCnpj().replace(".", "").replace("/", "").replace("-", ""));
		statement.setDouble(3, parceiro.getValor_contratado());

		if (parceiro.getVencimento() == null) {
			statement.setDate(4, null);
		} else {
			statement.setDate(4, new java.sql.Date(parceiro.getVencimento().getTime()));
		}
		if (parceiro.getDia_inicio() == null) {
			statement.setDate(5, null);
		} else {
			statement.setDate(5, new java.sql.Date(parceiro.getDia_inicio().getTime()));
		}

		statement.setString(6, parceiro.getEmail());
		statement.setString(7, parceiro.getTelefone());
		if (parceiro.getUltimo_pagamento() == null) {
			statement.setDate(8, null);
		} else {
			statement.setDate(8, new java.sql.Date(parceiro.getUltimo_pagamento().getTime()));
		}
		statement.setInt(9, cParceiro);
		statement.execute();
	}

	public void removerParceiro(int cParceiro) throws Exception {
		Connection con = BDConfig.getConnection();

		String sql = "delete from parceiro where parceiro.cparceiro = ?";

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, cParceiro);
		statement.execute();
	}

	public String numeroExtenso(double vlr) throws Exception {
		return Util.valorExtenso(vlr);
	}
}
