package frames;

import Objects.WalkManGame;
import UI.CustomizedButtonUI;
import Objects.WalkManPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Created by amini on 06/26/2017.
 */


public class setting implements ActionListener {

    public  static JPanel subPanel;
    private JButton map;
    private JButton back;
    private JButton manMaker;
    private Tbox line_number;
    private Tbox game_speed;
    private Tbox game_moves;
    private Tbox game_img;
    private Tbox multi_num;
    private Tbox lives;
    private JFrame frame;
    public err erros;


    public void MenuLauncher() {

        subPanel = new JPanel();

        frame = new JFrame("Game_setting");

        Color normalColor = new Color(102, 234, 170);
        Color hoverColor = new Color (65,178,242);
        Color pressedColor = new Color(25, 92, 130);
        Color fontColor = new Color(105, 30, 189);
        String fontName = "Snap ITC";
        int fontSizeButtons = 20;
        int buttonPositionX = 10;
        int buttonPositionY = 370;
        int horizentalDistance = 150;

        this.back = new JButton("back");
        this.back.addActionListener(this);
        this.back.setUI(new CustomizedButtonUI(normalColor,hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.back.setBounds(buttonPositionX, buttonPositionY, 140, 30);
        subPanel.add(this.back);

        this.manMaker = new JButton("make man");
        this.manMaker.addActionListener(this);
        this.manMaker.setUI(new CustomizedButtonUI(normalColor,hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.manMaker.setBounds(buttonPositionX + horizentalDistance, buttonPositionY, 140, 30);
        subPanel.add(this.manMaker);

        this.map = new JButton("make map");
        this.map.addActionListener(this);
        this.map.setUI(new CustomizedButtonUI(normalColor,hoverColor, pressedColor,
                new Font(fontName, Font.PLAIN, fontSizeButtons), fontColor));
        this.map.setBounds(buttonPositionX + horizentalDistance*2, buttonPositionY, 140, 30);
        subPanel.add(this.map);

        frame.setLayout(new FlowLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //////////////////////////////////////////////////
        erros = new err();

        JLabel label = new JLabel("game setting");
        label.setBounds(20, 20, 600, 35);
        label.setFont(new Font("Bauhaus 93", Font.BOLD, 30));
        label.setForeground(Color.cyan);
        font("line number :",0,0);
        line_number = new  Tbox(0,0,WalkManPanel.line_numbers);
        WalkManPanel.line_numbers =  line_number.num;

        font("game time :", 50,0);
        game_speed = new Tbox(50,0, WalkManGame.MAX_LIFE_TIME);
        WalkManGame.MAX_LIFE_TIME_save = game_speed.num;

        font("game speed :",100,0);
        game_moves = new Tbox(100,0,WalkManPanel.Game_speed);
        WalkManPanel.Game_speed = game_moves.num;

        font("man type :",150,0);
        game_img = new Tbox(150,0,WalkManPanel.Man_type);
        WalkManPanel.Man_type = game_img.num;

        font("multi number :",0,240);
        multi_num = new Tbox(0,240,WalkManGame.num_player);
        WalkManGame.num_player = multi_num.num;

        font("Lives :",50,240);
        lives = new Tbox(50,200,WalkManGame.lives);
        WalkManGame.lives = lives.num;

        subPanel.add(label);

        //////////////////////////////////////////////////

        frame.setContentPane(subPanel);
        frame.setLayout(null);
        frame.setSize(MenuFrame.width,MenuFrame.height);
        frame.setVisible(true);

        JLabel background = new JLabel(new ImageIcon(MenuFrame.backgroundIMG));
        background.setBounds(28, frame.getHeight() - 438, 400, 438);
        background.setBounds(0, 0, MenuFrame.width, MenuFrame.height);
        subPanel.add(background);
    }
    /////////////////////////////////////////////////////////////////

    private void font(String a,int y,int x){
        JLabel start = new JLabel(a);
        start.setBounds(30+x, 100+y, 200, 35);
        start.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        subPanel.add(start);
    }

    public void actionPerformed(ActionEvent action) {
                if (action.getSource() == this.back) {
                    WalkManPanel.line_numbers = line_number.Tbox_check();
                    WalkManGame.MAX_LIFE_TIME_save = game_speed.Tbox_check();
                    WalkManPanel.Game_speed = game_moves.Tbox_check();
                    WalkManPanel.Man_type = game_img.Tbox_check();
                    WalkManGame.num_player = multi_num.Tbox_check();
                    WalkManGame.lives = lives.Tbox_check();
                    if (WalkManPanel.line_numbers>6 || WalkManPanel.line_numbers<1){
                        erros.Terr = "number is to big!";
                        erros.ChTerr();
                        erros.eers.setVisible(true);
                    }else if (WalkManGame.MAX_LIFE_TIME < 500 || WalkManGame.MAX_LIFE_TIME > 2000){
                    erros.Terr = "time is unvalid";
                    erros.ChTerr();
                    erros.eers.setVisible(true);
                    }else if (WalkManGame.lives < 1 || WalkManGame.lives > 5){
                        erros.Terr = "lives is unvalid";
                        erros.ChTerr();
                        erros.eers.setVisible(true);
                    }else if (WalkManGame.num_player < 1 || WalkManGame.num_player > 5){
                        erros.Terr = "number of player is unvalid";
                        erros.ChTerr();
                        erros.eers.setVisible(true);
                    }else if (WalkManPanel.Man_type < 1 || WalkManPanel.Man_type > 3){
                        erros.Terr = "man type is unvalid";
                        erros.ChTerr();
                        erros.eers.setVisible(true);
                    }else if (WalkManGame.MAX_LIFE_TIME < 0 || WalkManGame.MAX_LIFE_TIME > 2000){
                        erros.Terr = "time is unvalid";
                        erros.ChTerr();
                        erros.eers.setVisible(true);
                    }else{
                        WalkManGame.ManY = WalkManPanel.line_numbers*40 + 75;
                        MenuFrame omenu = new MenuFrame();
                        omenu.MenuFrames("walk man");
                        frame.dispose();
                    }

                } else if (action.getSource() == this.manMaker) {
                    WalkManPanel.line_numbers = line_number.Tbox_check();
                    WalkManGame.MAX_LIFE_TIME_save = game_speed.Tbox_check();
                    WalkManPanel.Game_speed = game_moves.Tbox_check();
                    WalkManPanel.Man_type = game_img.Tbox_check();
                    WalkManGame.num_player = multi_num.Tbox_check();
                    WalkManGame.lives = lives.Tbox_check();
                    shape oshape = new shape();
                        oshape.shaper();
                        frame.dispose();
                }else if (action.getSource() == this.map) {
                    WalkManPanel.line_numbers = line_number.Tbox_check();
                    WalkManGame.MAX_LIFE_TIME_save = game_speed.Tbox_check();
                    WalkManPanel.Game_speed = game_moves.Tbox_check();
                    WalkManPanel.Man_type = game_img.Tbox_check();
                    WalkManGame.num_player = multi_num.Tbox_check();
                    WalkManGame.lives = lives.Tbox_check();
                    make_map os = new make_map();
                    frame.dispose();
                }
            }
}
class err extends setting {
        public JLabel eers;
        public String Terr;
        public  err() {
            eers = new JLabel(Terr, SwingConstants.CENTER);
            eers.setBounds(30, 330, 400, 35);
            eers.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            eers.setBackground(Color.red);
            eers.setForeground(Color.yellow);
            eers.setOpaque(true);
            eers.setVisible(false);
            subPanel.add(eers);
        }
        public void ChTerr(){
            eers.setText(Terr);
        }
    }
class Tbox extends setting {
    int num;
    String name;
    JTextField Lnumber = new JTextField();
    JTextField Lname = new JTextField();
    public Tbox(int y,int x,int First_val){
        Lnumber =new JTextField(String.valueOf(First_val));
        Lnumber.setBounds(180+x, 105+y, 40, 30);
        subPanel.add(Lnumber);
    }
    public int Tbox_check(){
        this.num = Integer.parseInt(Lnumber.getText());
        return num;
    }

    public Tbox(int y,int x,String First_val){
        Lname = new JTextField(First_val);
        Lname.setBounds(180+x, 105+y, 40, 30);
        subPanel.add(Lname);
    }
    public int Tbox_Tcheck(){
        this.name = Lname.getText();
        return num;
    }
}
