package com.minecreatr.fractal.logic;

import com.minecreatr.fractal.math.Complex;

/**
 * Represents an implementation of a type of fractal logic
 *
 * Fractal int[] format
 * [0] = max iterations
 * [1-16] reserved
 */
public interface IFractalLogic {

    /**
     * Generate the fractal data
     * @param seed The Complex number that represents the seed for this specific fractal
     * @param data The int[] to put the result in
     */
    void generateFractal(Complex seed, int[] data);
}
