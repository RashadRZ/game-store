/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lenovo
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonRound extends JButton {

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public ButtonRound() {
        setFont(new Font("Arial", Font.BOLD, 14));
        setForeground(new Color(255, 255, 255));
        setBorder(BorderFactory.createEmptyBorder());
        setFocusable(false);
        setContentAreaFilled(false);

        setColor(new Color(111, 166, 32));
        colorOver = new Color(179, 250, 160);
        colorClick = new Color(152, 184, 144);
        borderColor = new Color(30, 136, 56);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(colorOver);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(color);
                over = false;
            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    setBackground(colorOver);
                } else {
                    setBackground(color);
                }
            }
        });
    }

    public void setBold() {
        this.setFont(new Font("Arial", Font.BOLD, 24));
        this.setForeground(new Color(255, 255, 255));
    }

    public void removeBold() {
        this.setFont(new Font("Arial", Font.PLAIN, 24));
        this.setForeground(new Color(158, 158, 158));
    }

    public void setSearchIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/search-icon.png"));
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(this.getWidth() - 8, this.getHeight() - 8, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        this.setIcon(scaledIcon);
    }

    public void setEditIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/edit-icon.png"));
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(this.getWidth() - 80, this.getHeight() - 8, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        this.setIcon(scaledIcon);
    }

    public void setDeleteIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/delete-icon.png"));
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(this.getWidth() - 80, this.getHeight() - 8, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        this.setIcon(scaledIcon);
    }
    
    public void setGameImage(String loc){
        ImageIcon icon = new ImageIcon(loc);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(this.getWidth() - 10, this.getHeight() - 8, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        this.setIcon(scaledIcon);
    }

    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 15;

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }
}
