package Object;

import FlyWeight.ClownPlatesFactory;
import Levels.LevelState;
import Levels.State;
import MVC.Controller;
import Observer.MyObserver;
import Observer.MySubject;
import World.CircusOfPlates;
import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swidan on 14/05/17.
 */
public class PlatesIterator implements MySubject,Iterator {
    //State state = new LevelState();
    private CircusOfPlates circusOfPlates;
    private ClownPlatesFactory cpp;
    private static GameObject previousPlate;
    private static GameObject rightPreviousPlate;
    private static int score = 0;

    public static int getScore() {
        return score;
    }

    private static int stackFlag = 0;
    public State state = new LevelState();
    private static int c;
    private static int leftplatesCount;
    private ClownLeftHand clh;
    private ClownRightHand clr;
    private boolean timeout;
    private static int MAX_TIME = 1 * 90 * 1000;
    private static long startTime = System.currentTimeMillis();
    private static int stackflagright = 0;
    private static List<Shape> leftPlatesColors = new ArrayList<>();
    private static List<Shape> rightPlatesColors = new ArrayList<>();

    public PlatesIterator(CircusOfPlates circusOfPlates, ClownPlatesFactory cpp, ClownLeftHand clh, ClownRightHand clr, State state,int c) {
        this.circusOfPlates = circusOfPlates;
        this.cpp = cpp;
        this.c = c;
        this.state = state;
        this.clh = clh;
        this.clr = clr;
        if (stackFlag == 0)
            previousPlate = clh;
        if (stackflagright == 0)
            rightPreviousPlate = clr;
    }

    @Override
    public boolean hasNext() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;
        return timeout;

    }

    @Override
    public GameObject next(GameObject m) {
        Shape k = (Shape) m;
        Shape z = (Shape) m;

        ClownRightHand clownRightHand = (ClownRightHand) circusOfPlates.getControlableObjects().get(2);

        ClownLeftHand clownLeftHand = (ClownLeftHand) circusOfPlates.getControlableObjects().get(1);
        if (k.getPosition().equals(PlatePos.DOWN_LEFT))
            downLeftState(m);
        else if (k.getPosition().equals(PlatePos.DOWN_RIGHT))
            downRightState(m);
        else if (k.getPosition().equals(PlatePos.UP_LEFT))
            upLeftState(m);
        else if (k.getPosition().equals(PlatePos.UP_RIGHT))
            upRightState(m);
        if (m.isVisible()) {

            if (circusOfPlates.intersect(previousPlate, m)) {

                //if(previousPlate instanceof PlateShape)
              //  System.out.println(((PlateShape)previousPlate).getY());
                stackFlag++;
                MyObserver n = cpp.acquirePlate(((Shape) m).getColor(),m.toString());

                n.setSubject(this);
                register(n,"left");
                n.setX(clownLeftHand.getX() + 25);

                if (n != null) {
                    n.setY(clownLeftHand.getY() + clownLeftHand.getPlatesHeight()+10);
                   if(previousPlate instanceof ClownLeftHand)
                    n.setX(clownLeftHand.getX() + 25);
                    else n.setX(previousPlate.getX());
                    clownLeftHand.incPlatesHeight();
                    ((Shape)n).setHorizMovPlateFlag(true);
                    circusOfPlates.getControlableObjects().add(n);
                    if (k.getPosition().equals(PlatePos.UP_LEFT)) {
                        m.setY(15);
                        m.setX(-1 - (int) (Math.random() * 100));
                        //System.out.println(k.getPosition() + " " + m.getY());

                    } else if (k.getPosition().equals(PlatePos.UP_RIGHT)) {
                        m.setY(15);
                        m.setX(circusOfPlates.getWidth() + (int) (Math.random() * 100));
                        //System.out.println(k.getPosition() + " " + m.getY());
                    } else if (k.getPosition().equals(PlatePos.DOWN_RIGHT)) {
                        m.setY(95);
                        m.setX(circusOfPlates.getWidth() + (int) (Math.random() * 100));
                        //System.out.println(k.getPosition() + " " + m.getY());
                    } else if (k.getPosition().equals(PlatePos.DOWN_LEFT)) {
                        m.setY(95);
                        m.setX(-1 - (int) (Math.random() * 100));
                        // System.out.println(k.getPosition() + " " + m.getY());
                    }
                    ( (Shape) n).setleft(true);

                    if (  leftPlatesColors.size() > 2) {
                        if (leftPlatesColors.get(leftPlatesColors.size() - 2).getColor() ==
                                leftPlatesColors.get(leftPlatesColors.size() - 3).getColor()
                                && leftPlatesColors.get(leftPlatesColors.size() - 2).getColor() ==
                                leftPlatesColors.get(leftPlatesColors.size() - 1).getColor()) {

                           // System.out.println("entered if");
                            notifyObservers("left");
                            circusOfPlates.getControlableObjects().remove(leftPlatesColors.get(leftPlatesColors.size() - 1));
                            circusOfPlates.getControlableObjects().remove(leftPlatesColors.get(leftPlatesColors.size() - 2));
                            circusOfPlates.getControlableObjects().remove(leftPlatesColors.get(leftPlatesColors.size() - 3));

                            unregister(leftPlatesColors.get(leftPlatesColors.size() - 1),"left");
                            unregister(leftPlatesColors.get(leftPlatesColors.size() - 1),"left");
                            unregister(leftPlatesColors.get(leftPlatesColors.size() - 1),"left");
                              clownLeftHand.decPlatesHeight();
                            if (leftPlatesColors.size() == 0)
                                previousPlate = clh;
                            else previousPlate = leftPlatesColors.get(leftPlatesColors.size()-1);
                            score += 30;
                            return m;
                        }
                    }





                    //score++;    // gain score
                }
                score += 10;
                previousPlate = leftPlatesColors.get(leftPlatesColors.size() - 1);

            }



        }
        if (m.isVisible()) {

            if (circusOfPlates.intersect(rightPreviousPlate, m)) {
               // if(rightPreviousPlate instanceof PlateShape)
                   // System.out.println(((PlateShape)rightPreviousPlate).getY());
                stackflagright++;
                MyObserver a= cpp.acquirePlate(((Shape) m).getColor(),m.toString());
                a.setSubject(this);
                register(a,"right");
             //   a.setX(clownRightHand.getX() +25);

                if (a != null) {
                    a.setY(clownRightHand.getY() + clownRightHand.getPlatesHeight()+10);
                    if(rightPreviousPlate instanceof ClownRightHand)
                    a.setX(clownRightHand.getX() +25);
                    else
                        a.setX(rightPreviousPlate.getX());
                    clownRightHand.incPlatesHeight();
                    ((Shape)a).setHorizMovPlateFlag(true);
                    circusOfPlates.getControlableObjects().add(a);
                    if (k.getPosition().equals(PlatePos.UP_LEFT)) {
                        m.setY(15);
                        m.setX(-1 - (int) (Math.random() * 100));
                        //System.out.println(k.getPosition() + " " + m.getY());

                    } else if (k.getPosition().equals(PlatePos.UP_RIGHT)) {
                        m.setY(15);
                        m.setX(circusOfPlates.getWidth() + (int) (Math.random() * 100));
                        //System.out.println(k.getPosition() + " " + m.getY());
                    } else if (k.getPosition().equals(PlatePos.DOWN_RIGHT)) {
                        m.setY(95);
                        m.setX(circusOfPlates.getWidth() + (int) (Math.random() * 100));
                        //System.out.println(k.getPosition() + " " + m.getY());
                    } else if (k.getPosition().equals(PlatePos.DOWN_LEFT)) {
                        m.setY(95);
                        m.setX(-1 - (int) (Math.random() * 100));
                        // System.out.println(k.getPosition() + " " + m.getY());
                    }
                    ((Shape) a).setRight(true);


                    if (  rightPlatesColors.size() > 2) {
                        if (rightPlatesColors.get(rightPlatesColors.size() - 2).getColor() ==
                                rightPlatesColors.get(rightPlatesColors.size() - 3).getColor()
                                && rightPlatesColors.get(rightPlatesColors.size() - 2).getColor() ==
                                rightPlatesColors.get(rightPlatesColors.size() - 1).getColor()) {

                            //System.out.println("entered if");
                            notifyObservers("right");
                            circusOfPlates.getControlableObjects().remove(rightPlatesColors.get(rightPlatesColors.size()-1));
                            circusOfPlates.getControlableObjects().remove(rightPlatesColors.get(rightPlatesColors.size()-2));
                            circusOfPlates.getControlableObjects().remove(rightPlatesColors.get(rightPlatesColors.size()-3));

                            unregister(rightPlatesColors.get(rightPlatesColors.size() - 1),"right");
                            unregister(rightPlatesColors.get(rightPlatesColors.size() - 1),"right");
                            unregister(rightPlatesColors.get(rightPlatesColors.size() - 1),"right");
                             clownRightHand.decPlatesHeight();
                            if (rightPlatesColors.size() == 0)
                                rightPreviousPlate = clr;
                            else rightPreviousPlate = rightPlatesColors.get(rightPlatesColors.size()-1);
                            score += 30;
                            return m;
                        }
                    }




                    //score++;    // gain score
                }
                score += 10;
                rightPreviousPlate = rightPlatesColors.get(rightPlatesColors.size() - 1);

            }}



        return m;
    }


    private void downLeftState(GameObject m) {
        //PlateShape k = (PlateShape) m;
        //score++;
        // System.out.println(c);
        m.setY(m.getY() + 1);
        // System.out.println(m.getX());
        if (m.getX() != 150) {
            m.setY(85);
            m.setX(m.getX() + 1);
        }
        if (m.getX() == 150) {
            // state.setState(1);

            if (Controller.getLevel() == "WorldClass")
                CircusOfPlates.setC( 2);
            else if (Controller.getLevel() == "Beginner")
                CircusOfPlates.setC( 0);

            else if (Controller.getLevel() == "Professional")
                CircusOfPlates.setC( 1);

            //  System.out.println(c);

            if(CircusOfPlates.getC()==0&&score>450)
                CircusOfPlates.setC(1);
            if(CircusOfPlates.getC()==1&&score>750)
            {CircusOfPlates.setC(2);
                }
            for (int i = 0; i < CircusOfPlates.getC(); i++) {
               // System.out.println("c===" + CircusOfPlates.getC());
                m.setX(150);
                m.setY(m.getY() + 1);
            }

        }
        if (m.getY() == circusOfPlates.getHeight()) {
            // reuse the star in another position
            // platePool.release((PlateObject) m);
            m.setY(95);
            m.setX(-1);
        }
    }

    private void downRightState(GameObject m) {
        m.setY(m.getY() + 1);

        if (m.getX() != 592) {
            m.setY(85);
            m.setX(m.getX() - 1);
        }

        if (m.getX() == 592) {
            // state.setState(1);
            if (Controller.getLevel() == "WorldClass")
                CircusOfPlates.setC( 2);
            else if (Controller.getLevel() == "Beginner")
                CircusOfPlates.setC( 0);

            else if (Controller.getLevel() == "Professional")
                CircusOfPlates.setC( 1);
            if(CircusOfPlates.getC()==0&&score>450)
                CircusOfPlates.setC(1);
            if(CircusOfPlates.getC()==1&&score>750)
            {CircusOfPlates.setC(2);
            }
            for (int z = 0; z < CircusOfPlates.getC(); z++) {
                m.setX(592);
                m.setY(m.getY() + 1);

            }
            // m.setX((int)(Math.random() * getWidth()));
        }

        if (m.getY() == circusOfPlates.getHeight()) {
            // reuse the star in another position
            // platePool.release((PlateObject) m);
            m.setY(95);
            m.setX(circusOfPlates.getWidth());
        }
    }

    private void upLeftState(GameObject m) {
        m.setY(m.getY() + 1);
        //  System.out.println(m.getX());
        if (m.getX() != 315) {
            m.setY(5);
            m.setX(m.getX() + 1);
        }

        if (m.getX() == 315) {
            //  state.setState(1);
            if (Controller.getLevel() == "WorldClass")
                CircusOfPlates.setC( 2);
            else if (Controller.getLevel() == "Beginner")
                CircusOfPlates.setC( 0);

            else if (Controller.getLevel() == "Professional")
                CircusOfPlates.setC( 1);
            if(CircusOfPlates.getC()==0&&score>450)
                CircusOfPlates.setC(1);
            if(CircusOfPlates.getC()==1&&score>750)
            {CircusOfPlates.setC(2);
            }
            for (int z = 0; z < CircusOfPlates.getC(); z++) {
                m.setX(315);
                m.setY(m.getY() + 1);

            }
            // m.setX((int)(Math.random() * getWidth()));
        }

        if (m.getY() == circusOfPlates.getHeight()) {
            // reuse the star in another position
            // platePool.release((PlateObject) m);
            m.setY(15);
            m.setX(-1);
        }


    }

    private void upRightState(GameObject m) {
        m.setY(m.getY() + 1);
        //System.out.println(m.getX());
        if (m.getX() != 435) {
            m.setY(5);
            m.setX(m.getX() - 1);
        }

        if (m.getX() == 435) {
            //  state.setState(1);
            if (Controller.getLevel() == "WorldClass")
                CircusOfPlates.setC( 2);
            else if (Controller.getLevel() == "Beginner")
                CircusOfPlates.setC( 0);

            else if (Controller.getLevel() == "Professional")
                CircusOfPlates.setC( 1);
            if(CircusOfPlates.getC()==0&&score>450)
                CircusOfPlates.setC(1);
            if(CircusOfPlates.getC()==1&&score>750)
            {CircusOfPlates.setC(2);
            }
            for (int z = 0; z < CircusOfPlates.getC(); z++) {
                m.setX(435);
                m.setY(m.getY() + 1);

            }
            // m.setX((int)(Math.random() * getWidth()));
        }
        if (m.getY() == circusOfPlates.getHeight()) {
            // reuse the star in another position
            // platePool.release((PlateObject) m);
            m.setY(15);
            m.setX(circusOfPlates.getWidth());
        }
    }


    @Override
    public void register(MyObserver obj, String s) {
if(s.equals("left"))
    leftPlatesColors.add((Shape) obj);




if(s.equals("right"))
    rightPlatesColors.add((Shape) obj);


}

    @Override
    public void notifyObservers(String s) {
if(s.equals("left"))
{leftPlatesColors.get(leftPlatesColors.size() - 1).update();
        leftPlatesColors.get(leftPlatesColors.size() - 2).update();
        leftPlatesColors.get(leftPlatesColors.size() - 3).update();}
        if(s.equals("right"))
        { rightPlatesColors.get(rightPlatesColors.size() - 1).update();
        rightPlatesColors.get(rightPlatesColors.size() - 2).update();
        rightPlatesColors.get(rightPlatesColors.size() - 3).update();}
    }

    @Override
    public void unregister(MyObserver obj, String s) {
        if(s.equals("left"))
        { leftPlatesColors.remove(obj);
        ((Shape)obj).setHorizMovPlateFlag(false);
            //cpp.release((Shape) obj,((Shape)obj).toString());
        }

        if(s.equals("right"))
        { rightPlatesColors.remove(obj);
            ((Shape)obj).setHorizMovPlateFlag(false);
           //cpp.release((Shape) obj,((Shape)obj).toString());
        }

    }
}

