package com.minecreatr.fractal;

import com.minecreatr.fractal.logic.Fractal;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

/**
 * Fractal Manager class
 */
public class FractalManager {

    private Fractal currentFractal;

    private Deque<Fractal> previousFractals;

    private Deque<Fractal> nextFractals;

    public FractalManager(Fractal currentFractal){
        this.currentFractal = currentFractal;
        this.previousFractals = new ArrayDeque<>();
        this.nextFractals = new ArrayDeque<>();
    }

    public FractalManager(){
        this(new Fractal());
    }

    public boolean hasNext(){
        return nextFractals.size() > 0;
    }

    public void next(){
        if (hasNext()){
            this.previousFractals.add(this.currentFractal);
            this.currentFractal = this.nextFractals.peekLast();
            this.nextFractals.remove(this.currentFractal);
        }
        else {
            this.previousFractals.add(this.currentFractal);
            this.currentFractal = new Fractal();
        }
    }

    public boolean hasPrevious(){
        return this.previousFractals.size() > 0;
    }

    public void previous(){
        if (hasPrevious()){
            this.nextFractals.add(this.currentFractal);
            this.currentFractal = this.previousFractals.peekLast();
            this.previousFractals.remove(this.currentFractal);
        }
    }

    public Fractal getCurrentFractal(){
        return this.currentFractal;
    }

    public void actOnAll(Consumer<Fractal> action){
        this.actOnPrevious(action);
        this.actOnNext(action);
        action.accept(this.currentFractal);
    }

    public void actOnNext(Consumer<Fractal> action){
        this.nextFractals.forEach(action);
    }

    public void actOnPrevious(Consumer<Fractal> action){
        this.previousFractals.forEach(action);
    }
}
