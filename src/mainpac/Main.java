package mainpac;
import java.awt.*;
import java.util.Random;

public class Main {

    // GUI constants
    public static mainGui frame = new mainGui();
    public static Color ON_COLOR = new Color(0x9C9CA6);
    public static Color OFF_COLOR = new Color(0x151521);
    // other constants
    public static final int GRID_SIZE = 2500; // total num of cells
    public static final int GRID_DIMENSIONS = (int) Math.sqrt(GRID_SIZE);

    /*
    Note, when an array is set equal to another array, the new array
    will point to the old one, rather than create a copy in memory
     */
    // defines main array
    public static cell[] cells = new cell[GRID_SIZE];

    // runs one simulation of the game
    public static void cycle() {

        // gets new state for each cell
        boolean[] newStates = new boolean[GRID_SIZE];
        for (int i=0; i<GRID_SIZE; i++) {
            newStates[i] = cells[i].evaluate();
        }

        // applies new state
        for (int i=0; i<GRID_SIZE; i++) {
            cells[i].changeStateTo(newStates[i]);
        }
    }

    // stop the program for a time
    public static void halt(int time) {
        try {Thread.sleep(time);} catch(InterruptedException ex) {Thread.currentThread().interrupt();}
    }

    // randomly reassigns each cell a new state
    public static void randomize(){
        Random rn = new Random();
        boolean alive;
        for (int i=0; i<GRID_SIZE; i++){
            int rand = rn.nextInt(5);
            alive = rand == 0;
            cells[i].changeStateTo(alive);
        }
    }

    // Main
    public static void main(String[] args) {

        int[] arr = {1, 2, 3 ,4};
        System.out.println((double)(arr[arr.length/2] + arr[(arr.length/2)+1])/2);

        // populates cells array with cell objects
        for (int i=0; i<GRID_SIZE; i++){
            cells[i] = new cell((i%GRID_DIMENSIONS)*(700/GRID_DIMENSIONS), (i/GRID_DIMENSIONS)*(700/GRID_DIMENSIONS));
        }
        frame.repaint();

    }
}
