// Card Class
import java.awt.*;


public class Card
{

	//VARIABLE DECLARATION SECTION
	//Here's where you state which variables you are going to use.
   public int xpos;				//the x position
   public int ypos;				//the y position
   public int width;
   public int height;
   public boolean isAlive;			//a boolean to denote if the hero is alive or dead.
   public int dx;					//the speed of the hero in the x direction
   public int dy;					//the speed of the hero in the y direction
   public Rectangle rec;


   public String name;				//holds the name of the card
   public int cardValue;
   public String suit;
   public int suitValue;
   public int cardNumber;
   

	// METHOD DEFINITION SECTION

   public Card(int suitNumber, int cn){ 
      
      cardNumber =cn;
      //set the suitValue and suitName  
      suitValue = suitNumber;
      switch (suitNumber){
         case 0: suit = "Hearts";
            break;
         case 1: suit = "Spades";
            break;         
         case 2: suit = "Diamonds";
            break;      
         case 3: suit = "Clubs";
            break;
      }
                 
      //set the card's name and cardValue
      
      switch ( cardNumber){
         case 1: cardValue = 11;    //the ace
            name = "Ace";
            break;
         case 11:cardValue = 10;
            name = "Jack";
            break;
         case 12:cardValue = 10;
            name = "Queen";        
            break;
         case 13:cardValue = 10;
            name = "King";
            break;            
         default:cardValue = cardNumber;
            name = ""+cardNumber;
            break;
      
      }
      
  
                      
      isAlive = true;
      rec = new Rectangle(xpos,ypos,width,height);
   
   
   } // constructor


   public void printCard()
   {
      System.out.println(name+" of "+suit);
   }
 
} //end of the card class

