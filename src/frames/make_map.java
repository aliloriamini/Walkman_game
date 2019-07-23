package frames;

import Objects.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by amini on 07/03/2017.
 */

class map extends JPanel {

    BufferedImage ManUp,cactos,car2_Left,car2_Right,Truck_Left,Truck_Right,semi_Left,semi_Right;
    public static int man_map_x,man_map_y,cross_map = crossroad.crossroad_x ;

    public final ArrayList<LaneItem> car_1 = new ArrayList<LaneItem>();
    public final ArrayList<LaneItem> car_2 = new ArrayList<LaneItem>();
    public final ArrayList<LaneItem> car_3 = new ArrayList<LaneItem>();
    public map(){
        try {

            Truck_Left = ImageIO.read((new File("src/textures/Truck-Left.png")));
            Truck_Right = ImageIO.read((new File("src/textures/Truck-Right.png")));
            semi_Left = ImageIO.read((new File("src/textures/Semi-Left.png")));
            semi_Right = ImageIO.read((new File("src/textures/Semi-Right.png")));
            car2_Left = ImageIO.read((new File("src/textures/Car2-Left.png")));
            car2_Right = ImageIO.read((new File("src/textures/Car2-Right.png")));
            cactos = ImageIO.read((new File("src/textures/cactos.png")));
            ManUp = ImageIO.read((new File("src/textures/Man1Up.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }


        addMouseListener(new MouseAdapter() {
            public int dir = 1;
            @Override
            public void mouseClicked(MouseEvent e) {
                if((WalkManPanel.line_numbers/2)*40+75 >e.getY())
                {
                    dir = 1;
                }else {dir = 0;}
                    switch (make_map.tpypes){
                        case 1:
                            car_1.add(new Car(0, 1, dir, e.getX(), e.getY()-20));
                            break;
                        case 2:
                            car_2.add(new Car(0, 1, dir, e.getX(), e.getY()-20));
                            break;
                        case 3:
                            car_3.add(new Car(0, 1, dir, e.getX(), e.getY()-20));
                            break;
                        case 4:
                                man_map_x =e.getX();
                                man_map_y = e.getY();
                            break;
                        case 5:
                                cross_map =e.getX();
                                break;
                        case 6:

                            break;
                        }
                        System.out.printf("%d%d",man_map_x,e.getX());
                        //points.add(event.getPoint());
                        repaint();
                    }
                }
        );
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(157,90,66));
        g.fillRect(0, 0, getWidth(), getHeight()); //fill the background

        //white lines of the road-------------------
        g.setColor(Color.white);
        g.drawLine(0, 75, getWidth(), 75);
        g.drawLine(0, WalkManPanel.line_numbers * 40 + 75, getWidth(), WalkManPanel.line_numbers * 40 + 75);
        //road--------------------------------------
        g.setColor(Color.GRAY);
        g.fillRect(0, 76, getWidth(), WalkManPanel.line_numbers * 40 - 1);
        //bottom black bar----------------------
        g.setColor(Color.BLACK);
        g.fillRect(0, getHeight()-30, getWidth(), 30);
        //yellow lines on road----------------
        g.setColor(Color.yellow);
        for (int y = 116; y < WalkManPanel.line_numbers * 40 + 64; y += 39) {
            for (int x = 10; x < getWidth() - 10; x += 90) {
                g.fillRect(x, y, 60, 4);
            }
        }

        //Block--------------------------------------
        g.setColor(Color.BLACK);
        g.fillRect(0, (WalkManPanel.line_numbers / 2) * 40 + 74, getWidth(), 6);

        //yellow lines on crossroad----------------
        g.setColor(Color.white);
        for (int y = WalkManPanel.line_numbers * 40 + 64; y > 80; y -= 10) {
            g.fillRect(cross_map, y, crossroad.Crossroad_width, crossroad.Crossroad_hight);
        }
        //cactos-------------------------------
        g.drawImage(cactos, 60, 0, null);
        g.drawImage(cactos, 100, 290, null);
        g.drawImage(cactos, 220, 310, null);
        g.drawImage(cactos, 520, 320, null);
        g.drawImage(cactos, 300, 340, null);

        g.drawImage(ManUp,man_map_x,man_map_y, null);

        for (int i=0;i<car_1.size();i++) {
            BufferedImage temp = car2_Right;
            if (car_1.get(i).getDirection() == 1){
                temp = car2_Left;
            }
            g.drawImage(temp,(int)car_1.get(i).getX(),(int)car_1.get(i).getY(),null);
        }
        for (int j=0;j<car_2.size();j++) {
            BufferedImage temp = Truck_Right;
            if (car_2.get(j).getDirection() == 1){
                temp = Truck_Left;
            }
            g.drawImage(temp,(int)car_2.get(j).getX(),(int)car_2.get(j).getY(),null);
        }
        for (int k=0;k<car_3.size();k++) {
            BufferedImage temp = semi_Right;
            if (car_3.get(k).getDirection() == 1){
                temp = semi_Left;
            }
            g.drawImage(temp,(int)car_3.get(k).getX(),(int)car_3.get(k).getY(),null);
        }
    }
}

public class make_map {
    public static int tpypes = 0;
    public make_map(){
        JFrame application = new JFrame("make_map");
        application.setSize(700,450);
        map map = new map();
        JButton car = new JButton("car");
        JButton bus = new JButton("bus");
        JButton semi = new JButton("truck");
        JButton man = new JButton("man");
        JButton cross = new JButton("cross");
        JButton clear = new JButton("clear");
        JButton save = new JButton("save");
        JLabel help = new JLabel("choose and then click in the map");
        help.setBounds(30,application.getHeight()-95,200,30);
        help.setForeground(Color.white);
        car.setBounds(application.getWidth()-150,application.getHeight()-100,70,20);
        bus.setBounds(application.getWidth()-250,application.getHeight()-100,70,20);
        semi.setBounds(application.getWidth()-350,application.getHeight()-100,70,20);
        man.setBounds(application.getWidth()-450,application.getHeight()-100,70,20);
        clear.setBounds(application.getWidth()-350,application.getHeight()-70,70,20);
        cross.setBounds(application.getWidth()-450,application.getHeight()-70,70,20);
        save.setBounds(application.getWidth()-250,application.getHeight()-70,70,20);
        application.add(car);
        application.add(bus);
        application.add(semi);
        application.add(man);
        application.add(cross);
        application.add(clear);
        application.add(save);
        car.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tpypes = 1;
            }
        });
        bus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tpypes = 2;
            }
        });
        semi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tpypes = 3;
            }

        });
        man.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tpypes = 4;
            }

        });
        cross.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tpypes = 5;
            }

        });
        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                map.car_1.clear();
                map.car_2.clear();
                map.car_3.clear();
                map.repaint();
            }
        });
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setting ostting = new setting();
                ostting.MenuLauncher();
                application.dispose();
            }
        });
        application.add(help);
        application.add(map);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setVisible(true);
    }
}
