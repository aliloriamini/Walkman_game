package panels;

import Objects.WalkManPanel;
import UI.PlaceholderTextField;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import javax.swing.*;

/**
 * Created by amini on 06/26/2017.
 */

public class GameOver extends JPanel {
    public static PlaceholderTextField usernameTextArea;
    int index;

    public GameOver(int index) {
        this.setOpaque(false);
        this.setVisible(true);
        this.setLayout((LayoutManager) null);
        this.index = index;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        Composite old = g2d.getComposite();
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5F));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setComposite(old);
        g.setColor(Color.black);
        g.setFont(new Font("Consolas", 1, 80));
        g.drawString("GAME OVER ", WalkManPanel.WIDTH / 2 - 220, WalkManPanel.HEIGHT / 2 - 40);
    }

}