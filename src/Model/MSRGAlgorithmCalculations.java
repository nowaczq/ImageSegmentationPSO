package Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by dom on 2015-12-07.
 */
public class MSRGAlgorithmCalculations
{
   /* Pseudo code uses following variables:
    SEED: position of seed (x, y).
    RCOUNT: Counter to keep track of current region being
    grown.
    PG: stack to store pixels to grow.
    BP: stack to store boundary pixels of grown region.
    REGION: matrix with same size of image I, storing the
    labels of grown region.
    CP8-nb (j): 8-neighbours of CP, where j= 1, 2, 3….8.
    PSEUDOCODE:
    Region_Growing(RGB image I)
    [R, C] = size (I)
    SEED = (R/2, C/2)
    RCOUNT = 1
    i = 1
    j = 1
    PG (i) = SEED
    Step1:
    Calculate: THR (Otsu’s adaptive threshold)
    While PG not empty
        CP = PG (i)
        i = i-1
        For (8-nb of CP , k=1:8 )
            If (REGION (CP8-nb(k)) not labeled)
                Calculate: DIST(SEED,CP8-nb(k))(as (2))
                If (DIST<THR)
                    REGION (CP8-nb(k)) = RCOUNT
                    i = i+1
                    PG (i) = CP8-nb(k)
                Else
                    j = j+1
                    BP (j) = CP8-nb(k)
                End if
            End if
        End for
    End while
    Step2:
    While BP not empty
        SEED = BP (j)
        j = j-1
        RCOUNT = RCOUNT+1
        i = 1
        PG (i) = SEED
        Go to Step1
    End while
    */
    private int seedPoint[]
    private int regionCounter;

    public void srgAlgorithm(BufferedImage image)
    {
        int height = image.getHeight();
        int width = image.getWidth();
        this.seedPoint = new int[2];
        this.seedPoint[0] = height/2;
        this.seedPoint[1] = width/2;
        this.regionCounter = 1;
        ArrayList<int[]> stackOfSeeds = new ArrayList<>();
        int i = 1;
        int j = 1;
        stackOfSeeds.add(this.seedPoint);
        stackOfSeeds.add(this.seedPoint);
        otsuAdaptativeThreshold();

        while (!(stackOfSeeds.isEmpty()))
        {
            int [] centerPoint = new int [2];
            centerPoint = stackOfSeeds.get(i);
            for (int nb = 0; nb < 8; nb++)
            {

            }

        }
    }

    private void otsuAdaptativeThreshold()
    {

    }
}
