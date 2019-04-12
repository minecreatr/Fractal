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
        int index = getIndexFor(x, y, data[WIDTH_INDEX]);
        if (data[index]!=0){
            System.out.println("Overlap detected at " + x +" " + y+ " and index "+ index);
        }
        else {
            data[index] = iterations;
        }
    }

    public static int getData(int[] data, int x, int y){
        return data[getIndexFor(x, y, data[WIDTH_INDEX])];
    }

    private static int getIndexFor(int x, int y, int width){
        return (y * width) + x + OFFSET;
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

    public static boolean checkRegion(int[] data, int x, int y, int width, int height){
        for (int i = 0; i < height ; i++){
            int index = getIndexFor(x, y + i, data[WIDTH_INDEX]);
            for (int j = 0 ; j < width ; j++){
                if (data[index + j] != 0){
                    return true;
                }
            }
        }
        return false;
    }

    public static DataWrapper wrap(int[] data){
        return new DataWrapper(data);
    }

    public static class DataWrapper {

        private int[] data;

        public DataWrapper(int[] data){
            this.data = data;
        }

        public int getHeight(){
            return this.data[HEIGHT_INDEX];
        }

        public int getWidth(){
            return this.data[WIDTH_INDEX];
        }

        public int getMaxIterations(){
            return this.data[MAX_ITER_INDEX];
        }

        public int getData(int x, int y){
            return StorageHelper.getData(this.data, x, y);
        }
        public void putData(int x, int y, int i){
            StorageHelper.putData(this.data, x, y, i);
        }

        public void putIfEmpty(int x, int y, int i){
            StorageHelper.putIfEmpty(this.data, x, y, i);
        }

        public int getSumSquare(int x, int y, int dim){
            int tot = 0;
            for (int xo = 0 ; xo < dim ; xo++){
                for (int yo = 0 ; yo < dim ; yo++){
                    tot += getData(x + xo, y + yo);
                }
            }
            return tot;
        }

        public boolean checkRegion(int x, int y, int width, int height){
            return StorageHelper.checkRegion(this.data, x, y, width, height);
        }
    }



}
