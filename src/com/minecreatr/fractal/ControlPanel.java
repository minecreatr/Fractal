package com.minecreatr.fractal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel implements ActionListener {

    private JButton saveButton;
    private JButton randomButton;
    private JButton lastButton;

    public JLabel dataReal;

    public JLabel dataImag;

    private JButton copy;

    public ControlPanel(){

        this.saveButton = new JButton("Save");
        addButton(this.saveButton);
        
        this.randomButton = new JButton("Random");
        addButton(this.randomButton);
        
        this.lastButton = new JButton("Previous");
        addButton(this.lastButton);

        this.dataReal = new JLabel("Real: 0");
        this.dataImag = new JLabel("Imaginary: 0i");
        this.add(this.dataReal);
        this.add(this.dataImag);

        this.copy = new JButton("Copy complex constant to keyboard.");
        addButton(this.copy);

        dataReal.setText("Real: " + Main.currentFractal.getReal());
        dataImag.setText("Imaginary: " + Main.currentFractal.getImaginary() + "i");
    }

    private static Dimension dim(int width, int height){
        return new Dimension(width, height);
    }

    private void addButton(AbstractButton button){
        button.addActionListener(this);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equalsIgnoreCase("Save")){
            String name = JOptionPane.showInputDialog("Please name the fractal.");

            if (name == null || name.equalsIgnoreCase("null")){
                return;
            }

            System.out.println("Saving Fractal...");
            try {
                BufferedImage image = Fractal.generateFractal();
                ImageIO.write(image,"png", new File(name + ".png"));
                JOptionPane.showMessageDialog(this, "Fractal saved to file " + name + ".png");
                System.out.println("Fractal Saved!");
            } catch (IOException exception){
                exception.printStackTrace();
                JOptionPane.showMessageDialog(this, "Could not save fractal to file " + name + ".png");
                System.err.println("Saving failed!");
            }
        }
        else if (e.getActionCommand().equalsIgnoreCase("Random")){
            System.out.println("Creating Random Fractal...");
            Main.previousFractals.add(Main.currentFractal);
            Main.currentFractal = Fractal.randomComplex();
            Fractal.updateDisplay();
        }
        else if (e.getActionCommand().equalsIgnoreCase("Previous")){
            if (Main.previousFractals.size() > 0){
                Complex cur = Main.previousFractals.peekLast();
                if (cur != null){
                    System.out.println("Viewing previous fractal...");
                    Main.nextFractals.add(Main.currentFractal);
                    Main.currentFractal = cur;
                    Main.previousFractals.remove(cur);
                    this.randomButton.setText("Next");
                    Fractal.updateDisplay();
                }
            }
        }
        else if (e.getActionCommand().equalsIgnoreCase("Next")){
            Main.previousFractals.add(Main.currentFractal);
            if (Main.nextFractals.size() > 0){
                Complex cur = Main.nextFractals.peekLast();
                if (cur != null){
                    System.out.println("Viewing Next Fractal...");
                    Main.currentFractal = cur;
                    Main.nextFractals.remove(cur);
                    if (Main.nextFractals.size() == 0){
                        this.randomButton.setText("Random");
                    }
                    Fractal.updateDisplay();
                }
            }
        }
        else if (e.getActionCommand().equals(this.copy.getText())){
            System.out.println("Copying numbers to clipboard...");
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(Main.currentFractal.toString());
            clip.setContents(selection, null);
        }
    }
}
