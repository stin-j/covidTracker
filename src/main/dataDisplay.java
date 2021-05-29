package covidTracker;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class dataDisplay extends JPanel {
    private final JLabel status;

    public dataDisplay(JLabel status) {

        this.status = status;
        initDisplay();
    }
    private void initDisplay() {
    	setPreferredSize(new Dimension(600, 500)); //sets size of display screen
       // var path = "src/resources/" + i + ".png";
        //img[i] = (new ImageIcon(path)).getImage();

        addMouseListener(new ClickAdapter());
        //newDisplay();
    }
    private class ClickAdapter extends MouseAdapter {
    	 @Override
         public void mousePressed(MouseEvent e) {
    		  int x = e.getX();
              int y = e.getY();
              status.setText(Integer.toString(x)+", "+Integer.toString(y));
    	 }
    }

}
