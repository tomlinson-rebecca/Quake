import java.util.ArrayList;

import objectdraw.*;
//TODO integrate different 
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
	 public static final String GOTO_KITCHEN = "gotoKitchen";
	 public static final String GOTO_ROOM = "gotoRoom";
	 public static final String EX_HALL = "exHall";
	 public static final String EX_KITCHEN = "exKitchen";
	 public static final String EX_ROOM = "exRoom";
	 public static final String KITCHEN_SCENARIOS = "kitchenScenarios";
	 public static final String ROOM_SCENARIOS = "roomScenarios";


	 
	 DrawingCanvas canvas;
	 int bigSize = 32; //for story details
	 int lvl = 1; 
	 String level = "start"; //the level of the game. Starts at start.
	 boolean choseWindow = false;
	 boolean choseCenter = false;
	 boolean called = false;
	 boolean inKitchen = false;
	 boolean inRoom = false;
	 boolean sink = false;
	 boolean bottle = false;
	 
	 boolean tank = false;
	 boolean toilet = false;
	 boolean fridge = false;
	 
	 String answer3A = "";
	 String answer4 = "";
	 String answer4cont = "";
	 String answer9 = "";
	 String cover = "cover";
	 String stoveFood = "food";
	 
	 
	 
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
		 _3B, _4A, _4B, _4C, _5A, _6A, _7A, _8A, _9A, _9B, _10A, _10B, _10C, _10D, _10E,
		 _11A, _11B, _11C, _11D, _12A, _13A, _14A, _15A, _16A, _17A, _17B, _19A, _18A, _18B,
		 _20A, _21A, _22A, exHall, exKitchen, exRoom, gotoKitchen, gotoRoom, kitchenScenarios, roomScenarios, 
		 eat, drink, noodles, cereal, melon, cheese, _24A, stoveFood, _24B, death1, _25A, _25B,
		 waterHunt, tank, toilet, fridge, _26A, _26B, _26C, _26D, _26E, window1, window1A, window1B, death2, computer;
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
			 texts.add(new TextItem("C)AAAAAAAAAAAAAAAAAAAAAAA", "_9A",
						bigSize, 20, 400, "SHAKER", SHAKE_SPEED, canvas));
			 
			 break;
		
		 case _9B:
			 answer9 = "No stupid, ";
		 case _9A:
			 texts.add(new TextItem(new String[]{answer9+"IT'S AN EARTHQUAKE!\n",
					 "And a big one at that! \n",
					 "The shaking is so violent, you should take cover!",
					 "What will you do?"}, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("A)Take cover under the desk", "_10A",
						bigSize, 20, 300, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("B)Hide near the window", "_10B",
						bigSize, 20, 350, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("C)Crawl under the bed", "_10C",
						bigSize, 20, 400, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("D)Crouch in the center of the room", "_10D",
						bigSize, 20, 450, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("E)Stop drop and roll!", "_10E",
						bigSize, 20, 500, "SHAKER", SHAKE_SPEED, canvas));
			 
			break;
			
		 case _10A:
			 texts.add(new TextItem(new String[]{"You quickly take cover under your desk.\n",
					 "It's a tight squeeze, but you know your\n",
					 "desk is sturdy enough to keep you safe from \n",
					 "any falling objects. What do you do now?"}, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("A)Cover your head and neck with your hands.", "_11A",
						bigSize, 20, 300, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("B)Scream for help and pound the wall.", "_11B",
						bigSize, 20, 350, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("C)Cover your eyes with your hands.", "_11C",
						bigSize, 20, 400, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("D)Keep doing your homework.", "_11D",
						bigSize, 20, 450, "SHAKER", SHAKE_SPEED, canvas));
			 cover = "desk";
			 break;
			 
		 case _10B:
			 
			 choseWindow = true;
			 texts.add(new TextItem(new String[]{"You rush to the windows and lean against them.\n",
					 "Careful! The glass could shatter at any time!\n",
					 "You see a large crack begin to form in the glass. \n",
					 "ALWAYS stay away from windows during an earthquake!",
					 "What do you do now?"}, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("A)Take cover under the desk", "_10A",
						bigSize, 20, 300, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("B)Crawl under the bed", "_10C",
						bigSize, 20, 350, "SHAKER", SHAKE_SPEED, canvas));
			 //eliminates this wrong choice if they have already made it
			 if(choseCenter == false){
				 texts.add(new TextItem("C)Crouch in the center of the room", "_10D",
						bigSize, 20, 400, "SHAKER", SHAKE_SPEED, canvas)); 
			 }
			
			 
			 break;
			 
		 case _10C:
			 texts.add(new TextItem(new String[]{"You hide under the bed.\n",
					 "Luckily you just cleaned up under here, \n",
					 "and there is enough space for you to crouch. \n",
					 "It is a little dusty though.",
					 "What do you do now?"}, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("A)Cover your head and neck with your hands.", "_11A",
						bigSize, 20, 300, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("B)Scream for help and pound the wall.", "_11B",
						bigSize, 20, 350, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("C)Cover your eyes with your hands.", "_11C",
						bigSize, 20, 400, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("D)Keep doing your homework.", "_11D",
						bigSize, 20, 450, "SHAKER", SHAKE_SPEED, canvas));
			 cover = "bed";
			 break;
			 
		 case _10D:
			 choseCenter = true;
			 texts.add(new TextItem(new String[]{"You curl up in a ball in the center of your room.\n",
					 "As the shaking persists, the objects on your \n",
					 "bookshelf start to fall on the ground. A book falls\n",
					 "right next to your head! That was a close call. ",
					 "Being out in the open doesn't seem safe, because",
					 "you could get hit by a falling object.",
					 "You should move."}, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("A)Take cover under the desk", "_10A",
						bigSize, 20, 350, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("B)Crawl under the bed", "_10C",
						bigSize, 20, 400, "SHAKER", SHAKE_SPEED, canvas));
			 //eliminates this wrong choice if they have already made it
			 if(choseWindow == false){
				 texts.add(new TextItem("C)Hide near the window", "_10B",
							bigSize, 20, 450, "SHAKER", SHAKE_SPEED, canvas));
			 }
			 break;
			 
		 case _10E:
			 texts.add(new TextItem("Ummmm that's for fires. Try again, sweetie :)", "NULL", 
						bigSize, 20, 20, "SHAKER",SHAKE_SPEED, canvas));
			 texts.add(new TextItem("A)Take cover under the desk", "_10A",
						bigSize, 20, 300, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("B)Hide near the window", "_10B",
						bigSize, 20, 350, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("C)Crawl under the bed", "_10C",
						bigSize, 20, 400, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("D)Crouch in the center of the room", "_10D",
						bigSize, 20, 450, "SHAKER", SHAKE_SPEED, canvas));
			 break;
		 
		 case _11A:
			 texts.add(new TextItem(new String[]{"You protect your head and neck with your hands.\n",
					 "A wise choice, clearly you paid attention \n",
					 "during those silly earthquake drills in school.",
					 "Protecting your brain from falling objects is the",
					 "most important thing you can do!"
					}, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("<Enter> Continue. ", "_12A",
						bigSize, 350, 400, "FADER", 75, canvas));
			 
			 break;
			 
		 case _11B:
			 texts.add(new TextItem(new String[]{"You scream with fear and desperation, hoping \n",
					 "someone will hear you. Your cries fall on ears \n",
					 "deafened by the monsterous earthquake that has",
					 "just wrought itself upon the 23 million people ",
					 "of Southern California.",
					 "Hang in there, you're not alone."
					}, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("<Enter> Continue. ", "_12A",
						bigSize, 350, 400, "FADER", 75, canvas));
			 break;
			 
		 case _11C:
			 texts.add(new TextItem(new String[]{"You cover your eyes and try your hardest\n",
					 "to find your happy place. Out of sight, out of mind, right...?",
					 "Not quite, it's hard to ignore the violent shaking.",
					 "You've never experienced\n a quake this strong before. ",
					 "Hang in there."
					}, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("<Enter> Continue. ", "_12A",
						bigSize, 350, 400, "FADER", 75, canvas));
			 
			 break;
			 
		 case _11D:
			 texts.add(new TextItem(new String[]{"Like the true UCSD student you are, \n",
					 "you keep working amidst the quake. ",
					 "Not like a giant earthquake is gonna stop you ",
					 "from getting full credit, right?! ",
					 "Though your handwriting is a little shakey."
					}, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("<Enter> Continue. ", "_12A",
						bigSize, 350, 400, "FADER", 75, canvas));
			 
			 break;
			 
		 case _12A:
			 texts.add(new TextItem(new String[]{"The shaking continues for what seems like \n",
					 "an eternity. Though in reality, it was only a couple",
					 "of minutes, it's the longest wait in your life. ",
					 "From under your "+cover+" you hear muffled sounds",
					 "of rumbling and crashing from downstairs, in addition",
					 "to the incessant roar of the quake."}
					, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED, canvas));
			 texts.add(new TextItem("<Enter> Continue. ", "_13A",
						bigSize, 350, 400, "FADER", 75, canvas));
			 break;
		 case _13A:
			 texts.add(new TextItem(new String[]{"And then..."}
					, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED + 50, canvas));
			 texts.add(new TextItem("<Enter> Continue. ", "_14A",
						bigSize, 350, 400, "FADER", 75, canvas));
			 break;
			 
		 case _14A:
			 texts.add(new TextItem(new String[]{"Just like that..."}
					, "NULL",
					bigSize, 20, 20, "SHAKER", SHAKE_SPEED + 75, canvas));
			 texts.add(new TextItem("<Enter> Continue. ", "_15A",
						bigSize, 350, 400, "FADER", 75, canvas));
			 break;
		 case _15A:
			 texts.add(new TextItem(new String[]{"The shaking stops."}
					, "NULL",
					bigSize, 20, 20, canvas));
			 texts.add(new TextItem("<Enter> Continue. ", "_16A",
						bigSize, 350, 400, "FADER", 75, canvas));
			 break;
			 
		 case _16A:
			 texts.add(new TextItem(new String[]{"...what do you do now?"}
					, "NULL",
					bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Venture out from under the "+cover+".", "_17A",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Stay here for a little bit...", "_17B",
						bigSize, 20, 300, canvas));
			
			 break;
			 
		 case _17A:
			 texts.add(new TextItem(new String[]{"You crawl out from under your "+cover,
					 "and observe the cluttered mess that was once ",
					 "your room. Dust and broken glass cover the floor," ,
					 "and your possessions are strewn about. "}
				, "NULL",
				bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Look out the window.", "_18A",
					bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Leave your room.", "_18B",
					bigSize, 20, 300, canvas));
			 break;
			 
		 case _17B:
			 texts.add(new TextItem(new String[]{"That was terrifying, and you need a breather.",
					 "You compose yourself for a couple minutes,",
					 "trying to comprehend what just happened."}
				, "NULL",
				bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Venture out from under the "+cover+".", "_17A",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Stay here for a little while longer.", "_19A",
						bigSize, 20, 300, canvas));
			 break;
		 case _19A:
			 texts.add(new TextItem(new String[]{"You stay hidden even longer. Come on,",
					 "chicken. Don't you wanna see all the destruction?",
					 }
				, "NULL",
				bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Venture out from under the "+cover+".", "_17A",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Stay here for a little while longer.", "_19A",
						bigSize, 20, 300, canvas));
		
			 break;
			 
		 case _18A:
			 texts.add(new TextItem(new String[]{"You make your way over to the window, careful",
					 "not to cut your feet on the broken glass and debris.",
					 "The window has been broken, and you can see that",
					 "your apartment wasn't the only one to take damage.",
					 "Many of the Sixth apartments have broken windows and",
					 "collapsed walls. Oh the humanity. "
					 }
				, "NULL",
				bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Leave your room.", "_18B",
						bigSize, 20, 350, canvas));
			 
			 break;
			 
		 case _18B:
			 texts.add(new TextItem(new String[]{"You make your way over to the door.",
					 "It creaks open, pushing aside quite a bit of rubble.",
					 "Your breath is taken as you observe the damage.",
					 "It seems like part of the ceiling collapsed into ",
					 "the living room and blocked the only exit!",
					 "It seems like you are trapped in your apartment."
					 }
				, "NULL",
				bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Call for help.", "_20A",		//only use once
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("B) Examine your surroundings.", EX_HALL,	//should be available for all rooms
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("C) Go into the kitchen", GOTO_KITCHEN,	//triggers food/water events
						bigSize, 20, 400, canvas));
			 texts.add(new TextItem("D) Go into your room", GOTO_ROOM,		//triggers do homework/use computer event
						bigSize, 20, 450, canvas));
			 
			 break;
			 
		
			 
		 case _20A:
			 called = true;
			 texts.add(new TextItem(new String[]{"You scream as loud as you can, ",
					 "hoping somebody will save you.",
					 "You remember your roommates both ",
					 "left for their final exam. ",
					 "So you are home alone. Stuck. Home stuck.",
					 }
				, "NULL",
				bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) I meant like 'call' as in 'phone call', numbnuts.", "_21A",		//only use once
						bigSize, 20, 300, canvas));
			 
			 break;
			 
		 case _21A:
			 texts.add(new TextItem(new String[]{"O-oh right, of course, I knew that. ",
					 "You dial 911, but are met with the dial tone.",
					 "The line must be so busy with everyone else",
					 "calling for help. ",
					 "Yikes...",
					 }
				, "NULL",
				bigSize, 20, 20, canvas));
			 
			 String id = "_22A";
			 
			 if(inKitchen){
				id = KITCHEN_SCENARIOS;
				 
			 } else if (inRoom){
				 id = ROOM_SCENARIOS;
			 }
			 texts.add(new TextItem("<Enter> Continue. ", id,
						bigSize, 350, 400, "FADER", 75, canvas));
			 
			 
			 break;
			 
		//maybe a central case? Happens after phone call.
		 case _22A:
			 texts.add(new TextItem(new String[]{"What will you do now?"}
				, "NULL",
				bigSize, 20, 20, canvas));
		
			
				 texts.add(new TextItem("A) Go into the kitchen", GOTO_KITCHEN,	//triggers food/water events
							bigSize, 20, 300, canvas));
				 texts.add(new TextItem("B) Go into your room", GOTO_ROOM,	//triggers food/water events
							bigSize, 20, 350, canvas));	 
				 texts.add(new TextItem("C) Examine your surroundings", EX_HALL,		//only use once
							bigSize, 20, 400, canvas));
				 
			 
			 if(!called){
				 texts.add(new TextItem("D) Call for help.", "_20A",		//only use once
							bigSize, 20, 450, canvas));
			 }
			 
			
			 
			 
			 break;
			 
			 //examining hall case
		 case exHall:
			 texts.add(new TextItem(new String[]{"You look around at what used to be your",
					 "living room. Part of the ceiling has collapsed,",
					 "leaving the room strewn with dust and debris.",
					 "Thank god you didn't decide to study on the couch...",
					 "The front door is blocked, leaving you trapped."
			 }
				, "NULL",
				bigSize, 20, 20, canvas));
			 
			 texts.add(new TextItem("<Enter> Continue. ", "_22A",
						bigSize, 350, 400, "FADER", 75, canvas));
			
			 break;
			 
		 case exRoom:
			 texts.add(new TextItem(new String[]{"You room is a mess.",
					 "Most of your possessions lay strewn across",
					 "the floor. Your shelf has collapsed, leaving",
					 "books and knicknacks all over the place. ",
					 "The windows have shattered, leaving broken glass",
					 "on the floor. ",
					 
			 }
				, "NULL",
				bigSize, 20, 20, canvas)); 
			 
			 texts.add(new TextItem("<Enter> Continue. ", ROOM_SCENARIOS,
						bigSize, 350, 400, "FADER", 75, canvas));
			 
			 break;
		 case exKitchen:
			 texts.add(new TextItem(new String[]{"The earthquake has knocked many",
					 "dishes onto the floor, leaving shards of",
					 "ceramic and glass on the floor. ",
					 "The fridge and stove are still upright, ",
					 "though you can't hear the fridge running.",
					 "Maybe the power is out.",
					 
			 }
				, "NULL",
				bigSize, 20, 20, canvas)); 
			 
			 texts.add(new TextItem("<Enter> Continue. ", KITCHEN_SCENARIOS,
						bigSize, 350, 400, "FADER", 75, canvas));
			
			 break;
			 
		 case gotoKitchen:
			 
			 inKitchen = true;
			 inRoom = false;
			 texts.add(new TextItem(new String[]{"You decide to head into the kitchen.",
					 "Many of your roommate's dishes have falled off",
					 "the counter and shattered on the ground. Well, ",
					 "that's what they get for not putting away ",
					 "their dishes. Aside from the mess,",
					 "your fridge and stove seem intact.",
					 
			 }
				, "NULL",
				bigSize, 20, 20, canvas));
			 
			 texts.add(new TextItem("<Enter> Continue. ", KITCHEN_SCENARIOS,
						bigSize, 350, 400, "FADER", 75, canvas));
			
			 break;
			 
			 //stuff you can do in kitchen
		 case kitchenScenarios:
			 texts.add(new TextItem(new String[]{"What will you do now?"}
				, "NULL",
				bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Make myself a snack.", "eat",
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("B) Get myself a drink.", "drink",
						bigSize, 20, 400, canvas));
			 texts.add(new TextItem("C) Examine surroundings", EX_KITCHEN,
						bigSize, 20, 450, canvas));
			 texts.add(new TextItem("D) Go into your room", GOTO_ROOM,	//triggers food/water events
						bigSize, 20, 500, canvas));	 
			 
			 if(!called){
				 texts.add(new TextItem("E) Call for help.", "_20A",
							bigSize, 20, 550, canvas));
			 }
			 
			 break;
			 
		 case gotoRoom:
			 inKitchen = false;
			 inRoom = true;
			 texts.add(new TextItem(new String[]{"You head back into your room.",
					 "It's a mess. I mean, more of a mess than usual.",
					 "Whelp, at least the ceiling is intact. ",
					 "The living room suffered much worse of a fate.",
					 "I hope HDH doesn't charge you for this mess..."}
				, "NULL",
				bigSize, 20, 20, canvas));
			 
			 texts.add(new TextItem("<Enter> Continue. ",ROOM_SCENARIOS,
						bigSize, 350, 400, "FADER", 75, canvas));
			 
			 break;
		 case roomScenarios:
			 texts.add(new TextItem(new String[]{"What will you do now?"}
				, "NULL",
				bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Look out the window", "window1",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Do your homework...", "homework",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("C) Use the computer", "computer",
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("D) Examine surroundings", EX_ROOM,
						bigSize, 20, 400, canvas));
			 texts.add(new TextItem("E) Go to the kitchen.", GOTO_KITCHEN,	//triggers food/water events
						bigSize, 20, 450, canvas));	 
			 
			 if(!called){
				 texts.add(new TextItem("F) Call for help.", "_20A",
							bigSize, 20, 500, canvas));
			 }
			 
			 break;
			 
		 case computer:
			 texts.add(new TextItem(new String[]{"You were so proud of your awesome",
					 "gaming desktop. Well now the power is out,",
					 "and your computer doesn't work.",
					 "Shiiiiiiieeeeet."}
				, "NULL",
				bigSize, 20, 20, canvas));
			 texts.add(new TextItem("<Enter> Continue. ",ROOM_SCENARIOS,
						bigSize, 350, 400, "FADER", 75, canvas));
			 break;
			 
			 
			 //look out window, can climb out but die
		 case window1:
			 texts.add(new TextItem(new String[]{"You make your way over to the window, careful",
					 "not to cut your feet on the broken glass and debris.",
					 "The window has been broken, and you can see that",
					 "your apartment wasn't the only one to take damage.",
					 "Many of the Sixth apartments have broken windows and",
					 "collapsed walls. Oh the humanity. "
					 }
				, "NULL",
				bigSize, 20, 20, canvas));
			 
			 texts.add(new TextItem("A) Wow that sucks. ", ROOM_SCENARIOS,
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("B) Try to escape through the window. ", "window1A",
						bigSize, 20, 400, canvas));
			 
			 break;
			 
		 case window1A:
			 texts.add(new TextItem(new String[]{"The glass has been broken, so maybe",
					 "it would be possible to climb out the window.",
					 "You seat yourself on the windowsill and look down.",
					 "WOW that's a big drop...",
					 "It's three stories high, ",
					 "and the landing doesn't look soft.",
					 }
				, "NULL",
				bigSize, 20, 20, canvas));
			 
			 texts.add(new TextItem("A) Too scary for me. I'll try something else.", ROOM_SCENARIOS,
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("B) Jump out the window. ", "window1B",
						bigSize, 20, 400, canvas));
			 
			 break;
			 
		 case window1B:
			 texts.add(new TextItem(new String[]{"You sure you want to jump out?",
					 "That's quite a drop...",
					 "I don't know if you'll make it.",
					 }
				, "NULL",
				bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Yeah okay I don't want to die heh...", ROOM_SCENARIOS,
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("B) I don't care, let me out of here! ", "death2",
						bigSize, 20, 400, canvas));
			 break;
			 
			 //TODO connect w/ death
		 case death2:
			 texts.add(new TextItem(new String[]{"You jump out the window.",
					 "And fall to your doom.",
					 "CRUNCH!",
					 }
				, "NULL",
				bigSize, 20, 20, canvas));
			 break;
			 
		 case eat:
			 texts.add(new TextItem(new String[]{"All that earthquaking sure made",
					 "you hungry! If you're trapped in here,",
					 "might as well eat something. ",
					 "What do you want to eat?",
			 }
				, "NULL",
				bigSize, 20, 20, canvas));
			 
			 texts.add(new TextItem("A) Ramen noodles!", "noodles",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Bowl of cereal.", "cereal",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("C) WATERMELON!", "melon",
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("D) Grilled cheese sandwich.", "cheese",
						bigSize, 20, 400, canvas));
			 break;
			 
		 case noodles:
			 texts.add(new TextItem(new String[]{"You fill a pot with water ",
					 "and prepare to boil it on the stove.",
					 "WAIT!!!",
					 "Don't turn on the stove, the gas line might",
					 "have been broken, so turning it on could cause",
					 "a HUGE explosion!!"
			 }, "NULL",
						bigSize, 20, 20, canvas));	 
			 
			 texts.add(new TextItem("A) Okay fine. I can eat something else.", "eat",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("B) Do it anyway.", "_24A",
						bigSize, 20, 350, canvas));
			 
			 break;
			 
			 //noodle death
		 case _24A:
			 texts.add(new TextItem(new String[]{"You sure...?",
					 "Doesn't seem safe...",
			 }, "NULL",
						bigSize, 20, 20, canvas));	
			 
			 texts.add(new TextItem("A) Yeah you're right, I don't want to die.", "eat",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("B) I WANT MY NOODLES!!", "death1",
						bigSize, 20, 350, canvas));
			 break;
			 
			 //grilled cheese death
		 case _24B:
			 texts.add(new TextItem(new String[]{"You sure...?",
					 "Doesn't seem safe...",
			 }, "NULL",
						bigSize, 20, 20, canvas));	
			 
			 texts.add(new TextItem("A) Yeah you're right, I don't want to die.", "eat",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("B) I WANT MY GRILLED CHEEEEEEESE!!", "death1",
						bigSize, 20, 350, canvas));
			 break;
			 
		 case cereal:
			 texts.add(new TextItem(new String[]{"You pick your favorite cereal",
					 "box off the floor and open the fridge",
					 "to get milk. Yep, the power is definitely out.",
					 "That was a bad quake. Hopefully your food ",
					 "doesn't spoil. After eating cereal you feel better.",
					 
			 }, "NULL",
						bigSize, 20, 20, canvas));	
			 
			 texts.add(new TextItem("<Enter> Continue. ", KITCHEN_SCENARIOS,
						bigSize, 350, 400, "FADER", 75, canvas));
			 break;
			 
		 case melon:
			 texts.add(new TextItem(new String[]{"You grab a large watermelon",
					 "from the fridge. Yep, the power is definitely out.",
					 "That was a bad quake. Hopefully your food ",
					 "doesn't spoil. You slice the watermelon and",
					 "eat it. Mmm delicious!"
					 
			 }, "NULL",
						bigSize, 20, 20, canvas));	
			 
			 texts.add(new TextItem("<Enter> Continue. ", KITCHEN_SCENARIOS,
						bigSize, 350, 400, "FADER", 75, canvas));
			 break;
		 case cheese:
			 texts.add(new TextItem(new String[]{"You get bread and a pan",
					 "and place it on the stove",
					 "WAIT!!!",
					 "Don't turn on the stove, the gas line might",
					 "have been broken, so turning it on could cause",
					 "a HUGE explosion!!"
			 }, "NULL",
						bigSize, 20, 20, canvas));	 
			 
			 texts.add(new TextItem("A) Okay fine. I can eat something else.", "eat",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("B) Do it anyway.", "_24B",
						bigSize, 20, 350, canvas));
			 
			 break;
			 
			 
		 case drink:
			 texts.add(new TextItem(new String[]{"Through all the panic, you",
					 "haven't even noticed how hot and sweaty ",
					 "you are. And thirsty. So thirsty...",
					 "Let's get some water!",
			 }, "NULL",
						bigSize, 20, 20, canvas));
			 
			 texts.add(new TextItem("A) Look for a water bottle", "_25A",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Get some gross sink water.", "_25B",
						bigSize, 20, 300, canvas));
			 
			 break;
			 
		 case _25A:
			 bottle = true;
			 texts.add(new TextItem(new String[]{"You search all over your apartment,",
					 "and eventually find a single bottle. ",
					 "It only has a little water left, which you",
					 "quickly gulp down. You need more water!",
			 }, "NULL",
						bigSize, 20, 20, canvas));
			 
			 if(!sink){
				 texts.add(new TextItem("A) Try the sink.", "_25B",
							bigSize, 20, 300, canvas));
			 } else {
				 texts.add(new TextItem("<Enter> Continue. ", "waterHunt",
							bigSize, 350, 400, "FADER", 75, canvas));
			 }
			 break;
			 
		 case _25B:
			 sink = true;
			 texts.add(new TextItem(new String[]{"You try to get some water from the faucet.",
					 "Unfortunately no water comes out. ",
					 "The pipes must have broken in the quake...",
					 "You need water!",
			 }, "NULL",
						bigSize, 20, 20, canvas));
			 if(!bottle){
				 texts.add(new TextItem("A) Look for a water bottle.", "_25A",
							bigSize, 20, 300, canvas));
			 } else {
				 texts.add(new TextItem("<Enter> Continue. ", "waterHunt",
							bigSize, 350, 400, "FADER", 75, canvas));
			 }
			 break;
			 
			 
		 case waterHunt:
			 texts.add(new TextItem(new String[]{"Hmmm... where can you find drinkable water",
					 "in your apartment? You wrack your brain ",
					 "and come up with several possible water sources.",
			 }, "NULL",
						bigSize, 20, 20, canvas));
		

			 texts.add(new TextItem("A)The toilet has water in it!!", "toilet",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B)There's gotta be something drinkable in the fridge.", "fridge",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("C)My roommate's fish tank...", "tank",
						bigSize, 20, 350, canvas));
			 break;
		
			 //can't drink tank. can drink other two.
		 case tank:	 
			 tank = true;
			 texts.add(new TextItem(new String[]{"You creep into your roommate's room,",
					 "which is unlocked as usual. Despite the recent ",
					 "quake, their fish tank is completely intact.",
					 "This fish is the luckiest fish on earth...",
					 "until you came to drink his water."
			 }, "NULL",
						bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Drink fishtank water!", "_26A",
						bigSize, 20, 250, canvas));
			 
			 break;
			 
		 case _26A:
			 texts.add(new TextItem(new String[]{"Whoa hold on hold on!,",
					 "That is not sanitary! You could get",
					 "some wicked parasites or bacteria in your gut,",
					 "and have miserable diarrhea!",
					 "Plus, ummm, rude much? Did you even ask",
					 "the fish for his permisson??",
					 "You should find another water source."
			 }, "NULL",
						bigSize, 20, 20, canvas));
			 texts.add(new TextItem("<Enter> Continue. ", "waterHunt",
						bigSize, 350, 400, "FADER", 75, canvas));
			 break;
			 
		 case toilet:
			 toilet = true;
			 texts.add(new TextItem(new String[]{"You head into the nearest bathroom.",
					 "The quake left this room mostly intact.",
					 "You are greeted by the porceline thrown,",
					 "filled with possibly the last water in",
					 "the apartment. Do you dare drink from it?",	 
					 
			 }, "NULL",
						bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Yes. Bring on the toilet water.", "_26B",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Ewww no way, I'll find water somewhere else.", "waterHunt",
						bigSize, 20, 300, canvas));
			 break;
			 
		 case _26B:
			 texts.add(new TextItem(new String[]{"Well I have some good news for you.",
					 "Did you know the water in the tank of the toilet,",
					 "you know, the big back part, is completely",
					 "sanitary to drink?",
			 }, "NULL",
						bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Oh awesome I don't have to drink poo water", "_26C",
						bigSize, 20, 250, canvas));
			 break;
			 
		 case _26C:
			 texts.add(new TextItem(new String[]{"That's right!",
					 "And so you quench your thirst with the",
					 "totally clean not-poo water. Ahhh, refreshing!",
					 "You return to the kitchen."
			 }, "NULL",
						bigSize, 20, 20, canvas));
			 
			 texts.add(new TextItem("<Enter> Continue. ", KITCHEN_SCENARIOS,
						bigSize, 350, 400, "FADER", 75, canvas));
			 
			 break;
			 
			 
		 case fridge:
			 fridge = true;
			 texts.add(new TextItem(new String[]{"You scout out the fridge for any",
					 "drinkable substances. Unfortunately,",
					 "your roommate's super stoned friends came over",
					 "and drank ALL the soda! There's NOTHING to drink in",
					 "the fridge... but what about the freezer?"
			 }, "NULL",
						bigSize, 20, 20, canvas));
			 
			 texts.add(new TextItem("A) What about the freezer?", "_26D",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Oh yeah the freezer hahahah totally.", "_26D",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("C) What will I drink melted ice cream?", "_26D",
						bigSize, 20, 350, canvas));
			 
			 break;
			 
		 case _26D:
			 texts.add(new TextItem(new String[]{"With the power outage, the freezer can't",
					 "remain cool. What once was ice, must now be...",
			 }, "NULL",
						bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Ohh, I get it now. ","26E",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Wtf I am not a chemistry major explain pls", "_26E",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("C) Ice cream!", "_26E",
						bigSize, 20, 350, canvas));
			 break;
			 
		 case _26E:
			 texts.add(new TextItem(new String[]{"The ice cubes in the freezer have melted",
					 "into trays of sanitary, drinkable water!",
					 "You drink carefully, making sure not to spill.",
					 "Ahhhh, your thirst is quenched!"
			 }, "NULL",
						bigSize, 20, 20, canvas));
			 texts.add(new TextItem("<Enter> Continue. ", KITCHEN_SCENARIOS,
						bigSize, 350, 400, "FADER", 75, canvas));
			 
			 break;
			 
			 
		//this line is only reached if a break has been forgotten
		 case tests:
			 texts.add(new TextItem("You have chosen choice A!! AND FORGOTTEN A BREAKPOINT", "NULL", 
						bigSize, 20, 20, canvas));
			 texts.add(new TextItem("A) Choice 1.", "_3A",
						bigSize, 20, 250, canvas));
			 texts.add(new TextItem("B) Choice 2.", "_3B",
						bigSize, 20, 300, canvas));
			 texts.add(new TextItem("C) Choice 3.", "_3C",
						bigSize, 20, 350, canvas));
			 texts.add(new TextItem("D) Choice 4.", "_3D",
						bigSize, 20, 400, canvas));
		 
		 case death1:
						 //TODO

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