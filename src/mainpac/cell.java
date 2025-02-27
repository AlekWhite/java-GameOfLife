package mainpac;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class cell implements MouseListener {

    boolean state = false;
    JPanel panel = new JPanel();

    // constructor
    public cell(int xPos, int yPos){
        // makes a new panel
        panel.setBackground(Main.OFF_COLOR);
        panel.setBounds(xPos, yPos, 700/Main.GRID_DIMENSIONS, 700/Main.GRID_DIMENSIONS);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.addMouseListener(this);
        Main.frame.add(panel);
        this.changeStateTo(false);
    }

    // finds new state, given neighbors state
    public boolean evaluate(){

        // gets number of positive neighbors
        int neighbors = 0;
        int thisIndex = Arrays.asList(Main.cells).indexOf(this);
        int[] thisIndexPos = {thisIndex%Main.GRID_DIMENSIONS, thisIndex/Main.GRID_DIMENSIONS};

        // repeat for every neighbor, gets neighbor index
        for (int i=0; i<3; i++){
            int[] nextIndexPos = {0, thisIndexPos[1]-1+i};
            for (int j=0; j<3; j++) {
                nextIndexPos[0] = thisIndexPos[0] - 1 + j;

                // record if the neighbor is positive
                if (((nextIndexPos[0] != -1) && (nextIndexPos[0] < Main.GRID_DIMENSIONS)) && ((nextIndexPos[1] != -1) && (nextIndexPos[1] < Main.GRID_DIMENSIONS))){
                    int nextIndex = (nextIndexPos[1]*Main.GRID_DIMENSIONS)+nextIndexPos[0];
                    if ((Main.cells[nextIndex].state) && (nextIndex != thisIndex)){
                        neighbors ++;
                    }
                }
            }
        }

        // apply rules of conways game of life
        return (((neighbors >= 2) && (neighbors <= 3)) || (!state)) && ((neighbors == 3) || (state));
    }

    // changes state var, and updates color
    public void changeStateTo(boolean newState) {
        state = newState;
        if (state) {panel.setBackground(Main.ON_COLOR);}
        else {panel.setBackground(Main.OFF_COLOR);}
    }


    // inverts the state when pressed
    @Override
    public void mouseClicked(MouseEvent e) {
        changeStateTo(!state);}

    // unused overrides
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
