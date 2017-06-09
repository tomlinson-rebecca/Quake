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
	 public static final int SHAKE_SPEED = 100;
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
		 
		 NONE, 
		 NULL, 
		 start,
		 tests,
		 _1, 
		 _2A,
		 _2B,
		 _2C,
		 _2D,
		 _3A,
		 _3B, _4A, _4B, _4C, _5A, _6A, _7A, _8A;
	 }
	 
	 /**
	  * Display all the text for this current level
	  * @return an array of these textItems
	  */
	 public ArrayList<TextItem> playStory(String level){
		 
		 ArrayList<TextItem> texts = new ArrayList<TextItem>();
		 String answer3A = "";
		 String answer4 = "";
		 String answer4cont = "";


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
		
	
		 case _2A: case _2C:
		 
			 texts.add(new TextItem(new String[]{"Well, good luck! Remember, C's get degrees! \n",
					 "I should probably leave you alone so you can \n",
					 "get back to work... but first, \n ",
					 "do you want to know a cool fact? "}, "NULL",
						bigSize, 20, 20, canvas));
			 
			texts.add(new TextItem("A) Yeah!!", "_3A",
						bigSize, 20, 250, canvas));
			texts.add(new TextItem("B) No!! Go away!!", "_3B",
					bigSize, 20, 300, canvas));
		
			break;
		 case _2B:
			texts.add(new TextItem(new String[]{"Isn't Ash Smith such a great professor?!! \n",
					"Oh, and Christine is the best CAT 125 TA ever!! \n",
					 "Well, I should probably leave you alone so you can \n",
					 "get back to work... but first, \n ",
					 "do you want to know a cool fact? "}, "NULL",
						bigSize, 20, 20, canvas));
			texts.add(new TextItem("A) Yeah!!", "_3A",
					bigSize, 20, 250, canvas));
			texts.add(new TextItem("B) No!! Go away!!", "_3B",
				bigSize, 20, 300, canvas));
			 
			break;
			
		 case _2D:
			texts.add(new TextItem(new String[]{"Wow, you big procrastinator. \n",
					 "Well, I should probably leave you alone so you can \n",
					 "get back to work... but first, \n ",
					 "do you want to know a cool fact? "}, "NULL",
						bigSize, 20, 20, canvas));
			texts.add(new TextItem("A) Yeah!!", "_3A",
					bigSize, 20, 250, canvas));
			texts.add(new TextItem("B) No!! Go away!!", "_3B",
				bigSize, 20, 300, canvas));
			 
			break;
			
		 //HOW TO DEAL WITH MULTIchoice text: save it as a string.
		 case _3B:
			 answer3A = "Well I am gonna tell you anyways. \n";	
			 
		 case _3A:
			 texts.add(new TextItem(new String[]{answer3A + 
					 "Did you know there is a 99% that a  \n",
					 "major earthquake will strike California\n",
					 "in the next 30 years?!! Doesn't that just \n",
					 "freak you out?!"}, "NULL",
						bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Yes that is quite freaky.", "_4A",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Eh, I am not worried.", "_4B",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("C) Please go away, I need to study.", "_4B",
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("D) <ignore and keep working>.", "_4B",
						bigSize, 20, 400, canvas));
			 
			 break;
			 
		 case _4A:
			 if(lvl == ID._4A){
				 answer4 = "I KNOW right?!! You";
			 }
			 
		 
		 case _4B:
			 if(lvl == ID._4B ){
				 answer4 = "Suit yourself... but you";
				
			 }
			 
			 answer4cont =  "never know when the big one will hit.";
			 texts.add(new TextItem(new String[]{answer4, answer4cont,
					 "Okay, now I will leave you alone for real.",
					 "Bye bye!"}, "NULL",
						bigSize, 20, 20, canvas));
			 
			 texts.add(new TextItem("A) Bye!.", "_5A",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) <ignore and keep working>.", "_5A",
						bigSize, 20, 300, canvas));
			 
			 break;
			 
		 case _5A:
			 texts.add(new TextItem("And so you continue to work...", "NULL",
					bigSize, 20, 20, canvas));
			 texts.add(new TextItem("<Enter> Continue. ", "_6A",
						bigSize, 350, 350, "FADER", 75, canvas));
			 break;
			 
		 case _6A:
			 texts.add(new TextItem("And work...", "NULL",
					bigSize, 20, 20, canvas));
			 texts.add(new TextItem("<Enter> Continue. ", "_7A",
						bigSize, 350, 350, "FADER", 75, canvas));
			 break;
			 
		 case _7A:
			 texts.add(new TextItem("CRASH!", "NULL",
						300, 20, 50, "SHAKER", 75,canvas));
			 texts.add(new TextItem("<Enter> Continue. ", "_8A",
							bigSize, 350, 400, "FADER", 75, canvas));
			 break;
			 
		 case _8A:
			 texts.add(new TextItem(new String[]{"What the hell was that?!\n",
					 "Suddenly the floor JOLTS and starts \n",
					 "to shake violently. The stuff on your desk \n",
					 "and book shelves clatter and fall.\n",
					 "WHAT'S GOING ON?!!" }, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("A)OH MY GOD AN EARTHQUAKE!", "_9A",
						bigSize, 20, 300, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("B)Probably a truck going by or something...", "_9B",
						bigSize, 20, 350, "SHAKER", SHAKE_SPEED, canvas));
			 break;
			
		 case tests:
			 texts.add(new TextItem("You have chosen choice A!!", "NULL", 
						bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Choice 1.", "_3A",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Choice 2.", "_3B",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("C) Choice 3.", "_3C",
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("D) Choice 4.", "_3D",
						bigSize, 20, 400, canvas));
		 
		 
			
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