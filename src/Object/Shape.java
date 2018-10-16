package Object;

import Observer.MyObserver;

/**
 * Created by swidan on 12/05/17.
 */
public interface Shape extends MyObserver{

    PlatePos getPosition();

    @Override
    String toString();

    void setHorizMovPlateFlag(boolean horizMovPlateFlag);

    public Shape setColor(java.awt.Color color);
    public java.awt.Color getColor();

    @Override
    boolean isVisible();

    Shape setVisible(boolean visible);

    boolean getLeft();

    void setleft(boolean left);

    boolean isRight();

    void setRight(boolean right);

}
