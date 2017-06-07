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
import java.util.ArrayList;

import objectdraw.DrawingCanvas;
import objectdraw.WindowController;
//TODO: Make arrayList of texts, even for sinle text cases, so the multi-lined texts
//can change color at once. Put everythin in this oen class.
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
	public int WORD_BUFFER = 10; //extra space between lines
	double move_dir_x = 5; //move in a circle
	double move_dir_y = 0;
	double movTot = 1;
	
	int colorLevel = 255; //starts at white
	int colorChanger = 10;
	
	
	int size;
	//Text text;
	String words;
	String font = Font.MONOSPACED;
	
	boolean shouldRun = true;
	String style = "NONE"; //SHAKER for a shaking text, FADER for a fading text
	String shakingObject = "SHAKER";
	String fadingObject = "FADER";
	
	ArrayList<Text> texts = new ArrayList<Text>(); //used to animate all the lines at once
	//String[] texts;
	/**
	 * Fully animated, font specified 
	 * @param words Text to be displayed
	 * @param size Font size
	 * @param x position of corner?
	 * @param y position of corner?
	 * @param aniStyle animation style
	 * @param aniSpeed animation speed 
	 * @param canvas
	 */
	public TextItem(String[] words, String id, int size, int x, int y,
			String aniStyle, int aniSpeed, DrawingCanvas canvas){
		
		int start_x = x;
		int start_y = y;
		this.size = size;
		Text temp;
		
		//put each text in texts
		for(String word: words){
			temp = new Text(word, start_x, start_y, canvas);
			initText(temp);
			texts.add(temp);
			
			//put a new line!
			start_y += (size + WORD_BUFFER);
			
			
		}
		//this.words = words;
		//call break up string to make multiple text labels
		
		/*
		text = new Text(words, x, y, canvas);
		
		
		text.setColor(Color.BLACK);
		text.setFontSize(size);
		
		text.hide();
		text.setFont(font);
		*/
		
		
		
		this.id = id;
		
		//set the movement style
		this.style = new String(aniStyle);
		
		
		PAUSE_TIME = aniSpeed; 
		
		start();
	}
	
	
	//no font entered, multi-string. The descriptor.
	public TextItem(String[] words, String id, int size, int x, int y
			, DrawingCanvas canvas){
		
		
		this(words, id, size, x, y, "NONE", 0, canvas);
	}
	
	//single string ctor, no animations, no font
	public TextItem(String word, String id, int size, int x, int y
			, DrawingCanvas canvas){
		
		
		//creates an array from single string
		this( new String[]{word}, id, size, x, y, "NONE", 0, canvas);
	}
	
	//animated, single string
	public TextItem(String word, String id, int size, int x, int y,
			String aniStyle, int aniSpeed, DrawingCanvas canvas){
		
		this( new String[]{word}, id, size, x, y, aniStyle, aniSpeed, canvas);
		
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
	
	
	public void run(){
		//fade-in
		
		Color currCol;
		int r; 
		
		for(Text text: texts){
			
			text.show();
			//fades the text out before it disappears
			currCol = text.getColor();
			
			r = currCol.getRed();
			while(r < 255){
				
				text.setColor(new Color(r, r, r));
				r += 25;
				pause(75); 

			}
			
			text.setColor(Color.WHITE);
		}
			
		
		//shake the text
		while(shouldRun){
			
			if(style.equals(shakingObject)){
				
				//movTot *= -0.005;
				
				move_dir_x *= -1;
				//move_dir_y *= -1;
				move_dir_x *= movTot;
				
				//move all the texts
				for(Text text: texts){
				
					text.move(move_dir_x, move_dir_y);
				}
			}
			
			if(style.equals(fadingObject)){
				
				colorLevel -= colorChanger;
				
				//switch fade direction
				if(colorLevel <= 20 || colorLevel >= 255){
					colorChanger *= -1;
				}		
				
				for(Text text: texts){
					text.setColor(new Color(colorLevel, colorLevel, colorLevel));
				}
				
			}
			
			pause(PAUSE_TIME);
			
		}
		
		//fades the text out before it disappears
		currCol = Color.WHITE;
		
		r = currCol.getRed();
		while(r > 0){
			
			for(Text text: texts) {
				text.setColor(new Color(r, r, r));
			}
			
			r -= 25;
			pause(100); 
		}
		
		
		for(Text text: texts) {
		//hide each text
			text.hide();
		}
		
		
		
	}
	
}