package Objects;

import collision.CollisionDetector;
import panels.GameOver;
import panels.YouWin;
import Save.GameSave;
import Save.VideoSaver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by amini on 06/26/2017.
 */
public class WalkManPanel extends JPanel implements KeyListener, Runnable {

    public static int Game_speed = 40;
    public static int line_numbers = 4;
    public static int HEIGHT = 450;
    public static int WIDTH = 700;
    public static int Man_type = 1;
    public static boolean AI = false;
    public static boolean video = true;
    private CollisionDetector checkForCollision;

    BufferedImage car2_Left, car2_Right, Truck_Left, Truck_Right, semi_Left, semi_Right, ManUp, ManDown,
            ManLeft, ManRight,cactos;
    WalkManGame game;

    Thread saveThread = new Thread() {
        GameSave oSaver = new GameSave();

        public void run() {
            oSaver.Delete();
            try {
                oSaver.SaveLine(line_numbers);
                oSaver.SaveCar(WalkManGame.carLanes);
                oSaver.SaveUser(WalkManGame.player);
                oSaver.SaveUserHelper(new SaveHelper(WalkManGame.lives, WalkManGame.getTimeLeft2()));
            } catch (Exception e) {
                System.out.println("Save Error");
            }
        }
    };
    Thread VideoThread = new Thread() {
        VideoSaver oSaver = new VideoSaver("Video");

        public void run() {
            oSaver.Delete();
            oSaver.SaveLine(line_numbers);
            while (!game.DEAD || !game.WIN) {
                try {
                    oSaver.SaveCar(WalkManGame.carLanes);
                    oSaver.SaveUser(WalkManGame.player, new SaveHelper(WalkManGame.lives, WalkManGame.getTimeLeft2()));
                    Thread.sleep(100);

                } catch (Exception e) {
                    System.out.println("Video Error");
                }
            }
        }
    };

    public WalkManPanel() {
        setSize(WIDTH, HEIGHT);
        this.checkForCollision = new CollisionDetector();

        reset();
        Thread pThread;

        try {
            pThread = new Thread(this);
            pThread.start();
            if (video == true) {
                VideoThread.start();
            }
        } catch (Exception e) {
            System.err.println("Error creating thread.");
            e.printStackTrace();
            System.exit(-2);
        }

        try {
            car2_Left = ImageIO.read((new File("src/textures/Car2-Left.png")));
            car2_Right = ImageIO.read((new File("src/textures/Car2-Right.png")));
            Truck_Left = ImageIO.read((new File("src/textures/Truck-Left.png")));
            Truck_Right = ImageIO.read((new File("src/textures/Truck-Right.png")));
            semi_Left = ImageIO.read((new File("src/textures/Semi-Left.png")));
            semi_Right = ImageIO.read((new File("src/textures/Semi-Right.png")));
            cactos = ImageIO.read((new File("src/textures/cactos.png")));

            switch (Man_type) {
                case 1:
                    ManUp = ImageIO.read((new File("src/textures/Man1Up.png")));
                    ManDown = ImageIO.read((new File("src/textures/Man1Down.png")));
                    ManLeft = ImageIO.read((new File("src/textures/Man1Left.png")));
                    ManRight = ImageIO.read((new File("src/textures/Man1Right.png")));
                    break;
                case 2:
                    ManUp = ImageIO.read((new File("src/textures/Man2Up.png")));
                    ManDown = ImageIO.read((new File("src/textures/Man2Down.png")));
                    ManLeft = ImageIO.read((new File("src/textures/Man2Left.png")));
                    ManRight = ImageIO.read((new File("src/textures/Man2Right.png")));
                    break;
                case 3:
                    ManUp = ImageIO.read((new File("src/textures/Man3Up.png")));
                    ManDown = ImageIO.read((new File("src/textures/Man3Down.png")));
                    ManLeft = ImageIO.read((new File("src/textures/Man3Left.png")));
                    ManRight = ImageIO.read((new File("src/textures/Man3Right.png")));
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error Loading Images: " + e.getMessage());
            e.printStackTrace();
            System.exit(-1); //if loading fails, end the program.
        }
        addKeyListener(this);

    }

    @Deprecated
    public void keyReleased(KeyEvent e) {
        //unused
    }

    @Deprecated
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if ((game.getPlayer().getY() - 40) > 30) {
                    if (game.getPlayer().getY() != (line_numbers / 2) * 40 + 75 || CollisionDetector.in_line) {
                        game.getPlayer().setY(game.getPlayer().getY() - 10);
                    }
                }
                game.getPlayer().setDirection(Man.UP);
                break;
            case KeyEvent.VK_DOWN:
                if ((game.getPlayer().getY() + 40) < getHeight() - 100) {
                    if (game.getPlayer().getY() != (line_numbers / 2) * 40 + 55 || CollisionDetector.in_line) {
                        game.getPlayer().setY(game.getPlayer().getY() + 10);
                    }
                }
                game.getPlayer().setDirection(Man.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                if ((game.getPlayer().getX() - 30) > 0) {
                    game.getPlayer().setX(game.getPlayer().getX() - 10);
                }
                game.getPlayer().setDirection(Man.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                if ((game.getPlayer().getX() + 40) < getWidth() - 30) {
                    game.getPlayer().setX(game.getPlayer().getX() + 10);
                }
                game.getPlayer().setDirection(Man.RIGHT);
                break;
            case KeyEvent.VK_ADD:
                if (Game_speed < 95) {
                    Game_speed += 5;
                }
                break;
            case KeyEvent.VK_SUBTRACT:
                if (Game_speed > 0) {
                    Game_speed -= 5;
                }
                break;
            case KeyEvent.VK_Q:
                if (!AI) {
                    AI = true;
                } else {
                    AI = false;
                }
                break;
            case KeyEvent.VK_W:
                if (!saveThread.isAlive()) {
                    saveThread.start();
                } else {
                    saveThread.run();
                }
        }
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            update();
            repaint();
            try {
                if (game.DEAD) {
                    WalkManGame.player.setY(1000000000);
                    GameOver gameOver;
                    gameOver = new GameOver(0); //
                    gameOver.setBounds(0, 0, WIDTH, HEIGHT);
                    this.getParent().getParent().add(gameOver, new Integer(2), 0);
                    Thread.sleep(1000);
                }
                if (game.WIN) {
                    WalkManGame.MAX_LIFE_TIME = WalkManGame.MAX_LIFE_TIME_save;
                    YouWin youWin;
                    youWin = new YouWin(true, 0); //
                    youWin.setBounds(0, 0, WIDTH, HEIGHT);
                    this.getParent().getParent().add(youWin, new Integer(2), 0);
                    Thread.sleep(1000000000L);

                }
                Thread.sleep(Game_speed); //todo correct times per second?

            } catch (Exception e) {
                System.err.println("Error Sleeping.");
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void paint(Graphics g) {
        g.setColor(new Color(157,90,66));
        g.fillRect(0, 0, getWidth(), getHeight()); //fill the background

        //white lines of the road-------------------
        g.setColor(Color.white);
        g.drawLine(0, 75, getWidth(), 75);
        g.drawLine(0, line_numbers * 40 + 75, getWidth(), line_numbers * 40 + 75);
        //road--------------------------------------
        g.setColor(Color.GRAY);
        g.fillRect(0, 76, getWidth(), line_numbers * 40 - 1);
        //bottom black bar----------------------
        g.setColor(Color.BLACK);
        g.fillRect(0, getHeight()-30, getWidth(), 30);
        //yellow lines on road----------------
        g.setColor(Color.yellow);
        for (int y = 116; y < line_numbers * 40 + 64; y += 39) {
            for (int x = 10; x < getWidth() - 10; x += 90) {
                g.fillRect(x, y, 60, 4);
            }
        }

        //Block--------------------------------------
        g.setColor(Color.BLACK);
        g.fillRect(0, (line_numbers / 2) * 40 + 74, getWidth(), 6);

        //yellow lines on crossroad----------------
        g.setColor(Color.white);
        for (int y = line_numbers * 40 + 64; y > 80; y -= 10) {
            g.fillRect(crossroad.crossroad_x, y, crossroad.Crossroad_width, crossroad.Crossroad_hight);
        }

        //text----------------------------------
        g.setColor(Color.GRAY);
        g.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        g.drawString("Lives:", 10, 25);
        g.drawString("Time Left:", 400, 25);
        g.drawString("speed: " + (-((Game_speed / 5) - 20)), 10, getHeight() - 5);
        g.drawString("for PC play press Q ", 400, getHeight() - 5);
        g.drawString("for Save press W ", 150, getHeight() - 5);

        //lives---------------------------------
        g.setColor(Color.MAGENTA);
        for (int i = 0; i < game.getLives(); i++) {
            g.drawString("♥", 80 + i * 30, 25);
        }

        //time left----------------
        int i = game.getTimeLeft();
        i /= 10;
        if (i >= 60) //change colour of bar based on time left
        {
            g.setColor(Color.green);
        } else if (i >= 40) {
            g.setColor(Color.orange);
        } else {
            g.setColor(Color.RED);
        }
        if (WalkManGame.MAX_LIFE_TIME == 0) {
            WalkManGame.DEAD = true;
        }

        g.fillRect(500, 8, i + 5, 20); //draw timer based on time left
        g.drawRect(500, 8, WalkManGame.MAX_LIFE_TIME / 10 + 5, 20); //timer outline

        //multi_player---------------------
        if (WalkManGame.multiplayer) {
            for (int j = 0; j < game.getNum_player(); j++) {
                g.drawImage(ManLeft, WIDTH / 2 + 30 * j, WalkManPanel.line_numbers * 40 + 75, null);
            }
        }

        //cactos-------------------------------
        g.drawImage(cactos, 60, 0, null);
        g.drawImage(cactos, 100, 290, null);
        g.drawImage(cactos, 220, 310, null);
        g.drawImage(cactos, 520, 320, null);
        g.drawImage(cactos, 300, 340, null);


        //draw man --------------------------
        switch (game.getPlayer().getDirection()) { //draw man based on direction
            case Man.UP:
                g.drawImage(ManUp, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
            case Man.DOWN:
                g.drawImage(ManDown, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
            case Man.LEFT:
                g.drawImage(ManLeft, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
            case Man.RIGHT:
                g.drawImage(ManRight, game.getPlayer().getX(), game.getPlayer().getY(), null);
                break;
        }

        //MOVING OBJECTS ---------------------------------------
        //cars ------------
        for (CarLane cl : game.getCarLanes()) //all car lanes
        {
            for (int p = 0; p < cl.laneItems.size(); p++) //each car in that lane
            {
                if (cl.laneItems.get(p).getDirection() == Lane.RIGHT && cl.laneItems.get(p).getType() == Car.CAR_2) {
                    g.drawImage(car2_Right, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                } else if (cl.laneItems.get(p).getDirection() == Lane.LEFT && cl.laneItems.get(p).getType() == Car.CAR_2) {
                    g.drawImage(car2_Left, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                } else if (cl.laneItems.get(p).getDirection() == Lane.LEFT && cl.laneItems.get(p).getType() == Car.LIMO) {
                    g.drawImage(Truck_Left, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                } else if (cl.laneItems.get(p).getDirection() == Lane.RIGHT && cl.laneItems.get(p).getType() == Car.LIMO) {
                    g.drawImage(Truck_Right, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                } else if (cl.laneItems.get(p).getDirection() == Lane.LEFT && cl.laneItems.get(p).getType() == Car.SEMI) {
                    g.drawImage(semi_Left, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                } else if (cl.laneItems.get(p).getDirection() == Lane.RIGHT && cl.laneItems.get(p).getType() == Car.SEMI) {
                    g.drawImage(semi_Right, (int) cl.laneItems.get(p).getX(), (int) cl.laneItems.get(p).getY(), null);
                }

            }
        }
    }

    void update() {
        game.update();
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    void reset() {
        this.game = new WalkManGame();
    }

}
