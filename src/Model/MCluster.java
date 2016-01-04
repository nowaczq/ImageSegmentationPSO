package Model;

/**
 * Created by dom on 2015-11-30.
 */
public class MCluster
{
    private int id;
    private int pixelCount;
    private int red;
    private int green;
    private int blue;
    private int reds;
    private int greens;
    private int blues;


    public MCluster(int id, int rgb)
    {
        this.red = rgb>>16&0x000000FF;
        this.green = rgb>> 8&0x000000FF;
        this.blue = rgb>> 0&0x000000FF;
        this.id = id;
        addPixel(rgb);

    }

    public void clear()
    {
        red = 0;
        green = 0;
        blue = 0;
        reds = 0;
        greens = 0;
        blues = 0;
        pixelCount = 0;
    }

    public int getId() {
        return this.id;
    }

    public int getRGB()
    {
        int r = reds / pixelCount;
        int g = greens / pixelCount;
        int b = blues / pixelCount;
        return 0xff000000|r<<16|g<<8|b;
    }

    public void addPixel(int color)
    {
        int r = color>>16&0x000000FF;
        int g = color>> 8&0x000000FF;
        int b = color>> 0&0x000000FF;
        reds+=r;
        greens+=g;
        blues+=b;
        pixelCount++;
        red   = reds/pixelCount;
        green = greens/pixelCount;
        blue  = blues/pixelCount;
    }

    public void removePixel(int color)
    {
        int r = color>>16&0x000000FF;
        int g = color>> 8&0x000000FF;
        int b = color>> 0&0x000000FF;
        reds-=r;
        greens-=g;
        blues-=b;
        pixelCount--;
        red   = reds/pixelCount;
        green = greens/pixelCount;
        blue  = blues/pixelCount;
    }

    public int distance(int color)
    {
        int r = color>>16&0x000000FF;
        int g = color>> 8&0x000000FF;
        int b = color>> 0&0x000000FF;
        int rx = Math.abs(red-r);
        int gx = Math.abs(green-g);
        int bx = Math.abs(blue-b);
        int d = (rx+gx+bx) / 3;
        return d;
    }





}
