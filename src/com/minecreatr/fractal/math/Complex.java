package com.minecreatr.fractal.math;

import java.util.Random;

/**
 * Represents a complex number with the real imaginary form
 */
public class Complex {

    public static final Random random = new Random();

    /**
     * The real component of the complex number
     */
    private final double real;

    /**
     * The imaginary component of the complex number
     */
    private final double imaginary;

    /**
     * The magnitude of the complex number
     */
    private double magnitude;

    private boolean hasMag;

    public Complex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
        this.magnitude = Math.sqrt(real*real + imaginary*imaginary);
    }

    public double getReal(){
        return this.real;
    }

    public double getImaginary(){
        return this.imaginary;
    }

    public double getMagnitude(){
        if (!this.hasMag){
            this.magnitude = Math.sqrt(real*real + imaginary*imaginary);
            this.hasMag = true;
        }
        return this.magnitude;
    }

    public Complex add(Complex other){
        return new Complex(this.getReal() + other.getReal(), this.getImaginary() + other.getImaginary());
    }

    public Complex square(){
        return new Complex((getReal() * getReal()) - (getImaginary() * getImaginary()), getReal() * getImaginary() * 2);
    }

    @Override
    public String toString(){
        return this.getReal() + " " + this.getImaginary() + "i";
    }

    public static Complex fromString(String constants){
        String[] parts = constants.split(" ");
        boolean reverse = false;
        if (parts[0].endsWith("i") && !parts[1].endsWith("i")){
            reverse = true;
            parts[0] = parts[0].substring(0, parts[0].length()-1);
        }
        else if (parts[1].endsWith("i")){
            parts[1] = parts[1].substring(0, parts[1].length()-1);
        }
        else {
            throw new NumberFormatException("Number contains no complex component!");
        }
        return (reverse) ? new Complex(Double.parseDouble(parts[1]), Double.parseDouble(parts[0]))
                : new Complex(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
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
