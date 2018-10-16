package FlyWeight;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import Object.*;
import Object.Shape;
import Observer.*;
/**
 * Created by swidan on 12/05/17.
 */
public class ClownPlatesFactory implements FlyWeight {
    private static Map<Color, HashMap<String,Stack<MyObserver>>> free = new HashMap<>();
    private static Map<Color, HashMap<String,Stack<MyObserver>>> used=new HashMap<>();
    private int screen_Width,screen_height;
    private Constructor<?> cons,cons1;
    public ClownPlatesFactory(int screen_Width, int screen_height, Constructor<?> cons, Constructor<?> cons1) {
        this.screen_height=screen_height;
        this.screen_Width=screen_Width;
        this.cons=cons;
        this.cons1=cons1;
        PlateShape p1 = new PlateShape((int) (Math.random() * screen_Width),
                -1 * (int) (Math.random() * screen_height),true,true, Color.red ,PlatePos.DOWN_LEFT);
        for(Color color : new Color[] { Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA }) {

            try {
                Stack<MyObserver> plateStack=new Stack<MyObserver>();
                Stack<MyObserver> plateStack2=new Stack<MyObserver>();
                plateStack.push((MyObserver) cons.newInstance((int) (Math.random() * screen_Width),
                        -1 * (int) (Math.random() * screen_height),true,true, color ,PlatePos.DOWN_LEFT));
                plateStack.push((MyObserver) cons.newInstance((int) (Math.random() * screen_Width),
                        -1 * (int) (Math.random() * screen_height),true,true, color ,PlatePos.DOWN_LEFT));
                plateStack2.push((MyObserver) cons1.newInstance((int) (Math.random() * screen_Width),
                        -1 * (int) (Math.random() * screen_height),true,true, color ,PlatePos.DOWN_LEFT));
                plateStack2.push((MyObserver) cons1.newInstance((int) (Math.random() * screen_Width),
                        -1 * (int) (Math.random() * screen_height),true,true, color ,PlatePos.DOWN_LEFT));
                plateStack2.push((MyObserver) cons1.newInstance((int) (Math.random() * screen_Width),
                        -1 * (int) (Math.random() * screen_height),true,true, color ,PlatePos.DOWN_LEFT));
                //System.out.println(color.toString());
                HashMap<String,Stack<MyObserver>> map=new HashMap<>();
                map.put(cons.getName().substring(7),plateStack);

                map.put(cons1.getName().substring(7),plateStack2);
                free.put(color,map);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public MyObserver acquirePlate(Color color, String shape) {
        MyObserver plateShape;
        HashMap<String,Stack<MyObserver>> m1;
        if(free.get(color).get(shape).isEmpty())
        {
           // System.out.println("dgrfv");
            Stack<MyObserver> freeStack=free.get(color).get(shape);
            try {
                if(cons1.getName().equals("Object."+shape))
                freeStack.push((MyObserver) cons1.newInstance((int) (Math.random() * screen_Width),
                        -1 * (int) (Math.random() * screen_height),true,true, color ,PlatePos.DOWN_LEFT));
                else
                    freeStack.push((MyObserver) cons.newInstance((int) (Math.random() * screen_Width),
                            -1 * (int) (Math.random() * screen_height),true,true, color ,PlatePos.DOWN_LEFT));

                m1=free.get(color);
                m1.put(shape,freeStack);
                free.put(color,m1);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


        }
        plateShape=free.get(color).get(shape).pop();
        Stack<MyObserver> usedStack;
        Stack<MyObserver> usedStack1;
        HashMap<String,Stack<MyObserver>> m2;
        if(used.get(color)==null) {
           // System.out.println("faefe");
            usedStack = new Stack<MyObserver>();
            usedStack.push(plateShape);
            m2=new HashMap<>();
            m2.put(shape,usedStack);
            used.put(color,m2);
          //  System.out.println(used.size());
            ((Shape)plateShape).setVisible(true);
            return plateShape;

        }
        if(used.get(color).get(shape)==null)
        {
            usedStack1=new Stack<MyObserver>();
            usedStack1.push(plateShape);
            m2=used.get(color);
            m2.put(shape,usedStack1);
            used.put(color,m2);

        }
        usedStack=used.get(color).get(shape);
      //  System.out.println("usedStack = " + usedStack);
        usedStack.push(plateShape);
        m2=used.get(color);
        used.put(color,m2);
        ((Shape)plateShape).setVisible(true);
        return plateShape;

    }

    public void release (Shape p,String shape)
    {
        Stack<MyObserver> freeStack;

        freeStack=free.get(p.getColor()).get(shape);
        MyObserver p1=used.get(p.getColor()).get(shape).pop();
        freeStack.push(p1);
        HashMap<String,Stack<MyObserver>> m=free.get(p.getColor());
        m.put(shape,freeStack);
        free.put(p.getColor(),m);
        System.gc();

    }
}
