package World;

import DynamicLinkage.DynamicLinkage;
import FlyWeight.ClownPlatesFactory;
import Levels.LevelState;
import Levels.Originator;
import Levels.SnapShot;
import Levels.State;
import MVC.Controller;
import Observer.MyObserver;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import Object.*;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by AHMED ESSAM on 5/8/2017.
 */
public class CircusOfPlates implements World {
    public static int c ;
    private int score = 0;
    private static int MAX_TIME = 1 * 90 * 1000;
    public static long startTime = System.currentTimeMillis();
    private int width;
    private int height;
    private  List<GameObject> Clown_list = new LinkedList<GameObject>();
    private  final List<GameObject> Plate_list = new LinkedList<GameObject>();
    private final List<GameObject> Rails_list = new LinkedList<GameObject>();
    private ClownPlatesFactory cpp;
    private static CircusOfPlates circus;
    private  String PlayerName= Controller.getName();
    private State state=new LevelState();

    public static int getC() {
        return c;
    }

    public static void setC(int c) {
        CircusOfPlates.c = c;

    }

    private static int flag=0;
    public static synchronized CircusOfPlates getInstance(int screen_Width, int screen_height, State state,int c,String shapeName1,String shapeName2)
    {
        if (circus==null)
            circus=new CircusOfPlates(screen_Width,screen_height,state,c,shapeName1,shapeName2);
        return circus;
    }
    private CircusOfPlates(int screen_Width, int screen_height, State state,int c,String shapeName1,String shapeName2) {
        try {
        this.state=state;
        width = screen_Width;
        CircusOfPlates.c =c;
        height = screen_height;


        int rateL = 130;int rateR = 640;int rateUPL = 300;int rateUPR = 550;
            DynamicLinkage dynamicLinkage=new DynamicLinkage();
            Class shape=dynamicLinkage.load(shapeName1);
            Class shape2=dynamicLinkage.load(shapeName2);
            Constructor<?> cons=shape.getConstructor(int.class, int.class,boolean.class,boolean.class,Color.class,PlatePos.class);
            Constructor<?> cons1=shape2.getConstructor(int.class, int.class,boolean.class,boolean.class,Color.class,PlatePos.class);
            cpp = new ClownPlatesFactory(width, height,cons,cons1);
            //            Object obj=cons.newInstance((rateL), -1 * (screen_height), true,true,color,PlatePos.DOWN_LEFT);

           // PlateShape p=(PlateShape) obj;
            //System.out.println(p.getColor());




            Clown_list.add(ClownObject.getInstance(screen_height - 425, (int) (screen_height * 0.70), "./res/Clown6.png"));
        Clown_list.add(ClownLeftHand.getInstance(screen_height - 440, (int) (screen_height * 0.70 - 24), "./res/BIN2.png"));
        Clown_list.add(ClownRightHand.getInstance(screen_height - 310, (int) (screen_height * 0.70 - 24), "./res/BIN2.png"));

        Rails_list.add(new BarObject((int) (screen_height * 0.001), (int) (screen_Width * 0.05), 320, true, Color.gray));
        Rails_list.add(new BarObject((int) (screen_height * 0.68), (int) (screen_Width * 0.05), 350, true, Color.gray));
        Rails_list.add(new BarObject((int) (screen_height * 0.001), (int) (screen_Width * 0.15), 150, true, Color.gray));
        Rails_list.add(new BarObject((int) (screen_height * 0.9), (int) (screen_Width * 0.15), 180, true, Color.gray));
        for(Color color : new Color[] { Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA }) {

            Plate_list.add((MyObserver)cons.newInstance((rateL), -1 * (screen_height), true,true,color,PlatePos.DOWN_LEFT));
            Plate_list.add((MyObserver)cons1.newInstance((rateR), -1 * (screen_height), true,true,color,PlatePos.DOWN_RIGHT));
            Plate_list.add((MyObserver)cons.newInstance((rateUPL), -1 * (screen_height), true,true,color,PlatePos.UP_LEFT));
            Plate_list.add((MyObserver)cons1.newInstance((rateUPR), -1 * (screen_height), true,true,color,PlatePos.UP_RIGHT));
            rateL -= 80;
            rateR += 80;
            rateUPL -= 80;
            rateUPR += 80;
        }


        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public boolean intersect(GameObject o1, GameObject o2) {
        if(o1==null||o2==null) return false;
        return (Math.abs((o1.getX() + o1.getWidth() /2) - (o2.getX() + o2.getWidth()/2 )) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight()/2 ) - (o2.getY() + o2.getHeight()/2 )) <= o1.getHeight()/2);
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return Rails_list;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return Plate_list;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return Clown_list;
    }

    @Override
    public int getWidth() {
        return width;
    }


    @Override
    public int getHeight() {
        return height;
    }


    @Override
    public boolean refresh() {
         // time end and game over
        ClownRightHand clownRightHand = (ClownRightHand) Clown_list.get(2);

        ClownLeftHand clownObject = (ClownLeftHand) Clown_list.get(1);
        PlatesIterator p=new PlatesIterator(this,cpp,clownObject,clownRightHand,this.state,c);

        for (GameObject m : Plate_list) {
           score=PlatesIterator.getScore();
           // PlateShape k = (PlateShape) m;
            if (!p.hasNext()) {
                //DON'T FORGET
                p.next(m);
               /* if (!timeout & intersect(m, clownObject))
                    score = Math.max(0, score - 10);*/
            }
            //m.setX(m.getX() + (Math.random() > 0.5 ? 1 : -1));
            // lose score
            if(p.hasNext())
            {
                if (flag==0)
                {
                    System.out.println("score="+score);
                    flag=1;
                    SnapShot snapShot=new SnapShot(score,PlayerName);
                    Originator originator =new Originator();
                    originator.save(snapShot);
                }
            }
        }
        return !p.hasNext();
    }

    @Override
    public String getStatus() {

        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);
    }


    @Override
    public int getSpeed() {
        return 10;
    }


    @Override
    public int getControlSpeed() {
        return 20;
    }
}
