package com.minecreatr.fractal.logic;

/**
 * Help with storing data in the fractal array
 * Fractal int[] format
 * [0] = max iterations
 * [1] = height (y values)
 * [2] = width (x values)
 * [3-15] reserved
 * [16-WIDTH*HEIGHT] = Fractal Data - data cell = amount of iterations
 *
 * [16 + (y * WIDTH) + x;
 */
public class StorageHelper {

    public static final int OFFSET = 16;

    public static final int MAX_ITER_INDEX = 0;
    public static final int HEIGHT_INDEX = 1;
    public static final int WIDTH_INDEX = 2;


    public static int[] newStorage(int width, int height, int maxIterations){
        int[] storage = new int[width * height + OFFSET + 1];
        storage[MAX_ITER_INDEX] = maxIterations;
        storage[HEIGHT_INDEX] = height;
        storage[WIDTH_INDEX] = width;
        return storage;
    }


    public static void putData(int[] data, int x, int y, int iterations){
        int index = (y * data[WIDTH_INDEX]) + x + OFFSET;
        if (data[index]!=0){
            System.out.println("Overlap detected at " + x +" " + y+ " and index "+ index);
        }
        else {
            data[index] = iterations;
        }
    }

    public static int getData(int[] data, int x, int y){
        return data[(y * data[WIDTH_INDEX]) + x + OFFSET];
    }

    public static void putIfEmpty(int[] data, int x, int y, int iterations){
        if (getData(data, x, y) == 0){
            putData(data, x, y, iterations);
        }
    }

    private void nullify(int[] data){
        for (int i = 0 ; i < data.length ; i++){
            data[i] = 0;
        }
    }



}
