package com.minecreatr.fractal;

/**
 * Represents a complex number with the real imaginary form
 */
public class Complex {

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




}
