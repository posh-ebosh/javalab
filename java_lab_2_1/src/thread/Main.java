package thread;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        HenThread henThread = new HenThread();
        henThread.start();

        henThread.join();

        EggThread eggThread = new EggThread();
        eggThread.start();

        eggThread.join();

        TirexThread tirexThread = new TirexThread();
        Thread tirex = new Thread();
        tirex.run();

        for(int i = 0; i <100; i++){
            System.out.println("human");

        }
    }
}
