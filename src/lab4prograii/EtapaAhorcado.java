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
public enum EtapaAhorcado {
  
    VACIO, CABEZA, TRONCO, BRAZO_IZQ, BRAZO_DER, PIERNA_IZQ, PIERNA_DER;

    public static EtapaAhorcado dePorErrores(int errores) {
        if (errores < 0 || errores > 6) {
            throw new IllegalArgumentException("La cantidad de errores debe estar entre 0 y 6.");
        }
        return values()[errores];
    }  
}
