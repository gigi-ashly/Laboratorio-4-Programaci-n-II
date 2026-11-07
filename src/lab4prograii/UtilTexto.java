/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4prograii;
import java.text.Normalizer;
import java.util.Locale;
/**
 *
 * @author gigiw
 */
public final class UtilTexto {

    private UtilTexto() {
    }

    public static String normalizar(String texto) {

        if (texto == null) {
            return "";
        }

        texto = texto.toUpperCase(Locale.ROOT);
        texto = texto.replace("Ñ", "\u0001");
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("\\p{M}", "");
        texto = texto.replace("\u0001", "Ñ");
        return texto;
    }

    public static boolean esLetra(char letra) {
        return (letra >= 'A' && letra <= 'Z') || letra == 'Ñ';
    }
}

