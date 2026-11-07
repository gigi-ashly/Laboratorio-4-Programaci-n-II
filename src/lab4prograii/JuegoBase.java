
package lab4prograii;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author gigiw
 */
public abstract class JuegoBase implements JuegoAhorcado, Serializable {

    private static final long serialVersionUID = 1L;

    protected String palabraSecreta;
    protected char[] palabraMostrada;

    protected int intentosRestantes;

    protected static final int MAX_INTENTOS = 6;

    protected List<Character> letrasAcertadas;
    protected List<Character> letrasErradas;

    protected EtapaAhorcado etapa;
    protected EstadoPartida estado;

    public JuegoBase() {
        letrasAcertadas = new ArrayList<>();
        letrasErradas = new ArrayList<>();
        intentosRestantes = MAX_INTENTOS;
        etapa = EtapaAhorcado.VACIO;
        estado = EstadoPartida.EN_CURSO;
    }

    @Override
    public void establecerPalabraSecreta(String palabra) throws PalabraInvalidaException {

        String palabraNormalizada = UtilTexto.normalizar(palabra);

        if (palabraNormalizada.length() < 3 || palabraNormalizada.length() > 15) {
            throw new PalabraInvalidaException("La palabra debe tener entre 3 y 15 letras.");
        }

        for (int i = 0; i < palabraNormalizada.length(); i++) {
            if (!UtilTexto.esLetra(palabraNormalizada.charAt(i))) {
                throw new PalabraInvalidaException("La palabra no puede contener espacios, números o símbolos.");
            }
        }

        palabraSecreta = palabraNormalizada;
        palabraMostrada = new char[palabraSecreta.length()];
        for (int i = 0; i < palabraMostrada.length; i++) {
            palabraMostrada[i] = '_';
        }

        letrasAcertadas.clear();
        letrasErradas.clear();

        intentosRestantes = MAX_INTENTOS;
        etapa = EtapaAhorcado.VACIO;
        estado = EstadoPartida.EN_CURSO;
    }

    @Override
    public ResultadoLetra ingresarLetra(String entrada) throws LetraInvalidaException, LetraRepetidaException, PartidaTerminadaException {
        if (estado != EstadoPartida.EN_CURSO) {
            throw new PartidaTerminadaException("La partida ya terminó.");
        }
        if (entrada == null || entrada.length() != 1) {
            throw new LetraInvalidaException("Ingrese una única letra (A-Z o Ñ).");
        }

        String normalizada = UtilTexto.normalizar(entrada);
        char letra = normalizada.charAt(0);

        if (!UtilTexto.esLetra(letra)) {
            throw new LetraInvalidaException("Ingrese una única letra (A-Z o Ñ).");
        }

        if (letrasAcertadas.contains(letra)|| letrasErradas.contains(letra)) {
            throw new LetraRepetidaException("La letra " + letra + " ya fue ingresada.");
        }

        ResultadoLetra resultado;

        if (palabraSecreta.indexOf(letra) >= 0) {
            letrasAcertadas.add(letra);
            revelarLetra(letra, 0);
            resultado = ResultadoLetra.ACIERTO;
        } else {
            letrasErradas.add(letra);
            intentosRestantes--;
            etapa = EtapaAhorcado.dePorErrores(MAX_INTENTOS - intentosRestantes);
            resultado = ResultadoLetra.ERROR;
        }

        if (haGanado(0)) {
            estado = EstadoPartida.GANADA;
        } else if (haPerdido()) {
            estado = EstadoPartida.PERDIDA;
        }
        return resultado;
    }

    @Override
    public void reiniciar() throws PalabraInvalidaException {
        establecerPalabraSecreta(elegirPalabra());
    }

    protected void revelarLetra(char letra, int posicion) {
        if (posicion >= palabraSecreta.length()) {
            return;
        }

        if (palabraSecreta.charAt(posicion) == letra) {
            palabraMostrada[posicion] = letra;
        }

        revelarLetra(letra, posicion + 1);
    }

    protected boolean haGanado(int posicion) {
        if (posicion >= palabraSecreta.length()) {
            return true;
        }

        if (palabraMostrada[posicion] != palabraSecreta.charAt(posicion)) {
            return false;
        }

        return haGanado(posicion + 1);
    }

    public boolean haPerdido() {
        return intentosRestantes == 0;
    }

    protected abstract String elegirPalabra()
            throws PalabraInvalidaException;

    @Override
    public abstract Modalidad getModalidad();

    @Override
    public String getPalabraMostrada() {
        return palabraMostrada == null ? "" : new String(palabraMostrada);
    }

    @Override
    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    @Override
    public List<Character> getLetrasAcertadas() {
        return Collections.unmodifiableList(
                new ArrayList<>(letrasAcertadas)
        );
    }

    @Override
    public List<Character> getLetrasErradas() {
        return Collections.unmodifiableList(new ArrayList<>(letrasErradas));
    }

    @Override
    public EtapaAhorcado getEtapa() {
        return etapa;
    }

    @Override
    public EstadoPartida getEstado() {
        return estado;
    }

    @Override
    public String getPalabraSecreta() {
        return palabraSecreta;
    }
}

