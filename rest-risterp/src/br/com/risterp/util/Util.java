package br.com.risterp.util;

public class Util {
	
	public static final String STR_SEPARATOR = "##RIST_SEPARATOR##";
	
	public static String valorExtenso(double nValor)
    {
        return Extenso.format(nValor, "", "", 2, 0, "", 3)[1];
    }
}
