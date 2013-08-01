/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author arturzxc
 */
public class MaskApplier {

    private BufferedImage buffImg;
    private int redMax = 0;
    private int redMin = 255;

    public MaskApplier() {
    }

    public void setBuffImg(BufferedImage buffImg) {
        this.buffImg = buffImg;
        findAndSetMinMaxValuesRGB(buffImg);
        System.out.println("MAX RED: " + redMax);


    }

    private void findAndSetMinMaxValuesRGB(BufferedImage buffImg) {
        for (int currentImageX = 0; currentImageX < buffImg.getWidth(); currentImageX++) {
            for (int currentImageY = 0; currentImageY < buffImg.getHeight(); currentImageY++) {

                int rgb = buffImg.getRGB(currentImageX, currentImageY);
                Color color = new Color(rgb);

                if (color.getRed() > redMax) {
                    redMax = color.getRed();
                } else if (color.getRed() < redMin) {
                    redMin = color.getRed();
                }

            }
        }
    }

    private int scaleVals(float val, int min, int max) {


        if (val > 255) {
            val = max;

        } else if (val < 0) {
            val = min;

        }

        return (int) val;


    }

    private BufferedImage applyMaskAndReturnImg(float[][] mask, float scalar) {

        int imgW = buffImg.getWidth();
        int imgH = buffImg.getHeight();
        BufferedImage tempBufferedImage = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_RGB);

        for (int currentImageX = 1; currentImageX < imgW - 1; currentImageX++) {
            for (int currentImageY = 1; currentImageY < imgH - 1; currentImageY++) {


                float red = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {

                        int rgb = buffImg.getRGB(x + currentImageX, y + currentImageY);
                        Color color = new Color(rgb);

                        red += color.getRed() * mask[x + 1][y + 1];


                    }
                }

                Color finalColor = new Color(
                        scaleVals(red / scalar, redMin, redMax),
                        scaleVals(red / scalar, redMin, redMax),
                        scaleVals(red / scalar, redMin, redMax));

                tempBufferedImage.setRGB(currentImageX, currentImageY, finalColor.getRGB());

            }
        }

        return tempBufferedImage;

    }

    public BufferedImage applySharpMask() {

        float[][] mask = {
            {0.0f, -0.25f, 0.0f},
            {-0.25f, 2.0f, -0.25f},
            {0.0f, -0.25f, 0.0f}
        };

        return applyMaskAndReturnImg(mask, 1);


    }

    public BufferedImage applyAvgMask() {


        float[][] mask = {
            {1.0f, 1.0f, 1.0f},
            {1.0f, 1.0f, 1.0f},
            {1.0f, 1.0f, 1.0f}
        };

        return applyMaskAndReturnImg(mask, 9);

    }

    public BufferedImage applyWeightedAvgMask() {

        float[][] mask = {
            {1, 2, 1},
            {2, 4, 2},
            {1, 2, 1}
        };


        return applyMaskAndReturnImg(mask, 16);

    }

    public BufferedImage apply20Brightness() {

        BufferedImage bimg = new BufferedImage(
                buffImg.getWidth(),
                buffImg.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (int currentImageX = 0; currentImageX < buffImg.getWidth(); currentImageX++) {
            for (int currentImageY = 0; currentImageY < buffImg.getHeight(); currentImageY++) {

                int rgb = buffImg.getRGB(currentImageX, currentImageY);
                Color color = new Color(rgb);
                int red;
                red = color.getRed() + 20;

                color = new Color(
                        scaleVals(red, redMin, redMax),
                        scaleVals(red, redMin, redMax),
                        scaleVals(red, redMin, redMax));



                bimg.setRGB(currentImageX, currentImageY, color.getRGB());
            }
        }
        return bimg;

    }

    public BufferedImage median() {

        BufferedImage bimg = new BufferedImage(
                buffImg.getWidth(),
                buffImg.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (int currentImageX = 1; currentImageX < buffImg.getWidth() - 1; currentImageX++) {
            for (int currentImageY = 1; currentImageY < buffImg.getHeight() - 1; currentImageY++) {

                ArrayList<Integer> vals = new ArrayList<>();

                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {

                        int rgb = buffImg.getRGB(x + currentImageX, y + currentImageY);
                        Color color = new Color(rgb);

                        vals.add(color.getRed());
                    }
                }

                Collections.sort(vals);

                Color finalColor = new Color(
                        scaleVals(vals.get(4), redMin, redMax),
                        scaleVals(vals.get(4), redMin, redMax),
                        scaleVals(vals.get(4), redMin, redMax));

                bimg.setRGB(currentImageX, currentImageY, finalColor.getRGB());
            }
        }
        return bimg;

    }

    BufferedImage min() {
        BufferedImage bimg = new BufferedImage(
                buffImg.getWidth(),
                buffImg.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (int currentImageX = 1; currentImageX < buffImg.getWidth() - 1; currentImageX++) {
            for (int currentImageY = 1; currentImageY < buffImg.getHeight() - 1; currentImageY++) {

                int min = 255;

                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {

                        int rgb = buffImg.getRGB(x + currentImageX, y + currentImageY);
                        Color color = new Color(rgb);
                        if (color.getRed() < min) {
                            min = color.getRed();
                        }

                    }
                }


                Color finalColor = new Color(
                        scaleVals(min, redMin, redMax),
                        scaleVals(min, redMin, redMax),
                        scaleVals(min, redMin, redMax));

                bimg.setRGB(currentImageX, currentImageY, finalColor.getRGB());
            }
        }
        return bimg;
    }

    BufferedImage max() {
        BufferedImage bimg = new BufferedImage(
                buffImg.getWidth(),
                buffImg.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (int currentImageX = 1; currentImageX < buffImg.getWidth() - 1; currentImageX++) {
            for (int currentImageY = 1; currentImageY < buffImg.getHeight() - 1; currentImageY++) {

                int max = 0;

                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {

                        int rgb = buffImg.getRGB(x + currentImageX, y + currentImageY);
                        Color color = new Color(rgb);
                        if (color.getRed() > max) {
                            max = color.getRed();
                        }

                    }
                }


                Color finalColor = new Color(
                        scaleVals(max, redMin, redMax),
                        scaleVals(max, redMin, redMax),
                        scaleVals(max, redMin, redMax));

                bimg.setRGB(currentImageX, currentImageY, finalColor.getRGB());
            }
        }
        return bimg;
    }

    BufferedImage mid() {

        BufferedImage bimg = new BufferedImage(
                buffImg.getWidth(),
                buffImg.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (int currentImageX = 1; currentImageX < buffImg.getWidth() - 1; currentImageX++) {
            for (int currentImageY = 1; currentImageY < buffImg.getHeight() - 1; currentImageY++) {

                int max = 0;
                int min = 255;

                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {

                        int rgb = buffImg.getRGB(x + currentImageX, y + currentImageY);
                        Color color = new Color(rgb);

                        if (color.getRed() < min) {
                            min = color.getRed();
                        }
                        if (color.getRed() > max) {
                            max = color.getRed();
                        }


                    }
                }


                Color finalColor = new Color(
                        scaleVals((max + min) / 2, redMin, redMax),
                        scaleVals((max + min) / 2, redMin, redMax),
                        scaleVals((max + min) / 2, redMin, redMax));

                bimg.setRGB(currentImageX, currentImageY, finalColor.getRGB());
            }
        }
        return bimg;
    }

    BufferedImage invert() {
        BufferedImage bimg = new BufferedImage(
                buffImg.getWidth(),
                buffImg.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        for (int currentImageX = 0; currentImageX < buffImg.getWidth(); currentImageX++) {
            for (int currentImageY = 0; currentImageY < buffImg.getHeight(); currentImageY++) {


                int rgb = buffImg.getRGB(currentImageX, currentImageY);
                Color color = new Color(rgb);
                int red = color.getRed();




                Color finalColor = new Color(
                        scaleVals(255 - red, redMin, redMax),
                        scaleVals(255 - red, redMin, redMax),
                        scaleVals(255 - red, redMin, redMax));


                bimg.setRGB(currentImageX, currentImageY, finalColor.getRGB());
            }
        }
        return bimg;
    }

    public BufferedImage lab4_1() {

        float[][] mask = {
            {0.0f, 1.0f, 0.0f},
            {1.0f, -4.0f, 1.0f},
            {0.0f, 1.0f, 0.0f}
        };

        return applyMaskAndReturnImg(mask, 1);

    }

    public BufferedImage lab4_2() {

        float[][] mask = {
            {0.0f, -1.0f, 0.0f},
            {-1.0f, 4.0f, -1.0f},
            {0.0f, -1.0f, 0.0f}
        };

        return applyMaskAndReturnImg(mask, 1);

    }

    public BufferedImage lab8_1() {

        float[][] mask = {
            {1.0f, 1.0f, 1.0f},
            {1.0f, -8.0f, 1.0f},
            {1.0f, 1.0f, 1.0f}
        };

        return applyMaskAndReturnImg(mask, 1);

    }

    public BufferedImage lab8_2() {

        float[][] mask = {
            {-1.0f, -1.0f, -1.0f},
            {-1.0f, 8.0f, -1.0f},
            {-1.0f, -1.0f, -1.0f}
        };

        return applyMaskAndReturnImg(mask, 1);

    }

    public BufferedImage lap_boosted(float boost) {

        float[][] mask = {
            {0.0f, -1.0f, 0.0f},
            {-1.0f, boost + 4.0f, -1.0f},
            {0.0f, -1.0f, 0.0f}
        };

        return applyMaskAndReturnImg(mask, 1);

    }
}
