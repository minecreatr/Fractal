package com.minecreatr.fractal.display;

import com.minecreatr.fractal.Bootstrap;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FractalPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g){
//        if (!(gIn instanceof Graphics2D)){
//            System.err.println("Graphics not 2D");
//            return;
//        }
//
//        Graphics2D g = (Graphics2D) gIn;

        BufferedImage image = Bootstrap.mainInstance.getFractalManager().getCurrentFractal().getImage();
        g.drawImage(image, 0, 0, null);
//
//        int width = Main.WIDTH / 5;
//        int height = Main.HEIGHT / 5;
//
//        g.setColor(Color.gray);
//
//        g.fillRect(0, 0, width, height);
//
//        g.setColor(Color.black);
//
//        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
//
//        drawCenteredX(g, "Fractal Parameters", 0, width, 25);

        //g.drawString("Fractal Parameters",width/4, 25);
    }
//
//    private static void drawCenteredX(Graphics2D g, String message, int x1, int x2, int y){
//        int size = g.getFontMetrics().stringWidth(message);
//        int x = x1 + ((x2 - x1 - size)/2);
//        g.drawString(message, x, y);
//    }
//
//    private static void drawCenteredY(Graphics2D g, String message, int x, int y1, int y2){
//        int size = g.getFontMetrics().stringWidth(message);
//        int y = y1 + ((y2 - y1 - size)/2);
//        g.drawString(message, x, y);
//    }
}
