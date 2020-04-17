package br.com.risterp.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Extenso {

	private static ArrayList nro;
	private static BigInteger num;
	private static String qualificadores[][] = { { "centavo", "centavos" }, { "", "" }, { "mil", "mil" },
			{ "milhão", "milhões" }, { "bilhão", "bilhões" }, { "trilhão", "trilhões" },
			{ "quatrilhão", "quatrilhões" }, { "quintilhão", "quintilhões" }, { "sextilhão", "sextilhões" },
			{ "septilhão", "septilhões" } };
	private static String numeros[][] = {
			{ "zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez", "onze", "doze",
					"treze", "quatorze", "quinze", "desesseis", "desessete", "dezoito", "dezenove" },
			{ "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa" },
			{ "cem", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos",
					"oitocentos", "novecentos" } };
	private static String moedas[][] = { { "", "" }, { "cruzeiro", "cruzeiros" }, { "dolar", "dolares" },
			{ "real", "reais" } };

	public static String[] format(double valor, String textoIni, String textoFim, int linhas, int largura, String resto,
			int moeda) {
		String[] vext = new String[linhas];
		nro = new ArrayList();
		if (valor < 0) {
			valor *= -1;
		} else if (valor == 0) {
			vext[0] = "zero";
			for (int i = 1; i < linhas; i++) {
				vext[i] = "";
			}

			return vext;
		}
		num = new BigDecimal(valor).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100))
				.toBigInteger();

		// Adiciona valores
		nro.clear();
		if (num.equals(BigInteger.ZERO)) {
			// Centavos
			nro.add(new Integer(0));
			// Valor
			nro.add(new Integer(0));
		} else {
			// Adiciona centavos
			addRemainder(100);

			// Adiciona grupos de 1000
			while (!num.equals(BigInteger.ZERO)) {
				addRemainder(1000);
			}
		}
//        setNumber(valor);
		StringBuilder buf = new StringBuilder();

//        int numero = ((Integer) nro.get(0)).intValue();
		int ct;

		for (ct = nro.size() - 1; ct > 0; ct--) {
			// Se ja existe texto e o atual n�o � zero
			if (buf.length() > 0 && !ehGrupoZero(ct)) {
				buf.append(" e ");
			}
			buf.append(numToString(((Integer) nro.get(ct)).intValue(), ct));
		}
		if (buf.length() > 0) {
			if (ehUnicoGrupo()) {
//                buf.append(" de ");
			}
			while (buf.toString().endsWith(" ")) {
				buf.setLength(buf.length() - 1);
			}
			if (ehPrimeiroGrupoUm()) {
//                buf.insert(0, "h");
			}
			if (nro.size() == 2 && ((Integer) nro.get(1)).intValue() == 1) {
//                buf.append(" real");
				buf.append(" " + moedas[moeda][0]);
			} else {
				buf.append(" " + moedas[moeda][1]);
//                buf.append(" reais");
			}
			if (((Integer) nro.get(0)).intValue() != 0) {
				buf.append(" e ");
			}
		}
		if (((Integer) nro.get(0)).intValue() != 0) {
			buf.append(numToString(((Integer) nro.get(0)).intValue(), 0));
		}
		String retorno = (textoIni + buf.toString() + textoFim).toUpperCase();
		buf = new StringBuilder();
		int contador = 0;
		int totLinhas = 0;
		for (int i = 0; i < retorno.length() && totLinhas < linhas; i++) {
			if (contador == largura) {
				vext[totLinhas] = buf.toString();
				totLinhas++;
				buf = new StringBuilder();
				contador = 0;
			}
			contador++;
			buf.append(retorno.charAt(i));
			if (i == retorno.length() - 1) {
				int larguraRestante = largura - contador;
				for (int a = 0; a < larguraRestante; a++) {
					buf.append(resto);
				}
				vext[totLinhas] = buf.toString();
				buf = new StringBuilder();
				totLinhas++;
				int linhasRestante = linhas - totLinhas;

				for (int a = 0; a < linhasRestante; a++) {
					for (int b = 0; b < largura; b++) {
						buf.append(resto);
					}
					vext[totLinhas] = buf.toString();
					buf = new StringBuilder();
					totLinhas++;
				}
			}
		}
		return vext;
	}

	private static boolean ehPrimeiroGrupoUm() {
		if (((Integer) nro.get(nro.size() - 1)).intValue() == 1) {
			return true;
		}
		return false;
	}

	/**
	 * Adds a feature to the Remainder attribute of the Extenso object
	 *
	 * @param divisor The feature to be added to the Remainder attribute
	 */
	private static void addRemainder(int divisor) {
		// Encontra newNum[0] = num modulo divisor, newNum[1] = num dividido divisor
		BigInteger[] newNum = num.divideAndRemainder(BigInteger.valueOf(divisor));

		// Adiciona modulo
		nro.add(new Integer(newNum[1].intValue()));

		// Altera numero
		num = newNum[0];
	}

	/**
	 * Descri��o do M�todo
	 *
	 * @param ps Descri��o do Par�metro
	 * @return Descri��o do Valor Retornado
	 */
	private static boolean temMaisGrupos(int ps) {
		for (; ps > 0; ps--) {
			if (((Integer) nro.get(ps)).intValue() != 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Descri��o do M�todo
	 *
	 * @param ps Descri��o do Par�metro
	 * @return Descri��o do Valor Retornado
	 */
	private static boolean ehUltimoGrupo(int ps) {
		return (ps > 0) && ((Integer) nro.get(ps)).intValue() != 0 && !temMaisGrupos(ps - 1);
	}

	/**
	 * Descri��o do M�todo
	 *
	 * @return Descri��o do Valor Retornado
	 */
	private static boolean ehUnicoGrupo() {
		if (nro.size() <= 3) {
			return false;
		}
		if (!ehGrupoZero(1) && !ehGrupoZero(2)) {
			return false;
		}
		boolean hasOne = false;
		for (int i = 3; i < nro.size(); i++) {
			if (((Integer) nro.get(i)).intValue() != 0) {
				if (hasOne) {
					return false;
				}
				hasOne = true;
			}
		}
		return true;
	}

	private static boolean ehGrupoZero(int ps) {
		if (ps <= 0 || ps >= nro.size()) {
			return true;
		}
		return ((Integer) nro.get(ps)).intValue() == 0;
	}

	/**
	 * Descri��o do M�todo
	 *
	 * @param numero Descri��o do Par�metro
	 * @param escala Descri��o do Par�metro
	 * @return Descri��o do Valor Retornado
	 */
	private static String numToString(int numero, int escala) {
		int unidade = (numero % 10);
		int dezena = (numero % 100); // * nao pode dividir por 10 pois verifica de 0..19
		int centena = (numero / 100);
		StringBuilder buf = new StringBuilder();

		if (numero != 0) {
			if (centena != 0) {
				if (dezena == 0 && centena == 1) {
					buf.append(numeros[2][0]);
				} else {
					buf.append(numeros[2][centena]);
				}
			}

			if ((buf.length() > 0) && (dezena != 0)) {
				buf.append(" e ");
			}
			if (dezena > 19) {
				dezena /= 10;
				buf.append(numeros[1][dezena - 2]);
				if (unidade != 0) {
					buf.append(" e ");
					buf.append(numeros[0][unidade]);
				}
			} else if (centena == 0 || dezena != 0) {
				buf.append(numeros[0][dezena]);
			}

			buf.append(" ");
			if (numero == 1) {
				buf.append(qualificadores[escala][0]);
			} else {
				buf.append(qualificadores[escala][1]);
			}
		}

		return buf.toString();
	}
}
