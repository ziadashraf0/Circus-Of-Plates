package Main;

import Levels.LevelState;
import Levels.State;
import MVC.Controller;
import MVC.Viewer;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
	public static State state=new LevelState() ;
	public static int x;
	 private static String level= Controller.getLevel();
		public static void main(String[] args) throws IOException {


			Viewer viewer=new Viewer();
			viewer.CreateUI();

			/*if(level.equals("Beginner"))
			{
				 state.setState(0);
				 x=state.getState();
			}
			else if(level.equalsIgnoreCase("Professional"))
			{
				state.setState(1);
				x=state.getState();
			}
			else if(level.equalsIgnoreCase("WorldClass"))
			{
				state.setState(2);
				x=state.getState();
			}
*/
			//state.setState(2);
			///x=state.getState();
		/*JMenuBar menuBar = new JMenuBar();
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
		final GameController gameController = GameEngine.start("Very Simple Game in 99 Line of Code",
				World.CircusOfPlates.getInstance(800, 700,state,x,
						JOptionPane.showInputDialog(null,"Enter Shape"),
				JOptionPane.showInputDialog(null,"Enter Shape")), menuBar, Color.lightGray);
		*//*	try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/home/ziad/Desktop/Sia-CheapThrills.mp3").getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch(Exception ex) {
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}*//*
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.changeWorld(World.CircusOfPlates.getInstance(400, 700,state,x,JOptionPane.showInputDialog(null,"Enter Shape"),JOptionPane.showInputDialog(null,"Enter Shape")));
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

	}*/}}
