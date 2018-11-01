package com.minecreatr.fractal.display.render;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderFractalBlackAndWhite implements IRenderFractal {

    @Override
    public void renderFractal(BufferedImage image, int[] data){
        RenderHelper.iteratePixel(image, data, ((x, y, iterations) -> {
            if (!(iterations <= 10)){
                return Color.BLACK.getRGB();
            }
            else {
                return Color.WHITE.getRGB();
            }
        }));
    }
}
