package Model;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by dom on 2015-12-21.
 */
public class MKmeansPsoAlgorithm
{
    private MCluster[] clusters;
    private MCluster[] tableOfParticles;
    BufferedImage image;

    public BufferedImage calculate (BufferedImage imag, int k)
    {
        this.image = imag;
        this.clusters = createClusters(image, k);
        this.tableOfParticles = createParticles(image);

            //TO SIE ROBI MILIARD RAZY, POPRAWIC NAJPRAWDOPODOBNIEJ POZYCJONOWANIE W TABLICY -- GDZIE CO JEST ITP
            findBetterColourCentroid(image, k);

        long start = System.currentTimeMillis();
        int w = image.getWidth();
        int h = image.getHeight();
        int[] stackOfPixels = new int[w*h]; //ARRAY OF PARTICLES
        Arrays.fill(stackOfPixels, -1);
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
                    int pixel = image.getRGB(x, y);
                    MCluster cluster = findMinimalCluster(pixel);
                    if (stackOfPixels[w * y + x] != cluster.getId())
                    {
                        pixelChangedCluster = true;
                        stackOfPixels[w * y + x] = cluster.getId();
                    }
                }

            }

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
            //TUTAJ TRZEBA ZMIENIĆ SRODKI NA BARDZIEJ OPTYMALNE
            //this.clusters = createClusters(image,k);
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

    private void findBetterColourCentroid(BufferedImage image, int k)
    {
        int x =0, y =0, prevX =0, prevY =0;
        int dx = image.getHeight()/k;
        int dy = image.getWidth()/k;

        int min;
        int avg = 0;

        for (int i = 0; i < k; i++)
        {

            min = this.clusters[i].getRGB();
            while (x < dx)
            {
                while (y < dy)
                {
                    avg += this.tableOfParticles[x*image.getWidth()+y].getRGB();

                }
            }
            avg = avg/(dx*dy);
            if(avg < min)
            {
                min = avg;
                this.clusters[i] = new MCluster(i,min);
            }
            x += dx;
            y += dy;
            dx += dx;
            dy += dy;
        }
    }

    private MCluster[] createClusters(BufferedImage image, int k)
    {
        MCluster[] result = new MCluster[k];
        int p ,u,prevX=0,prevY=0;
        int x = 0; int y = 0;
        int dx = image.getWidth()/k;
        int dy = image.getHeight()/k;
        for (int i=0;i<k;i++)
        {
            if(i == 0)
            {
                p=new Random().nextInt(x+1);
                u=new Random().nextInt(y+1) ;
            }
            else
            {
                p=new Random().nextInt(x-prevX+1)+prevX;
                u=new Random().nextInt(y-prevY+1)+prevY ;
            }
            result[i] = new MCluster(i,image.getRGB(p,u));
            prevX=x;
            prevY=y;
            System.out.println(p +" " +u +" x: "+prevX+" y: "+prevY);
            x+=dx; y+=dy;
        }
        return result;
    }

    private MCluster[] createParticles(BufferedImage image)
    {
        MCluster[] result = new MCluster[image.getHeight()*image.getWidth()];
        int jump = image.getHeight()*image.getWidth();
        int i =0;
        for(int x = 0; x < image.getWidth()-1;x++)
        {
            for(int y = 0; y < image.getHeight()-1;y++)
            {
                result[i] = new MCluster(i,image.getRGB(x,y));
                bestMatchCluster(result[i].getRGB());
                i++;
            }
        }
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

    private void bestMatchCluster(int rgb) //distance to cluster center -- COLOUR
    {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < this.clusters.length;i++)
        {
            int distance = this.clusters[i].distance(rgb);
            if(distance < min)
            {
                min = distance;
                this.clusters[i].addPixel(distance);
            }
        }
    }
}
