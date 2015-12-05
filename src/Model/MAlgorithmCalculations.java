package Model;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Created by dom on 2015-11-30.
 */
public class MAlgorithmCalculations
{
    public static final int MODE_CONTINUOUS = 1;
    public static final int MODE_ITERATIVE = 2;
    private MCluster[] clusters;



    public BufferedImage calculate(BufferedImage image, int k, int mode)
    {
        long start = System.currentTimeMillis();
        int w = image.getWidth();
        int h = image.getHeight();
        this.clusters = createClusters(image,k);
        int[] lut = new int[w*h];
        Arrays.fill(lut, -1);


        boolean pixelChangedCluster = true;


        int loops = 0;
        while (pixelChangedCluster)
        {
            pixelChangedCluster = false;
            loops++;
            for (int y=0;y<h;y++)
            {
                for (int x=0;x<w;x++)
                {
                    int pixel = image.getRGB(x, y); //TUTAJ TRZEBA PODPIĄĆ ALGORYTM PSO ŻEBY ZAMIAST Z PĘTLI WYBIERAŁ MOŻLIWIE NAJLEPSZE PUNKTY
                    MCluster cluster = findMinimalCluster(pixel);
                    if (lut[w*y+x]!=cluster.getId())
                    {
                        if (mode==MODE_CONTINUOUS)
                        {
                            if (lut[w*y+x]!=-1)
                            {
                                this.clusters[lut[w*y+x]].removePixel(pixel);
                            }

                            cluster.addPixel(pixel);
                        }
                        pixelChangedCluster = true;
                        lut[w*y+x] = cluster.getId();
                    }
                }
            }
            if (mode==MODE_ITERATIVE)
            {

                for (int i=0;i<this.clusters.length;i++)
                {
                    this.clusters[i].clear();
                }
                for (int y=0;y<h;y++)
                {
                    for (int x=0;x<w;x++)
                    {
                        int clusterId = lut[w*y+x];
                        this.clusters[clusterId].addPixel(image.getRGB(x, y));
                    }
                }
            }

        }

        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int y=0;y<h;y++)
        {
            for (int x=0;x<w;x++)
            {
                int clusterId = lut[w*y+x];
                result.setRGB(x, y, this.clusters[clusterId].getRGB());
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Clustered to "+k
                + " clusters in "+loops
                +" loops in "+(end-start)+" ms.");
        return result;
    }
    public MCluster findMinimalCluster(int rgb)
    {
        MCluster cluster = null;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < this.clusters.length;i++)
        {
            int distance = this.clusters[i].distance(rgb);
            if(distance < min)
            {
                min = distance;
                cluster = this.clusters[i];
            }
        }
        return cluster;
    }

    public MCluster[] createClusters(BufferedImage image, int k)
    {
        MCluster[] result = new MCluster[k];
        int x = 0; int y = 0;
        int dx = image.getWidth()/k;
        int dy = image.getHeight()/k;
        for (int i=0;i<k;i++)
        {
            result[i] = new MCluster(i,image.getRGB(x, y));
            x+=dx; y+=dy;
        }
        return result;
    }

}
