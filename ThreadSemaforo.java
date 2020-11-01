package semaforo;

public class ThreadSemaforo implements Runnable {

    // atributo tipo CorSemaforo
    private CorSemaforo cor;

    // atributo de parada;
    private boolean parar;

    // atributo que auxilia na mudanca de cor
    private boolean corMudou;

    // construtor para a thread
    public ThreadSemaforo(){

        this.cor = CorSemaforo.VERMELHO;
        this.corMudou = false;
        this.parar = false;

        //inicia a thread
        new Thread(this).start();
    }

    @Override
    public void run() {
        //associando o tempo do semarofo
        while(!parar){
            try{
                /* switch(this.cor) {
                    case VERMELHO:
                    Thread.sleep(2000);
                        break;
                    case AMARELO:
                        Thread.sleep(300);
                        break;
                    case VERDE:
                        Thread.sleep(3000);
                    break;
                default:
                    break;
                } */

                Thread.sleep(this.cor.getTempoEspera());
                this.mudarCor();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    // metodo para mudar a cor
    private synchronized void mudarCor(){
        switch (this.cor) {
            case VERMELHO:
                this.cor = CorSemaforo.VERDE;
                break;
            case AMARELO:
                this.cor = CorSemaforo.VERMELHO;
                break;
            case VERDE:
                this.cor = CorSemaforo.AMARELO;
                break;
        }
        this.corMudou = true;
        notify();
    }

    public synchronized void esperaCorMudar(){
        while(!this.corMudou){
            try{
                wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        this.corMudou = false;
    }

    public synchronized void desligarSemaforo(){
        this.parar = true;
    }

    public CorSemaforo getCor() {
        return cor;
    }

}
