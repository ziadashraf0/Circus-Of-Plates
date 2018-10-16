package Observer;

/**
 * Created by swidan on 19/05/17.
 */
public interface MySubject {
    void register(MyObserver obj, String s);

    void unregister(MyObserver obj, String s);

    void notifyObservers(String s);
}
