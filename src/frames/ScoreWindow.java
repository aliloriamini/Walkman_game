package frames;

import UI.CustomizedButtonUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by amini on 06/26/2017.
 */

public class ScoreWindow extends JFrame {
    public int width = 660;
    public int height = 600;

    public ScoreWindow(String title) {
        super(title);
        setSize(width, height);
        setUndecorated(true);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);

        //Escape should close the window
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancel");
        am.put("cancel", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JLayeredPane lpane = new JLayeredPane();
        setLayout(new BorderLayout());
        add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, width, height);
        ScorePanel cp = new ScorePanel();
        cp.setBounds(0, 0, width, height);
        add(cp);
        lpane.add(cp, new Integer(0), 0);
    }

    public static class ScorePanel extends JPanel implements ActionListener {

        Image WalkManLogo;
        Image WalkManavatar;
        Image[] allImages;
        private JButton back;

        public ScorePanel() {
            setOpaque(false);
            setLayout(null);

            try {
                WalkManLogo = ImageIO.read(new File("src/textures/WalkMan_logo.jpg")).getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                WalkManavatar = ImageIO.read(new File("src/textures/WalkMan_avatar.jpg")).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                allImages = new Image[5];
                for (int i = 0; i < 5; i++) {
                    Image img = WalkManavatar;
                    allImages[i] = img;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            JLabel team = new JLabel("High Scores", JLabel.CENTER);
            team.setFont(new Font("Ravie", Font.BOLD, 32));
            team.setBounds(0, 0, 500, 40);
            team.setLocation(640 / 2 - team.getWidth() / 2 + 50, 45);
            team.setForeground(new Color(25, 92, 130));
            add(team);

            JLabel shadow = new JLabel("High Scores", JLabel.CENTER);
            shadow.setFont(new Font("Ravie", Font.BOLD, 32));
            shadow.setBounds(0, 0, 500, 40);
            shadow.setLocation(640 / 2 - team.getWidth() / 2 + 50 + 2, 45 + 2);
            shadow.setForeground(new Color(105, 30, 189));
            add(shadow);

            ArrayList<String> allScores = new ArrayList<>();
            ArrayList<String> allNames = new ArrayList<>();

            try {
                FileReader fr = new FileReader("src/files/scores.txt");
                BufferedReader br = new BufferedReader(fr);
                FileReader frNames = new FileReader("src/files/names.txt");
                BufferedReader brNames = new BufferedReader(frNames);
                for (int i = 0; i < 5; i++) {
                    allScores.add(br.readLine());
                }

                for (int i = 0; i < 5; i++) {
                    allNames.add(brNames.readLine());
                }

                frNames.close();
                brNames.close();
                br.close();
                fr.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            ArrayList<JLabel> lables = new ArrayList<>();
            lables.add(new JLabel(allNames.get(0), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(1), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(2), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(3), JLabel.LEFT));
            lables.add(new JLabel(allNames.get(4), JLabel.LEFT));

            lables.add(new JLabel(allScores.get(0), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(1), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(2), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(3), JLabel.LEFT));
            lables.add(new JLabel(allScores.get(4), JLabel.LEFT));


            for (int i = 0; i < lables.size(); i++) {
                JLabel jl = lables.get(i);
                int X = 140;
                int Y = (198 + i * 70);
                jl.setFont(new Font("Snap ITC", Font.BOLD, 25));
                jl.setForeground(new Color(105, 30, 189));
                if (i > 4) {
                    X = 400;
                    Y = (198 + (i - 5) * 70);
                    jl.setFont(new Font("Snap ITC", Font.PLAIN, 24));
                    jl.setForeground(new Color(70, 70, 70));
                }


                jl.setBounds(0, 0, 400, 22);
                jl.setLocation(X, Y);

                add(jl);
            }


            back = new JButton("Exit");
            back.addActionListener(this);
            back.setBorder(BorderFactory.createEmptyBorder());
            back.setUI(new CustomizedButtonUI(new Color(102, 234, 170),
                    new Color(65,178,242), new Color(25, 92, 130),
                    new Font("Snap ITC", Font.PLAIN, 25), new Color(105, 30, 189)));
            back.setBounds(0, 0, 120, 40);
            back.setLocation(640 / 2 - 120 / 2, 550);
            add(back);

        }

        public void actionPerformed(ActionEvent action) {
            if (action.getSource() == back) {
                  System.exit(0);
//                MenuFrame oMenu = new MenuFrame();
//                oMenu.MenuFrames("walkman");
//                SwingUtilities.getWindowAncestor(this).dispose();
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(WalkManLogo, (getWidth() - WalkManLogo.getWidth(this)) / 2 - 135, 25, this);

            for (int i = 0; i < 5; i++) {
                g2.drawImage(allImages[i], 60, (180 + i * 70), 50, 50, this);
            }

        }

    }
}
