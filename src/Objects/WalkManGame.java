package Objects;

import Save.VideoSaver;
import collision.CollisionDetector;

/**
 * Created by amini on 06/26/2017.
 */
public class WalkManGame {

    public static int PLAYER_WINS = 2;
    public static int PLAYING = 0;
    public static int MAX_LIFE_TIME = 1000;
    public static int MAX_LIFE_TIME_save = MAX_LIFE_TIME;
    public static boolean DEAD = false;
    public static boolean WIN = false;
    public static boolean LOAD = false;
    public static boolean VIDEO = false;
    public static boolean multiplayer = false;
    public static int lives =3 , num_player = 3 ;
    public static int startLifeTime = 1;

    // x&y man
    public static int ManX, ManY;
    public static final int CarLaneInitialY = 75;
    public static CarLane[] carLanes;

    int status;
    boolean reachedMiddle;
    static Man player;

    Thread Video = new Thread() {
        VideoSaver oSaver = new VideoSaver("Video");
        int[] oUserX = oSaver.LoadUserX();
        int[] oUserY = oSaver.LoadUserY();
        int[] oUserDir = oSaver.LoadUserDir();
        int[] oUserLife = oSaver.LoadUserLive();
        int[] oUserTime = oSaver.LoadUserTime();

        public void run() {

            try {
                for (int i = 0; i < oUserX.length; i++) {
                   player.setX(oUserX[i]);
                    player.setY(oUserY[i]);
                    player.setDirection(oUserDir[i]);
                    lives = oUserLife[i];
                    MAX_LIFE_TIME = oUserTime[i];
                    Thread.sleep(150);
                }

            } catch (Exception e) {
                System.out.print(e);
            }

        }
    };

    public WalkManGame() {
        status = WalkManGame.PLAYING;
        reachedMiddle = false;
        player = new Man(ManX, ManY);

        //car lanes -------------
        if (!VIDEO) {
            if (!LOAD) {
                carLanes = new CarLane[WalkManPanel.line_numbers];
                for (int i = 0; i < WalkManPanel.line_numbers; i++) {
                    if (i >= carLanes.length / 2) {
                        carLanes[i] = new CarLane(3, Lane.RIGHT, CarLaneInitialY + i * 40);
                    } else {
                        carLanes[i] = new CarLane(3, Lane.LEFT, CarLaneInitialY + i * 40);
                    }
                }

                for (int t = 0; t < 1000; t++) //calls update on all lanes before loading game
                {
                    update();
                }
            } else {
                carLanes = new CarLane[WalkManPanel.line_numbers];

                for (int i = 0; i < WalkManPanel.line_numbers; i++) {
                    if (i >= WalkManPanel.line_numbers / 2) {
                        carLanes[i] = new CarLane(3, Lane.RIGHT, CarLaneInitialY + i * 40);
                    } else {
                        carLanes[i] = new CarLane(3, Lane.LEFT, CarLaneInitialY + i * 40);
                    }
                }

                LOAD = false;

            }

        } else {
            carLanes = new CarLane[WalkManPanel.line_numbers];
            for (int i = 0; i < WalkManPanel.line_numbers; i++) {
                if (i >= WalkManPanel.line_numbers / 2) {
                    carLanes[i] = new CarLane(3, Lane.RIGHT, CarLaneInitialY + i * 40);
                } else {
                    carLanes[i] = new CarLane(3, Lane.LEFT, CarLaneInitialY + i * 40);
                }
                if (!Video.isAlive()) {
                    Video.start();

                }

            }
            VIDEO = false;

        }
    }

    void update() {
        for (int u = 0; u < WalkManPanel.line_numbers; u++) {
            carLanes[u].update();
        }
        for (int y = 0; y < WalkManPanel.line_numbers; y++) {
            runChecks();
        }
    }

//    public int getStatus() {
//        return status;
//    }
    public int getLives() {
        return lives;
    }

    public int getNum_player() {
        return num_player;
    }

    public Man getPlayer() {
        return player;
    }

    public CarLane[] getCarLanes() {
        return carLanes;
    }

    public int getTimeLeft() {
        return MAX_LIFE_TIME = MAX_LIFE_TIME - startLifeTime;
    }

    public static int getTimeLeft2() {
        return MAX_LIFE_TIME;
    }

    void playerDeath() {
        lives--;
        if (lives > 0) {
            player.setX(ManX);
            player.setY(ManY);
        } else {
            if (multiplayer) {
                num_player--;
                lives = 4;
                if (num_player == -1) {
                    DEAD = true;
                }
            } else {
                DEAD = true;
            }
        }
    }

    void carCheck() {
        //kills player when contacting car
        if (CollisionDetector.CollisionDetector(this.getPlayer(), this.getCarLanes())) {
            playerDeath();
        }
    }

    void checkifThePlayerWin() {
        if (this.player.getY() <= 75) {
            if (multiplayer) {
                PLAYER_WINS++;
                num_player--;
                if (num_player == -1) {
                    WIN = true;
                }
                lives = 3;
                this.player.setY(275);
                this.player.setX(WalkManPanel.WIDTH / 2 - 40);
            } else {
                WIN = true;
            }
        }
    }

    void runChecks() {
        carCheck();
        checkifThePlayerWin();

    }

}
