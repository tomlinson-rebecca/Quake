import java.util.ArrayList;

import objectdraw.*;

/**
 * Will manager which level of the story we are on.
 * Like a branch that offers lesser branches. Should
 * simplify too many if-else stuffs in Controller. 
 * Maybe will store each scenario.
 * Each situation could be assigned a number, binary tree style?
 * @author Beaky
 *
 */
public class StoryManager {
	 DrawingCanvas canvas;
	 int bigSize = 32; //for story details
	 int lvl = 1; 
	 String level = "start"; //the level of the game. Starts at start.
	 
	 //formatting for levels will be string 1,2,3... and choices are 1A.
	 //to get integer format, use lvl?
	 
	 ArrayList<TextItem> stories = new ArrayList<TextItem>();
	 
	 public StoryManager(DrawingCanvas canvas){
		 this.canvas = canvas;
	 }
	 //must use enums so switch-case-break works on string
	 public enum ID{
		 _1, _2A, NONE, NULL, start;
	 }
	 
	 /**
	  * Display all the text for this current level
	  * @return an array of these textItems
	  */
	 public ArrayList<TextItem> playStory(String level){
		 
		 ArrayList<TextItem> texts = new ArrayList<TextItem>();
		 
		 //update level
		 this.level = level;
		 
		 ID lvl = ID.valueOf(level);
		 
		 switch(lvl){
		 
		 case NULL:
			 return texts;
			 
			 
		 //first ever choice
		 case _1:
			//create the actual text items here  
			texts.add(new TextItem(new String[]{"What a lovely spring day at the University of \n ",  
					"California, San Diego. Or, it would be a nice day, ",
					"if you weren't stuck inside studying.",
					"So boring, I am sorry. What are you working on?"}, "NULL", 
					bigSize, 20, 20, canvas));
			
			texts.add(new TextItem("A) Studying for Finals!", "_2A",
					bigSize, 20, 250, canvas));
			texts.add(new TextItem("B) Working on my CAT 125 Project.", "_2B",
					bigSize, 20, 300, canvas));
			texts.add(new TextItem("C) Doing my homework...", "_2C",
					bigSize, 20, 350, canvas));
			texts.add(new TextItem("D) I was supposed to be working? Woops lmao", "_2D",
					bigSize, 20, 400, canvas));
			
			break;
			
		 case _2A:
			 texts.add(new TextItem("You have chosen choice A!!", "null", 
						bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Choice 1.", "_3A",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Choice 2.", "_3B",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("C) Choice 3.", "_3C",
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("D) Choice 4.", "_3D",
						bigSize, 20, 400, canvas));
			 
			 break;
			
		 }
		 
		 
		 return texts;
		 
	 }
	 
	
	 
	 //an option has been chosen, from 1 to 4?
	 public void choose(String option){
		 
	 }
	 
	public boolean isValidChoice(String choice){
		 
		boolean valid = false;  
		try {
				 ID lvl = ID.valueOf(choice);
				 valid = true;
	
				
		 } catch (IllegalArgumentException e){
			 System.out.println("Invalid button press.");
			 valid = false;
			 
		 }
		
		return valid;
		 
	 }
	
	 
	 /**
	  * Returns the current level we are on, story wise. Ex: first screen is lvl 1, etc...
	  * @return
	  */
	 public String getLevel(){
		 return level;
	 }
	 
	 /*
	 public void incrementLevel(){
		 //increment level to a string
		 int lvl = Integer.parseInt(level);
		 lvl++;
		 level = new String("" + lvl);
	 }
	 */
	
}