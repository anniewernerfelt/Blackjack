//Annie Wernerfelt
import java.awt.*;


public class Player
{
   public int xpos;				//the x position
   public int ypos;				//the y position
   public int width;
   public int height;
   public boolean isAlive;			//a boolean to denote if the hero is alive or dead.
   public int dx;					//the speed of the hero in the x direction
   public int dy;					//the speed of the hero in the y direction
   public Rectangle rec;

   public Card[] hand;
   public int numOfCards;
   public int total;
   public boolean busted;
   public String name;
   public Image cardPic;
   public int cardWidth = 225, cardHeight = 315; 
   
     
   public Player()
   {
      hand = new Card[12];
      numOfCards = 0;
      total = 0;
      busted = false;
      rec = new Rectangle(xpos,ypos,width,height);
   }
   
   public void printHand()
   {
      for(int i=0;i<numOfCards;i++)
      {
         System.out.println(hand[i].name + " of " + hand[i].suit);
      }
      
      System.out.println("The total is "+total); 
   }
   
}

