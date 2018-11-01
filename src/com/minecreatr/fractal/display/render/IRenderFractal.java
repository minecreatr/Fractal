package com.minecreatr.fractal.display.render;

import java.awt.image.BufferedImage;

/**
 * Renders the fractal based on data
 */
public interface IRenderFractal {

    /**
     * Render the fractal the the specified image
     * @param image the image
     * @param data the fractal data
     */
    void renderFractal(BufferedImage image, int[] data);
}
