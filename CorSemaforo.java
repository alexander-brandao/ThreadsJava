package semaforo;

public enum CorSemaforo {
   //O sinal permanece verde por 3 segundos, o amarelo 1 segundo e o vermelho 2 segundos
    VERDE(3000), AMARELO(1000), VERMELHO(2000);

    private int tempoEspera;

    CorSemaforo(int tempoEspera){
        this.tempoEspera = tempoEspera;
    }

    public int getTempoEspera(){
        return tempoEspera;
    }
}
