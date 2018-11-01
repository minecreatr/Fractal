package com.minecreatr.fractal.display.render;

import com.minecreatr.fractal.logic.StorageHelper;

import java.awt.image.BufferedImage;

/**
 * Some help for rendering and stuff
 */
public class RenderHelper {

    public static void iteratePixel(BufferedImage image, int[] data, PixelIterator iterator){
        for (int i = 0 ; i < (data.length - StorageHelper.OFFSET - 1) ; i++){
            int x = i % data[StorageHelper.WIDTH_INDEX];
            int y = i / data[StorageHelper.WIDTH_INDEX];
            try {
                image.setRGB(x, y, iterator.getRGB(x, y, data[i + StorageHelper.OFFSET]));
            } catch (Exception e){
                System.out.println(x + " " + y + " data: "+ i);
                throw new RuntimeException(e);
            }
        }
    }


    @FunctionalInterface
    public interface PixelIterator {

        int getRGB(int x, int y, int iterations);
    }
}
