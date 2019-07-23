package frames;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by amini on 06/26/2017.
 */

class PaintPanel extends JPanel {
    public static Graphics lg;
    public final ArrayList<Point> points = new ArrayList<>();
    public PaintPanel(){
        addMouseMotionListener(
                new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent event){
                        points.add(event.getPoint());
                        repaint();
                    }
                }
        );
    }
    public void saveImage(String name,String type) {
        BufferedImage image = new BufferedImage(380,240, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        try{
            ImageIO.write(image, type, new File("src/textures/"+name+"."+type));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(shape.shape_color);
        setBackground(Color.DARK_GRAY);
        for (Point point : points)
            g.fillOval(point.x,point.y,10,10);
    }
}

public class shape {
    public static Color shape_color = Color.orange;

    public void shaper() {
        JFrame application = new JFrame("paint car");
        PaintPanel paintPanel = new PaintPanel();
        JButton saveJButton = new JButton("save");
        JButton clearJButton = new JButton("clear");
        JButton colorJButton = new JButton("color");
        saveJButton.setSize(80,20);
        clearJButton.setSize(80,20);
        colorJButton.setSize(80,20);
        saveJButton.setBounds(312,0,70,20);
        clearJButton.setBounds(242,0,70,20);
        colorJButton.setBounds(172,0,70,20);
        application.add(saveJButton);
        application.add(clearJButton);
        application.add(colorJButton);
        saveJButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String a ="car"+1;
                paintPanel.saveImage(a,"JPEG");
                setting ostting = new setting();
                ostting.MenuLauncher();
                application.dispose();
            }
        });
        clearJButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.printf("a");
                paintPanel.points.clear();
                paintPanel.repaint();
            }
        });
        colorJButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                shape_color = JColorChooser.showDialog(application,"choose a color",shape_color);
                if(shape_color == null) shape_color = Color.orange;
                PaintPanel.lg.setColor(shape_color);
            }

        });
        application.add(new JLabel("drag the mouse to draw"),BorderLayout.NORTH);
        application.add(paintPanel,BorderLayout.CENTER);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setSize(400,300);
        application.setVisible(true);
    }
}
