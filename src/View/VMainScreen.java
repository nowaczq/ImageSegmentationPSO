package View;

import Controler.CMainAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by dom on 2015-10-28.
 */
public class VMainScreen extends JFrame
{
    public String fileName="obraz.jpg";
    public JMenu optionsMenu = new JMenu("Options");
    public JMenuItem exit = new JMenuItem("Exit");
    public JMenuItem fileChooser = new JMenuItem("Load file");
    public JMenuItem help = new JMenuItem("Help");
    public JMenuItem fileSaver = new JMenuItem("Save file");
    public JButton startButton;

    public VMainScreen() throws IOException
    {
            setSize(1280, 720);
            setTitle("PSOSegmentation");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setJMenuBar(getJMenuBar());
            //addStartButton();
            add(addStartButton());
            setLocationRelativeTo(null);
            setResizable(false);
            setLayout(null);
            setVisible(true);

    }

    private JButton addStartButton()
    {
        this.startButton = new JButton("Start");
        this.startButton.setBounds(50,50,180,30);
        this.startButton.setBackground(Color.green);
        this.startButton.addActionListener(new CMainAlgorithm());
        return this.startButton;
    }

    @Override
    public JMenuBar getJMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(null);
        this.optionsMenu.add(this.fileChooser);
        this.optionsMenu.add(this.fileSaver);
        this.optionsMenu.add(this.help);
        this.optionsMenu.add(this.exit);
        this.fileSaver.addActionListener(new CMainAlgorithm());
        this.exit.addActionListener(new CMainAlgorithm());
        this.fileChooser.addActionListener(new CMainAlgorithm());
        this.help.addActionListener(new CMainAlgorithm());
        menuBar.add(this.optionsMenu);
        return menuBar;
    }

    public String getFileName()
    {
        return this.fileName;
    }


}