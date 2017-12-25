import edu.duke.*;  // note, ImageResource, DirectoryResource, Pixel are all classes defined in edu.duke library
import java.io.*;
/**
 * let user select multiple image files, convert these image to grayscale,
 * and save the grayscale with "gray-" + <original-file-name>
 * @Xiangzhen Sun 
 * @12/24/2017
 */
public class BatchGrayScale {
    public ImageResource grayScale( ImageResource inImage ) {
        // creates an empty image (all black) of the given size in pixels
        ImageResource outImage = new ImageResource( inImage.getWidth(), inImage.getHeight() );
        
        for( Pixel pixel : outImage.pixels() ) { // Iterable that provides access to each of the pixels in the image
            // copy the pixel at ( pixel.getX(), pixel.getY() ) of inImage to inPixel
            Pixel inPixel = inImage.getPixel( pixel.getX(), pixel.getY() );
            int avg = ( inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue() ) / 3;  // apply gray filter by averaging
            
            pixel.setRed( avg );
            pixel.setGreen( avg );
            pixel.setBlue( avg );
        }
        // outImage.draw();  // test grayScale( ImageResource )
        
        return outImage;
    }
    
    public void batchProcessImages() {
        DirectoryResource dr = new DirectoryResource();
        
        for( File f : dr.selectedFiles() ) {
            String fileName = f.getName();
            String newFileName = "gray-" + fileName;
            
            ImageResource inImage = new ImageResource( f ); // create an ImageResource object from a file given as a parameter
            ImageResource newImage = grayScale( inImage );
            newImage.setFileName( newFileName );  // set name to newFileName
            // newImage.draw();
            newImage.save();  // save the change
        }
    }
}
