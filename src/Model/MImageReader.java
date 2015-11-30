package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by dom on 2015-10-28.
 */
public class MImageReader
{
    private String imageName;
    private BufferedImage image;

    public MImageReader(String name)
    {
        this.imageName = name;
        readImage();
    }

    public BufferedImage readImage()
    {
        this.image = null;
        try
        {
            image = ImageIO.read(new File(this.imageName));
        }
        catch (IOException e)
        {
            System.out.println(e.toString()+" Image '"
                    +this.imageName+"' not found.");
        }
        return this.image;
    }
    public BufferedImage getImage()
    {
        return this.image;
    }
/*
    public int getColourOfPixel(int x, int y)
    {
       return this.image.getRGB(x,y);
    }

    public int getHeight()
    {
        return this.image.getHeight();
    }

    public int getWidth()
    {
        return this.image.getWidth();
    }
*/
}
