// Annie Wernerfelt  


import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Blackjack extends JPanel implements Runnable, MouseMotionListener, MouseListener
{
   public static int xTitle;
   public static int yTitle;
   public static String worldName;
   
   public static Card[] deck;
   public int topOfDeck;
   public static int counter;
   static Card tempCard = new Card(1,1);
   public static Player player1;
   public static Player dealer;
   
   public static Image background;
   public static Image hitPic;
   public static Image stayPic;
   public static Image restartPic;
   public static Image cardPic;
   public static MyButton hitbutton;
   public static MyButton staybutton;
   public static MyButton restartbutton;
   public static boolean hitButtonClicked;
   public static boolean stayButtonClicked;
   public static boolean restartButtonClicked;
   public static boolean playerWon;
   public static boolean dealerWon;
   public static boolean push;
   
   public static Font firstFont;
   
   public int cardWidth = 225, cardHeight = 315;
  
   Thread thread;

   public void main(String[] args) throws IOException {
      JFrame frame = new JFrame("My Swing App");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel panel = new Blackjack();
      panel.setPreferredSize(new Dimension(800, 520));
      frame.getContentPane().add(panel);
      frame.pack();
      frame.setVisible(true);
      frame.addMouseListener((MouseListener) panel);

      //setSize(800,520);
      xTitle=100;
      yTitle=100;
   
      worldName = "BLACKJACK";
      
      deck = new Card[52];
      player1 = new Player();
      dealer = new Player();
      
      tempCard = new Card(1,1);
      counter = 0;

      background = ImageIO.read(new File("background.png"));
      
      dealerWon=false;
      playerWon=false;
      stayButtonClicked=false;
      hitButtonClicked=false;
      restartButtonClicked=false;
      push=false;
      
      firstFont= new Font ("Sathu", 1, 15);

      //button
      hitPic = ImageIO.read(new File("hitbutton.jpg"));
      stayPic = ImageIO.read(new File("staybutton.jpg"));
      restartPic = ImageIO.read(new File("restartbutton.jpg"));
      cardPic = ImageIO.read(new File("cards.jpg"));

      hitbutton = new MyButton( 100, 460, 152, 60, "hit");
      staybutton = new MyButton (350, 460, 152, 60, "stay");
      restartbutton = new MyButton (600, 460, 152, 60, "restart");

      addMouseMotionListener(this);
      addMouseListener(this);
      
      //fill the deck
      for(int suit=0; suit<=3; suit++)
      {
         for(int cardNumber=1; cardNumber<=13;cardNumber++)
         {
            deck[counter] = new Card( suit, cardNumber);
            counter++;
         }         
      }
      
      System.out.println("Creating Deck of Cards");
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("Shuffling Deck");
      System.out.println();
      System.out.println();
      shuffle();
      deal();
      
      thread = new Thread(this);
      thread.start();
   }//init()



   public void shuffle( )
   {
      for(int i=0;i<deck.length;i++)
      {
         tempCard = deck[i];
         int randomNumber = (int)(Math.random()*52);
         deck[i]=deck[randomNumber];
         deck[randomNumber]=tempCard;
      }
       
   }//shuffle()
   
   public void deal()
   {
      for(int i=0; i<2;i++)
      {
         player1.hand[player1.numOfCards] = deck[topOfDeck];
         topOfDeck++;
         player1.total = player1.hand[player1.numOfCards].cardValue + player1.total;
         player1.numOfCards++;
      
      
         dealer.hand[dealer.numOfCards] = deck[topOfDeck];
         topOfDeck++;
         dealer.total = dealer.hand[dealer.numOfCards].cardValue + dealer.total;
         dealer.numOfCards++;
      }
      System.out.println("For the player:");
      player1.printHand();
      System.out.println("For the dealer:");   
      dealer.printHand();
   }//deal()

   public void printDeck()
   {
      for(int i=0;i<deck.length;i++)
      {
         deck[i].printCard();
      }
   
   }//printDeck()
   
   public void hit()
   {
      player1.hand[player1.numOfCards] = deck[topOfDeck];
      topOfDeck++;
      player1.total = player1.hand[player1.numOfCards].cardValue+player1.total;
      player1.numOfCards++;
      System.out.println("For the player:");
      player1.printHand();
      System.out.println("For the dealer:");   
      dealer.printHand();
        
   
      if(dealer.total > 21&&playerWon==false)
      {
         System.out.println("dealer BUSTED");
         dealer.busted=true;
      }
               
      if(player1.total > 21&&dealerWon==false)
      {
         System.out.println("player BUSTED");
         player1.busted=true;
      }
      repaint();
          
   }//hit()
   
   public void stay()
   {
      for(int i=0;i<12;i++)
      {
         if(dealer.total < 17)
         {
            dealer.hand[dealer.numOfCards] = deck[topOfDeck];
            topOfDeck++;
            dealer.total = dealer.hand[dealer.numOfCards].cardValue+dealer.total;
            dealer.numOfCards++;
            System.out.println("For the dealer:");
            dealer.printHand();
         }
      }
      repaint();
   }//stay()
   
 
   public void restart()
   {
      player1.total=0;
      player1.numOfCards=0;
      player1.busted=false;
      dealer.total=0;
      dealer.numOfCards=0;
      dealer.busted=false;
      stayButtonClicked=false;
      playerWon=false;
      dealerWon=false;
      push=false;
      deal();
      revalidate();
      repaint();
   }
   

   public void run() {
   
      while(true)
      {
         repaint();
      
         try {
            thread.sleep(10);
         }
         catch (Exception e){ }
      }//while
   
   }// run()


   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.setColor(Color.WHITE);
      g.setFont(firstFont);
   
      g.drawString("This is "+worldName,xTitle,yTitle);
      g.drawImage(background,0,0,800,600,this);
      g.drawImage(hitPic, hitbutton.xpos, hitbutton.ypos, hitbutton.width, hitbutton.height, this);
      g.drawImage(stayPic, staybutton.xpos, staybutton.ypos, staybutton.width, staybutton.height, this);
      g.drawImage(restartPic, restartbutton.xpos, restartbutton.ypos, restartbutton.width, restartbutton.height, this);

      g.drawString("The dealer's total is: " + dealer.total, 605, 175);
      g.drawString("The player's total is: " + player1.total, 605, 145); 
      
      
      if(push==true)
      {
         g.drawString("PUSH", 350, 180);
      }
      
      if(dealer.total==21&&push==false)
      {
         dealerWon=true;
      }
         
      if(player1.total==21&&push==false)
      {
         playerWon=true;
      }
      
      if(player1.busted==true&&dealerWon==false)
      {
         g.drawString("PLAYER BUSTED",350,180);
      }
         
        
      if(dealer.busted==true&&playerWon==false)
      {
         g.drawString("DEALER BUSTED",350,180);
      }
     
    
      if(stayButtonClicked==true)
      {
         
         
         if(playerWon==true)
         {
            g.drawString("THE PLAYER WON", 350, 180);
         }
         
      
         if(dealerWon==true)
         {
            g.drawString("THE DEALER WON", 350, 170);
         }
         
      
         if(dealer.total>player1.total&&dealer.total>17&&dealer.total<22)
         {
            dealerWon=true;
            g.drawString("The player's total is: " + player1.total, 605, 145);
         }
         
        
         if(dealer.total<player1.total&&dealer.total>=17&&dealer.total<22)
         {
            playerWon=true;
            g.drawString("The player's total is: " + player1.total, 605, 145);
         }
         
         
         if(dealer.total<player1.total&&dealer.total<17)
         {
            dealer.hand[dealer.numOfCards] = deck[topOfDeck];
            topOfDeck++;
            dealer.total = dealer.hand[dealer.numOfCards].cardValue + dealer.total;
            dealer.numOfCards++;
         }
         
         
         if(player1.total==dealer.total)
         {
            push=true;
         }
         
         
         if(dealer.total<17)
         {
            for(int i=0; i<12;i++)
            {
               dealer.hand[dealer.numOfCards] = deck[topOfDeck];
               topOfDeck++;
               dealer.total = dealer.hand[dealer.numOfCards].cardValue + dealer.total;
               dealer.numOfCards++;
            }
         }
         
         
         if(dealer.total>21&&playerWon==false)
         {
            dealer.busted=true;
         }
      }
     
      
         
      if(hitButtonClicked=true)
      {
         g.drawString("The player's total is: " + player1.total, 605, 145); 
         
         if(player1.total>21)
         {
            g.drawString("PLAYER BUSTED", 350, 180);
         }
         
         if(dealer.total>21)
         {
            g.drawString("DEALER BUSTED", 350, 180);
         }
      }
      
      
      for(int x = 0; x<player1.numOfCards;x++)
      {
      
         int suitValue = player1.hand[x].suitValue;
         int cardNumber = player1.hand[x].cardNumber-1;
         g.drawImage(cardPic, 100+(x*70), 200, 280+(x*70), 387 ,  cardWidth*cardNumber ,cardHeight*suitValue, cardWidth*(cardNumber+1),cardHeight*(suitValue+1), this);
            
      }
   
   
   
   
   }// paint()

   public void mouseMoved(MouseEvent e) {
   
   }
   public void mouseDragged(MouseEvent e) {
   }
//*****MouseListener Methods*****************************************************
   public void mousePressed(MouseEvent e) {
   }
   public void mouseReleased(MouseEvent e) {
   }
   public void mouseEntered(MouseEvent e) {
   }
   public void mouseExited(MouseEvent e) {
   }
   public void mouseClicked(MouseEvent e) {
   
      int mouseX = e.getX();

      int mouseY = e.getY();
   
      if(hitbutton.rec.contains(mouseX, mouseY-30))
      {
         hitButtonClicked=true;
         System.out.println("HIT CLICKED");
         hit();
      
      }
      if(staybutton.rec.contains(mouseX, mouseY-30))
      {
         stayButtonClicked=true;
         System.out.println("STAY CLICKED");
         stay();
      }
   
      
      if(restartbutton.rec.contains(mouseX, mouseY-30))
      {
         restartButtonClicked=true;
         System.out.println("RESTART CLICKED");
         restart();
      }
      
   }


}//Applet