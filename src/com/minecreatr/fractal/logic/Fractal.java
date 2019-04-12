package com.minecreatr.fractal.logic;

import com.minecreatr.fractal.Bootstrap;
import com.minecreatr.fractal.math.Complex;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Fractal {



    private static final int MAX_ITER = 256;


    private int[] data;

    private BufferedImage image;

    private boolean dirty;

    private Complex constants;

    public Fractal(Complex constants){
        this.constants = constants;
        //this.data
        this.image = Bootstrap.mainInstance.getFractalGenerator().createFractal(constants);
        //generateFractal(this.constants, this.data);
        //this.image = generateImage(this.data);
        this.dirty = false;
    }

    public Fractal(){
        this(Complex.randomComplex());
    }

    public BufferedImage getImage() {
        if (this.dirty){
            this.image = Bootstrap.mainInstance.getFractalGenerator().createFractal(constants);
            //this.image = generateImage(this.data);
            this.dirty = false;
        }
        return this.image;
    }

    public Complex getConstants() {
        return this.constants;
    }

//    private static BufferedImage generateImage(int[] data){
//        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
//        for (int i = 0 ; i < data.length ; i++){
//            float brightness = data[i] < MAX_ITER ? 1f : 0;
//            float hue = (data[i] % MAX_ITER) / 255.0f;
//            Color color = Color.getHSBColor(hue, 1f, brightness);
////            if (data[i] == 300){
////                color = Color.BLUE;
////            }
//            image.setRGB(i % HEIGHT, i / WIDTH, color.getRGB());
//        }
//        return image;
//    }

//    private static void generateFractal(Complex num, int[] data){
//        //Amount of iterations per pixel
//
//        //Create the image to output to
//        //BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
//        //Iterate for every pixel
//        for (int x = 0; x < WIDTH ; x++ ){
//            for (int y = 0; y < HEIGHT ; y++){
//                //Complex number with real part 2 * (x-hw)/hw and imaginary part 1.33 * (x-hh)/hh
//                double real = 2 * ((x - HALF_WIDTH) / HALF_WIDTH);
//                double imag = 1.33 * ((y - HALF_HEIGHT) / HALF_HEIGHT);
//                Complex cur = new Complex(real, imag);
//                int i;
//                //Iterate for the pixel max_iter number of times or until it breaks
//                for (i = 0 ; i < MAX_ITER ; i++ ){
//                    cur = cur.square().add(num);
//                    if (cur.getMagnitude() > 2){
//                        break;
//                    }
//                }
//                //System.out.println("Storing: " + i + " in " + ((y  * HEIGHT) + x));
//                if (data[(y  * HEIGHT) + x] == 0){
//                    data[(y  * HEIGHT) + x] = i;
//                    //System.out.println("What at "+ x + " " + y);
//                }
//                else {
//                    //data[(y  * HEIGHT) + x + (int)HALF_WIDTH] = i;
//                }
//                //data[(y  * HEIGHT) + x] = i;
////                if (blackAndWhite){
////                    if (!(i <= 30)){
////                        image.setRGB(x, y, Color.BLACK.getRGB());
////                    }
////                    else {
////                        image.setRGB(x, y, Color.WHITE.getRGB());
////                    }
////                }
////                else {
////                    float brightness = i < max_iter ? 1f : 0;
////
////                    float hue = (i % 256) / 255.0f;
////
////                    Color color = Color.getHSBColor(hue, 1f, brightness);
////
//////                if (i > 100 && i < 200){
//////                    System.out.println("xy: " + x + " " + y + "i is "+i);
//////                }
////
////                    image.setRGB(x, y, color.getRGB());
////                }
//
//            }
//        }
//
//        //return image;
//    }

    public boolean isDirty(){
        return this.dirty;
    }

    public void setDirty(boolean value){
        this.dirty = value;
    }

//    public static void updateDisplay(){
//        updateText();
//        Main.fractalPanel.repaint();
//        layer.moveToFront(Main.controlPanel);
//        Main.controlPanel.repaint();
//    }
//
//    public static void updateText(){
//        Main.controlPanel.dataReal.setText("Real: " + Main.currentFractal.getReal());
//        Main.controlPanel.dataImag.setText("Imaginary: " + Main.currentFractal.getImaginary() + "i");
//    }



}
