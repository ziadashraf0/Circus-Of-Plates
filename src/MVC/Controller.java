package MVC;
import World.*;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AHMED ESSAM on 5/21/2017.
 */
public class Controller implements ItemListener{
    private static List<String> Shapes=new ArrayList<>();
    private static String Name;
    private static String Level;
    private JCheckBox box1;
    private JCheckBox box2;
    private int Flag=0;
    private static  String playerName;
    private String level;

    public Controller(){}
    public Controller(JCheckBox box1, JCheckBox box2) {
        this.box1=box1;
        this.box2=box2;
    }

    public static String getLevel() {
        return Level;
    }

    public static void setLevel(String level) {
        Level = level;
    }

    public static String getName() {
        return Name;
    }

    public static void setName(String name) {
        Name = name;
    }

    public int getFlag() {
        return Flag;
    }

    public void setFlag(int flag) {
        Flag = flag;
    }
    public static List<String> getShapes() {
        return Shapes;
    }
    public static void setShapes(List<String> shapes) {
        Shapes = shapes;
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if(box1.isSelected()&& box2.isSelected()){
            Flag=3;       //both plates are picked to be Played with.
        }else if(box2.isSelected()){
            Flag=2;       //only the OvalPlate is chosen.
        }else if(box1.isSelected()){
            Flag=1;       //only the rectanglePlate is chosen.
        }
    }

    //public void setPlayerName(String name){
      //  model.setName(name);
    //}

    //public String getStudentName(){
      //  return model.getName();
    //}

    //public void setStudentRollNo(String rollNo){
      //  model.setRollNo(rollNo);
    //}

    //public String getStudentRollNo(){
      //  return model.getRollNo();
    //}

    //public void updateView(){
      ///  view.printStudentDetails(model.getName(), model.getRollNo());
    //}
}
