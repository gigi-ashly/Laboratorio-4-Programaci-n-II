package lab4prograii;

public enum EfectoSonido{
    ACIERTO(new int[]{880}, new int[]{120}),
    ERROR(new int[]{220}, new int[]{250}),
    ALERTA(new int[]{440, 0, 440}, new int[]{80, 60, 80}),
    VICTORIA(new int[]{523, 659, 784}, new int[]{150, 150, 300}),
    DERROTA(new int[]{392, 330, 262}, new int[]{200, 200, 400});

    private int[] frecuencias;
    private int[] duraciones;

    EfectoSonido(int[] frecuencias, int[] duraciones){
        this.frecuencias = frecuencias;
        this.duraciones = duraciones;
    }

    public int[] getFrecuencias(){
        return frecuencias;
    }

    public int[] getDuraciones(){
        return duraciones;
    }
}
