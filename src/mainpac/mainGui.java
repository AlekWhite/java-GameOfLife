package mainpac;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainGui extends JFrame  {

    private simLoop loop;

    // sets up main frame
    public mainGui(){

        // frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("GOL");
        this.setSize(1000, 740);
        ImageIcon image= new ImageIcon("C:\\Users\\alekw\\IdeaProjects\\javaPlayground\\src\\ico.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(44, 43, 43));
        this.setVisible(true);

        // settings label
        JLabel label = new JLabel("Settings:");
        label.setForeground(new Color(0x9B9A9A));
        label.setFont(new Font("Consolas", Font.PLAIN, 26));
        label.setBounds(794, 20, 150, 40);
        this.add(label);

        // starting
        addText("// starts the simulation", 800, 100 );
        JButton startButton = addButton("Start", 800, 120);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loop == null) {
                    loop = new simLoop();
                    Thread t = new Thread(loop);
                    t.start();
            }}});

        // stopping
        addText("// stops the simulation", 800, 160 );
        JButton stopButton = addButton("Stop", 800, 180);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loop.stop();
                loop = null;
            }});

        // cycle
        addText("// runs one iteration", 800, 220 );
        JButton cycleButton = addButton("Cycle", 800, 240);
        cycleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.cycle();
            }});

        // random
        addText("// randomizes the grid", 800, 280 );
        JButton randButton = addButton("Random", 800, 300);
        randButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.randomize();
            }});

        // clear
        addText("// kills all living cells", 800, 340 );
        JButton killButton = addButton("Clear", 800, 360);
        killButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i=0; i<Main.GRID_SIZE; i++){
                    Main.cells[i].changeStateTo(false);
                }}});

        // speed
        addText("// time between iterations", 800, 400);
        JSlider speed = new JSlider(JSlider.HORIZONTAL, 50, 250, 100);
        speed.setBounds(800, 420, 150, 30);
        speed.setBackground(new Color(0x9B9A9A));
        speed.setValue(150);
        speed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                simLoop.delay = ((JSlider)e.getSource()).getValue();
            }});
        this.add(speed);

        // other text
        addText("/*", 800, 470);
        addText("OTHER: to change the", 800, 485);
        addText("state of a cell, right", 800, 500);
        addText("or left click on it", 800, 515);
        addText("*/", 800, 530);

        // sidebar
        JPanel settingsPanel = new JPanel();
        settingsPanel.setBounds(720, 0, 60, 740);
        settingsPanel.setBackground(new Color(0x151517));
        this.add(settingsPanel);

    }

    // general form for adding text
    private void addText(String text, int xPos, int yPos){
        // settings label
        JLabel label = new JLabel(text);
        label.setForeground(new Color(0x525252));
        label.setFont(new Font("Consolas", Font.PLAIN, 12));
        label.setBounds(xPos, yPos, text.length()*12+10, 20);
        this.add(label);
    }

    // general form for adding buttons
    private JButton addButton(String text, int xPos, int yPos){
        JButton button = new JButton(text);
        button.setBounds(xPos, yPos, 85, 20);
        button.setBorderPainted(false);
        button.setBackground(new Color(0x9B9A9A));
        button.setFocusPainted(false);
        this.add(button);
        return button;
    }
}
