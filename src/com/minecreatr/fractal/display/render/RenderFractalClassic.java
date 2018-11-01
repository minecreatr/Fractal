package com.minecreatr.fractal.display.render;

import com.minecreatr.fractal.logic.StorageHelper;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderFractalClassic implements IRenderFractal {

    @Override
    public void renderFractal(BufferedImage image, int[] data){
        RenderHelper.iteratePixel(image, data, ((x, y, iterations) -> {
            float brightness = iterations < data[StorageHelper.MAX_ITER_INDEX] ? 1f : 0;
            float hue = (float)(iterations % data[StorageHelper.MAX_ITER_INDEX]) / (float)(data[StorageHelper.MAX_ITER_INDEX]-1);
            Color color = Color.getHSBColor(hue, 1f, brightness);
            return color.getRGB();
        }));
    }
}
