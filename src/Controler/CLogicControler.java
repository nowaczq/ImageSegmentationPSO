package Controler;

import Model.MImageReader;
import View.VMainScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by dom on 2015-10-28.
 */
public class CLogicControler implements ActionListener
{
    private static VMainScreen mainScreen;
    private static MImageReader imageReader;

    public static void main(String[] args)
    {
        mainScreen = new VMainScreen();

    }

    public BufferedImage getImage()
    {
        return this.imageReader.getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component c = (Component)e.getSource();
        if (c == mainScreen.exit)
            System.exit(0);
        if (c == mainScreen.fileChooser)
        {
            FileDialog fd =new FileDialog(mainScreen,"Load",FileDialog.LOAD);
            fd.setVisible(true);
            String directoryInput=fd.getDirectory();
            mainScreen.fileName=fd.getFile();
            imageReader = new MImageReader(mainScreen.getFileName());
            System.out.println(imageReader.getHeight());
            System.out.println(imageReader.getWidth());
            //imageReader.colourOfPixel(1,1);
        /*TRANSFORMATE TO COLOUR*/
            Color myColor = new Color(imageReader.getColourOfPixel(1, 1));
            System.out.println(myColor);
        }
        if (c == mainScreen.fileSaver)
        {

        }
        if (c == mainScreen.help)
        {

        }
    }
}
