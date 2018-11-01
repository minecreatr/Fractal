package com.minecreatr.fractal;

import com.minecreatr.fractal.display.render.IRenderFractal;
import com.minecreatr.fractal.logic.IFractalLogic;
import com.minecreatr.fractal.logic.StorageHelper;
import com.minecreatr.fractal.math.Complex;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Generate the fractal
 */
public class FractalGenerator {

    private Map<String, IFractalLogic> fractalTypes;
    private Map<String, IRenderFractal> renderTypes;

    private IFractalLogic currentLogic;
    private IRenderFractal currentRender;

    public FractalGenerator(){
        this.fractalTypes = new HashMap<>();
        this.renderTypes = new HashMap<>();
    }

    public void addFractalType(String name, IFractalLogic logic){
        this.fractalTypes.put(name, logic);
    }

    public void addRenderType(String name, IRenderFractal render){
        this.renderTypes.put(name, render);
    }

    public void setFractalType(String type){
        this.currentLogic = fractalTypes.getOrDefault(type, this.currentLogic);
    }

    public void setRenderType(String type){
        this.currentRender = renderTypes.getOrDefault(type, this.currentRender);
    }

    public boolean isRenderType(String type){
        return this.currentRender == renderTypes.getOrDefault(type, null);
    }

    public BufferedImage createFractal(Complex complex){
        long time = System.currentTimeMillis();
        System.out.println("Generating Fractal");
        int[] data = StorageHelper.newStorage(Main.WIDTH, Main.HEIGHT, 256);
        this.currentLogic.generateFractal(complex, data);
        System.out.println("Done Generating in " + (System.currentTimeMillis() - time) + "ms");
        BufferedImage image = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
        this.currentRender.renderFractal(image, data);
        System.out.println("Done Rendering");
        return image;
    }
}
