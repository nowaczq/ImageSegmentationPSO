package View;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by dom on 2015-10-28.
 */
public class VRawImage extends JFrame
{
    BufferedImage img = null;

    public VRawImage(BufferedImage i)
    {
        this.img = i;
        ImageIcon imageIcon = new ImageIcon(this.img); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(500,500,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        setSize(500, 500);
        setTitle("RawImage");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel p =new Panel();
        add(p);
        JLabel l =new JLabel(imageIcon);
        p.add(l);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }
}
