/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.GridBagConstraints;

/**
 *
 * @author Artur Krzynowek
 */
public class LayoutHelper {

    /**
     * Sets up GridBag constraints for MenuContentPane.
     */
    public static void setUpConstraints(GridBagConstraints cons) {

        cons.anchor = GridBagConstraints.NORTHWEST;
        cons.fill = GridBagConstraints.BOTH;
        cons.weightx = 1;
        cons.weighty = 1;
        cons.gridheight = 1;
        cons.gridwidth = 1;

    }

    /**
     * This method is used to modify GridBagConstraints for MenuContentPane.
     */
    public static void changeConstraints(GridBagConstraints cons,
            int gridx, int gridy,
            double weightx, double weighty,
            int gridwidth, int gridheight) {
        cons.gridx = gridx;
        cons.gridy = gridy;
        cons.weightx = weightx;
        cons.weighty = weighty;
        cons.gridheight = gridheight;
        cons.gridwidth = gridwidth;
    }
}
