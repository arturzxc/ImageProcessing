package imageprocessing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import utils.ResourceLoader;

/**
 *
 * @author arturzxc
 */
public class ImageProcessing extends JFrame {

    private static boolean running = true;
    private JMenuItem avgMask;
    private BufferedImage buffImg;
    private JMenu file;
    private JMenuItem invert;
    private JMenuItem lap4_1;
    private JMenuItem lap4_2;
    private JMenuItem lap8_1;
    private JMenuItem lap8_2;
    private JMenuItem lap_boosted;
    private JMenuItem max;
    private JMenuItem median;
    private JMenuItem mid;
    private JMenuItem min;
    private JMenuItem sharp;
    private JMenuItem shiftValues;
    private JMenu simple;
    private JMenuItem undo;
    private BufferedImage undoImg;
    private JPanel drawingPanel;

    public static void main(String[] args) throws IOException {
        ImageProcessing im = new ImageProcessing();


        while (running) {
            im.render();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private JMenu filters;
    private JMenuBar mBar;
    private MaskApplier maskApplier;
    private JMenuItem weightedAvgMask;

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public void setBuffImg(BufferedImage buffImg) {
        this.buffImg = buffImg;
    }

    private void addMenuBar() {
        mBar = new JMenuBar();

        file = new JMenu("File");
        simple = new JMenu("Basic");
        shiftValues = new JMenuItem("Shift values by 20");
        invert = new JMenuItem("Invert");

        filters = new JMenu("Filtering");
        undo = new JMenuItem("Undo");
        sharp = new JMenuItem("Sharpen");
        avgMask = new JMenuItem("Avg. Mask");
        weightedAvgMask = new JMenuItem("Weighted Avg Mask");
        median = new JMenuItem("Median");
        min = new JMenuItem("Min");
        max = new JMenuItem("Max");
        mid = new JMenuItem("Mid");
        lap4_1 = new JMenuItem("Lap4_1");
        lap4_2 = new JMenuItem("Lap4_2");
        lap8_1 = new JMenuItem("Lap8_1");
        lap8_2 = new JMenuItem("Lap8_2");
        lap_boosted = new JMenuItem("Lap Boosted");

        mBar.add(file);
        mBar.add(simple);
        mBar.add(filters);

        file.add(undo);

        simple.add(shiftValues);
        simple.add(invert);

        filters.add(sharp);
        filters.add(avgMask);
        filters.add(weightedAvgMask);
        filters.add(median);
        filters.add(min);
        filters.add(max);
        filters.add(mid);
        filters.add(lap4_1);
        filters.add(lap4_2);
        filters.add(lap8_1);
        filters.add(lap8_2);
        filters.add(lap_boosted);

        setJMenuBar(mBar);
    }

    private void addMenuBarListeners() {
        sharp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.applySharpMask();
            }
        });
        avgMask.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.applyAvgMask();
            }
        });

        weightedAvgMask.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.applyWeightedAvgMask();
            }
        });

        undo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoImg != null) {
                    buffImg = undoImg;
                    undoImg = null;
                    maskApplier.setBuffImg(buffImg);
                } else {
                    JOptionPane.showMessageDialog(drawingPanel, "Nothing to undo!");
                }
            }
        });

        shiftValues.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.apply20Brightness();

            }
        });

        invert.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.invert();

            }
        });


        median.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.median();

            }
        });

        min.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.min();

            }
        });

        max.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.max();

            }
        });

        mid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.mid();

            }
        });

        lap4_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.lab4_1();

            }
        });

        lap4_2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.lab4_2();

            }
        });

        lap8_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.lab8_1();

            }
        });

        lap8_2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                buffImg = maskApplier.lab8_2();

            }
        });

        lap_boosted.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                undoImg = buffImg;
                maskApplier.setBuffImg(buffImg);
                String booststr = JOptionPane.showInputDialog("How much boost to use? default is 1");
                float boost = 1;
                try {
                    boost = Float.parseFloat(booststr);
                } catch (Exception exboost) {
                    JOptionPane.showMessageDialog(null, "The number must be float!");
                }
                buffImg = maskApplier.lap_boosted(boost);

            }
        });

    }

    public ImageProcessing() throws IOException {
        super("Image processing");

        String imgName = JOptionPane.showInputDialog("Enter name of image to load "
                + "or leave blank. Available options are: dots.png, "
                + "image.jpg, log.jpg, noise.jpg, pow.jpg, test.jpg"
                + "and wood.jpg");

        buffImg = ResourceLoader.loadBufferedImage(imgName);
        if (buffImg == null) {
            buffImg = ResourceLoader.loadBufferedImage("test.jpg");
        }

        maskApplier = new MaskApplier();
        drawingPanel = new DrawingPanel();
        setContentPane(drawingPanel);
        addMenuBar();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(buffImg.getWidth() + 10, buffImg.getHeight() + 70);
        addMenuBarListeners();
        setVisible(true);
    }

    public boolean toggleRunning() {
        running = !running;
        return running;
    }

    public void render() {
        drawingPanel.repaint();
    }

    private class DrawingPanel extends JPanel {

        public DrawingPanel() {
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(buffImg, null, 0, 0);

        }
    }
}
