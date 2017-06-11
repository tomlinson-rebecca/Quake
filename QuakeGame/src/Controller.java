//Displays a black screen with words on it. Maybe word displays can be classes.
//Curr problem: how to wrap text? Maybe need a JPanel. Also Git pls
import java.awt.*;
import objectdraw.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import objectdraw.DrawingCanvas;
import objectdraw.WindowController;

/*
 * Canvas will be responsible for listening for key stuff. When key stuff happens,
 * diff text scenarios will be displayed.
 * @author Beaky
 */
public class Controller extends WindowController
	implements ActionListener, KeyListener {
	
	//keeps track of all objects on the screen. Will be used to clear them.
	ArrayList<TextItem> displayedTexts = new ArrayList<TextItem>();
	
	public final static int WIDTH = 1024;
	public final static int HEIGHT = 600;
	public final static int TITLE_OFFSET_X = 200;
	public final static int TITLE_OFFSET_Y = 40;
	
	public StoryManager storyManager;
	
	//calling Acme.Mainframe makes the canvas appear
	public static void main(String[] args){
		
		 new Acme.MainFrame(new Controller(), args,
		    	WIDTH,
		    	HEIGHT);
		
	}
	
	//WindowController initializes stuff
	public void begin(){
		FilledRect blackSquare = new FilledRect(0,0,WIDTH,HEIGHT, canvas);
		blackSquare.setColor(Color.BLACK);
		blackSquare.sendToBack(); //put it in the wayyyy back
		//JPanel mainText = new JPanel();
		
		int welcomeSize = 200;
		int medSize = 100;
		TextItem welcome = new TextItem("QUAKE", "NULL", welcomeSize,
				TITLE_OFFSET_X,
				TITLE_OFFSET_Y, 
				"SHAKER", 150, 
				canvas);
		
		displayedTexts.add(welcome);
		
		TextItem threat = new TextItem("Can you survive a major earthquake?", "NULL", 32,
				190, 300, canvas);
		displayedTexts.add(threat);

		TextItem startMsg = new TextItem("Click any button to start.","NULL", 32, 275, 400,
				"FADER", 75 , canvas);
		displayedTexts.add(startMsg);
		TextItem author = new TextItem("Made by Rebecca Tomlinson for CAT 125","NULL", 16, 650, 575,
				canvas);
		displayedTexts.add(author);

		//init story manager
		storyManager = new StoryManager(canvas);
		
		//makes button pressed visible
	  	canvas.requestFocusInWindow();

		
		//controller will listen for key pressed
		canvas.addKeyListener(this);
		
	}
	
	/**
	 * Remove all objects on the screen
	 */
	public void clearScreen(){
		
		for(int i = displayedTexts.size() - 1; i >= 0; i--){
			
			//clear the object and remove it from list
			displayedTexts.get(i).clear();
			displayedTexts.remove(i);
			
		}
		
	}
	
	//ScenarioManager?

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	  	canvas.requestFocusInWindow();

		// TODO Auto-generated method stub
		//pressing option alerts ScenarioManager, which keeps track of where we are in story
		
	}
	
	/**
	 * Releasing a button will change the scene.
	 * @param arg0
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		//initial choice is the level.
		//A naked level with no letters indicates a branch w/ no choices
		int keyCode = arg0.getKeyCode();

		String choice = "NULL";
		
		try { 
			
			//choice A is placed at position 1 in the array
			if(keyCode == KeyEvent.VK_A){
				//get the id from here
				choice = displayedTexts.get(1).getID(); 
								
			} else if(keyCode == KeyEvent.VK_B){
				//get the id from here
				choice = displayedTexts.get(2).getID(); 
								
			} else if(keyCode == KeyEvent.VK_C){
				//get the id from here
				choice = displayedTexts.get(3).getID(); 
								
			} else if(keyCode == KeyEvent.VK_D){
				//get the id from here
				choice = displayedTexts.get(4).getID(); 
			} else if(keyCode == KeyEvent.VK_E){
				//get the id from here
				choice = displayedTexts.get(5).getID(); 
				
			//the case when there are no choices available
			} else if(keyCode == KeyEvent.VK_ENTER &&
					displayedTexts.size() <= 2){
				choice = displayedTexts.get(displayedTexts.size()-1).getID();
				
			//special case for the first screen
			} else if(storyManager.getLevel().equals("start")){
				choice = "_1"; 
			}
			
			//if no valid button press has been made, don't do anything
			if(!(choice.equals("NULL")) && storyManager.isValidChoice(choice)){
				//remove stuff
				clearScreen();
				System.out.println("choice  "+ choice);
				displayedTexts = storyManager.playStory(choice);
				
				
				//time to show the next level
				//storyManager.incrementLevel();
			}

			
			
		} catch (IndexOutOfBoundsException index ){
			//in situations where a 4th choice is picked, but only 3 available,
			//error is caught and nothing should happen.
			System.out.println("ERROR array out of bounds ");
		}
		
		
		//display the next texts?
		//displayedTexts = storyManager.playStory(storyManager.getLevel());
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}