package com.minecreatr.fractal.display;

import com.minecreatr.fractal.Bootstrap;
import com.minecreatr.fractal.math.Complex;
import com.minecreatr.fractal.logic.Fractal;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ControlPanel extends JPanel implements ActionListener {

    private JButton saveButton;
    private JButton randomButton;
    private JButton lastButton;

    public JLabel dataReal;

    public JLabel dataImag;

    private JButton copy;

    private JButton toggleBW;

    private Map<String, Function<ActionEvent, String>> actions;

    private Map<String, JButton> buttonMap;

    public ControlPanel(){
    }

    public void init(){
        this.actions = new HashMap<>();
        this.buttonMap = new HashMap<>();

        this.saveButton = new JButton("Save");
        this.saveButton.setActionCommand("save_image");
        addButton(this.saveButton);
        addAction(this.saveButton, (event) -> {
            String name = JOptionPane.showInputDialog("Please name the fractal.");

            if (name == null || name.equalsIgnoreCase("null")){
                return "";
            }

            System.out.println("Saving Fractal...");
            try {
                BufferedImage image = Bootstrap.mainInstance.getFractalManager().getCurrentFractal().getImage();
                ImageIO.write(image,"png", new File(name + ".png"));
                JOptionPane.showMessageDialog(this, "Fractal saved to file " + name + ".png");
                System.out.println("Fractal Saved!");
            } catch (IOException exception){
                exception.printStackTrace();
                JOptionPane.showMessageDialog(this, "Could not save fractal to file " + name + ".png");
                System.err.println("Saving failed!");
            }
            return "";
        });

        this.randomButton = new JButton("Random");
        this.randomButton.setActionCommand("next_fractal");
        addButton(this.randomButton);
        addAction(this.randomButton, (event) -> {
            Bootstrap.mainInstance.getFractalManager().next();
            Bootstrap.mainInstance.getDisplayManager().updateDisplay();
            updateText();
            this.lastButton.setEnabled(true);
            if (Bootstrap.mainInstance.getFractalManager().hasNext()){
                return "Next";
            }
            else {
                return "Random";
            }
        });

        this.lastButton = new JButton("Previous");
        this.lastButton.setActionCommand("previous_fractal");
        addButton(this.lastButton);
        addAction(this.lastButton, (event) -> {
            if (Bootstrap.mainInstance.getFractalManager().hasPrevious()) {
                Bootstrap.mainInstance.getFractalManager().previous();
                updateText();
                this.randomButton.setText("Next");
                Bootstrap.mainInstance.getDisplayManager().updateDisplay();
                if (!Bootstrap.mainInstance.getFractalManager().hasPrevious()){
                    this.lastButton.setEnabled(false);
                }
            }
            return "";
        });
        this.lastButton.setEnabled(false);

        this.dataReal = new JLabel("Real: 0");
        this.dataImag = new JLabel("Imaginary: 0i");
        this.add(this.dataReal);
        this.add(this.dataImag);

        this.copy = new JButton("Copy Complex constants to keyboard.");
        this.copy.setActionCommand("copy_constants");
        addButton(this.copy);
        addAction(this.copy, (event) -> {
            System.out.println("Copying numbers to clipboard...");
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(Bootstrap.mainInstance.getFractalManager().getCurrentFractal().getConstants().toString());
            clip.setContents(selection, null);
            return "";
        });

        this.toggleBW = new JButton("Enable Black & White");
        this.toggleBW.setActionCommand("toggle_bw");
        addButton(this.toggleBW);
        addAction(this.toggleBW, (event) -> {
            boolean enableText = false;
            if (Bootstrap.mainInstance.getFractalGenerator().isRenderType("blackAndWhite")){
                Bootstrap.mainInstance.getFractalGenerator().setRenderType("classic");
                enableText = true;
            }
            else {
                Bootstrap.mainInstance.getFractalGenerator().setRenderType("blackAndWhite");
            }
            Bootstrap.mainInstance.getFractalManager().actOnAll((fractal) -> {
                fractal.setDirty(true);
            });
            Bootstrap.mainInstance.getDisplayManager().updateDisplay();
            if (enableText){
                return "Enable Black & White";
            }
            else {
                return "Disable Black & White";
            }
        });

    }

    public void updateText(){
        Complex comp = Bootstrap.mainInstance.getFractalManager().getCurrentFractal().getConstants();
        this.dataReal.setText("Real: " + comp.getReal());
        this.dataImag.setText("Imaginary: " + comp.getImaginary() + "i");
    }


    private static Dimension dim(int width, int height){
        return new Dimension(width, height);
    }

    private void addButton(AbstractButton button){
        button.addActionListener(this);
        this.add(button);
    }

    private void addAction(JButton button, Function<ActionEvent, String> action){
        this.actions.put(button.getActionCommand(), action);
        this.buttonMap.put(button.getActionCommand(), button);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String newVal = this.actions.getOrDefault(e.getActionCommand(), (event) -> {return "";}).apply(e);
        if ((newVal != null && !newVal.equals("")) && this.buttonMap.containsKey(e.getActionCommand())){
            this.buttonMap.get(e.getActionCommand()).setText(newVal);
        }
    }
}
