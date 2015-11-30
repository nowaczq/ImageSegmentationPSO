package View;

import Controler.CMainAlgorithm;

import javax.swing.*;

/**
 * Created by dom on 2015-10-28.
 */
public class VMainScreen extends JFrame
{
    public String fileName="obraz.jpg";
    private JTextArea textArea;
    public JMenu optionsMenu = new JMenu("Options");
    public JMenuItem exit = new JMenuItem("Exit");
    public JMenuItem fileChooser = new JMenuItem("Load file");
    public JMenuItem help = new JMenuItem("Help");
    public JMenuItem fileSaver = new JMenuItem("Save file");

    public VMainScreen()
    {
            setSize(1280, 720);
            setTitle("PSOSegmentation");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setJMenuBar(getJMenuBar());
            setLocationRelativeTo(null);
            setResizable(false);
            setLayout(null);
            setVisible(true);

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
        //return this.fileName;
        return this.fileName;
    }

 
}
