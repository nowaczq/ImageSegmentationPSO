package Controler;

import Model.MImageReader;
import Model.MImageSaver;
import Model.MKMeansAlgorithm;
import View.VMainScreen;
import View.VRawImage;
import View.VSegmentedImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by dom on 2015-10-28.
 */
public class CMainAlgorithm implements ActionListener
{

    public static MImageReader mImageReader;
    public static MImageSaver mImageSaver;
    private static VMainScreen mainScreen;
    private static BufferedImage dstImage;


    public static void main(String[] args) throws IOException {
        mainScreen = new VMainScreen();
    }

    public CMainAlgorithm ()
    {
    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        Component c = (Component)e.getSource();
        if (c == mainScreen.exit)
            System.exit(0);
        if (c == mainScreen.fileChooser)
        {
            FileDialog fd =new FileDialog(mainScreen,"Load",FileDialog.LOAD);
            fd.setVisible(true);
            mainScreen.fileName=fd.getFile();
            mImageReader = new MImageReader(mainScreen.getFileName());
            dstImage = mImageReader.getImage();
            new VRawImage(dstImage);
        }
        if (c == mainScreen.fileSaver)
        {
            FileDialog fd =new FileDialog(mainScreen,"Save",FileDialog.SAVE);
            fd.setVisible(true);
            mainScreen.fileName=fd.getFile();
            mImageSaver = new MImageSaver(dstImage,mainScreen.getFileName()+".png");

        }
        if (c == mainScreen.help)
        {
            JOptionPane.showMessageDialog(mainScreen, "Usage: \n" +
                    "1. Select input image from Options -> Load\n" +
                    "2. Fill colour field with number from range 1-255\n" +
                    "3. Fill mode field with proper number -- 1 or 2  (continous or iterative mode)\n" +
                    "4. Press the start button and wait for a while (a while will be longer \n"+
                    "    if you have choosen iterative mode or/plus wide range of colours)\n"+
                    "5. To save image please select Options -> Save and fill the field for image name\n");
        }
        if (c == mainScreen.startButton)
        {
            /*MKMeansAlgorithm MKMeansAlgorithm = new MKMeansAlgorithm();
            this.dstImage = MKMeansAlgorithm.calculate(mImageReader.readImage(),Integer.parseInt(mainScreen.rangeText.getText()));
            new VSegmentedImage(dstImage);*/
            MKMeansAlgorithm MKMeansAlgorithm = new MKMeansAlgorithm();
            this.dstImage = MKMeansAlgorithm.calculate(mImageReader.readImage(),Integer.parseInt(mainScreen.rangeText.getText()));
            new VSegmentedImage(dstImage);
        }
    }
}
