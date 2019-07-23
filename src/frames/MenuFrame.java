package frames;

import Objects.CarLane;
import Objects.WalkManFrame;
import Objects.Man;
import Objects.SaveHelper;
import Objects.WalkManGame;
import Objects.WalkManPanel;
import Save.GameSave;

import Save.VideoSaver;
import UI.CustomizedButtonUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by amini on 06/26/2017.
 */
public class MenuFrame implements ActionListener {

    private JPanel subPanel;
    private JFrame frame;
    private JButton Continue;
    private JButton multi_player;
    private JButton video;
    private JButton start;
    private JButton score;
    private JButton setting;
    private JButton exit;
    public static Image backgroundIMG;
    public static int width = 500;
    public static int height = 510;

    public void MenuFrames(String title) {
        subPanel = new JPanel();
        frame = new JFrame("Game_menu");
//        frame.setLocationRelativeTo(null);
//        frame.setUndecorated(true);
//        frame.setResizable(false);

        try {
            this.backgroundIMG = ImageIO.read(new File("src/textures/background.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Border emptyBorder = BorderFactory.createEmptyBorder();

        int titleWidth = 320;
        int titleHeight = 55;
        int titleX = 90;
        int titleY = 30;
        int titleSize = 40;

        JLabel gameTitle = new JLabel("Walk Man");
        gameTitle.setBounds(0, 0, titleWidth, titleHeight);
        gameTitle.setFont(new Font("Ravie", Font.BOLD, titleSize));
        gameTitle.setForeground(new Color(29, 235, 218));
        gameTitle.setLocation(titleX, titleY);
        subPanel.add(gameTitle);

        JLabel gameTitleShadow = new JLabel("Walk Man");
        gameTitleShadow.setBounds(0, 0, titleWidth, titleHeight);
        gameTitleShadow.setFont(new Font("Ravie", Font.BOLD, titleSize));
        gameTitleShadow.setForeground(new Color(68, 71, 140));
        gameTitleShadow.setBackground(Color.black);
        gameTitleShadow.setLocation(titleX + 2, titleY + 2);
        subPanel.add(gameTitleShadow);

        Color normalColor = new Color(102, 234, 170);
        Color hoverColor = new Color(65, 178, 242);
        Color pressedColor = new Color(25, 92, 130);
        Color fontColor = new Color(105, 30, 189);
        String fontName = "Snap ITC";
        int fontSizeButtons = 25;
        int buttonPositionX = 130;
        int buttonPositionY = 120;
        int verticalDistance = 50;

        this.start = new JButton("Start");
        this.start.addActionListener(this);
        this.start.setBorder(emptyBorder);
        this.start.setUI(new CustomizedButtonUI(normalColor, hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.start.setBounds(buttonPositionX, buttonPositionY, 190, 40);
        subPanel.add(this.start);

        this.Continue = new JButton("Continue");
        this.Continue.addActionListener(this);
        this.Continue.setBorder(emptyBorder);
        this.Continue.setUI(new CustomizedButtonUI(normalColor, hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.Continue.setBounds(buttonPositionX, buttonPositionY + verticalDistance, 190, 40);
        subPanel.add(this.Continue);

        this.multi_player = new JButton("multi_player");
        this.multi_player.addActionListener(this);
        this.multi_player.setBorder(emptyBorder);
        this.multi_player.setUI(new CustomizedButtonUI(normalColor, hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.multi_player.setBounds(buttonPositionX, buttonPositionY + verticalDistance * 2, 190, 40);
        subPanel.add(this.multi_player);

        this.video = new JButton("video");
        this.video.addActionListener(this);
        this.video.setBorder(emptyBorder);
        this.video.setUI(new CustomizedButtonUI(normalColor, hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.video.setBounds(buttonPositionX, buttonPositionY + verticalDistance * 3, 190, 40);
        subPanel.add(this.video);

        this.score = new JButton("Scores");
        this.score.addActionListener(this);
        this.score.setBorder(emptyBorder);
        this.score.setUI(new CustomizedButtonUI(normalColor, hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.score.setBounds(buttonPositionX, buttonPositionY + verticalDistance * 4, 190, 40);
        subPanel.add(this.score);

        this.setting = new JButton("Setting");
        this.setting.addActionListener(this);
        this.setting.setBorder(emptyBorder);
        this.setting.setUI(new CustomizedButtonUI(normalColor, hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.setting.setBounds(buttonPositionX, buttonPositionY + verticalDistance * 5, 190, 40);
        subPanel.add(this.setting);

        this.exit = new JButton("Exit");
        this.exit.addActionListener(this);
        this.exit.setBorder(emptyBorder);
        this.exit.setUI(new CustomizedButtonUI(normalColor, hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.exit.setBounds(buttonPositionX, buttonPositionY + verticalDistance * 6, 190, 40);
        subPanel.add(this.exit);

        //Layer
        frame.setLayout(new FlowLayout());
        frame.setContentPane(subPanel);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);

        JLabel background = new JLabel(new ImageIcon(this.backgroundIMG));
        background.setBounds(28, frame.getHeight() - 438, 400, 438);
        background.setBounds(0, 0, width, height);
        subPanel.add(background);
    }

    public void actionPerformed(ActionEvent action) {
        if (action.getSource() == this.exit) {
            System.exit(0);
        } else if (action.getSource() == this.start) {
            //start a new game
            WalkManGame.ManX = WalkManPanel.WIDTH / 2 - 40;
            WalkManGame.ManY = WalkManPanel.line_numbers*40 +75;
            Man.direction = 3;
            WalkManGame.MAX_LIFE_TIME_save = 1000;
            CarLane.LOAD = false;
            WalkManGame.LOAD = false;
            WalkManGame.VIDEO = false;
            CarLane.VIDEO = false;
            WalkManPanel.video = true;
            new WalkManFrame();
            frame.dispose();

        } else if (action.getSource() == this.Continue) {
            // ********************   GameSave   **********************
            GameSave osave = new GameSave();
            Man oman = osave.LoadUser();
            SaveHelper oHelper = osave.LoadUserHelper();
            WalkManGame.ManX = oman.getX();
            WalkManGame.ManY = oman.getY();
            WalkManGame.lives = oHelper.getLife();
            WalkManGame.MAX_LIFE_TIME_save = oHelper.getTime();
            CarLane.LOAD = true;
            WalkManGame.LOAD = true;
            WalkManGame.VIDEO = false;
            CarLane.VIDEO = false;
            WalkManPanel.video = false;
            Man.direction = oman.getDirection();
            WalkManPanel.line_numbers = osave.LoadLine();
            new WalkManFrame();
            frame.dispose();

        } else if (action.getSource() == this.multi_player) {

            WalkManGame.ManX = WalkManPanel.WIDTH / 2 - 40;
            WalkManGame.ManY = WalkManPanel.line_numbers*40 +75;
            WalkManGame.multiplayer = true;
            CarLane.LOAD = false;
            WalkManGame.LOAD = false;
            WalkManGame.VIDEO = false;
            CarLane.VIDEO = false;
            WalkManPanel.video = false;
            new WalkManFrame();
            frame.dispose();

        } else if (action.getSource() == this.video) {
            VideoSaver osaver = new VideoSaver("video");
            WalkManPanel.line_numbers = osaver.LoadLine() ;
            CarLane.LOAD = false;
            WalkManGame.LOAD = false;
            WalkManGame.VIDEO = true;
            CarLane.VIDEO = true;
            WalkManPanel.video = false;
            new WalkManFrame();
            frame.dispose();

        } else if (action.getSource() == setting) {
            setting os = new setting();
            os.MenuLauncher();
            frame.dispose();

        } else if (action.getSource() == score) {

            ScoreWindow sWindow = new ScoreWindow("Scores");
            sWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sWindow.setVisible(true);
            frame.dispose();
        }
    }

}
