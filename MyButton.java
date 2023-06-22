//A simple class to use as a button

import java.awt.*;      //so we can use the Rectangle class
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyButton
{

	//VARIABLE DECLARATION SECTION
   // You'll need to keep track of where on the screen to put the button and how big it is.
   public int height;
   public int width;
   public int xpos;
   public int ypos;
   public String text;
   public Rectangle rec;   //we'll use this Rectangle with the mouse pointer


	// Constructor Definition
	// To create the button we'll need to supply parameters for the position and size. The text is optional.
	public MyButton(int tempX, int tempY, int tempWidth, int tempHeight, String tempText)
	{
   
      //put the values of the parameters into the variables.
		xpos = tempX;
		ypos = tempY;

		height = tempHeight;
		width = tempWidth;

		text = tempText;

      //create a Rectangle
		rec = new Rectangle( xpos, ypos, width, height);

	}
} //MyButton

