package DynamicLinkage;

import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by swidan on 19/05/17.
 */
public class DynamicLinkage {


    public Class<?> load(String s)
        {


            try {
                File file = new File("./Shapes/"+s);
                //System.out.println(file.toURL());
                URL url[] = { file.toURI().toURL() };
                URLClassLoader child = new URLClassLoader(url, this.getClass().getClassLoader());
                //String className = JOptionPane.showInputDialog(null, "Enter class name");
                Class classToLoad = Class.forName( "Object."+s, true, child);
                return classToLoad;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }



}
