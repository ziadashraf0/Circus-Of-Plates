package Levels;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by AHMED ESSAM on 5/14/2017.
 */
public class Originator {
    private static SnapShot snapShot;
    private static int co=0;
    public static SnapShot getSnapShot() {
        return snapShot;
    }

    public static void setSnapShot(SnapShot snapShot) {
        Originator.snapShot = snapShot;
    }

    public static void save(SnapShot snapShot)
    {
        try {

            File file=new File("./res\\Scores.txt");
            FileWriter fileWriter=new FileWriter(file,true);
            CharSequence name=snapShot.getName();
            int score =snapShot.getScore();
            if(co==0)
            {
                fileWriter.write(name+"\n"+score+"\n");
            }
            else {
            fileWriter.append(name+"\n"+score+"\n");}
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void restore(HashMap<String,Integer> Map){
        try {
            int indicator=1;
            int score=0;
            String Name="";
            File file=new File("./res\\Scores.txt");
            Scanner reader=new Scanner(file);
            String l;
            while(reader.hasNextLine())
            {
                if(indicator%2==0)
                {
                    score=Integer.parseInt(reader.nextLine());
                }
                else {
                    Name=reader.nextLine();
                }
            indicator++;
                Map.put(Name,score);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}