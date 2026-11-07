package lab4prograii;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class GestorSonido{
    public static final int muestrasPorSegundo = 44100;

    private AudioFormat formato = new AudioFormat(muestrasPorSegundo, 8, 1, true, false);
    private boolean silenciado = false;
    private boolean disponible = true;

    public GestorSonido(){
        try{
            AudioSystem.getSourceDataLine(formato);
        }catch(LineUnavailableException e){
            disponible = false;
        }catch(IllegalArgumentException e){
            disponible = false;
        }
    }

    public void reproducir(EfectoSonido efecto){
        if(silenciado || !disponible){
            return;
        }
        Reproductor hilo = new Reproductor(efecto);
        hilo.start();
    }

    public void setSilenciado(boolean silenciado){
        this.silenciado = silenciado;
    }

    public boolean isSilenciado(){
        return silenciado;
    }

    public boolean isDisponible(){
        return disponible;
    }

    private byte[] crearNota(int frecuencia, int duracion){
        int muestras = muestrasPorSegundo*duracion/1000;
        byte[] nota = new byte[muestras];
        if(frecuencia>0){
            for(int m=0;m<muestras;m++){
                double angulo = 2*Math.PI*frecuencia*m/muestrasPorSegundo;
                nota[m] = (byte)(Math.sin(angulo)*60); 
            }
        }
        return nota;
    }

    private class Reproductor extends Thread{
        private EfectoSonido efecto;

        public Reproductor(EfectoSonido efecto){
            this.efecto = efecto;
        }

        @Override
        public void run(){
            int[] frecuencias = efecto.getFrecuencias();
            int[] duraciones = efecto.getDuraciones();
            try{
                SourceDataLine linea = AudioSystem.getSourceDataLine(formato);
                linea.open(formato);
                linea.start();
                for(int i=0;i<frecuencias.length;i++){
                    byte[] nota = crearNota(frecuencias[i], duraciones[i]);
                    linea.write(nota, 0, nota.length);
                }
                linea.drain();
                linea.close();
            }catch(LineUnavailableException e){
                disponible = false; 
            }
        }
    }
}
