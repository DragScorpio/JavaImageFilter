import edu.duke.*;
import java.io.*;
/**
 * let user select multiple images and apply inversion filter to each of these images
 * rename the newly created images as "inverted-" + <original-file-name>
 * @Xiangzhen Sun 
 * @12/24/2017
 */
public class BatchInversions {
    public ImageResource makeInversion( ImageResource inImage ) {
        ImageResource outImage = new ImageResource( inImage.getWidth(), inImage.getHeight() );
        
        for( Pixel pixel : outImage.pixels() ) {
            Pixel inPixel = inImage.getPixel( pixel.getX(), pixel.getY() );
            // apply inversion algorithm to each pixels
            pixel.setRed( 255 - inPixel.getRed() );
            pixel.setGreen( 255 - inPixel.getGreen() );
            pixel.setBlue( 255 - inPixel.getBlue() );
        }
        
        // outImage.draw()  // test
        return outImage;
    }
    
    // batch process image files by applying inversion filter
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        
        for( File f : dr.selectedFiles() ) {
            String fileName = f.getName();
            String newFileName = "inverted-" + fileName;
            
            ImageResource inImage = new ImageResource( f );
            ImageResource outImage = makeInversion( inImage );
            outImage.setFileName( newFileName );
            // outImage.draw();  // test filter effect
            outImage.save();
        }
    }

}
