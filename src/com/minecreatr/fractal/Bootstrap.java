package com.minecreatr.fractal;

import com.sun.imageio.plugins.png.PNGMetadata;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import java.io.File;

/**
 * Bootstrap/starting point for the fractal program
 */
public class Bootstrap {
    
    public static Main mainInstance;

    public static void main(String[] args) {
        mainInstance = new Main();
        mainInstance.start(args);

//        try {
//            ImageReader imageReader = ImageIO.getImageReadersByFormatName("png").next();
//            File file = new File("metatest.png");
//            System.out.println(file.exists());
//            imageReader.setInput(ImageIO.createImageInputStream(new File("metatest.png")));
//            IIOMetadata metadata = imageReader.getImageMetadata(0);
//            if (metadata == null){
//                System.out.println("What?");
//            }
//            PNGMetadata pngmeta = (PNGMetadata) metadata;
//            NodeList childNodes = pngmeta.getStandardTextNode().getChildNodes();
//
//            for (int i = 0; i < childNodes.getLength(); i++) {
//                Node node = childNodes.item(i);
//                String keyword = node.getAttributes().getNamedItem("keyword").getNodeValue();
//                String value = node.getAttributes().getNamedItem("value").getNodeValue();
//                System.out.println(keyword + ": " + value);
//            }
//        } catch (Exception exception){
//            exception.printStackTrace();
//        }
    }
}
