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
	 String level = "1"; //the level of the game. Starts at start.
	 
	 //formatting for levels will be string 1,2,3... and choices are 1A.
	 //to get integer format, use lvl?
	 
	 ArrayList<TextItem> stories = new ArrayList<TextItem>();
	 
	 public StoryManager(DrawingCanvas canvas){
		 this.canvas = canvas;
	 }
	 
	 /**
	  * Display all the text for this current level
	  * @return an array of these textItems
	  */
	 public ArrayList<TextItem> playStory(String level){
		 
		 ArrayList<TextItem> texts = new ArrayList<TextItem>();
		 
		 
		 if(level.equals("")){
			 return texts;
		 }
		 
		 //first ever choice.
		 if(level.equals("1")){
			//create the actual text items here  
			texts.add(new TextItem(new String[]{"What a lovely spring day at the University of \n ",  
					"California, San Diego. Or, it would be a nice day, ",
					"if you weren't stuck inside studying.",
					"So boring, I am sorry. What are you working on?"}, "null", 
					bigSize, 20, 20, canvas));
			
			texts.add(new TextItem("A) Choice 1.", "2A",
					bigSize, 20, 250, canvas));
			texts.add(new TextItem("B) Choice 2.", "2B",
					bigSize, 20, 300, canvas));
			texts.add(new TextItem("C) Choice 3.", "2C",
					bigSize, 20, 350, canvas));
			
		 }
		 
		 if(level.equals("2A")){
			 
			 texts.add(new TextItem("You have chosen choice A!!", "null", 
						bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Choice 1.", "3A",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Choice 2.", "3B",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("C) Choice 3.", "3C",
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("D) Choice 4.", "3D",
						bigSize, 20, 400, canvas));
			  
		 }
		 if(level.equals("2B")){
			 
			 texts.add(new TextItem("You have chosen choice B!!", "null", 
						bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Choice 1.", "4A",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Choice 2.", "4B",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("C) Choice 3.", "4C",
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("D) Choice 4.", "4D",
						bigSize, 20, 400, canvas));
			 
	
		 }
		 if(level.equals("CB")){
			 
			 texts.add(new TextItem("You have chosen choice C!!", "null", 
						bigSize, 20, 20, canvas));
			  
		 }
		 
		 return texts;
		 
	 }
	 
	 //an option has been chosen, from 1 to 4?
	 public void choose(String option){
		 
	 }
	
	 
	 /**
	  * Returns the current level we are on, story wise. Ex: first screen is lvl 1, etc...
	  * @return
	  */
	 public String getLevel(){
		 return level;
	 }
	 
	 public void incrementLevel(){
		 //increment level to a string
		 int lvl = Integer.parseInt(level);
		 lvl++;
		 level = new String("" + lvl);
	 }
	
}