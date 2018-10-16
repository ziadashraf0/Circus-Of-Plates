package Object;

import eg.edu.alexu.csd.oop.game.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by AHMED ESSAM on 5/8/2017.
 */
public class LoadingImage implements GameObject {

        private static final int MAX_MSTATE = 1;
        private BufferedImage[] Images = new BufferedImage[MAX_MSTATE];
        private int x;
        private int y;
        private boolean visible;
        private int type;
        private int width;
        private boolean horizontalOnly;
        private Color color;
        private String path;

    public LoadingImage(int posX,int posY,int width,boolean horizontalOnly,Color color){
        this.x=posX;
        this.y=posY;
        this.width=width;
        this.horizontalOnly=horizontalOnly;
        this.color=color;

    }
        public LoadingImage(int posX, int posY, String path){
            this(posX, posY, path, 0);
        }


    public LoadingImage(int posX, int posY, String path, int type){
            this.x = posX;
            this.y = posY;
            this.type = type;
            this.path=path;

            this.visible = true;
            try {
                Images[0] = ImageIO.read(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {
        this.y = mY;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return Images;
    }

    @Override
    public int getWidth(){
        return Images[0].getWidth();
    }

    @Override
    public int getHeight() {
        return Images[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public String getPath() {
        return path;
    }

}


