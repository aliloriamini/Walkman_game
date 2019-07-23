package UI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by amini on 06/26/2017.
 */

public class CustomizedButtonUI extends BasicButtonUI implements MouseListener {

    private Color hoverColor = new Color(240, 240, 240);
    private Color normalColor = new Color(240, 240, 240);
    private Color pressedColor = new Color(240, 240, 240);
    private Color btnFontColor;
    private ImageIcon normalIcon;
    private ImageIcon hoverIcon;
    private ImageIcon pressedIcon;
    private Font btnFont;

    public CustomizedButtonUI() {
        super();
    }

    public CustomizedButtonUI(Color normalColor, Color hoverColor, Color pressedColor) {
        this(normalColor, hoverColor, pressedColor, null, Color.BLACK);
    }


    public CustomizedButtonUI(Color normalColor, Color hoverColor, Color pressedColor, Font btnFont, Color btnFontColor) {
        this.normalColor = normalColor;
        this.hoverColor = hoverColor;
        this.pressedColor = pressedColor;
        this.btnFont = btnFont;
        this.btnFontColor = btnFontColor;
    }

    public CustomizedButtonUI(ImageIcon normalIcon, ImageIcon hoverIcon, ImageIcon pressedIcon) {
        this(normalIcon, hoverIcon, pressedIcon, null, Color.BLACK);
    }

    public CustomizedButtonUI(ImageIcon normalIcon, ImageIcon hoverIcon, ImageIcon pressedIcon, Font btnFont, Color btnFontColor) {
        this.normalIcon = normalIcon;
        this.hoverIcon = hoverIcon;
        this.pressedIcon = pressedIcon;
        this.btnFont = btnFont;
        this.btnFontColor = btnFontColor;
    }

    @Override
    public void installUI(JComponent comp) {
        super.installUI(comp);
        comp.addMouseListener(this);
    }

    @Override
    public void uninstallUI(JComponent comp) {
        super.uninstallUI(comp);
        comp.removeMouseListener(this);
    }

    @Override
    protected void installDefaults(AbstractButton btn) {
        super.installDefaults(btn);
        btn.setIcon(this.normalIcon);
        btn.setBackground(this.normalColor);
        btn.setForeground(this.btnFontColor);
        btn.setFont(this.btnFont);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    @Override
    public Dimension getPreferredSize(JComponent comp) {
        Dimension dim = super.getPreferredSize(comp);
        if (comp.getBorder() != null) {
            Insets insets = comp.getBorder().getBorderInsets(comp);
            dim.setSize(dim.width + insets.left + insets.right, dim.height + insets.top + insets.bottom);
        }
        return dim;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        changeButtonStyle((JButton) e.getComponent(), this.pressedColor, this.pressedIcon);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        changeButtonStyle((JButton) e.getComponent(), this.pressedColor, this.pressedIcon);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        changeButtonStyle((JButton) e.getComponent(), this.normalColor, this.normalIcon);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        changeButtonStyle((JButton) e.getComponent(), this.hoverColor, this.hoverIcon);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        changeButtonStyle((JButton) e.getComponent(), this.normalColor, this.normalIcon);
    }

    private void changeButtonStyle(JButton btn, Color color, ImageIcon icon) {
        btn.setBackground(color);
        btn.setIcon(icon);
    }
}