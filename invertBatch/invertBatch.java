/**
 * invert a batch of images
 * @DinhHuuNguyen
 * @12/23/2015
 */
import edu.duke.*;
import java.io.*;
public class invertBatch {
    //I started with the image I wanted (inImage)
    public ImageResource invert(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel : outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //set pixel's red value, green value, blue value to inverted inPixel's values
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
    }
    
    public void selectandinvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource ir = new ImageResource(f);
            String filename = ir.getFileName();
            String newName = "inverted-" + filename;
            ImageResource inverted = invert(ir);
            inverted.setFileName(newName);           
            inverted.draw();
            inverted.save();

        }
    }
    
}