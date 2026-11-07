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
public class JuegoPalabraFija extends JuegoBase {

    private static final long serialVersionUID = 1L;

    private String palabraConfigurada;

    public JuegoPalabraFija(String palabra) throws PalabraInvalidaException {
        super();
        palabraConfigurada = palabra;
        reiniciar();
    }

    public void cambiarPalabra(String nuevaPalabra) {
        palabraConfigurada = nuevaPalabra;
    }

    @Override
    protected String elegirPalabra() throws PalabraInvalidaException {
        return palabraConfigurada;
    }

    @Override
    public Modalidad getModalidad() {
        return Modalidad.PALABRA_FIJA;
    }
}
