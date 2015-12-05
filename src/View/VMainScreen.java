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
    private JLabel rangeLabel;
    private JLabel modeLabel;
    public JTextArea rangeText;
    public JTextArea modeText;

    public VMainScreen() throws IOException
    {
            setSize(1280, 720);
            setTitle("PSOSegmentation");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setJMenuBar(getJMenuBar());
            add(addStartButton());
            addLabel();
            addText();
            setLocationRelativeTo(null);
            setResizable(false);
            setLayout(null);
            setVisible(true);

    }

    private JButton addStartButton()
    {
        this.startButton = new JButton("Start");
        this.startButton.setBounds(50,50,180,30);
        this.startButton.setFocusPainted(Boolean.parseBoolean(null));
        this.startButton.addActionListener(new CMainAlgorithm());
        return this.startButton;
    }

    private void addLabel()
    {
        this.rangeLabel = new JLabel("Colour range: ");
        this.rangeLabel.setBounds(50,85,100,30);
        add(this.rangeLabel);
        this.modeLabel = new JLabel("Mode: ");
        this.modeLabel.setBounds(50,120,100,30);
        add(this.modeLabel);
    }



    private void addText()
    {
        this.rangeText = new JTextArea();
        this.rangeText.setBounds(155,85,100,20);
        add(this.rangeText);
        this.modeText = new JTextArea();
        this.modeText.setBounds(155,120,100,20);
        add(this.modeText);
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