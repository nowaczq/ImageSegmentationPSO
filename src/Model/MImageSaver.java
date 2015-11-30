package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by dom on 2015-10-28.
 */
public class MImageSaver
{
    private BufferedImage image;
    private String fileName;

    public MImageSaver (BufferedImage img, String savedImage)
    {
        this.image = img;
        this.fileName = savedImage;
        saveImage();
    }

    private void saveImage()
    {
        File file = new File(this.fileName);
        try {
            ImageIO.write(this.image, "png", file);
        } catch (Exception e) {
            System.out.println(e.toString()+" Image '"+this.fileName
                    +"' saving failed.");
        }
    }
}
