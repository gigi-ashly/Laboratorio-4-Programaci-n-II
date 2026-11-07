package lab4prograii;

public class PalabraInvalidaException extends AhorcadoException{
    private static final long serialVersionUID = 1L;

    public PalabraInvalidaException(String mensaje){
        super(mensaje);
    }
}