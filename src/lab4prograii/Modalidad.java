/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4prograii;

/**
 *
 * @author gigiw
 */
public enum Modalidad {
    PALABRA_FIJA("Palabra fija"),
    AL_AZAR("Palabra al azar");

    private final String textoBoton;

    Modalidad(String textoBoton) {
        this.textoBoton = textoBoton;
    }

    public String getTextoBoton() {
        return textoBoton;
    }
}
