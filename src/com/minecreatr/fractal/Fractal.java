package com.minecreatr.fractal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static com.minecreatr.fractal.Main.*;

public class Fractal {

    public static final Random random = new Random();

    public static BufferedImage generateFractal(){
        //Complex Number C
        Complex num = Main.currentFractal;

        //Amount of iterations per pixel
        int max_iter = 256;

        //Create the image to output to
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
        //Iterate for every pixel
        for (int x = 0; x < WIDTH ; x++ ){
            for (int y = 0; y < HEIGHT ; y++){
                //Complex number with real part 2 * (x-hw)/hw and imaginary part 1.33 * (x-hh)/hh
                double real = 2 * ((x - HALF_WIDTH) / HALF_WIDTH);
                double imag = 1.33 * ((y - HALF_HEIGHT) / HALF_HEIGHT);
                Complex cur = new Complex(real, imag);
                int i;
                //Iterate for the pixel max_iter number of times or until it breaks
                for (i = 0 ; i < max_iter ; i++ ){
                    cur = cur.square().add(num);
                    if (cur.getMagnitude() > 2){
                        break;
                    }
                }

                float brightness = i < max_iter ? 1f : 0;

                float hue = (i % 256) / 255.0f;

                Color color = Color.getHSBColor(hue, 1f, brightness);

//                if (i > 100 && i < 200){
//                    System.out.println("xy: " + x + " " + y + "i is "+i);
//                }

                image.setRGB(x, y, color.getRGB());

            }
        }

        return image;
    }

    public static void updateDisplay(){
        updateText();
        Main.fractalPanel.repaint();
        layer.moveToFront(Main.controlPanel);
        Main.controlPanel.repaint();
    }

    public static void updateText(){
        Main.controlPanel.dataReal.setText("Real: " + Main.currentFractal.getReal());
        Main.controlPanel.dataImag.setText("Imaginary: " + Main.currentFractal.getImaginary() + "i");
    }

    public static Complex randomComplex() {
        boolean realNeg = random.nextFloat() > 0.5f;
        boolean imagNeg = random.nextFloat() > 0.5f;
        double randReal = random.nextDouble();
        double randImag = random.nextDouble();
        if (realNeg) {
            randReal = -randReal;
        }
        if (imagNeg) {
            randImag = -randImag;
        }
        return new Complex(randReal, randImag);
    }
}
