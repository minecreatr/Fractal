package com.minecreatr.fractal.display;

import javax.swing.*;
import java.awt.*;

/**
 * Class for managing visual components of the program
 */
public class DisplayManager {

    private int width;

    private int height;

    private JFrame frame;

    private JLayeredPane layeredPane;

    private ControlPanel controlPanel;

    private FractalPanel fractalPanel;

    public DisplayManager(int width, int height, String name){
        this.width = width;
        this.height = height;
        this.frame = new JFrame(name);
    }

    public void setIcon(Image icon){
        this.frame.setIconImage(icon);
    }

    public void initDisplay(){
        this.layeredPane = new JLayeredPane();
        this.frame.add(this.layeredPane);
        this.layeredPane.setBounds(0, 0, width, height);

        this.controlPanel = new ControlPanel();
        this.controlPanel.setSize(width/5, height/5);
        this.layeredPane.add(this.controlPanel, 0);
        this.controlPanel.init();

        this.fractalPanel = new FractalPanel();
        this.fractalPanel.setSize(width, height);
        this.layeredPane.add(this.fractalPanel, 1);

        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setSize(width, height);
        this.frame.setVisible(true);

        this.controlPanel.updateText();

    }

    public void updateDisplay(){
        this.fractalPanel.repaint();
        this.layeredPane.moveToFront(this.controlPanel);
        this.controlPanel.repaint();
    }
}
