package FlyWeight;

import Observer.MyObserver;

import java.awt.*;

/**
 * Created by swidan on 11/05/17.
 */
public interface FlyWeight {


    MyObserver acquirePlate(Color color, String shape);
}
