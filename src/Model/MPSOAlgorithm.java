package Model;

import java.awt.image.BufferedImage;

/**
 * Created by dom on 2015-10-28.
 */
public class MPSOAlgorithm
{
    private BufferedImage image;

    public MPSOAlgorithm(BufferedImage imageReader)
    {
        this.image = imageReader;
    }

    /*sketch of function, based on matlab and python sources*/
    public void regionGrowingFunction(BufferedImage imageM, int x, int y, int threshold)
    {
        threshold = 1;
        int height = imageM.getHeight();
        int width = imageM.getWidth();
        int orient [][] = new int[4][2];
        orient[0][0]=1 ;
        orient[0][1]=0 ;
        orient[1][0]=0 ;
        orient[1][1]=1 ;
        orient[2][0]= -1;
        orient[2][1]= 0;
        orient[3][0]= 0;
        orient[3][1]= -1;
        int dist = 0;
        int size = 1;
        int currentPixel [] = {x,y};
        int flag = 0;
        int pixelArea = width*height;
        //reg = cv.CreateImage( dims, cv.IPL_DEPTH_8U, 1) COŒ DO TWORZENIA OBRAZU TYLKO CO??


        while (dist < threshold && size < pixelArea)
        {
            for (int i = 0 ; i <4; i++)
            {
                int temporaryPixel [] = {currentPixel[0] + orient[i][0],currentPixel[1] + orient[i][1]};

                if((width > 0 && temporaryPixel[0] > 0) && (height > 0 && temporaryPixel[1] > 0))
                    flag= 1;
                else
                    flag = 0;
                /*candidate is taken if not already selected before*/

                if (flag == 1 /*&& ((reg[temporaryPixel[1], temporaryPixel[0]]==0))*/)
                    /*contour.append(temp_pix)
                contour_val.append(img[temp_pix[1], temp_pix[0]] )
                reg[temp_pix[1], temp_pix[0]] = 150
                    TUTAJ MA BYÆ STWORZONY OBRAZ JE¯ELI NIE ZOSTA£ WCZEŒNIEJ STWORZONY -- CHODZI O TEN REGION WYBRANY
                */
                    flag = 1;





            }
            /*

            TUTAJ NASTÊPUJE AKTUALIZACJA REGIONU, TYLKO JAK TO W JAVIE ZROBIÆ NIE WIEM

            dist_list = [abs(i - mean_reg) for i in contour_val ]
        dist = min(dist_list)    #get min distance
        index = dist_list.index(min(dist_list)) #mean distance index
        size += 1 # updating region size
        reg[cur_pix[1], cur_pix[0]] = 255

        #updating mean MUST BE FLOAT
        mean_reg = (mean_reg*size + float(contour_val[index]))/(size+1)
        #updating seed
        cur_pix = contour[index]

        #removing pixel from neigborhood
        del contour[index]
        del contour_val[index]

    return reg*/
        }
    }
}
