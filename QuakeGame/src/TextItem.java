//TODO download github. Make textitem an interafcae. 
//Displays a black screen with words on it. Maybe word displays can be classes.
import java.awt.*;
import objectdraw.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objectdraw.DrawingCanvas;
import objectdraw.WindowController;

/*
 * Canvas will be responsible for listening for key stuff. When key stuff happens,
 * diff text scenarios will be displayed.
 * @author Beaky
 *
 */
public class TextItem extends ActiveObject {
	
	String id; //used to identify a unique choice. 
	//What could happen is each text has a unique id, so instead of levels we have
	//ids to determine the branch
	
	public int PAUSE_TIME = 250; //pause length in MS
	
	double move_dir_x = 5; //move in a circle
	double move_dir_y = 0;
	double movTot = 1;
	
	int colorLevel = 255; //starts at white
	int colorChanger = 10;
	
	int x;
	int y; 
	int size;
	Text text;
	String words;
	String font;
	
	boolean shouldRun = true;
	String style = "NONE"; //SHAKER for a shaking text, FADER for a fading text
	String shakingObject = "SHAKER";
	String fadingObject = "FADER";
	
	/**
	 *  
	 * @param words Text to be displayed
	 * @param size Font size
	 * @param x position of corner?
	 * @param y position of corner?
	 * @param aniStyle animation style
	 * @param aniSpeed animation speed 
	 * @param canvas
	 */
	public TextItem(String words, String id, int size, int x, int y,
			String aniStyle, int aniSpeed, String font, DrawingCanvas canvas){
		
		this.x = x;
		this.y = y;
		this.size = size;
		
		this.words = words;
		this.font = font;
		//call break up string to make multiple text labels
		
		
		text = new Text(words, x, y, canvas);
		
		
		text.setColor(Color.BLACK);
		text.setFontSize(size);
		
		text.hide();
		text.setFont(font);
		
		
		
		
		this.id = id;
		
		//set the movement style
		this.style = new String(aniStyle);
		
		
		PAUSE_TIME = aniSpeed; 
		
		start();
	}
	
	
	public TextItem(String words,String id, int size, int x, int y
			, String font, DrawingCanvas canvas){
		
		
		this(words, id, size, x, y, "NONE", 0, font, canvas);
	}
	
	//no font entered
	public TextItem(String words, String id, int size, int x, int y
			, DrawingCanvas canvas){
		
		
		this(words, id, size, x, y, "NONE", 0, Font.MONOSPACED, canvas);
	}
	
	//Does all the style stuff to this piece of text
	public void initText(Text text){
		text.setColor(Color.BLACK);
		text.setFontSize(size);
		text.hide();
		text.setFont(font);
	}

	
	
	public void setID(String id){
		this.id = id;
	}
	
	public String getID(){
		return id;
	}
	
	public void clear(){
		
		shouldRun = false;
		
	}
	
	//breaks a string into multiple text labels on new lines of length maxWidth
	public void breakText(String text, int maxWidth){
		int lastSpace = 0;
		String sub = text;
		//find earliest space before it goes over, break it there
		for(int i = 0; i < text.length(); i++){
			char currChar = text.charAt(i);
			
			//we want to make a substring less than maxWidth
			if(i % maxWidth <= 1){
				//if this character is a space, save the index
				if(currChar == ' '){
					lastSpace = i;
				}
			} else {
				//make a text label up until the last space
				sub = sub.substring(lastSpace);
				//i = lastSpace?
				
			}
			
		}
		
	}
	
	public void run(){
	
			text.show();
			//fades the text out before it disappears
			Color currCol = text.getColor();
			
			int r = currCol.getRed();
			while(r < 255){
				
				text.setColor(new Color(r, r, r));
				r += 25;
				pause(100); 

			}
			
			text.setColor(Color.WHITE);
		
		//shake the text
		while(shouldRun){
			
			if(style.equals(shakingObject)){
				
				//movTot *= -0.005;
				
				move_dir_x *= -1;
				//move_dir_y *= -1;
				move_dir_x *= movTot;
				
				text.move(move_dir_x, move_dir_y);
			}
			
			if(style.equals(fadingObject)){
				
				colorLevel -= colorChanger;
				
				//switch fade direction
				if(colorLevel <= 20 || colorLevel >= 255){
					colorChanger *= -1;
				}		
				
				text.setColor(new Color(colorLevel, colorLevel, colorLevel));
				
			}
			
			pause(PAUSE_TIME);
			
		}
		
		//fades the text out before it disappears
		currCol = text.getColor();
		
		r = currCol.getRed();
		while(r > 0){
			
			text.setColor(new Color(r, r, r));
			r -= 25;
			pause(100); 

		}
		
		text.hide();
		
		
		
	}
	
}