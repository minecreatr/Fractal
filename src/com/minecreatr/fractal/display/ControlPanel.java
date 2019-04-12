package com.minecreatr.fractal.display;

import com.minecreatr.fractal.Bootstrap;
import com.minecreatr.fractal.FractalManager;
import com.minecreatr.fractal.math.Complex;
import com.minecreatr.fractal.logic.Fractal;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ControlPanel extends JPanel {

    private JButton saveButton;
    private JButton randomButton;
    private JButton lastButton;

    public JLabel dataReal;

    public JLabel dataImag;

    private JButton copy;

    private JButton paste;

    private JButton toggleBW;

    private JComboBox<String> fractalTypes;

    //private Map<String, Function<ActionEvent, String>> actions;

    //private Map<String, JComponent> componentMap;

    public ControlPanel(){
    }

    public void init(){
        //this.actions = new HashMap<>();
        //this.componentMap = new HashMap<>();

        this.saveButton = new JButton("Save");
        this.saveButton.addActionListener((event) -> {
            String name = JOptionPane.showInputDialog("Please name the fractal.");

            if (name == null || name.equalsIgnoreCase("null")){
                return;
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
        });
        this.add(this.saveButton);

        this.randomButton = new JButton("New Random");
        this.randomButton.addActionListener((event) -> {
            Bootstrap.mainInstance.getFractalManager().next();
            Bootstrap.mainInstance.getDisplayManager().updateDisplay();
            updateText();
            this.lastButton.setEnabled(true);
            if (Bootstrap.mainInstance.getFractalManager().hasNext()){
                this.randomButton.setText("Next");
            }
            else {
                this.randomButton.setText("New Random");
            }
        });
        this.add(this.randomButton);

        this.lastButton = new JButton("Previous");
        this.lastButton.addActionListener((event) -> {
            if (Bootstrap.mainInstance.getFractalManager().hasPrevious()) {
                Bootstrap.mainInstance.getFractalManager().previous();
                updateText();
                this.randomButton.setText("Next");
                Bootstrap.mainInstance.getDisplayManager().updateDisplay();
                if (!Bootstrap.mainInstance.getFractalManager().hasPrevious()){
                    this.lastButton.setEnabled(false);
                }
            }
        });
        this.lastButton.setEnabled(false);
        this.add(this.lastButton);

        this.dataReal = new JLabel("Real: 0");
        this.dataImag = new JLabel("Imaginary: 0i");
        this.add(this.dataReal);
        this.add(this.dataImag);

        this.copy = new JButton("Copy Complex constants to keyboard.");
        this.copy.addActionListener((event) -> {
            System.out.println("Copying numbers to clipboard...");
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(Bootstrap.mainInstance.getFractalManager().getCurrentFractal().getConstants().toString());
            clip.setContents(selection, null);
        });
        this.add(this.copy);

        this.paste = new JButton("Copy Constants from keyboard.");
        this.paste.addActionListener((event) -> {
            System.out.println("Reading numbers from clipboard.");
            try {
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                String constants = (String)clip.getData(DataFlavor.stringFlavor);
                Complex com = Complex.fromString(constants);
                Bootstrap.mainInstance.getFractalManager().newFractal(com);
                Bootstrap.mainInstance.getDisplayManager().updateDisplay();
                updateText();
                this.lastButton.setEnabled(true);

            } catch (Exception exception){
                System.out.println("Error reading constants from keyboard!");
                exception.printStackTrace();
            }
        });
        this.add(this.paste);

        this.toggleBW = new JButton("Enable Black & White");
        this.toggleBW.addActionListener((event) -> {
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
                this.toggleBW.setText("Enable Black & White");
            }
            else {
                this.toggleBW.setText("Disable Black & White");
            }
        });
        this.add(this.toggleBW);

        this.fractalTypes = new JComboBox<String>(Bootstrap.mainInstance.getFractalGenerator().getFractalTypes().toArray(new String[]{}));
        this.fractalTypes.addActionListener((event) -> {
            Bootstrap.mainInstance.getFractalGenerator().setFractalType((String)this.fractalTypes.getSelectedItem());
            System.out.println("Changed type to: " + this.fractalTypes.getSelectedItem());
        });
        this.add(this.fractalTypes);

    }

    public void updateText(){
        Complex comp = Bootstrap.mainInstance.getFractalManager().getCurrentFractal().getConstants();
        this.dataReal.setText("Real: " + comp.getReal());
        this.dataImag.setText("Imaginary: " + comp.getImaginary() + "i");
    }


    private static Dimension dim(int width, int height){
        return new Dimension(width, height);
    }

//    private void addComponent(JComponent component){
//        addActionListener(component, this);
//        this.add(component);
//    }

//    private void addAction(JComponent component, Function<ActionEvent, String> action){
//        this.actions.put(getActionCommand(component), action);
//        this.componentMap.put(getActionCommand(component), component);
//    }

//    @Override
//    public void actionPerformed(ActionEvent e){
//        String newVal = this.actions.getOrDefault(e.getActionCommand(), (event) -> {return "";}).apply(e);
//        if ((newVal != null && !newVal.equals("")) && this.componentMap.containsKey(e.getActionCommand())){
//            setText(this.componentMap.get(e.getActionCommand()), newVal);
//        }
//    }

//    private String getActionCommand(JComponent component){
//        if (component instanceof AbstractButton){
//            return ((AbstractButton)component).getActionCommand();
//        }
//        else if (component instanceof JComboBox){
//            return ((JComboBox)component).getActionCommand();
//        }
//        else {
//            return null;
//        }
//    }
//
//    private void addActionListener(JComponent component, ActionListener listener){
//        if (component instanceof AbstractButton){
//            ((AbstractButton)component).addActionListener(listener);
//        }
//        else if (component instanceof JComboBox){
//            ((JComboBox)component).addActionListener(listener);
//        }
//    }
//
//    private void setText(JComponent component, String text){
//        if (component instanceof AbstractButton){
//            ((AbstractButton) component).setText(text);
//        }
//    }
}
