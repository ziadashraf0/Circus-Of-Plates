package Observer;

import eg.edu.alexu.csd.oop.game.GameObject;

/**
 * Created by swidan on 19/05/17.
 */
public interface MyObserver extends GameObject {

    void update();
    void setSubject(MySubject sub);
}
