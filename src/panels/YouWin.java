package panels;

import Objects.WalkManPanel;
import UI.CustomizedButtonUI;
import UI.PlaceholderTextField;
import frames.ScoreWindow;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by amini on 06/26/2017.
 */

public class YouWin extends JPanel implements ActionListener {
    public static PlaceholderTextField usernameTextArea;
    JButton ok;
    Font customFont;
    List<String> allNames = new ArrayList<>();
    int index;
    BufferedImage speaker;

    public YouWin(boolean showUserInput, int index) {
        this.setOpaque(false);
        this.setVisible(true);
        this.setLayout((LayoutManager) null);
        this.index = index;
        try {
            speaker = ImageIO.read((new File("src/textures/speaker.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (showUserInput) {
            usernameTextArea = new PlaceholderTextField();
            usernameTextArea.setBounds(0, 0, 270, 35);
            usernameTextArea.setFont(new Font("Consolas", 1, 30));
            usernameTextArea.setLocation(WalkManPanel.WIDTH / 2 - usernameTextArea.getWidth() / 2, WalkManPanel.HEIGHT / 2 - usernameTextArea.getHeight() / 2 + 40);
            usernameTextArea.setBackground(new Color(25, 92, 130));

            usernameTextArea.setForeground(Color.white);
            usernameTextArea.setBorder(BorderFactory.createEmptyBorder());
            usernameTextArea.setPlaceholder("Winner name ;)");
            usernameTextArea.setHorizontalAlignment(0);
            usernameTextArea.setAlignmentY(0.0F);
            this.add(usernameTextArea);

            try {
                this.customFont = Font.createFont(0, new File("src/textures/ka1.ttf")).deriveFont(14.0F);
                GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
                e.registerFont(this.customFont);
            } catch (IOException var3) {
                System.out.println(var3.getMessage());
            } catch (FontFormatException var4) {
                System.out.println(var4.getMessage());
            }

            this.ok = new JButton("OK");
            this.ok.addActionListener(this);
            this.ok.setBorder(BorderFactory.createEmptyBorder());
            this.ok.setUI(new CustomizedButtonUI(new Color(102, 234, 170), new Color(65, 178, 242), new Color(25, 92, 130), new Font("Snap ITC", Font.PLAIN, 25), new Color(105, 30, 189)));
            this.ok.setBounds(0, 0, 100, 40);
            this.ok.setLocation(WalkManPanel.WIDTH / 2 - this.ok.getWidth() / 2, WalkManPanel.HEIGHT / 2 - this.ok.getHeight() / 2 + 150);
            this.add(this.ok);
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        Composite old = g2d.getComposite();
        g2d.setColor(Color.white);
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5F));
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setComposite(old);
        g.setColor(Color.black);
        g.setFont(new Font("Consolas", 1, 80));
        g.drawString("WIN !!!!", WalkManPanel.WIDTH / 2 - 180, WalkManPanel.HEIGHT / 2 - 40);
        g.drawImage(speaker, WalkManPanel.WIDTH / 2 - 300, WalkManPanel.HEIGHT / 2 - 120, null);
    }

    public void actionPerformed(ActionEvent action) {
        String name = usernameTextArea.getText();
        if ("".equals(name)) {
            name = "No name";
        }

        try {
            FileReader fReader = new FileReader("src/files/names.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            for (int i = 0; i < 5; i++) {
                this.allNames.add(bReader.readLine());
            }
            this.allNames.add(index, name);
            bReader.close();
            fReader.close();

            FileWriter fileWriter = new FileWriter("src/files/names.txt");
            PrintWriter write = new PrintWriter(fileWriter);
            for (int i = 0; i < 5; i++) {
                write.printf("%s%n", this.allNames.get(i));
            }
            fileWriter.close();
            write.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        SwingUtilities.getWindowAncestor(this).dispose();
        ScoreWindow sWindow = new ScoreWindow("Scores");
        sWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sWindow.setVisible(true);
    }
}