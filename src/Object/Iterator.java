package Object;

import eg.edu.alexu.csd.oop.game.GameObject;

/**
 * Created by swidan on 14/05/17.
 */
public interface Iterator {


    boolean hasNext();

    GameObject next(GameObject m);
}
