package Controler;

import Model.MAlgorithmCalculations;
import Model.MImageReader;
import Model.MImageSaver;
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
    private static VRawImage raw;
    private static VSegmentedImage segmn;

    public static void main(String[] args) throws IOException {
        mainScreen = new VMainScreen();
    }

    public CMainAlgorithm ()
    {
    }

    public BufferedImage getImage()
    {
        return this.dstImage;
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
            raw = new VRawImage(dstImage);
            MAlgorithmCalculations mAlgorithmCalculations = new MAlgorithmCalculations();
            this.dstImage = mAlgorithmCalculations.calculate(mImageReader.readImage(),10,1);
        }
        if (c == mainScreen.fileSaver)
        {
            FileDialog fd =new FileDialog(mainScreen,"Save",FileDialog.SAVE);
            fd.setVisible(true);
            mainScreen.fileName=fd.getFile();
            mImageSaver = new MImageSaver(dstImage,mainScreen.getFileName()+".png");
            segmn = new VSegmentedImage(dstImage);
        }
        if (c == mainScreen.help)
        {
            JOptionPane.showMessageDialog(mainScreen, "\"Usage: \"\n" +
                    "                    + \" [source image filename]\"\n" +
                    "                    + \" [destination image filename]\"\n" +
                    "                    + \" [clustercount 0-255]\"\n" +
                    "                    + \" [mode -i (ITERATIVE)|-c (CONTINUOS)]\"\n");
        }
        /*TUTAJ PRZYCISK STARTUJACY ALGORYTM
        if (c == mainScreen.start)
        {
            MAlgorithmCalculations mAlgorithmCalculations = new MAlgorithmCalculations();
            // create new KMeans object
            CMainAlgorithm kmeans = new CMainAlgorithm();
            // call the function to actually start the clustering
            this.dstImage = mAlgorithmCalculations.calculate(mImageReader.readImage(),
                    10,1);
            mImageSaver = new MImageSaver(dstImage,"ol112a.png");
        }*/
    }
}
