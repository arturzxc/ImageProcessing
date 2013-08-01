package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Artur Krzynowek
 */
public class ResourceLoader {

    private static final String IMAGE_PATH = "/resources/images/";

    /**
     * Loads an image given its name and is returned.
     *
     * @param
     * @return
     */
    public static ImageIcon loadImageIcon(String str) {
        return new ImageIcon(Object.class.getResource(IMAGE_PATH + str));
    }

    /**
     * Returns the image URL of any image.
     *
     * @param tooltipUrl
     * @return
     */
    public static URL getImageURL(String tooltipUrl) {
        URL url = Object.class.getResource(IMAGE_PATH + tooltipUrl);
        return url;
    }
    
    public static BufferedImage loadBufferedImage(String str){
        try {
            return ImageIO.read(Object.class.getResource(IMAGE_PATH + str));
        } catch (IOException ex) {
            Logger.getLogger(ResourceLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
