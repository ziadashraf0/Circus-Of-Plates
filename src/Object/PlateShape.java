package Object;

import Observer.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by swidan on 12/05/17.
 */
public class PlateShape implements  Shape {
    public static final int SPRITE_WIDTH = 40;
    private static final int MAX_MSTATE = 100;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean left;
    private boolean top;
    private boolean visible;
    private Color color;
    private boolean horizMovPlateFlag;
    private PlatePos position;
    private static MySubject mySubject;
    public boolean Left = false;
    public boolean right = false;


    public PlateShape() {
    }

    public PlateShape(PlateShape p) {
        this(p.getX(), p.getY(), true, true, p.getColor(), p.getPosition());

    }

    public PlateShape(int posX, int posY, boolean left, boolean top, Color color, PlatePos position) {
        this.x = posX;
        this.y = posY;
        this.left = left;
        this.top = top;
        this.visible = true;
        this.position = position;
        this.color = color;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        int j = 0;
        for (int i = 0; i < spriteImages.length; i++) {
            spriteImages[i] = new BufferedImage(SPRITE_WIDTH, SPRITE_WIDTH, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = spriteImages[i].createGraphics();
            g2.setColor(Color.black);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            j = Math.abs(spriteImages.length / 2 - i);
            //  double theta = j * Math.PI / (2 * spriteImages.length);
            double x = SPRITE_WIDTH / 2.0;
            double y = SPRITE_WIDTH / 2.0;
            int x1 = (int) ((SPRITE_WIDTH / 2.0) - x);
            int y1 = (int) ((SPRITE_WIDTH / 2.0) - y);
            int x2 = (int) ((SPRITE_WIDTH / 2.0) + x);
            int y2 = (int) ((SPRITE_WIDTH / 2.0) + y);
            g2.setStroke(new BasicStroke(5));
            g2.drawRect(x1 + 2, y1 + 2, x2 - 4, y2 - 30);
            g2.setColor(color);
            g2.fillRect(x1 + 2, y1 + 2, x2 - 4, y2 - 30);
            g2.dispose();
        }
    }

    @Override
    public PlatePos getPosition() {
        return position;
    }

    public boolean isHorizMovPlateFlag() {
        return horizMovPlateFlag;
    }

    @Override
    public String toString() {
        return "PlateShape";
    }

    @Override
    public void setHorizMovPlateFlag(boolean horizMovPlateFlag) {
        this.horizMovPlateFlag = horizMovPlateFlag;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        if (getLeft()) {
            if (mX > 5 && mX < 590)
                this.x = mX;
        } else if (isRight()) {
            if (mX > 135 && mX < 720)
                this.x = mX;
        } else
            this.x = mX;

    }

    public Shape setColor(java.awt.Color color) {
        this.color = color;
        return this;
    }

    public java.awt.Color getColor() {
        return color;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
        if (horizMovPlateFlag) ;
        else y = mY;


    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return SPRITE_WIDTH;
    }

    @Override
    public int getHeight() {
        return SPRITE_WIDTH;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public PlateShape setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    @Override
    public void update() {
        this.setVisible(false);
    }

    @Override
    public void setSubject(MySubject sub) {

        this.mySubject = sub;
    }

    @Override
    public boolean getLeft() {
        return Left;
    }

    @Override
    public void setleft(boolean left) {
        this.Left = left;
    }

    @Override
    public boolean isRight() {
        return right;
    }

    @Override
    public void setRight(boolean right) {
        this.right = right;
    }
}
