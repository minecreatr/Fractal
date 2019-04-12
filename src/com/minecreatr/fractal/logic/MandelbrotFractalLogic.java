package com.minecreatr.fractal.logic;

import com.minecreatr.fractal.math.Complex;

/**
 * Logic for creating a Mandelbrot Fractal
 */
public class MandelbrotFractalLogic implements IFractalLogic {

    public void generateFractal(Complex seed, int[] data){
        int height = data[StorageHelper.HEIGHT_INDEX];
        int width = data[StorageHelper.WIDTH_INDEX];
        double halfWidth = width/2;
        double halfHeight = height/2;
        for (int x = 0; x < width ; x++ ){
            for (int y = 0; y < height ; y++){
                double real = 2 * ((x - halfWidth) / halfWidth);
                double imag = 1.33 * ((y - halfHeight) / halfHeight);
                Complex add = new Complex(real, imag);
                Complex cur = new Complex(0, 0);
                //System.out.println(cur);
                int i;
                //Iterate for the pixel max_iter number of times or until it breaks
                for (i = 0 ; i < data[StorageHelper.MAX_ITER_INDEX] ; i++ ){
                    cur = cur.square().add(add);
                    if (cur.getMagnitude() > 2){
                        break;
                    }
                }
                if (StorageHelper.getData(data, x, y) != 0){
                    System.out.println("Second Visit: " + x + " " + y);
                }
                StorageHelper.putIfEmpty(data, x, y, i);
            }
        }
    }
}
