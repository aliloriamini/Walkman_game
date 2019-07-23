package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by amini on 06/26/2017.
 */

public class WalkManFrame extends JFrame {

    public WalkManFrame() {


        super("WalkMan");


        // Sets the close button to exit the program
        // setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //  Escape should close the window
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancel");
        am.put("cancel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //make game palyabel
        if (WalkManGame.MAX_LIFE_TIME != 0 ){
            WalkManGame.MAX_LIFE_TIME = WalkManGame.MAX_LIFE_TIME_save;}

        WalkManGame.WIN = false;
        // makes the window not able to be resized
        //setResizable(false);

        //center frame
        setSize(WalkManPanel.WIDTH, WalkManPanel.HEIGHT );
        //setLocationRelativeTo(null);
        //setUndecorated(true);

        // creates the window
        pack();


        // creates the panel
        WalkManPanel p = new WalkManPanel();

        // gets the frames insets
        Insets frameInsets = getInsets();
        // calculates panel size
        int frameWidth = p.getWidth()
                + (frameInsets.left + frameInsets.right);
        int frameHeight = p.getHeight()
                + (frameInsets.top + frameInsets.bottom);
        // sets the frame's size
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        // turns off the layout options
        setLayout(null);
        // adds the panel to the frame
        add(p);
        // adjusts the window to meet its new preferred size
        pack();
        // shows the frame
        setVisible(true);

    }
}