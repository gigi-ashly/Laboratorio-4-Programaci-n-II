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
public class JuegoAlAzar extends JuegoBase {

    private static final long serialVersionUID = 1L;
    private AdministradorPalabras administrador;

    public JuegoAlAzar(AdministradorPalabras administrador) throws PalabraInvalidaException {
        super();
        if (administrador == null) {
            throw new IllegalArgumentException("El administrador de palabras no puede ser nulo.");
        }

        this.administrador = administrador;
        reiniciar();
    }

    @Override
    protected String elegirPalabra() throws PalabraInvalidaException {
        return administrador.obtenerAleatoria();
    }

    @Override
    public Modalidad getModalidad() {
        return Modalidad.AL_AZAR;
    }
}

