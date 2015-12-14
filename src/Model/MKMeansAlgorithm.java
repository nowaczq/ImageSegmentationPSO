package Model;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by dom on 2015-11-30.
 */
public class MKMeansAlgorithm
{
    public static final int MODE_CONTINUOUS = 1;
    public static final int MODE_ITERATIVE = 2;
    private MCluster[] clusters;
    private int colourRange;



    public BufferedImage calculate(BufferedImage image, int k, int mode)
    {
        long start = System.currentTimeMillis();
        int w = image.getWidth();
        int h = image.getHeight();
        int amountOfParticles = w*h/k;
        this.clusters = createClusters(image,k);
        int[] stackOfPixels = new int[w*h];
        Arrays.fill(stackOfPixels, -1);
        this.colourRange =k;

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
                    if (stackOfPixels[w*y+x]!=cluster.getId())
                    {
                        /*if (mode==MODE_CONTINUOUS)
                        {
                            if (stackOfPixels[w*y+x]!=-1)
                            {
                                this.clusters[stackOfPixels[w*y+x]].removePixel(pixel);
                            }

                            cluster.addPixel(pixel);
                        }*/
                        pixelChangedCluster = true;
                        stackOfPixels[w*y+x] = cluster.getId();
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
                        int clusterId = stackOfPixels[w*y+x];
                        this.clusters[clusterId].addPixel(image.getRGB(x, y));
                    }
                }
            }
       //    clusters = redefineClusterCenter(image,clusters);


        }

        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        for (int y=0;y<h;y++)
        {
            for (int x=0;x<w;x++)
            {
                int clusterId = stackOfPixels[w*y+x];
                result.setRGB(x, y, this.clusters[clusterId].getRGB());


            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Clustered to "+k
                + " clusters in "+loops
                +" loops in "+(end-start)+" ms.");
        return result;
    }
    private MCluster findMinimalCluster(int rgb) //distance to cluster center -- COLOUR
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

    private int findMinimalEuclideanDistance(int rgb)
    {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < this.clusters.length;i++)
        {
            int distance = this.clusters[i].distance(rgb);
            if(distance < min)
            {
                min = distance;
            }
        }
        return min/this.clusters.length;
    }

    private int findMaximumEuclideanDistance(int rgb)
    {
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < this.clusters.length;i++)
        {
            int distance = this.clusters[i].distance(rgb);
            if(distance > max)
            {
                max = distance;
            }
        }
        return max/this.clusters.length;
    }

    //DODAĆ METODĘ WYLICZAJĄCĄ ŚRODKI W TRAKCIE, A NIE TYLKO PRZY TWORZENIU
    // result[i] = new MCluster(i,image.getRGB(fitnessFunction(p),fitnessFunction(u))); TO POWINNO BYĆ W METODZIE KTÓRA WYLICZA ŚRODKI(KOLORY) W TRAKCIE

    private MCluster[] redefineClusterCenter(BufferedImage image,MCluster []clstr)
    {
        MCluster []result = clstr;
        int xx=0;
        int yy=0;
        int dx = image.getWidth()/result.length;
        int dy= image.getHeight()/result.length;

        for(int i = 0; i < result.length;i++)
        {
            int p=new Random().nextInt(xx+1);
            int u=new Random().nextInt(yy+1) ;System.out.println(p +" " +u );
            result[i] = new MCluster(i,image.getRGB(xx,yy));


            xx+=dx; yy+=dy;
        }
        return result;
    }


    private MCluster[] createClusters(BufferedImage image, int k)
    {
        MCluster[] result = new MCluster[k];
        int x = 0; int y = 0;
        int dx = image.getWidth()/k;
        int dy = image.getHeight()/k;
        for (int i=0;i<k;i++)
        {
            //ZAMIAST NA SZTYWNO BRAĆ ŚRODEK TJ. KOLOR TRZEBA ZAINICJOWAĆ LOSOWY ŚRODEK TJ KOLOR
            int p=new Random().nextInt(x+1);
            int u=new Random().nextInt(y+1) ;
            result[i] = new MCluster(i,image.getRGB(p,u));

            System.out.println(p +" " +u );
            x+=dx; y+=dy;
        }
        return result;
    }

    //grupa, pozycja w grupie, liczba z tablicy stackOfPixels
    private void calculatePersonalBest(MCluster []clusters, int position,int x)
    {
        if (fitnessFunction(x) >= clusters[position].getPersonalBest())
            clusters[position].setPersonalBest(clusters[position-1].getPersonalBest());
        else
            clusters[position].setPersonalBest(x);

    }

    private int fitnessFunction(int k)
    {
        return 2*this.colourRange-1-findMinimalEuclideanDistance(k)+findMaximumEuclideanDistance(k);
    }

}
