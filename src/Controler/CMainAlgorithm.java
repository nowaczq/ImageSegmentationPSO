package Controler;

import Model.MAlgorithmCalculations;
import Model.MCluster;
import Model.MImageReader;
import Model.MImageSaver;
import View.VMainScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by dom on 2015-10-28.
 */
public class CMainAlgorithm implements ActionListener
{
    public static final int MODE_CONTINUOUS = 1;
    public static final int MODE_ITERATIVE = 2;
    private MCluster cluster;
    private MAlgorithmCalculations mAlgorithmCalculations;
    public static MImageReader mImageReader;
    public static MImageSaver mImageSaver;
    private static VMainScreen mainScreen;
    private BufferedImage dstImage;

    public static void main(String[] args) {
        if (args.length!=4) {
            System.out.println("Usage: java popscan.KMeans"
                    + " [source image filename]"
                    + " [destination image filename]"
                    + " [clustercount 0-255]"
                    + " [mode -i (ITERATIVE)|-c (CONTINUOS)]");
            return;
        }
        mainScreen = new VMainScreen();
        // parse arguments
        String src = args[0];
        String dst = args[1];
        int k = Integer.parseInt(args[2]);
        String m = args[3];
        int mode = 1;
        if (m.equals("-i")) {
            mode = MODE_ITERATIVE;
        } else if (m.equals("-c")) {
            mode = MODE_CONTINUOUS;
        }
        mImageReader = new MImageReader(src);
        MAlgorithmCalculations mAlgorithmCalculations = new MAlgorithmCalculations();
        // create new KMeans object
        CMainAlgorithm kmeans = new CMainAlgorithm();
        // call the function to actually start the clustering
        BufferedImage dstImage = mAlgorithmCalculations.calculate(mImageReader.readImage(),
                k,mode);
        // save the resulting image
        mImageSaver = new MImageSaver(dstImage,dst);
        //mImageSaver.saveImage(dst, dstImage);

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
            String directoryInput=fd.getDirectory();
            mainScreen.fileName=fd.getFile();
            mImageReader = new MImageReader(mainScreen.getFileName());
            this.dstImage = mImageReader.getImage();
            System.out.println(this.dstImage.getHeight());
        }
        if (c == mainScreen.fileSaver)
        {
            FileDialog fd =new FileDialog(mainScreen,"Save",FileDialog.SAVE);
            fd.setVisible(true);
            String directoryInput=fd.getDirectory();
            mainScreen.fileName=fd.getFile();
            //System.out.println(this.dstImage.getHeight());
            mImageSaver = new MImageSaver(mImageReader.getImage(),mainScreen.getFileName()+".png");

        }
        if (c == mainScreen.help)
        {

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
