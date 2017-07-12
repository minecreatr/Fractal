package com.minecreatr.fractal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Cool Fractal
 *
 * 0.36990800064524376 0.15094139442964172i
 */

public class Main {


    public static int WIDTH = 1600;

    public static int HEIGHT = 1000;

    public static double HALF_WIDTH;

    public static double HALF_HEIGHT;

    public static Complex currentFractal = new Complex(0, 0);

    public static FractalPanel fractalPanel;

    public static ControlPanel controlPanel;

    public static JLayeredPane layer;

    public static Deque<Complex> previousFractals = new ArrayDeque<>();

    public static Deque<Complex> nextFractals = new ArrayDeque<>();

    public static void main(String[] args){

        if (args.length >= 2){
            try {
                WIDTH = Integer.parseInt(args[0]);
                HEIGHT = Integer.parseInt(args[1]);
                System.out.println("Setting width to "+ WIDTH + " and height " + HEIGHT);
            } catch (NumberFormatException exception){

            }
        }

        HALF_WIDTH = WIDTH / 2;
        HALF_HEIGHT = HEIGHT /2;

        currentFractal = Fractal.randomComplex();

        //currentFractal = new Complex(0.36990800064524376, 0.15094139442964172);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception){
            System.out.println("tterrag lied!");
        }

        //Create the frame
        JFrame frame = new JFrame("Fractal");

        //Create the layered pane
        layer = new JLayeredPane();
        frame.add(layer);
        layer.setBounds(0, 0, WIDTH, HEIGHT);

        //Create the control panel
        controlPanel = new ControlPanel();
        controlPanel.setSize(new Dimension(WIDTH/5, HEIGHT/5));
        layer.add(controlPanel, 0);

        //Create the fractal panel
        fractalPanel = new FractalPanel();
        fractalPanel.setSize(WIDTH, HEIGHT);
        layer.add(fractalPanel, 1);

        //Finalize Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);



        //Scanner sc = new Scanner(System.in);

        //System.out.println("Create a fractal with the command with format {real} {imaginary} to set the c value");

//        Random random = new Random();
//
//        while (true) {
//            if (automaticMode){
//                boolean realNeg = random.nextFloat() > 0.5f;
//                boolean imagNeg = random.nextFloat() > 0.5f;
//                double randReal = random.nextDouble();
//                double randImag = random.nextDouble();
//                if (realNeg){
//                    randReal = - randReal;
//                }
//                if (imagNeg){
//                    randImag = - randImag;
//                }
//
//                currentFractal = new Complex(randReal, randImag);
//                System.out.println("Current Fractal: " + currentFractal.toString());
//                fractalPanel.repaint();
//                layer.moveToFront(controlPanel);
//                controlPanel.repaint();
//            }
//
//            try {
//                Thread.sleep(5000);
//            } catch (Exception exception){
//                exception.printStackTrace();
//            }
//            String input = sc.nextLine();
//
//            String[] parts = input.split(" ");
//            if (parts.length != 2) {
//                System.out.println("Invalid command, command format is {real}~{imaginary}");
//                continue;
//            }
//            try {
//                double real = Double.parseDouble(parts[0]);
//                double imag = Double.parseDouble(parts[1]);
//                currentFractal = new Complex(real, imag);
//                panel.repaint();
//                System.out.println("Creating fractal with number "+ currentFractal);
//            } catch (NumberFormatException exception) {
//                System.out.println("Invalid command, command format is {real}-{imaginary}");
//            }

        }

}
