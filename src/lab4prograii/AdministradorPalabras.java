package lab4prograii;

import java.io.Serializable;
import java.util.ArrayList;

public class AdministradorPalabras implements Serializable{
    private static final long serialVersionUID = 1L;

    private ArrayList<String> palabras = new ArrayList<String>();

    public AdministradorPalabras(){
        palabras.add("COMPUTADORA");
        palabras.add("OBJETO");
        palabras.add("HERENCIA");
        palabras.add("UNIVERSIDAD");
        palabras.add("ARBOL");
        palabras.add("CARRO");
        palabras.add("PIÑATA");
        palabras.add("TECLADO");
        palabras.add("PANTALLA");
        palabras.add("HONDURAS");
    }

    public void agregar(String palabra) throws PalabraInvalidaException, PalabraDuplicadaException{
        if(palabra==null){
            throw new PalabraInvalidaException("La palabra debe tener entre 3 y 15 letras, sin espacios ni numeros.");
        }
        String limpia = UtilTexto.normalizar(palabra.trim());
        if(!esPalabraValida(limpia)){
            throw new PalabraInvalidaException("La palabra debe tener entre 3 y 15 letras, sin espacios ni numeros.");
        }
        if(existe(limpia, 0)){
            throw new PalabraDuplicadaException("La palabra "+limpia+" ya existe en la lista.");
        }
        palabras.add(limpia);
    }

    private boolean esPalabraValida(String palabra){
        if(palabra.length()<3 || palabra.length()>15){
            return false;
        }
        for(int i=0;i<palabra.length();i++){
            if(!UtilTexto.esLetra(palabra.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public String obtenerAleatoria(){
        int posicion = (int)(Math.random()*palabras.size());
        return palabras.get(posicion);
    }

    private boolean existe(String palabra, int indice){
        if(indice>=palabras.size()){
            return false;
        }
        if(palabras.get(indice).equals(palabra)){
            return true;
        }
        return existe(palabra, indice+1);
    }

    public int cantidad(){
        return palabras.size();
    }

    public ArrayList<String> getPalabras(){
        return new ArrayList<String>(palabras);
    }
}