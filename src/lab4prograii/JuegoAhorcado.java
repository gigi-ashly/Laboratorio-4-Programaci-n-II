/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4prograii;
import java.util.List;

/**
 *
 * @author gigiw
 */
public interface JuegoAhorcado {

    void establecerPalabraSecreta(String palabra)
            throws PalabraInvalidaException;

    ResultadoLetra ingresarLetra(String entrada)
            throws LetraInvalidaException, LetraRepetidaException, PartidaTerminadaException;

    void reiniciar() throws PalabraInvalidaException;

    String getPalabraMostrada();
    int getIntentosRestantes();
    List<Character> getLetrasAcertadas();
    List<Character> getLetrasErradas();
    EtapaAhorcado getEtapa();
    EstadoPartida getEstado();
    Modalidad getModalidad();
    String getPalabraSecreta();
}

