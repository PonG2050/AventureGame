package UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Main.GamePanel;

public abstract class UIComponent {
    protected GamePanel gp;
    protected int x, y, width, height;
    protected BufferedImage image;

    public UIComponent(GamePanel gp, int x, int y, int width, int height) {
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update();
    public abstract void draw(Graphics2D g2);
}