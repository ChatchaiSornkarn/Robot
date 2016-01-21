package robots;
import becker.robots.icons.Icon;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *  displaying an image.
 *  since the Sim objects only take icons from Becker's package so
 *  we extended and ImageIcon out of the Becker's Icon
 *  with an Image variable 
 */
public class ImageIcon extends Icon {

    private Image image;

   
    public ImageIcon(String path) {//constructor 
        super();

        try {
            image = ImageIO.read(new File(path));//assign image to the image in that path
        } catch (IOException exception) {
            image = null;//catch if that image is null
        }
    }

  
    public Image getImage(int width, int height, double rotation) {//make sure the Image we create has the same dimensions of the Sim object
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
}