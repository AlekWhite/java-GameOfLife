
package mainpac;
import java.util.concurrent.atomic.AtomicBoolean;

public class simLoop implements Runnable{

    public static int delay = 150;
    private static AtomicBoolean isRunning;

    // constructor
    public simLoop(){
        isRunning = new AtomicBoolean(true);
    }

    // stops the loop
    public void stop(){
        isRunning.set(false);
    }

    // the loop
    public void run(){
        while (isRunning.get()){
            Main.cycle();
            Main.halt(delay);
        }
    }
}
