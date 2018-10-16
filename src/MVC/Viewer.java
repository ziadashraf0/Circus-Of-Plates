package MVC;

import Levels.LevelState;
import Levels.State;
import eg.edu.alexu.csd.oop.game.GameEngine;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by AHMED ESSAM on 5/21/2017.
 */
public class Viewer {
    public JFrame jFrame=new JFrame("Circus Of Plates");
    Controller controlling;
    public static int x=0;
    public void CreateUI() throws IOException {

        jFrame.setBounds(430,70,600,600);
       // JPanel panel=new JPanel();
        //panel.setLayout(null);
        JLabel label=new JLabel();
        label.setText("Welcome To Circus Of Plates");
        label.setSize(280,20);
        label.setLocation(180,10);
        JLabel label1=new JLabel();
        label1.setText("Enter Player Name :");
        label1.setSize(200,20);
        label1.setLocation(10,100);
        label1.setBackground(Color.LIGHT_GRAY);
        JTextField textField=new JTextField();
        textField.setSize(220,25);
        textField.setLocation(300,90);
        textField.setToolTipText("This Field must be filled");
        textField.setEnabled(true);
        textField.setEditable(true);
        textField.setFocusable(true);
        //panel.add(textField);
        BufferedImage MyPicture = ImageIO.read(new File("./res/Back.jpg"));
        JLabel background = new JLabel(new ImageIcon(MyPicture));
        jFrame.setContentPane(background);
        background.setLayout(null);
        background.add(textField);
        JButton button=new JButton("Submit");
        button.setSize(80,60);
        button.setLocation(350,435);
        JLabel label2=new JLabel();
        label2.setText("Difficulty :");
        label2.setSize(300,190);
        label2.setLocation(10,115);
        background.add(label2);
        String[] levels = {"Beginner", "Professional", "WorldClass"};
        JComboBox levelList = new JComboBox(levels);
        levelList.setForeground(Color.RED);
        levelList.setSize(200,25);
        levelList.setLocation(300,190);
        background.add(levelList);
        JLabel label3=new  JLabel();
        label3.setText("Choose Your Game Plates :");
        label3.setSize(300,190);
        label2.setForeground(Color.RED);
        label1.setForeground(Color.RED);
        label.setForeground(Color.RED);
        label3.setForeground(Color.RED);
        label3.setLocation(10,200);
        background.add(label3);
       JCheckBox checkbox1 = new JCheckBox("PlateShape");
        checkbox1.setBounds(300,280, 100,25);
        JCheckBox checkbox2 = new JCheckBox("newShape");
        checkbox1.setForeground(Color.RED);
        checkbox2.setForeground(Color.RED);
        checkbox2.setBounds(300,320, 100,25);
        //JCheckBox checkbox3 = new JCheckBox("Balls");
        //checkbox3.setBounds(300,240, 70,50);
        background.add(checkbox1);
        background.add(checkbox2);
        //panel.add(checkbox3);
        BufferedImage myPicture = ImageIO.read(new File("./res/newGameButton1.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(10,390,300,150);
        background.add(picLabel);
        BufferedImage myPicture1 = ImageIO.read(new File("./res/logo2.png"));
        jFrame.setIconImage(myPicture1);

        java.util.List<String> Shapes = new ArrayList<String>();
        ActionListener actionListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = "";
                String Level ="";
                name = textField.getText();
                Level =String.valueOf(levelList.getSelectedItem());

                State l=new LevelState();
                if(levels.equals("Beginner"))
                {
                    l.setState(0);
                    x=l.getState();
                }
                else if(levels.equals("Professional"))
                {
                    l.setState(1);
                    x=l.getState();
                }
                else if(levels.equals("WorldClass"))
                {
                    l.setState(2);
                    x=l.getState();
                }
                int i=0;
                if(checkbox1.isSelected())
                {
                    Shapes.add(i,checkbox1.getText());
                    i++;
                }
                else
                {
                    Shapes.add(i,checkbox2.getText());
                    i++;
                }
                if(checkbox2.isSelected())
                {
                    Shapes.add(i,checkbox2.getText());
                    i++;
                }
                else
                {
                    Shapes.add(i,checkbox1.getText());
                    i++;
                }
                //if (checkbox3.isSelected())
                //{
              //      Shapes.add(i,checkbox3.getText());
            //        i++;
          //
                Controller.setName(name);
                Controller.setLevel(Level);
                Controller.setShapes(Shapes);
                //System.out.println("name="+name.toString()+"\nLevel="+Level.toString()+"\nShapes="+Shapes.get(0).toString()+"\nShapes="+Shapes.get(1).toString());
                jFrame.dispose();
                JMenuBar menuBar = new JMenuBar();
                ;
                JMenu menu = new JMenu("File");

                State state = new LevelState();

                JMenuItem newMenuItem = new JMenuItem("New");
                JMenuItem pauseMenuItem = new JMenuItem("Pause");
                JMenuItem resumeMenuItem = new JMenuItem("Resume");
                //JMenuItem level1 = new JMenuItem("levelProceeder");



                menu.add(newMenuItem);
                //menu.add(level1);

                menu.addSeparator();
                menu.add(pauseMenuItem);
                menu.add(resumeMenuItem);
                menuBar.add(menu);
                final GameEngine.GameController gameController = GameEngine.start("Circus Of Plates",
                        World.CircusOfPlates.getInstance(800, 700,state,x,
                                Shapes.get(0),
                                Shapes.get(1)), menuBar, Color.lightGray);
		/*	try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/home/ziad/Desktop/Sia-CheapThrills.mp3").getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch(Exception ex) {
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}*/
                newMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameController.changeWorld(World.CircusOfPlates.getInstance(400, 700,state,x,Shapes.get(0),Shapes.get(1)));
                    }
                });
                pauseMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        gameController.pause();
                    }
                });

                resumeMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gameController.resume();
                    }
                });


            }
        };
        button.setForeground(Color.RED);
        button.setBackground(Color.YELLOW);
        button.addActionListener(actionListener);
        // System.out.println("name="+name.length+"Level="+Level+"Shapes="+Shapes);
        background.add(button);
        background.add(label1);
        background.add(label);
        //jFrame.add(background);
        //jFrame.pack();
        jFrame.setVisible(true);

    }
}
